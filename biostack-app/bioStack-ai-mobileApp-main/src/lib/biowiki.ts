export type WikiCategory =
  | 'postura'
  | 'skincare'
  | 'longevita'
  | 'sonno';

export interface WikiArticle {
  id: string;
  category: WikiCategory;
  title: string;
  readMinutes: number;
  summary: string;
  sections: { heading: string; body: string }[];
  relatedStackIds: string[];
  relatedStackNames: string[];
}

export const WIKI_CATEGORIES: { id: WikiCategory; label: string; blurb: string }[] = [
  { id: 'postura', label: 'Postura & Jawline', blurb: 'Mewing, collo e decompressione' },
  { id: 'skincare', label: 'Skincare & Viso', blurb: 'Tipo di pelle e routine minimale' },
  { id: 'longevita', label: 'Longevità & Integratori', blurb: 'NAD+, NMN, cortisolo' },
  { id: 'sonno', label: 'Sonno & Performance', blurb: 'Luce blu e respirazione' },
];

export const WIKI_ARTICLES: WikiArticle[] = [
  {
    id: 'mewing-guide',
    category: 'postura',
    title: 'Guida al Mewing consapevole',
    readMinutes: 6,
    summary: 'Cos’è il mewing, cosa può realistica­mente migliorare e come praticarlo senza forzature.',
    sections: [
      {
        heading: 'Il principio',
        body: 'Il mewing è una pratica di postura linguale: lingua al palato, respirazione nasale, labbra a riposo. È uno strumento di consapevolezza, non un sostituto di ortodonzia o chirurgia.',
      },
      {
        heading: 'Cosa dice (e non dice) la scienza',
        body: 'Esiste letteratura su postura linguale, respirazione orale e sviluppo cranio-facciale in età evolutiva. Negli adulti, aspettative di “cambiamento osseo rapido” sono spesso esagerate. I benefici più realistici: abitudine respiratoria, tono e postura.',
      },
      {
        heading: 'Protocollo pratico',
        body: '3–5 reminder al giorno. 2 minuti di check: lingua intera al palato, collo allungato, scapole basse. Abbina a posture reset se lavori al desk.',
      },
    ],
    relatedStackIds: ['s6'],
    relatedStackNames: ['Gua Sha Stone', 'Routine postura'],
  },
  {
    id: 'spinal-decomp',
    category: 'postura',
    title: 'Decompressione spinale quotidiana',
    readMinutes: 5,
    summary: 'Micro-routine per scaricare rachide cervicale e lombare dopo ore sedute.',
    sections: [
      {
        heading: 'Perché serve',
        body: 'La postura statica prolungata aumenta carico su dischi e muscoli posturali. Interruzioni brevi migliorano comfort e attenzione meglio di “correggersi” una volta al giorno.',
      },
      {
        heading: 'Sequenza 4 minuti',
        body: '1) Chin tuck ×10. 2) Cat-cow o flessione/estensione blanda. 3) Apertura petto al muro. 4) Camminata 60 secondi. Nessun dolore acuto: se c’è, ferma e consulta un professionista.',
      },
    ],
    relatedStackIds: [],
    relatedStackNames: ['Camminata 20 min', 'Posture reset'],
  },
  {
    id: 'skin-type',
    category: 'skincare',
    title: 'Capire il proprio tipo di pelle',
    readMinutes: 7,
    summary: 'Come riconoscere secca, grassa, mista o sensibile senza inseguire 12 prodotti.',
    sections: [
      {
        heading: 'Osserva, non indovinare',
        body: 'Dopo un cleanse delicato, aspetta 30–60 minuti senza prodotti. Tensione = tendenza secca. Lucentezza zona T = mista/grassa. Rossori facili = sensibile/reattiva.',
      },
      {
        heading: 'Regola d’oro',
        body: 'Barriera prima degli attivi. Se brucia o sfoglia, semplifica a cleanser + crema + SPF finché non si calma.',
      },
    ],
    relatedStackIds: ['s1', 's2', 's3'],
    relatedStackNames: ['CeraVe Hydrating Cleanser', 'Niacinamide', 'SPF50'],
  },
  {
    id: 'minimal-routine',
    category: 'skincare',
    title: 'Routine skincare minimalista',
    readMinutes: 5,
    summary: 'Tre passi che battono dieci prodotti usati male.',
    sections: [
      {
        heading: 'AM',
        body: 'Cleanse (se serve) → idratante leggero → SPF. Vitamina C opzionale se tollerata.',
      },
      {
        heading: 'PM',
        body: 'Cleanse → un attivo (retinolo O acido, non entrambi da subito) → crema barriera.',
      },
    ],
    relatedStackIds: ['s1', 's3'],
    relatedStackNames: ['Cleanser', 'Anthelios SPF50+'],
  },
  {
    id: 'nad-nmn',
    category: 'longevita',
    title: 'NAD+, NMN e aspettative realistiche',
    readMinutes: 8,
    summary: 'Cosa sono, cosa sappiamo oggi, e perché non sono una scorciatoia magica.',
    sections: [
      {
        heading: 'Contesto',
        body: 'NAD+ è un cofattore coinvolto in metabolismo energetico e riparo cellulare. NMN è un precursore studiato soprattutto in modelli preclinici; evidenza umana solida su longevity hard outcomes è ancora limitata.',
      },
      {
        heading: 'Approccio BioStack',
        body: 'Priorità: sonno, allenamento, proteine, luce, non fumare. Gli integratori “longevity” vanno discussi con un professionista; l’app non prescrive dosaggi terapeutici.',
      },
    ],
    relatedStackIds: ['s4', 's7'],
    relatedStackNames: ['Omega-3', 'Magnesium Glycinate'],
  },
  {
    id: 'cortisol',
    category: 'longevita',
    title: 'Gestione del cortisolo quotidiano',
    readMinutes: 6,
    summary: 'Il cortisolo non è il nemico: lo è il profilo cronicamente disregolato.',
    sections: [
      {
        heading: 'Segnali utili',
        body: 'Energia “piatta”, craving serali, sonno leggero, irritabilità. Non diagnosticano nulla da soli, ma guidano il protocollo.',
      },
      {
        heading: 'Leve pratiche',
        body: 'Luce mattutina, caffeina entro la mattina, allenamento non eccessivo ogni giorno, wind-down, proteine regolari. Riduci input notturni più che “integrare via” lo stress.',
      },
    ],
    relatedStackIds: ['s7'],
    relatedStackNames: ['Magnesium Glycinate', 'Digital sunset'],
  },
  {
    id: 'blue-light',
    category: 'sonno',
    title: 'Igiene della luce blu',
    readMinutes: 5,
    summary: 'Come usare (e limitare) gli schermi senza diventare estremisti.',
    sections: [
      {
        heading: 'Il meccanismo',
        body: 'Luce ricca di blu di sera può ritardare la melatonin onset. Intensità, timing e distanza contano più del “filtro magico”.',
      },
      {
        heading: 'Protocollo soft',
        body: '45–60 min pre-sonno: luminosità bassa, night shift, niente lavoro cognitivamente caldo a letto. Occhiali blue-block aiutano alcuni, non risolvono lo scroll ansioso.',
      },
    ],
    relatedStackIds: ['s10'],
    relatedStackNames: ['Blue-light glasses', 'Digital sunset'],
  },
  {
    id: 'breathing-478',
    category: 'sonno',
    title: 'Respirazione 4-7-8 per il wind-down',
    readMinutes: 4,
    summary: 'Tecnica semplice per abbassare arousal prima di dormire.',
    sections: [
      {
        heading: 'Come si fa',
        body: 'Inspira dal naso contando 4, trattieni 7, espira dalla bocca contando 8. Ripeti 4 cicli. Se trattenere 7 è scomodo, usa 4-4-6.',
      },
      {
        heading: 'Quando usarla',
        body: 'Nel wind-down, non mentre guidi. Se hai patologie respiratorie, adatta o chiedi consiglio medico.',
      },
    ],
    relatedStackIds: [],
    relatedStackNames: ['Wind-down 10 min'],
  },
];
