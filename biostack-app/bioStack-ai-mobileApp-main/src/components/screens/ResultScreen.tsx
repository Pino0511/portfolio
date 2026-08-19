import { Button } from '../ui/Button';
import { Card } from '../ui/Card';
import { ProgressRing } from '../ui/ProgressRing';
import { Screen } from '../ui/Screen';
import { useApp } from '../../context/AppContext';

export function ResultScreen() {
  const { state, enterApp, setNavTab } = useApp();
  const p = state.protocol;
  if (!p) return null;

  return (
    <Screen>
      <div className="flex-1 overflow-y-auto px-5 pb-8 pt-12 animate-fade-in">
        <p className="text-xs font-semibold uppercase tracking-[0.2em] text-forest-700 dark:text-moss-400">
          Protocollo pronto
        </p>
        <h1 className="mt-2 text-3xl font-semibold tracking-tight">
          {state.answers.name ? `${state.answers.name}, ecco da dove partire` : 'Ecco da dove partire'}
        </h1>
        <p className="mt-2 text-sm text-ink-500 dark:text-stone-400">{p.whySummary}</p>

        <div className="mt-8 flex justify-center">
          <ProgressRing value={p.score} label={`${p.score}`} sublabel="Protocol score" size={140} />
        </div>

        <h2 className="mt-8 text-sm font-semibold uppercase tracking-wide text-ink-400">3 priorità</h2>
        <div className="mt-3 space-y-3">
          {p.priorities.map((pr, i) => (
            <Card key={pr.title}>
              <p className="text-xs font-medium text-forest-700 dark:text-moss-400">0{i + 1}</p>
              <p className="mt-1 font-semibold">{pr.title}</p>
              <p className="mt-1 text-sm text-ink-500 dark:text-stone-400">{pr.detail}</p>
            </Card>
          ))}
        </div>

        <h2 className="mt-8 text-sm font-semibold uppercase tracking-wide text-ink-400">
          Routine del giorno
        </h2>
        <div className="mt-3 space-y-2">
          {(['morning', 'afternoon', 'evening'] as const).map((slot) => {
            const items = p.tasks.filter((t) => t.slot === slot);
            if (!items.length) return null;
            const label = slot === 'morning' ? 'Mattina' : slot === 'afternoon' ? 'Pomeriggio' : 'Sera';
            return (
              <Card key={slot} padding="sm">
                <p className="px-1 text-xs font-semibold uppercase tracking-wide text-ink-400">{label}</p>
                <ul className="mt-1 space-y-1">
                  {items.map((t) => (
                    <li key={t.id} className="rounded-xl px-2 py-2 text-sm">
                      <span className="font-medium">{t.title}</span>
                      <span className="ml-2 text-ink-400">{t.durationMin} min</span>
                    </li>
                  ))}
                </ul>
              </Card>
            );
          })}
        </div>

        <h2 className="mt-8 text-sm font-semibold uppercase tracking-wide text-ink-400">
          Stack consigliato
        </h2>
        <div className="mt-3 space-y-2">
          {p.stack.slice(0, 4).map((s) => (
            <Card key={s.id} padding="sm">
              <div className="flex items-start justify-between gap-3 px-1 py-1">
                <div>
                  <p className="font-medium">{s.name}</p>
                  <p className="text-xs text-ink-500 dark:text-stone-400">
                    {s.brand} · {s.timing}
                  </p>
                </div>
                <p className="text-sm font-semibold tabular-nums">€{s.price.toFixed(0)}</p>
              </div>
            </Card>
          ))}
        </div>

        <Card className="mt-6">
          <p className="text-sm font-semibold">Perché te lo consigliamo</p>
          <ul className="mt-2 space-y-2 text-sm text-ink-600 dark:text-stone-300">
            {p.habits.map((h) => (
              <li key={h} className="flex gap-2">
                <span className="mt-1.5 h-1.5 w-1.5 shrink-0 rounded-full bg-forest-600 dark:bg-moss-400" />
                {h}
              </li>
            ))}
          </ul>
        </Card>
      </div>

      <div className="safe-bottom sticky bottom-0 space-y-2 border-t border-stone-200/70 bg-canvas px-5 py-4 backdrop-blur shadow-sm dark:border-ink-800 dark:bg-ink-950/95 dark:rounded-t-3xl dark:shadow-2xl dark:shadow-black/45">
        <Button
          fullWidth
          onClick={() => {
            enterApp();
            setNavTab('home');
          }}
        >
          Inizia oggi
        </Button>
        <Button
          fullWidth
          variant="secondary"
          onClick={() => {
            enterApp();
            setNavTab('stack');
          }}
        >
          Vedi lo stack completo
        </Button>
      </div>
    </Screen>
  );
}
