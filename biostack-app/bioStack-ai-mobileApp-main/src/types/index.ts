export type AppPhase = 'welcome' | 'onboarding' | 'generating' | 'result' | 'app';
export type NavTab = 'home' | 'protocol' | 'insights' | 'stack' | 'pro';
export type RoutineSlot = 'morning' | 'afternoon' | 'evening';
export type BudgetTier = 'low' | 'medium' | 'elite';
export type EnergyLevel = 'low' | 'medium' | 'high';
export type SleepQuality = 'poor' | 'ok' | 'good';
export type StressLevel = 'high' | 'medium' | 'low';
export type TrainingLevel = 'none' | 'light' | 'regular' | 'intense';
export type StackKind = 'habit' | 'nutrition' | 'supplement' | 'recovery' | 'skincare' | 'tool';
export type ProductFilter = 'all' | StackKind;
export type SubscriptionPlan = 'annual' | 'monthly';
export type ProSection = 'settings' | 'scan';
export type UiOverlay = 'none' | 'notifications' | 'wiki';
export type TutorialKey =
  | 'morning_light'
  | 'protein_breakfast'
  | 'skincare_am'
  | 'skincare_pm'
  | 'mewing'
  | 'deep_work'
  | 'training'
  | 'walk'
  | 'digestion_pause'
  | 'digital_sunset'
  | 'wind_down'
  | 'daily_check'
  | 'cold_shower'
  | 'posture_reset';

export type PrimaryGoal =
  | 'skincare'
  | 'posture'
  | 'focus'
  | 'longevity'
  | 'testosterone'
  | 'digestion'
  | 'bodycomp';

export interface OnboardingAnswers {
  goal: PrimaryGoal | null;
  name: string;
  age: number | null;
  sex: 'male' | 'female' | 'other' | 'skip' | null;
  energy: EnergyLevel | null;
  sleep: SleepQuality | null;
  stress: StressLevel | null;
  training: TrainingLevel | null;
  focusAreas: PrimaryGoal[];
  limitations: string[];
  budget: BudgetTier | null;
  skippedSteps: number[];
}

export interface ProtocolTask {
  id: string;
  title: string;
  reason: string;
  durationMin: number;
  slot: RoutineSlot;
  kind: StackKind;
  xp: number;
  completed: boolean;
  tutorialKey: TutorialKey;
  timerActive?: boolean;
}

export interface StackItem {
  id: string;
  name: string;
  brand: string;
  kind: StackKind;
  price: number;
  timing: string;
  dosage?: string;
  why: string;
  safetyNote?: string;
  amazonUrl: string;
  budgetTier: BudgetTier[];
  goals: PrimaryGoal[];
}

export interface ProtocolResult {
  score: number;
  priorities: { title: string; detail: string }[];
  tasks: ProtocolTask[];
  stack: StackItem[];
  habits: string[];
  whySummary: string;
  metrics: {
    energy: number;
    recovery: number;
    focus: number;
    glow: number;
  };
}

export interface DayLog {
  date: string;
  completedCount: number;
  totalCount: number;
  adherence: number;
  xpEarned: number;
  completedTaskIds: string[];
}

export interface WeeklyPoint {
  week: number;
  score: number;
  adherence: number;
  sleep: number;
  focus: number;
  recovery: number;
  date: string;
}

export interface FacialReport {
  faceShape: 'oval' | 'square' | 'diamond' | 'heart' | 'round' | 'oblong';
  symmetry: number;
  jawAngle: number;
  skinQuality: 'excellent' | 'good' | 'fair' | 'needs_attention';
  hydration: number;
  contrastScore: number;
  brightness: number;
  imageWidth: number;
  imageHeight: number;
  hairRecommendation: string;
  hairReason: string;
  beardRecommendation: string;
  beardReason: string;
  eyewearRecommendation: string;
  eyewearStyles: string[];
  glowUpScore: number;
  analysisNotes: string[];
}

export interface PersistedState {
  version: 2;
  phase: AppPhase;
  navTab: NavTab;
  proSection: ProSection;
  uiOverlay: UiOverlay;
  isDarkMode: boolean;
  isPremium: boolean;
  answers: OnboardingAnswers;
  protocol: ProtocolResult | null;
  xpPoints: number;
  streakDays: number;
  lastCompletedDate: string | null;
  dayHistory: DayLog[];
  weeklyHistory: WeeklyPoint[];
  facialReport: FacialReport | null;
  remindersEnabled: boolean;
  reminderMorning: string;
  reminderEvening: string;
  reminderScan: boolean;
  demoMode: boolean;
}

export const defaultAnswers = (): OnboardingAnswers => ({
  goal: null,
  name: '',
  age: null,
  sex: null,
  energy: null,
  sleep: null,
  stress: null,
  training: null,
  focusAreas: [],
  limitations: [],
  budget: null,
  skippedSteps: [],
});
