import type { DayLog, PersistedState, WeeklyPoint } from '../types';
import { defaultAnswers } from '../types';

const KEY = 'biostack_v2';
const LEGACY_KEY = 'biostack_v1';
const THEME_KEY = 'biostack_theme';

export function loadThemePreference(): boolean | null {
  try {
    const t = localStorage.getItem(THEME_KEY);
    if (t === 'dark') return true;
    if (t === 'light') return false;
    return null;
  } catch {
    return null;
  }
}

export function saveThemePreference(isDark: boolean): void {
  try {
    localStorage.setItem(THEME_KEY, isDark ? 'dark' : 'light');
  } catch {
    // ignore
  }
}

export function applyDarkClass(isDark: boolean): void {
  document.documentElement.classList.toggle('dark', isDark);
}

function migrate(raw: Record<string, unknown>): PersistedState | null {
  const base = createInitialPersisted();
  const version = raw.version;
  if (version !== 1 && version !== 2) return null;

  const protocol = raw.protocol as PersistedState['protocol'] | null | undefined;
  if (protocol?.tasks) {
    protocol.tasks = protocol.tasks.map((t) => ({
      ...t,
      tutorialKey: t.tutorialKey ?? 'daily_check',
    }));
  }

  return {
    ...base,
    ...raw,
    version: 2,
    protocol: protocol ?? null,
    dayHistory: Array.isArray(raw.dayHistory) ? (raw.dayHistory as DayLog[]) : [],
    weeklyHistory: Array.isArray(raw.weeklyHistory) ? (raw.weeklyHistory as WeeklyPoint[]) : [],
    proSection: (raw.proSection as PersistedState['proSection']) ?? 'settings',
    uiOverlay: 'none',
    reminderMorning: (raw.reminderMorning as string) ?? '08:00',
    reminderEvening: (raw.reminderEvening as string) ?? '21:30',
    reminderScan: typeof raw.reminderScan === 'boolean' ? raw.reminderScan : true,
    answers: { ...defaultAnswers(), ...(raw.answers as object) },
  } as PersistedState;
}

export function loadState(): PersistedState | null {
  try {
    const raw = localStorage.getItem(KEY) ?? localStorage.getItem(LEGACY_KEY);
    if (!raw) return null;
    const parsed = JSON.parse(raw) as Record<string, unknown>;
    return migrate(parsed);
  } catch {
    return null;
  }
}

export function saveState(state: PersistedState): void {
  try {
    localStorage.setItem(KEY, JSON.stringify({ ...state, uiOverlay: 'none' }));
    saveThemePreference(state.isDarkMode);
  } catch {
    // ignore quota errors
  }
}

export function clearState(): void {
  localStorage.removeItem(KEY);
  localStorage.removeItem(LEGACY_KEY);
}

export function createInitialPersisted(): PersistedState {
  const themePref = loadThemePreference();
  const isDarkMode =
    themePref ?? window.matchMedia?.('(prefers-color-scheme: dark)').matches ?? true;

  return {
    version: 2,
    phase: 'welcome',
    navTab: 'home',
    proSection: 'settings',
    uiOverlay: 'none',
    isDarkMode,
    isPremium: false,
    answers: defaultAnswers(),
    protocol: null,
    xpPoints: 0,
    streakDays: 0,
    lastCompletedDate: null,
    dayHistory: [],
    weeklyHistory: [],
    facialReport: null,
    remindersEnabled: true,
    reminderMorning: '08:00',
    reminderEvening: '21:30',
    reminderScan: true,
    demoMode: false,
  };
}
