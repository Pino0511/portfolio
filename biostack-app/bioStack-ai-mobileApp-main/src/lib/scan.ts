import type { FacialReport } from '../types';

export interface ImageMetrics {
  width: number;
  height: number;
  aspectRatio: number;
  brightness: number;
  contrast: number;
  leftBrightness: number;
  rightBrightness: number;
  symmetryProxy: number;
}

function clamp(n: number, min: number, max: number) {
  return Math.max(min, Math.min(max, Math.round(n)));
}

function loadImage(dataUrl: string): Promise<HTMLImageElement> {
  return new Promise((resolve, reject) => {
    const img = new Image();
    img.onload = () => resolve(img);
    img.onerror = () => reject(new Error('Impossibile leggere l’immagine'));
    img.src = dataUrl;
  });
}

/** Analizza luminosità, contrasto e bilanciamento L/R via Canvas. */
export async function analyzeImage(dataUrl: string): Promise<ImageMetrics> {
  const img = await loadImage(dataUrl);
  const maxSide = 240;
  const scale = Math.min(1, maxSide / Math.max(img.width, img.height));
  const w = Math.max(1, Math.floor(img.width * scale));
  const h = Math.max(1, Math.floor(img.height * scale));

  const canvas = document.createElement('canvas');
  canvas.width = w;
  canvas.height = h;
  const ctx = canvas.getContext('2d', { willReadFrequently: true });
  if (!ctx) throw new Error('Canvas non disponibile');

  ctx.drawImage(img, 0, 0, w, h);
  const { data } = ctx.getImageData(0, 0, w, h);

  let sum = 0;
  let sumSq = 0;
  let leftSum = 0;
  let rightSum = 0;
  let leftCount = 0;
  let rightCount = 0;
  const mid = w / 2;
  const pixelCount = w * h;

  for (let i = 0; i < data.length; i += 4) {
    const r = data[i];
    const g = data[i + 1];
    const b = data[i + 2];
    const lum = 0.2126 * r + 0.7152 * g + 0.0722 * b;
    sum += lum;
    sumSq += lum * lum;
    const px = (i / 4) % w;
    if (px < mid) {
      leftSum += lum;
      leftCount++;
    } else {
      rightSum += lum;
      rightCount++;
    }
  }

  const brightness = sum / pixelCount;
  const variance = sumSq / pixelCount - brightness * brightness;
  const contrast = Math.sqrt(Math.max(0, variance));
  const leftBrightness = leftCount ? leftSum / leftCount : brightness;
  const rightBrightness = rightCount ? rightSum / rightCount : brightness;
  const asymmetry = Math.abs(leftBrightness - rightBrightness);
  const symmetryProxy = clamp(100 - asymmetry * 1.8, 55, 98);

  return {
    width: img.width,
    height: img.height,
    aspectRatio: img.width / img.height,
    brightness,
    contrast,
    leftBrightness,
    rightBrightness,
    symmetryProxy,
  };
}

function faceShapeFromAspect(ratio: number): FacialReport['faceShape'] {
  if (ratio > 0.95 && ratio < 1.05) return 'round';
  if (ratio >= 1.05 && ratio < 1.2) return 'oval';
  if (ratio >= 1.2) return 'oblong';
  if (ratio < 0.85) return 'square';
  if (ratio < 0.95) return 'heart';
  return 'diamond';
}

function skinFromMetrics(brightness: number, contrast: number): {
  quality: FacialReport['skinQuality'];
  hydration: number;
} {
  const hydration = clamp(40 + (brightness / 255) * 45 + (contrast / 80) * 15, 35, 96);
  let quality: FacialReport['skinQuality'] = 'fair';
  if (hydration >= 82 && contrast > 28) quality = 'excellent';
  else if (hydration >= 70) quality = 'good';
  else if (hydration < 52) quality = 'needs_attention';
  return { quality, hydration };
}

/**
 * Report unico derivato dalle metriche reali di fronte + profilo.
 * Non è diagnostica medica: stima educativa basata su pixel.
 */
export async function generateFacialReport(
  frontDataUrl: string,
  profileDataUrl: string,
): Promise<FacialReport> {
  const [front, profile] = await Promise.all([
    analyzeImage(frontDataUrl),
    analyzeImage(profileDataUrl),
  ]);

  const faceShape = faceShapeFromAspect(front.aspectRatio);
  const symmetry = clamp((front.symmetryProxy * 0.7 + profile.symmetryProxy * 0.3), 60, 97);
  const avgBrightness = (front.brightness + profile.brightness) / 2;
  const avgContrast = (front.contrast + profile.contrast) / 2;
  const { quality, hydration } = skinFromMetrics(avgBrightness, avgContrast);

  // Angolo mandibola proxy: profilo più “lungo” / contrasto laterale
  const jawAngle = clamp(105 + profile.aspectRatio * 18 + (profile.contrast / 10), 110, 145);

  const glowUpScore = clamp(
    symmetry * 0.35 + hydration * 0.35 + (avgContrast / 60) * 20 + 15,
    55,
    96,
  );

  const hairRecs: Record<string, { style: string; reason: string }> = {
    oval: { style: 'Textured Crop o Quiff Laterale', reason: 'Forma versatile: il textured crop esalta gli zigomi.' },
    square: { style: 'Taper Fade con volume sopra', reason: 'Volume alto bilancia la mascella definita.' },
    diamond: { style: 'Side Swept Fringe o Pompadour', reason: 'Volume laterale ammorbidisce gli zigomi alti.' },
    heart: { style: 'Medium Length Side Part', reason: 'Equilibra la fronte ampia con volume inferiore.' },
    round: { style: 'High Fade con Pompadour', reason: 'Aggiunge definizione verticale al viso rotondo.' },
    oblong: { style: 'Side Part con Texture', reason: 'Larghezza visuale bilancia il viso allungato.' },
  };
  const beardRecs: Record<string, { style: string; reason: string }> = {
    oval: { style: 'Full Beard corta o stubble', reason: 'La forma ovale supporta quasi ogni stile.' },
    square: { style: 'Pizzetto accennato', reason: 'Evita barbe piene per non appesantire.' },
    diamond: { style: 'Full Beard con sideburns corti', reason: 'Volume mascellare arrotonda i contorni.' },
    heart: { style: 'Goatee pieno', reason: 'Volume sul mento bilancia la fronte.' },
    round: { style: 'Van Dyke', reason: 'Definizione verticale senza aggiungere larghezza.' },
    oblong: { style: 'Sideburns medi', reason: 'Espansione orizzontale per bilanciare.' },
  };
  const eyewearRecs: Record<string, { styles: string[]; desc: string }> = {
    oval: { styles: ['Aviator', 'Wayfarer', 'Round'], desc: 'Forma versatile: quasi ogni stile funziona.' },
    square: { styles: ['Round', 'Clubmaster', 'Oval'], desc: 'Montature morbide ammorbidiscono gli angoli.' },
    diamond: { styles: ['Round', 'Cat-Eye', 'Clubmaster'], desc: 'Angoli morbidi alle tempie armonizzano.' },
    heart: { styles: ['Wayfarer', 'Aviator'], desc: 'Base ampia bilancia la fronte.' },
    round: { styles: ['Rectangular', 'Geometric'], desc: 'Montature angolate aggiungono struttura.' },
    oblong: { styles: ['Oversized', 'Round', 'Aviator'], desc: 'Montature ampie aggiungono larghezza.' },
  };

  const hair = hairRecs[faceShape];
  const beard = beardRecs[faceShape];
  const eyewear = eyewearRecs[faceShape];

  const notes: string[] = [
    `Fronte ${front.width}×${front.height}px · luminosità media ${Math.round(front.brightness)}.`,
    `Profilo ${profile.width}×${profile.height}px · contrasto ${Math.round(profile.contrast)}.`,
    `Simmetria L/R stimata da bilanciamento luminanza (non anatomia clinica).`,
  ];
  if (avgBrightness < 70) notes.push('Foto scura: idratazione stimata può risultare più bassa.');
  if (avgBrightness > 200) notes.push('Foto molto chiara: riduci overexposure per analisi più stabile.');
  if (Math.abs(front.leftBrightness - front.rightBrightness) > 25) {
    notes.push('Illuminazione laterale sbilanciata: sposta la luce frontale e ripeti lo scan.');
  }

  return {
    faceShape,
    symmetry,
    jawAngle,
    skinQuality: quality,
    hydration,
    contrastScore: clamp(avgContrast, 0, 100),
    brightness: clamp(avgBrightness, 0, 255),
    imageWidth: front.width,
    imageHeight: front.height,
    hairRecommendation: hair.style,
    hairReason: hair.reason,
    beardRecommendation: beard.style,
    beardReason: beard.reason,
    eyewearRecommendation: eyewear.desc,
    eyewearStyles: eyewear.styles,
    glowUpScore,
    analysisNotes: notes,
  };
}
