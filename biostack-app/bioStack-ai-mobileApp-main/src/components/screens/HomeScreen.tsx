import { Bell, Flame, Sparkles, ChevronRight, User } from 'lucide-react';
import { Screen, ScreenHeader } from '../ui/Screen';
import { ProgressRing } from '../ui/ProgressRing';
import { MetricCard } from '../ui/MetricCard';
import { Card } from '../ui/Card';
import { Button } from '../ui/Button';
import { useApp } from '../../context/AppContext';
import { SkeletonScreen } from '../ui/Skeleton';

export function HomeScreen() {
  const { state, adherence, levelInfo, setNavTab, toggleTask, setOverlay, openProfile, setProSection } =
    useApp();
  const p = state.protocol;
  const name = state.answers.name || 'atleta';
  const nextTasks = p?.tasks.filter((t) => !t.completed).slice(0, 3) ?? [];
  const coachTip =
    adherence < 40
      ? 'Parti da una sola task: consistenza batte intensità.'
      : adherence < 80
        ? 'Buon ritmo. Chiudi il blocco pomeridiano per spingere lo streak.'
        : 'Protocollo quasi completo. Proteggi il wind-down serale.';

  return (
    <Screen withBottomNav>
      <ScreenHeader
        title={`Ciao, ${name}`}
        subtitle="Il tuo giorno di protocollo"
        right={
          <div className="flex items-center gap-2">
            <button
              type="button"
              onClick={() => setOverlay('notifications')}
              className="flex h-11 w-11 items-center justify-center rounded-2xl bg-stone-100 dark:bg-stone-900"
              aria-label="Promemoria e notifiche"
            >
              <Bell className="h-5 w-5" />
            </button>
            <button
              type="button"
              onClick={openProfile}
              className="flex h-11 w-11 items-center justify-center rounded-2xl bg-stone-100 dark:bg-stone-900"
              aria-label="Profilo e impostazioni"
            >
              <User className="h-5 w-5" />
            </button>
          </div>
        }
      />

      <div className="space-y-5 px-5 py-5 animate-fade-in">
        {!p ? (
          <SkeletonScreen />
        ) : (
          <>
        <Card className="flex items-center gap-5">
          <ProgressRing value={adherence} sublabel="oggi" size={118} />
          <div className="min-w-0 flex-1">
            <div className="flex items-center gap-2 text-amber-600 dark:text-amber-400">
              <Flame className="h-4 w-4" />
              <span className="text-sm font-semibold">{state.streakDays} giorni streak</span>
            </div>
            <p className="mt-2 text-sm text-stone-600 dark:text-stone-300">
              Livello {levelInfo.level} · {levelInfo.name}
            </p>
            <p className="mt-1 text-xs text-stone-500">{state.xpPoints} XP</p>
            <Button className="mt-3" size="sm" onClick={() => setNavTab('protocol')}>
              Apri protocollo
            </Button>
          </div>
        </Card>

        <div className="grid grid-cols-2 gap-3">
          <MetricCard label="Energia" value={p?.metrics.energy ?? '—'} hint="stimata" />
          <MetricCard label="Recupero" value={p?.metrics.recovery ?? '—'} hint="sonno + stress" />
          <MetricCard label="Focus" value={p?.metrics.focus ?? '—'} />
          <MetricCard label="Glow" value={p?.metrics.glow ?? '—'} tone="good" />
        </div>

        <Card className="border-0 bg-gradient-to-br from-forest-800 to-forest-900 text-stone-50 dark:from-stone-900 dark:to-stone-950">
          <div className="flex items-start gap-3">
            <div className="flex h-10 w-10 items-center justify-center rounded-xl bg-white/10">
              <Sparkles className="h-5 w-5 text-moss-300" />
            </div>
            <div className="flex-1">
              <p className="text-xs font-semibold uppercase tracking-wide text-moss-300">AI Coach</p>
              <p className="mt-1 text-sm leading-relaxed text-stone-100">{coachTip}</p>
              <button
                type="button"
                onClick={() => setNavTab('protocol')}
                className="mt-3 inline-flex items-center gap-1 text-sm font-medium text-moss-300"
              >
                Vai alle task <ChevronRight className="h-4 w-4" />
              </button>
            </div>
          </div>
        </Card>

        <div>
          <div className="mb-3 flex items-center justify-between">
            <h2 className="text-sm font-semibold uppercase tracking-wide text-stone-500">Task del giorno</h2>
            <button
              type="button"
              onClick={() => setNavTab('protocol')}
              className="text-sm text-forest-700 dark:text-moss-400"
            >
              Tutte
            </button>
          </div>
          <div className="space-y-2">
            {nextTasks.length === 0 ? (
              <Card>
                <p className="font-semibold">Giorno completato</p>
                <p className="mt-1 text-sm text-stone-500">Ottimo lavoro. Domani si riparte.</p>
              </Card>
            ) : (
              nextTasks.map((t) => (
                <Card key={t.id} padding="sm" onClick={() => toggleTask(t.id)}>
                  <div className="flex items-center gap-3 px-1 py-1">
                    <span className="flex h-6 w-6 items-center justify-center rounded-full border-2 border-stone-300 dark:border-stone-600" />
                    <div className="min-w-0 flex-1">
                      <p className="truncate font-medium">{t.title}</p>
                      <p className="text-xs text-stone-500">
                        {t.durationMin} min · +{t.xp} XP
                      </p>
                    </div>
                  </div>
                </Card>
              ))
            )}
          </div>
        </div>

          {state.remindersEnabled && (
          <Card padding="sm" onClick={() => setOverlay('notifications')}>
            <div className="flex items-center gap-3 px-1 py-1">
              <Bell className="h-4 w-4 text-forest-700 dark:text-moss-400" />
              <div>
                <p className="text-sm font-medium">Reminder attivo</p>
                <p className="text-xs text-stone-500">
                  Mattina {state.reminderMorning} · Sera {state.reminderEvening}
                </p>
              </div>
            </div>
          </Card>
          )}

          <button
          type="button"
          onClick={() => {
            setProSection('scan');
            setNavTab('pro');
          }}
          className="flex w-full items-center justify-between rounded-3xl border border-amber-500/30 bg-amber-50 px-4 py-4 text-left dark:bg-amber-950/30"
        >
          <div>
            <p className="text-sm font-semibold text-amber-900 dark:text-amber-200">BioStack Pro</p>
            <p className="text-xs text-amber-800/80 dark:text-amber-200/70">Scan facciale multi-angolo</p>
          </div>
          <ChevronRight className="h-5 w-5 text-amber-700 dark:text-amber-300" />
          </button>
        </>
        )}
      </div>
    </Screen>
  );
}
