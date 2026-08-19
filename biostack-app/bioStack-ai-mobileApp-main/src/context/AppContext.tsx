import {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useMemo,
  useState,
  type ReactNode,
} from 'react';
import { supabase } from '../lib/supabase';
import analytics from '../lib/analytics';
import type {
  AppPhase,
  DayLog,
  FacialReport,
  NavTab,
  OnboardingAnswers,
  PersistedState,
  ProSection,
  ProtocolResult,
  ProtocolTask,
  UiOverlay,
} from '../types';
import { defaultAnswers } from '../types';
import {
  adherencePercent,
  buildDailySeries,
  buildDemoAnswers,
  buildWeeklyHistoryFromDays,
  computeStreakFromHistory,
  generateProtocol,
  getLevelInfo,
  todayKey,
} from '../lib/protocolEngine';
import {
  applyDarkClass,
  clearState,
  createInitialPersisted,
  loadState,
  saveState,
  saveThemePreference,
} from '../lib/storage';

interface AppContextValue {
  state: PersistedState;
  levelInfo: ReturnType<typeof getLevelInfo>;
  adherence: number;
  insightSeries: ReturnType<typeof buildDailySeries>;
  setPhase: (phase: AppPhase) => void;
  setNavTab: (tab: NavTab) => void;
  openProfile: () => void;
  setProSection: (section: ProSection) => void;
  setOverlay: (overlay: UiOverlay) => void;
  setDarkMode: (v: boolean) => void;
  setReminders: (v: boolean) => void;
  updateReminderPrefs: (patch: Partial<Pick<PersistedState, 'reminderMorning' | 'reminderEvening' | 'reminderScan'>>) => void;
  updateAnswers: (patch: Partial<OnboardingAnswers>) => void;
  startDemo: () => void;
  generateAndShowResult: () => void;
  enterApp: () => void;
  toggleTask: (taskId: string) => void;
  setPremium: (v: boolean) => void;
  setFacialReport: (r: FacialReport | null) => void;
  resetAll: () => void;
  patchProtocol: (protocol: ProtocolResult) => void;
}

const AppContext = createContext<AppContextValue | null>(null);

function syncDayLog(
  dayHistory: DayLog[],
  tasks: ProtocolTask[],
  xpDelta: number,
): DayLog[] {
  const today = todayKey();
  const completedTaskIds = tasks.filter((t) => t.completed).map((t) => t.id);
  const adherence = adherencePercent(tasks);
  const existing = dayHistory.find((d) => d.date === today);
  const entry: DayLog = {
    date: today,
    completedCount: completedTaskIds.length,
    totalCount: tasks.length,
    adherence,
    xpEarned: Math.max(0, (existing?.xpEarned ?? 0) + xpDelta),
    completedTaskIds,
  };
  const rest = dayHistory.filter((d) => d.date !== today);
  return [...rest, entry].sort((a, b) => a.date.localeCompare(b.date));
}

export function AppProvider({ children }: { children: ReactNode }) {
  const [state, setState] = useState<PersistedState>(() => {
    const initial = loadState() ?? createInitialPersisted();
    applyDarkClass(initial.isDarkMode);
    return initial;
  });
  const [hydrated, setHydrated] = useState(false);

  useEffect(() => {
    setHydrated(true);
    applyDarkClass(state.isDarkMode);
  }, []);

  useEffect(() => {
    // initialize analytics stub; replace with real init in production
    try {
      analytics.init();
      analytics.identify('anonymous');
    } catch {}
  }, []);

  // Accessibility: ensure focus outline visible for keyboard users
  useEffect(() => {
    function handleFirstTab(e: KeyboardEvent) {
      if (e.key === 'Tab') document.documentElement.classList.add('user-is-tabbing');
      window.removeEventListener('keydown', handleFirstTab);
    }
    window.addEventListener('keydown', handleFirstTab);
    return () => window.removeEventListener('keydown', handleFirstTab);
  }, []);

  // Listen for Supabase auth changes and hydrate user info
  useEffect(() => {
    if (!supabase) return;
    let mounted = true;

    const shouldShowOnboarding = (s: PersistedState) =>
      !s.protocol ||
      !s.answers.goal ||
      !s.answers.name ||
      s.answers.age === null ||
      !s.answers.sex ||
      !s.answers.energy ||
      !s.answers.sleep ||
      !s.answers.stress ||
      !s.answers.training ||
      !s.answers.budget;

    async function initSession() {
      try {
        const {
          data: { session },
        } = await supabase.auth.getSession();
        if (!mounted) return;
        const user = session?.user;
        if (user) {
          const name = (user.user_metadata as any)?.full_name || user.email || '';
          setState((s) => ({
            ...s,
            answers: { ...s.answers, name },
            phase: shouldShowOnboarding(s) ? 'onboarding' : 'app',
          }));
        }
      } catch (e) {
        // ignore
      }
    }

    initSession();

    const { data: listener } = supabase.auth.onAuthStateChange((_event, session) => {
      const user = (session as any)?.user;
      if (user) {
        const name = (user.user_metadata as any)?.full_name || user.email || '';
        setState((s) => ({
          ...s,
          answers: { ...s.answers, name },
          phase: shouldShowOnboarding(s) ? 'onboarding' : 'app',
        }));
      } else {
        setState((s) => ({ ...s, answers: { ...s.answers, name: '' }, phase: 'welcome' }));
      }
    });

    return () => {
      mounted = false;
      // unsubscribe
      try {
        // @ts-ignore
        listener?.subscription?.unsubscribe?.();
      } catch {}
    };
  }, []);

  useEffect(() => {
    if (!hydrated) return;
    saveState(state);
  }, [state, hydrated]);

  useEffect(() => {
    applyDarkClass(state.isDarkMode);
    saveThemePreference(state.isDarkMode);
  }, [state.isDarkMode]);

  const setPhase = useCallback((phase: AppPhase) => {
    setState((s) => ({ ...s, phase }));
  }, []);

  const setNavTab = useCallback((navTab: NavTab) => {
    setState((s) => ({ ...s, navTab }));
  }, []);

  const openProfile = useCallback(() => {
    setState((s) => ({ ...s, navTab: 'pro', proSection: 'settings', uiOverlay: 'none' }));
  }, []);

  const setProSection = useCallback((proSection: ProSection) => {
    setState((s) => ({ ...s, proSection }));
  }, []);

  const setOverlay = useCallback((uiOverlay: UiOverlay) => {
    setState((s) => ({ ...s, uiOverlay }));
  }, []);

  const setDarkMode = useCallback((isDarkMode: boolean) => {
    applyDarkClass(isDarkMode);
    saveThemePreference(isDarkMode);
    setState((s) => ({ ...s, isDarkMode }));
  }, []);

  const setReminders = useCallback((remindersEnabled: boolean) => {
    setState((s) => ({ ...s, remindersEnabled }));
  }, []);

  const updateReminderPrefs = useCallback(
    (patch: Partial<Pick<PersistedState, 'reminderMorning' | 'reminderEvening' | 'reminderScan'>>) => {
      setState((s) => ({ ...s, ...patch }));
    },
    [],
  );

  const updateAnswers = useCallback((patch: Partial<OnboardingAnswers>) => {
    setState((s) => ({ ...s, answers: { ...s.answers, ...patch } }));
  }, []);

  const startDemo = useCallback(() => {
    const answers = buildDemoAnswers();
    const protocol = generateProtocol(answers);
    setState((s) => ({
      ...s,
      answers,
      protocol,
      demoMode: true,
      dayHistory: [],
      weeklyHistory: [],
      phase: 'result',
    }));
  }, []);

  const generateAndShowResult = useCallback(() => {
    setState((s) => {
      const protocol = generateProtocol(s.answers);
      return {
        ...s,
        protocol,
        demoMode: false,
        dayHistory: [],
        weeklyHistory: [],
        phase: 'generating',
      };
    });
    window.setTimeout(() => {
      setState((s) => ({ ...s, phase: 'result' }));
    }, 2200);
  }, []);

  const enterApp = useCallback(() => {
    setState((s) => ({ ...s, phase: 'app', navTab: 'home' }));
  }, []);

  const toggleTask = useCallback((taskId: string) => {
    setState((s) => {
      if (!s.protocol) return s;
      let xpDelta = 0;
      const tasks = s.protocol.tasks.map((t: ProtocolTask) => {
        if (t.id !== taskId) return t;
        const completed = !t.completed;
        xpDelta = completed ? t.xp : -t.xp;
        return { ...t, completed };
      });
      const protocol = { ...s.protocol, tasks };
      const nextXp = Math.max(0, s.xpPoints + xpDelta);
      const dayHistory = syncDayLog(s.dayHistory, tasks, xpDelta);
      const weeklyHistory = buildWeeklyHistoryFromDays(dayHistory, s.protocol.score);
      const streakDays = computeStreakFromHistory(dayHistory);
      const allDone = tasks.length > 0 && tasks.every((t) => t.completed);
      const today = todayKey();
      const lastCompletedDate = allDone ? today : s.lastCompletedDate;

      return {
        ...s,
        protocol,
        xpPoints: nextXp,
        streakDays,
        lastCompletedDate,
        dayHistory,
        weeklyHistory,
      };
    });
  }, []);

  const setPremium = useCallback((isPremium: boolean) => {
    setState((s) => ({ ...s, isPremium }));
  }, []);

  const setFacialReport = useCallback((facialReport: FacialReport | null) => {
    setState((s) => ({ ...s, facialReport }));
  }, []);

  const resetAll = useCallback(() => {
    clearState();
    const fresh = createInitialPersisted();
    fresh.isDarkMode = state.isDarkMode;
    applyDarkClass(fresh.isDarkMode);
    setState(fresh);
  }, [state.isDarkMode]);

  const patchProtocol = useCallback((protocol: ProtocolResult) => {
    setState((s) => ({ ...s, protocol }));
  }, []);

  const adherence = useMemo(
    () => (state.protocol ? adherencePercent(state.protocol.tasks) : 0),
    [state.protocol],
  );

  const insightSeries = useMemo(() => {
    if (state.weeklyHistory.length >= 2) return state.weeklyHistory;
    return buildDailySeries(state.dayHistory, state.protocol?.score ?? 50);
  }, [state.weeklyHistory, state.dayHistory, state.protocol?.score]);

  const levelInfo = useMemo(() => getLevelInfo(state.xpPoints), [state.xpPoints]);

  const value: AppContextValue = {
    state,
    levelInfo,
    adherence,
    insightSeries,
    setPhase,
    setNavTab,
    openProfile,
    setProSection,
    setOverlay,
    setDarkMode,
    setReminders,
    updateReminderPrefs,
    updateAnswers,
    startDemo,
    generateAndShowResult,
    enterApp,
    toggleTask,
    setPremium,
    setFacialReport,
    resetAll,
    patchProtocol,
  };

  return <AppContext.Provider value={value}>{children}</AppContext.Provider>;
}

export function useApp() {
  const ctx = useContext(AppContext);
  if (!ctx) throw new Error('useApp must be used within AppProvider');
  return ctx;
}

export { defaultAnswers };
