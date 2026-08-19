import { useEffect, useMemo, useState } from 'react';
import { Check, Clock, Pause, Play, CircleHelp } from 'lucide-react';
import { Screen, ScreenHeader } from '../ui/Screen';
import { Chip } from '../ui/Chip';
import { Card } from '../ui/Card';
import { ProgressBar } from '../ui/ProgressBar';
import { EmptyState } from '../ui/EmptyState';
import { TutorialModal } from '../ui/TutorialModal';
import { useApp } from '../../context/AppContext';
import type { RoutineSlot, TutorialKey } from '../../types';

export function ProtocolScreen() {
  const { state, adherence, toggleTask, setNavTab } = useApp();
  const [slot, setSlot] = useState<RoutineSlot>('morning');
  const [activeTimerId, setActiveTimerId] = useState<string | null>(null);
  const [secondsLeft, setSecondsLeft] = useState(0);
  const [tutorialKey, setTutorialKey] = useState<TutorialKey | null>(null);

  const tasks = useMemo(
    () => state.protocol?.tasks.filter((t) => t.slot === slot) ?? [],
    [state.protocol, slot],
  );

  useEffect(() => {
    if (!activeTimerId || secondsLeft <= 0) return;
    const id = window.setInterval(() => {
      setSecondsLeft((s) => {
        if (s <= 1) {
          window.clearInterval(id);
          setActiveTimerId(null);
          return 0;
        }
        return s - 1;
      });
    }, 1000);
    return () => window.clearInterval(id);
  }, [activeTimerId, secondsLeft]);

  const startTimer = (taskId: string, minutes: number) => {
    if (activeTimerId === taskId) {
      setActiveTimerId(null);
      setSecondsLeft(0);
      return;
    }
    setActiveTimerId(taskId);
    setSecondsLeft(minutes * 60);
  };

  const format = (s: number) => {
    const m = Math.floor(s / 60);
    const r = s % 60;
    return `${m}:${r.toString().padStart(2, '0')}`;
  };

  if (!state.protocol) {
    return (
      <Screen withBottomNav>
        <EmptyState
          title="Nessun protocollo"
          description="Completa l’onboarding per generare la tua routine."
          actionLabel="Inizia"
          onAction={() => setNavTab('home')}
        />
      </Screen>
    );
  }

  const slotLabel = { morning: 'Mattina', afternoon: 'Pomeriggio', evening: 'Sera' }[slot];
  const doneInSlot = tasks.filter((t) => t.completed).length;

  return (
    <Screen withBottomNav>
      <ScreenHeader
        title="Daily Protocol"
        subtitle={`${adherence}% completato · streak ${state.streakDays}g`}
      />
      <div className="px-5 pt-4">
        <ProgressBar value={adherence} />
        <div className="mt-4 flex gap-2 overflow-x-auto pb-1">
          <Chip active={slot === 'morning'} onClick={() => setSlot('morning')}>
            Mattina
          </Chip>
          <Chip active={slot === 'afternoon'} onClick={() => setSlot('afternoon')}>
            Pomeriggio
          </Chip>
          <Chip active={slot === 'evening'} onClick={() => setSlot('evening')}>
            Sera
          </Chip>
        </div>
      </div>

      <div className="space-y-3 px-5 py-5 animate-fade-in" key={slot}>
        <p className="text-sm text-stone-500 dark:text-stone-400">
          {slotLabel}: {doneInSlot}/{tasks.length} completate
        </p>

        {tasks.map((t) => {
          const running = activeTimerId === t.id;
          return (
            <Card key={t.id} className={t.completed ? 'opacity-70' : ''}>
              <div className="flex items-start gap-3">
                <button
                  type="button"
                  onClick={() => toggleTask(t.id)}
                  className={`mt-0.5 flex h-8 w-8 shrink-0 items-center justify-center rounded-full border-2 transition-colors ${
                    t.completed
                      ? 'border-forest-700 bg-forest-700 text-white dark:border-moss-400 dark:bg-moss-400 dark:text-stone-950'
                      : 'border-stone-300 dark:border-stone-600'
                  }`}
                  aria-label={t.completed ? 'Segna incompleta' : 'Completa'}
                >
                  {t.completed && <Check className="h-4 w-4" />}
                </button>
                <button
                  type="button"
                  onClick={() => setTutorialKey(t.tutorialKey)}
                  className="mt-0.5 flex h-8 w-8 shrink-0 items-center justify-center rounded-full border border-stone-200 text-forest-700 dark:border-stone-700 dark:text-moss-400"
                  aria-label="Tutorial"
                >
                  <CircleHelp className="h-4 w-4" />
                </button>
                <div className="min-w-0 flex-1">
                  <p className={`font-semibold ${t.completed ? 'line-through' : ''}`}>{t.title}</p>
                  <p className="mt-1 text-sm leading-snug text-stone-500 dark:text-stone-400">{t.reason}</p>
                  <div className="mt-3 flex flex-wrap items-center gap-2">
                    <span className="inline-flex items-center gap-1 rounded-full bg-stone-100 px-2.5 py-1 text-xs dark:bg-stone-800">
                      <Clock className="h-3 w-3" /> {t.durationMin} min
                    </span>
                    <span className="rounded-full bg-stone-100 px-2.5 py-1 text-xs capitalize dark:bg-stone-800">
                      {t.kind}
                    </span>
                    <span className="rounded-full bg-forest-50 px-2.5 py-1 text-xs text-forest-800 dark:bg-stone-800 dark:text-moss-400">
                      +{t.xp} XP
                    </span>
                    <button
                      type="button"
                      onClick={() => setTutorialKey(t.tutorialKey)}
                      className="rounded-full bg-stone-100 px-2.5 py-1 text-xs font-medium text-forest-800 dark:bg-stone-800 dark:text-moss-400"
                    >
                      Guida rapida
                    </button>
                  </div>
                  <div className="mt-3 flex items-center gap-3">
                    <button
                      type="button"
                      onClick={() => startTimer(t.id, t.durationMin)}
                      className="inline-flex min-h-10 items-center gap-2 rounded-xl bg-stone-100 px-3 text-sm font-medium dark:bg-stone-800"
                    >
                      {running ? <Pause className="h-4 w-4" /> : <Play className="h-4 w-4" />}
                      {running ? format(secondsLeft) : 'Avvia timer'}
                    </button>
                    {running && (
                      <span className="animate-pulse-soft text-xs text-forest-700 dark:text-moss-400">
                        In corso…
                      </span>
                    )}
                  </div>
                </div>
              </div>
            </Card>
          );
        })}

        {tasks.length === 0 && (
          <EmptyState
            title={`Nessuna task in ${slotLabel.toLowerCase()}`}
            description="Il tuo protocollo non include attività in questa fascia."
          />
        )}

        <Card className="border-dashed">
          <p className="text-sm font-semibold">Motivazione rapida</p>
          <p className="mt-1 text-sm text-stone-500 dark:text-stone-400">
            Non serve perfezione. Serve ripetizione. Chiudi {Math.max(1, tasks.length - doneInSlot)}{' '}
            task e lo streak resta vivo.
          </p>
        </Card>
      </div>

      <TutorialModal tutorialKey={tutorialKey} onClose={() => setTutorialKey(null)} />
    </Screen>
  );
}
