import type { TutorialKey } from '../types';

export interface TutorialContent {
  key: TutorialKey;
  title: string;
  what: string;
  benefits: string[];
  steps: string[];
  mistakes: string[];
}

export const TUTORIALS: Record<TutorialKey, TutorialContent> = {
  morning_light: {
    key: 'morning_light',
    title: 'Luce circadiana mattutina',
    what: 'Esposizione a luce naturale (o full-spectrum) entro 30–60 minuti dal risveglio per sincronizzare il ritmo circadiano.',
    benefits: [
      'Aiuta il timing del cortisolo mattutino',
      'Migliora allerta diurna e qualità del sonno notturno',
      'Supporta umore e regolarità circadiana (evidence su light therapy / circadian biology)',
    ],
    steps: [
      'Entro un’ora dal risveglio, esci all’aperto senza occhiali da sole (se tollerato).',
      'Guarda verso il cielo / orizzonte, non fissare il sole.',
      'Resta 5–10 minuti (di più se cielo coperto).',
      'Se non puoi uscire: vicino a una finestra luminosa o lampada 10.000 lux.',
    ],
    mistakes: [
      'Solo luce artificiale debole indoor al mattino',
      'Rimanere a letto al buio fino a tardi',
      'Usare occhiali scuri nelle prime ore se l’obiettivo è il segnale circadiano',
    ],
  },
  protein_breakfast: {
    key: 'protein_breakfast',
    title: 'Colazione proteica + idratazione',
    what: 'Prima idratazione e un pasto con proteine adeguate per satiety e stabilità energetica.',
    benefits: [
      'Riduce craving mid-morning',
      'Supporta massa magra e recupero',
      'Aiuta focus se combini proteine + acqua dopo digiuno notturno',
    ],
    steps: [
      'Bevi 300–500 ml d’acqua al risveglio.',
      'Includi 25–40 g di proteine (uova, yogurt greco, whey, tofu).',
      'Aggiungi fibra o grassi buoni per sazietà.',
      'Evita solo zuccheri liquidi come “colazione”.',
    ],
    mistakes: [
      'Solo caffè a stomaco vuoto per ore',
      'Colazione solo carboidrati raffinati',
      'Saltare liquidi dopo 7–8 ore di digiuno',
    ],
  },
  skincare_am: {
    key: 'skincare_am',
    title: 'Routine skincare mattina',
    what: 'Protocollo minimale: detergere, trattare, proteggere. L’SPF è il passo non negoziabile.',
    benefits: [
      'Riduce photoaging (UV = driver principale)',
      'Barriera cutanea più stabile',
      'Texture più uniforme nel tempo',
    ],
    steps: [
      'Detergi con cleanser delicato.',
      'Applica siero (vitamina C o idratante).',
      'Chiudi con crema se serve.',
      'SPF 30–50 su viso e collo, ogni mattina.',
    ],
    mistakes: [
      'Saltare la protezione solare “perché è nuvoloso”',
      'Troppi attivi insieme al mattino',
      'Strofinare aggressivamente la pelle',
    ],
  },
  skincare_pm: {
    key: 'skincare_pm',
    title: 'Routine skincare sera',
    what: 'Pulizia + attivo riparativo (retinolo/niacinamide) + barriera. La notte è la finestra di riparo.',
    benefits: [
      'Rimozione sebo e inquinanti',
      'Supporto turnover cellulare con attivi notturni',
      'Meno irritazione se introduci un attivo alla volta',
    ],
    steps: [
      'Doppia detersione se hai SPF/makeup.',
      'Attendi pelle asciutta.',
      'Attivo (retinolo 2–4×/sett o niacinamide).',
      'Crema barriera; evita acidi + retinolo la stessa sera se sei principiante.',
    ],
    mistakes: [
      'Usare retinolo ogni sera da subito',
      'Dormire col makeup',
      'Miscelare troppi esfolianti',
    ],
  },
  mewing: {
    key: 'mewing',
    title: 'Mewing & posture reset',
    what: 'Consapevolezza della postura linguale (lingua al palato) e allineamento testa-collo-scapole. Non sostituisce valutazioni ortodontiche.',
    benefits: [
      'Migliora consapevolezza posturale',
      'Può ridurre tensioni cervicali da “forward head”',
      'Supporta un look più “aperto” se abbinato a postura',
    ],
    steps: [
      'Labbra chiuse, denti a contatto leggero o riposo.',
      'Tutta la lingua (anche posteriore) verso il palato.',
      'Respira dal naso.',
      'Spalle indietro e in basso, sguardo all’orizzonte 2–3 minuti.',
    ],
    mistakes: [
      'Forzare la mandibola in avanti con tensione',
      'Solo punta della lingua sul palato',
      'Aspettarsi cambiamenti ossei in pochi giorni',
    ],
  },
  posture_reset: {
    key: 'posture_reset',
    title: 'Posture reset',
    what: 'Micro-reset di scapole, collo e bacino per spezzare posture statiche prolungate.',
    benefits: ['Riduce carico cervicale', 'Migliora respirazione', 'Più presenza fisica'],
    steps: [
      'Alzati ogni 45–60 minuti.',
      'Chin tuck leggero + scapole “in tasca”.',
      'Apri il petto 5 respiri nasali.',
      'Cammina 1–2 minuti.',
    ],
    mistakes: ['Iperestendere il collo', 'Spalle alzate verso le orecchie', 'Solo “tirarsi su” 2 secondi'],
  },
  deep_work: {
    key: 'deep_work',
    title: 'Deep work',
    what: 'Blocco di attenzione protetta senza notifiche, tipicamente 15–50 minuti.',
    benefits: [
      'Output cognitivo più alto per minuto',
      'Meno switching cost',
      'Sensazione di progresso misurabile',
    ],
    steps: [
      'Scegli UN task chiaro.',
      'Silenzia notifiche / modalità Focus.',
      'Timer 15–25 minuti.',
      'Pausa 3–5 minuti; ripeti se serve.',
    ],
    mistakes: ['Multitasking con chat aperte', 'Blocchi da 2 ore senza pause', 'Obiettivo vago (“lavorare un po’”)'],
  },
  training: {
    key: 'training',
    title: 'Allenamento + recupero',
    what: 'Sessione di movimento con cool-down. Intensità calibrata al tuo livello.',
    benefits: ['Sonno e sensibilità insulinica', 'Composizione corporea', 'Resilienza allo stress'],
    steps: [
      'Riscaldamento 5 minuti.',
      'Lavoro principale (forza o cardio).',
      'Cool-down e respirazione.',
      'Proteine nel pasto successivo.',
    ],
    mistakes: ['Zero recupero tra sessioni intense', 'Saltare il warm-up', 'Allenarsi esausti ogni giorno'],
  },
  walk: {
    key: 'walk',
    title: 'Camminata consapevole',
    what: 'Zona 2 leggera: cammino sostenuto per circolazione, umore e recovery.',
    benefits: ['Basso costo, alto ROI', 'Aiuta digestione e glicemia post-pasto', 'Riduce rumination'],
    steps: ['Scarpe comode', 'Ritmo in cui puoi parlare', '20 minuti continui', 'Opzionale: senza cuffie 5 minuti'],
    mistakes: ['Solo passi “nervosi” in casa', 'Sostituire sempre l’allenamento con 5 minuti', 'Camminare curvi sullo smartphone'],
  },
  digestion_pause: {
    key: 'digestion_pause',
    title: 'Pausa post-pasto',
    what: 'Finestra senza scroll per favorire saturazione e ridurre stress digestivo.',
    benefits: ['Migliore consapevolezza della sazietà', 'Meno grazing automatico', 'Respiro più calmo'],
    steps: ['Fine pasto → telefono lontano', '10 respiri lenti o breve camminata', 'Bevi acqua se serve', 'Riprendi schermi dopo'],
    mistakes: ['Mangiare scrollando', 'Coricarsi subito dopo pasti abbondanti', 'Saltare la pausa “perché sei di fretta” sempre'],
  },
  digital_sunset: {
    key: 'digital_sunset',
    title: 'Digital sunset',
    what: 'Riduzione luce blu / stimoli digitali 45–60 minuti prima di dormire.',
    benefits: [
      'Favorisce melatonin timing',
      'Riduce arousal cognitivo',
      'Associa il letto al riposo, non allo scroll',
    ],
    steps: [
      'Imposta allarme “sunset” 45 min prima del target bedtime.',
      'Abbassa luminosità / night shift.',
      'Passa a libro, stretching, doccia.',
      'Camera fresca e buia.',
    ],
    mistakes: ['Netflix a letto a volume alto', 'Rispondere email “solo 5 minuti”', 'Caffeina nel tardo pomeriggio'],
  },
  wind_down: {
    key: 'wind_down',
    title: 'Wind-down serale',
    what: 'Rituale breve (respirazione, journaling, iper-semplice) per passare in modalità recupero.',
    benefits: ['Dormi più in fretta', 'Meno ruminazione', 'Routine che ancora l’aderenza'],
    steps: [
      'Stessa sequenza ogni sera.',
      'Respirazione 4-7-8 × 4 cicli oppure box breathing.',
      'Annota 1 vittoria del giorno.',
      'Luci basse, poi a letto.',
    ],
    mistakes: ['Rituale diverso ogni notte', 'Discussioni intense pre-sonno', 'Integratori al posto dell’igiene del sonno'],
  },
  daily_check: {
    key: 'daily_check',
    title: 'Check aderenza',
    what: 'Review di 3 minuti: cosa hai chiuso, cosa slitti, una priorità per domani.',
    benefits: ['Chiude il loop di feedback', 'Evita overload di abitudini', 'Alza la streak consapevole'],
    steps: ['Apri il protocollo', 'Segna task rimaste', 'Scegli 1 priorità per domani', 'Stop.'],
    mistakes: ['Aggiungere 5 abitudini nuove', 'Punirti per un giorno perso', 'Review di 30 minuti ansiosa'],
  },
  cold_shower: {
    key: 'cold_shower',
    title: 'Cold shower',
    what: 'Esposizione breve al freddo a fine doccia. Ormesi: stressor controllato, non tortura.',
    benefits: [
      'Aumento acuto di allerta',
      'Pratica di controllo del respiro sotto stress',
      'Possibile supporto all’umore (evidenza mista, individuale)',
    ],
    steps: [
      'Finisci la doccia calda normalmente.',
      'Porta l’acqua a freddo 30–90 secondi.',
      'Respira lenta e nasale; spalle basse.',
      'Esci, asciuga, muoviti.'],
    mistakes: [
      'Iperventilare o trattenere il respiro in panico',
      'Partire da 5 minuti da zero',
      'Usarlo se hai controindicazioni cardiovascolari senza parere medico',
    ],
  },
};

export function getTutorial(key: TutorialKey): TutorialContent {
  return TUTORIALS[key];
}
