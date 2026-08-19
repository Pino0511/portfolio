import type {
  BudgetTier,
  DayLog,
  OnboardingAnswers,
  PrimaryGoal,
  ProtocolResult,
  ProtocolTask,
  StackItem,
  WeeklyPoint,
} from '../types';

export const CATALOG: StackItem[] = [
  // --- SKINCARE ESSENZIALE ---
  {
    id: 's1',
    name: 'CeraVe Gel Detergente ',
    brand: 'CeraVe',
    kind: 'skincare',
    price: 14.5,
    timing: 'Mattina e sera',
    why: 'Pulisce i pori in profondità, rimuove l sebo in eccesso e riequilibra la barriera cutanea.',
    amazonUrl: 'https://link.amazon/B062akamg',
    budgetTier: ['low', 'medium', 'elite'],
    goals: ['skincare', 'longevity'],
  },
  {
    id: 's2',
    name: 'CeraVe Crema Idratante Viso SPF50',
    brand: 'CeraVe',
    kind: 'skincare',
    price: 18.9,
    timing: 'Mattina',
    why: 'Protezione UV ad ampio spettro e idratazione: il miglior trattamento anti-aging in assoluto.',
    amazonUrl: 'https://link.amazon/B05NMoDsV',
    budgetTier: ['low', 'medium', 'elite'],
    goals: ['skincare', 'longevity'],
  },
  {
    id: 's3',
    name: 'The Ordinary Niacinamide 10% + Zinc 1%',
    brand: 'The Ordinary',
    kind: 'skincare',
    price: 9.9,
    timing: 'Sera',
    dosage: '2–3 gocce',
    why: 'Regola la produzione di sebo, riduce i punti neri e uniforma la texture della pelle.',
    amazonUrl: 'https://link.amazon/B0acrOViF',
    budgetTier: ['low', 'medium', 'elite'],
    goals: ['skincare'],
  },

  // --- JAWLINE, POSTURA & RITUALI ---
  {
    id: 's4',
    name: 'Pietra Gua Sha in Giada Naturale',
    brand: 'BioStack Tools',
    kind: 'tool',
    price: 12.9,
    timing: 'Sera, 5 min',
    why: 'Favorisce il drenaggio linfatico facciale, sgonfia il viso e definisce i contorni della mascella.',
    amazonUrl: 'https://link.amazon/B03u9eZka',
    budgetTier: ['low', 'medium', 'elite'],
    goals: ['skincare', 'posture'],
  },
  {
    id: 's5',
    name: 'Mouth Tape per Respirazione Nasale',
    brand: 'SleepWell',
    kind: 'tool',
    price: 11.9,
    timing: 'Notte',
    why: 'Incoraggia la respirazione nasale notturna, migliorando la qualità del sonno e la struttura facciale.',
    amazonUrl: 'https://link.amazon/B0eB8E6su',
    budgetTier: ['low', 'medium', 'elite'],
    goals: ['longevity', 'posture', 'focus'],
  },
  {
    id: 's6',
    name: 'Correttore di Postura Regolabile',
    brand: 'AlignTech',
    kind: 'tool',
    price: 18.5,
    timing: '15–30 min al giorno',
    why: 'Apre le spalle, corregge la postura del collo (text neck) ed valorizza la postura complessiva.',
    amazonUrl: 'https://link.amazon/B04C18few',
    budgetTier: ['low', 'medium', 'elite'],
    goals: ['posture', 'longevity'],
  },

  // --- CAPELLI, BARBA & SONNO ---
  {
    id: 's7',
    name: 'Federa 100% Seta di Gelso',
    brand: 'SilkLuxury',
    kind: 'tool',
    price: 21.9,
    timing: 'Notte',
    why: 'Riduce l attrito su pelle e capelli durante la notte, prevenendo pieghe del sonno e crespo.',
    amazonUrl: 'https://link.amazon/B05Bg5WuM',
    budgetTier: ['medium', 'elite'],
    goals: ['skincare', 'longevity'],
  },
  {
    id: 's8',
    name: 'Olio di Argan Biologico Puro',
    brand: 'NaturaPure',
    kind: 'skincare',
    price: 13.9,
    timing: 'Sera / 2-3 volte a settimana',
    dosage: 'Poche gocce',
    why: 'Nutre e rinfoltisce barba, sopracciglia e capelli stimolandone la crescita sana.',
    amazonUrl: 'https://link.amazon/B09zGRUxf',
    budgetTier: ['low', 'medium', 'elite'],
    goals: ['skincare'],
  },

  // --- INTEGRATORI FONDAMENTALI (OPZIONALI) ---
  {
    id: 's9',
    name: 'Magnesium Glycinate',
    brand: 'Pure Encapsulations',
    kind: 'supplement',
    price: 24.9,
    timing: 'Sera',
    dosage: '200–300 mg',
    why: 'Sonno profondo e recupero muscolare: la base per avere energia il giorno dopo.',
    safetyNote: 'Non eccedere il dosaggio. Consulta un medico se hai patologie.',
    amazonUrl: 'https://link.amazon/B0bz8ZB3t',
    budgetTier: ['low', 'medium', 'elite'],
    goals: ['focus', 'longevity', 'testosterone'],
  },
  {
    id: 's10',
    name: 'Creatine Monohydrate',
    brand: 'Creapure',
    kind: 'supplement',
    price: 15.0,
    timing: 'Ogni giorno',
    dosage: '3–5 g',
    why: 'Supporta la performance mentale, la pienezza muscolare e la forza con evidenza scientifica solida.',
    safetyNote: 'Mantieni un ottima idratazione quotidiana.',
    amazonUrl: 'https://link.amazon/B0hhTfSZ4',
    budgetTier: ['low', 'medium', 'elite'],
    goals: ['focus', 'bodycomp', 'testosterone'],
  },
];

const GOAL_LABEL: Record<PrimaryGoal, string> = {
  skincare: 'Glow & pelle',
  posture: 'Postura & mewing',
  focus: 'Focus mentale',
  longevity: 'Longevità',
  testosterone: 'Vitalità',
  digestion: 'Digestione',
  bodycomp: 'Composizione corporea',
};

function clamp(n: number, min = 0, max = 100) {
  return Math.max(min, Math.min(max, Math.round(n)));
}

function scoreFromAnswers(a: OnboardingAnswers) {
  let base = 58;
  if (a.energy === 'high') base += 10;
  if (a.energy === 'low') base -= 8;
  if (a.sleep === 'good') base += 12;
  if (a.sleep === 'poor') base -= 12;
  if (a.stress === 'low') base += 8;
  if (a.stress === 'high') base -= 10;
  if (a.training === 'regular' || a.training === 'intense') base += 6;
  if (a.training === 'none') base -= 4;
  return clamp(base);
}

function buildTasks(a: OnboardingAnswers): ProtocolTask[] {
  const goal = a.goal ?? 'longevity';
  const busy = a.limitations.includes('busy');
  const tasks: ProtocolTask[] = [
    {
      id: 'm1',
      title: busy ? 'Luce mattutina 3–5 min' : 'Luce mattutina 5–10 min',
      reason: 'Allinea il ritmo circadiano: energia e umore più stabili.',
      durationMin: busy ? 4 : 8,
      slot: 'morning',
      kind: 'habit',
      xp: 15,
      completed: false,
      tutorialKey: 'morning_light',
    },
    {
      id: 'm2',
      title: 'Idratazione + proteine a colazione',
      reason: 'Stabilizza fame e focus nelle prime ore.',
      durationMin: 5,
      slot: 'morning',
      kind: 'nutrition',
      xp: 10,
      completed: false,
      tutorialKey: 'protein_breakfast',
    },
  ];

  if (goal === 'skincare' || a.focusAreas.includes('skincare')) {
    tasks.push(
      {
        id: 'm3',
        title: a.limitations.includes('sensitive_skin')
          ? 'Routine pelle delicata: cleanse + crema + SPF'
          : 'Routine pelle: cleanse + vitamina C + SPF',
        reason: 'SPF è il gesto anti-aging con il miglior ROI.',
        durationMin: busy ? 3 : 4,
        slot: 'morning',
        kind: 'skincare',
        xp: 15,
        completed: false,
        tutorialKey: 'skincare_am',
      },
      {
        id: 'e3',
        title: 'Routine sera: cleanse + attivo + crema',
        reason: 'Riparazione notturna = glow più consistente.',
        durationMin: busy ? 3 : 5,
        slot: 'evening',
        kind: 'skincare',
        xp: 15,
        completed: false,
        tutorialKey: 'skincare_pm',
      },
    );
  }

  if (!busy && (goal === 'posture' || a.focusAreas.includes('posture'))) {
    tasks.push({
      id: 'm4',
      title: 'Mewing + posture reset',
      reason: 'Consapevolezza orale e scapole: look più definito nel tempo.',
      durationMin: 10,
      slot: 'morning',
      kind: 'habit',
      xp: 15,
      completed: false,
      tutorialKey: 'mewing',
    });
  }

  if (goal === 'focus' || a.energy === 'low' || a.stress === 'high') {
    tasks.push({
      id: 'a1',
      title: busy ? 'Deep work 15 min' : 'Deep work 25 min (senza notifiche)',
      reason: 'Un blocco protetto batte 2 ore frammentate.',
      durationMin: busy ? 15 : 25,
      slot: 'afternoon',
      kind: 'habit',
      xp: 20,
      completed: false,
      tutorialKey: 'deep_work',
    });
  }

  if (a.training !== 'none') {
    tasks.push({
      id: 'a2',
      title: a.training === 'intense' ? 'Allenamento + cool-down' : 'Movimento 20–40 min',
      reason: 'Allenamento = recupero, sonno e composizione corporea.',
      durationMin: busy ? 20 : a.training === 'intense' ? 50 : 30,
      slot: 'afternoon',
      kind: 'recovery',
      xp: 25,
      completed: false,
      tutorialKey: 'training',
    });
  } else {
    tasks.push({
      id: 'a2',
      title: 'Camminata 20 min',
      reason: 'Movimento leggero alza energia senza stressare il sistema.',
      durationMin: 20,
      slot: 'afternoon',
      kind: 'habit',
      xp: 15,
      completed: false,
      tutorialKey: 'walk',
    });
  }

  if (!busy && (goal === 'digestion' || a.focusAreas.includes('digestion'))) {
    tasks.push({
      id: 'a3',
      title: 'Pausa post-pasto senza scroll',
      reason: 'Meno stress digestivo, più saturazione consapevole.',
      durationMin: 10,
      slot: 'afternoon',
      kind: 'habit',
      xp: 10,
      completed: false,
      tutorialKey: 'digestion_pause',
    });
  }

  if (!busy && (goal === 'longevity' || goal === 'testosterone')) {
    tasks.push({
      id: 'a4',
      title: 'Cold shower 30–90 sec',
      reason: 'Esposizione al freddo come stressor ormetico controllato.',
      durationMin: 3,
      slot: 'morning',
      kind: 'recovery',
      xp: 15,
      completed: false,
      tutorialKey: 'cold_shower',
    });
  }

  tasks.push(
    {
      id: 'e1',
      title: 'Digital sunset 45 min prima di dormire',
      reason: 'Migliora latenza del sonno e qualità del recupero.',
      durationMin: 5,
      slot: 'evening',
      kind: 'habit',
      xp: 15,
      completed: false,
      tutorialKey: 'digital_sunset',
    },
    {
      id: 'e2',
      title: a.sleep === 'poor' ? 'Rituale sonno + magnesio (se idoneo)' : 'Wind-down 10 min',
      reason: 'Il recupero notturno guida energia, pelle e focus.',
      durationMin: busy ? 6 : 10,
      slot: 'evening',
      kind: 'recovery',
      xp: 15,
      completed: false,
      tutorialKey: 'wind_down',
    },
  );

  if (!busy && (goal === 'longevity' || goal === 'testosterone' || goal === 'bodycomp')) {
    tasks.push({
      id: 'e4',
      title: 'Check aderenza: 3 priorità del giorno',
      reason: 'Consistenza > perfezione. Traccia solo ciò che conta.',
      durationMin: 3,
      slot: 'evening',
      kind: 'habit',
      xp: 10,
      completed: false,
      tutorialKey: 'daily_check',
    });
  }

  return tasks;
}

function pickStack(a: OnboardingAnswers): StackItem[] {
  const budget: BudgetTier = a.budget ?? 'medium';
  const goal = a.goal ?? 'longevity';
  const areas = new Set<PrimaryGoal>([goal, ...a.focusAreas]);
  const noSupplements = a.limitations.includes('no_supplements');
  const busy = a.limitations.includes('busy');

  const scored = CATALOG.map((item) => {
    let score = 0;
    if (noSupplements && item.kind === 'supplement') return { item, score: -99 };
    if (item.budgetTier.includes(budget)) score += 3;
    if (item.goals.some((g) => areas.has(g))) score += 4;
    if (budget === 'low' && item.price > 25) score -= 2;
    if (a.limitations.includes('budget_strict') && item.price > 20) score -= 3;
    if (a.sleep === 'poor' && item.id === 's7') score += 3;
    if (a.stress === 'high' && item.kind === 'recovery') score += 1;
    if (busy && item.kind === 'tool') score -= 1;
    if (a.limitations.includes('sensitive_skin') && item.id === 's2') score -= 2;
    return { item, score };
  })
    .filter((x) => x.score > 0)
    .sort((a, b) => b.score - a.score)
    .slice(0, budget === 'elite' ? 6 : budget === 'medium' ? 5 : 4)
    .map((x) => x.item);

  return scored;
}

export function generateProtocol(a: OnboardingAnswers): ProtocolResult {
  const score = scoreFromAnswers(a);
  const goal = a.goal ?? 'longevity';
  const tasks = buildTasks(a);
  const stack = pickStack(a);

  const priorities = [
    {
      title: a.sleep === 'poor' ? 'Ripristina il sonno' : 'Proteggi il recupero',
      detail:
        a.sleep === 'poor'
          ? 'Senza sonno solido, glow e focus restano fragili.'
          : 'Mantieni finestre di recupero per non bruciare adattamento.',
    },
    {
      title: `Priorità: ${GOAL_LABEL[goal]}`,
      detail: 'Il protocollo concentrerà energia su questo esito per 14 giorni.',
    },
    {
      title: a.stress === 'high' ? 'Abbassa il carico nervoso' : 'Rendi automatiche 3 abitudini',
      detail:
        a.stress === 'high'
          ? 'Meno input, più deep work e wind-down serale.'
          : 'Aderenza > complessità. Tre gesti al giorno bastano.',
    },
  ];

  const habits = [
    'Completa almeno l’80% del protocollo per 5 giorni su 7',
    'Una cosa nuova alla volta: non aggiungere stack extra subito',
    'Rivedi obiettivi ogni domenica in 3 minuti',
  ];

  const energy = clamp(
    50 +
      (a.energy === 'high' ? 25 : a.energy === 'medium' ? 10 : -10) +
      (a.sleep === 'good' ? 10 : a.sleep === 'poor' ? -15 : 0),
  );
  const recovery = clamp(
    55 + (a.sleep === 'good' ? 20 : a.sleep === 'poor' ? -20 : 5) + (a.stress === 'low' ? 10 : a.stress === 'high' ? -15 : 0),
  );
  const focus = clamp(
    52 + (a.stress === 'low' ? 18 : a.stress === 'high' ? -12 : 5) + (goal === 'focus' ? 10 : 0),
  );
  const glow = clamp(
    48 +
      (goal === 'skincare' ? 15 : 5) +
      (a.sleep === 'good' ? 12 : a.sleep === 'poor' ? -8 : 4) +
      (a.training === 'none' ? -5 : 6),
  );

  return {
    score,
    priorities,
    tasks,
    stack,
    habits,
    whySummary: `Abbiamo costruito il tuo protocollo intorno a ${GOAL_LABEL[goal].toLowerCase()}, bilanciando sonno, stress e allenamento. Non è un piano medico: è un sistema quotidiano evidence-informed per migliorare aderenza e risultati percepiti.`,
    metrics: { energy, recovery, focus, glow },
  };
}

export function buildDemoAnswers(): OnboardingAnswers {
  return {
    goal: 'skincare',
    name: 'Alex',
    age: 28,
    sex: 'skip',
    energy: 'medium',
    sleep: 'ok',
    stress: 'medium',
    training: 'light',
    focusAreas: ['focus', 'longevity'],
    limitations: [],
    budget: 'medium',
    skippedSteps: [],
  };
}

/** Costruisce punti insight dalle checklist reali salvate in dayHistory. */
export function buildWeeklyHistoryFromDays(
  dayHistory: DayLog[],
  baselineScore = 50,
): WeeklyPoint[] {
  if (!dayHistory.length) return [];

  const sorted = [...dayHistory].sort((a, b) => a.date.localeCompare(b.date));
  const byWeek = new Map<string, DayLog[]>();

  for (const day of sorted) {
    const d = new Date(day.date + 'T12:00:00');
    const onejan = new Date(d.getFullYear(), 0, 1);
    const week = Math.ceil(((d.getTime() - onejan.getTime()) / 86400000 + onejan.getDay() + 1) / 7);
    const key = `${d.getFullYear()}-W${week}`;
    const list = byWeek.get(key) ?? [];
    list.push(day);
    byWeek.set(key, list);
  }

  const points: WeeklyPoint[] = [];
  let runningScore = baselineScore;
  let idx = 0;
  for (const [, days] of byWeek) {
    idx += 1;
    const adherence = clamp(
      days.reduce((s, d) => s + d.adherence, 0) / days.length,
    );
    runningScore = clamp(runningScore + (adherence - 50) * 0.25);
    const last = days[days.length - 1];
    points.push({
      week: idx,
      score: runningScore,
      adherence,
      sleep: clamp(45 + adherence * 0.4),
      focus: clamp(40 + adherence * 0.45),
      recovery: clamp(42 + adherence * 0.42),
      date: last.date,
    });
  }
  return points;
}

/** Serie giornaliera (max 14) per grafici se le settimane sono poche. */
export function buildDailySeries(dayHistory: DayLog[], baselineScore = 50): WeeklyPoint[] {
  const sorted = [...dayHistory].sort((a, b) => a.date.localeCompare(b.date)).slice(-14);
  let running = baselineScore;
  return sorted.map((d, i) => {
    running = clamp(running + (d.adherence - 50) * 0.15);
    return {
      week: i + 1,
      score: running,
      adherence: d.adherence,
      sleep: clamp(45 + d.adherence * 0.4),
      focus: clamp(40 + d.adherence * 0.45),
      recovery: clamp(42 + d.adherence * 0.42),
      date: d.date,
    };
  });
}

export function computeStreakFromHistory(dayHistory: DayLog[], today = todayKey()): number {
  const doneDays = new Set(
    dayHistory.filter((d) => d.adherence >= 80 || d.completedCount === d.totalCount).map((d) => d.date),
  );
  if (!doneDays.size) return 0;
  let streak = 0;
  const cursor = new Date(today + 'T12:00:00');
  // If today not complete yet, start from yesterday
  if (!doneDays.has(today)) {
    cursor.setDate(cursor.getDate() - 1);
  }
  while (true) {
    const key = cursor.toISOString().slice(0, 10);
    if (!doneDays.has(key)) break;
    streak += 1;
    cursor.setDate(cursor.getDate() - 1);
  }
  return streak;
}

export function todayKey() {
  return new Date().toISOString().slice(0, 10);
}

export function adherencePercent(tasks: ProtocolTask[]) {
  if (!tasks.length) return 0;
  return Math.round((tasks.filter((t) => t.completed).length / tasks.length) * 100);
}

export function getLevelInfo(xp: number) {
  if (xp >= 3000) return { level: 5, name: 'Maestro Longevità', nextXp: 5000, progress: ((xp - 3000) / 2000) * 100 };
  if (xp >= 1500) return { level: 4, name: 'BioHacker Elite', nextXp: 3000, progress: ((xp - 1500) / 1500) * 100 };
  if (xp >= 700) return { level: 3, name: 'Protocol Specialist', nextXp: 1500, progress: ((xp - 700) / 800) * 100 };
  if (xp >= 300) return { level: 2, name: 'Apprendista', nextXp: 700, progress: ((xp - 300) / 400) * 100 };
  return { level: 1, name: 'Novizio', nextXp: 300, progress: (xp / 300) * 100 };
}
