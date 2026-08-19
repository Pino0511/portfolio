import { useRef, useState } from 'react';
import { Camera, TrendingUp, Sparkles } from 'lucide-react';
import { Screen, ScreenHeader } from '../ui/Screen';
import { Card } from '../ui/Card';
import { MetricCard } from '../ui/MetricCard';
import { EmptyState } from '../ui/EmptyState';
import { useApp } from '../../context/AppContext';

export function InsightsScreen() {
  const { state, adherence, insightSeries, setNavTab } = useApp();
  const history = insightSeries;
  const hasRealData = state.dayHistory.some((d) => d.completedCount > 0);
  const [before, setBefore] = useState<string | null>(null);
  const [after, setAfter] = useState<string | null>(null);
  const [slider, setSlider] = useState(50);
  const beforeRef = useRef<HTMLInputElement>(null);
  const afterRef = useRef<HTMLInputElement>(null);

  if (!hasRealData || history.length === 0) {
    return (
      <Screen withBottomNav>
        <ScreenHeader title="Insights" subtitle="I tuoi dati reali, giorno per giorno" />
        <EmptyState
          icon={<TrendingUp className="h-6 w-6" />}
          title="Ancora tutto a zero — ed è ok"
          description="Completa le checklist del protocollo: aderenza, streak e grafici nasceranno dai tuoi giorni reali, non da mock."
          actionLabel="Vai al protocollo"
          onAction={() => setNavTab('protocol')}
        />
        <div className="px-5 pb-8">
          <Card>
            <div className="flex gap-3">
              <Sparkles className="h-5 w-5 text-forest-700 dark:text-moss-400" />
              <div>
                <p className="text-sm font-semibold">Stato iniziale</p>
                <p className="mt-1 text-sm text-stone-500 dark:text-stone-400">
                  Aderenza oggi {adherence}% · Streak {state.streakDays} · Giorni tracciati{' '}
                  {state.dayHistory.length}
                </p>
              </div>
            </div>
          </Card>
        </div>
      </Screen>
    );
  }

  const last = history[history.length - 1];
  const first = history[0];
  const delta = last.score - first.score;
  const maxScore = Math.max(...history.map((h) => h.score), 100);
  const avgAdherence = Math.round(
    state.dayHistory.reduce((s, d) => s + d.adherence, 0) / state.dayHistory.length,
  );

  const points = history
    .map((h, i) => {
      const x = (i / Math.max(history.length - 1, 1)) * 280 + 10;
      const y = 110 - (h.score / maxScore) * 90;
      return `${x},${y}`;
    })
    .join(' ');

  const area = `10,110 ${points} ${280 + 10},110`;

  return (
    <Screen withBottomNav>
      <ScreenHeader
        title="Insights"
        subtitle={`${state.dayHistory.length} giorni reali · streak ${state.streakDays}`}
      />
      <div className="space-y-5 px-5 py-5 animate-fade-in">
        <div className="grid grid-cols-2 gap-3">
          <MetricCard
            label="Glow score"
            value={last.score}
            hint={delta >= 0 ? `+${delta} vs start` : `${delta}`}
            tone="good"
          />
          <MetricCard label="Aderenza oggi" value={`${adherence}%`} />
          <MetricCard label="Media storica" value={`${avgAdherence}%`} />
          <MetricCard label="Recupero" value={last.recovery} />
        </div>

        <Card>
          <div className="mb-3 flex items-center justify-between">
            <h3 className="font-semibold">Andamento (dai tuoi check)</h3>
            <span className="text-xs text-stone-500">{history.length} punti</span>
          </div>
          <svg viewBox="0 0 300 120" className="h-36 w-full">
            <defs>
              <linearGradient id="glowArea" x1="0" y1="0" x2="0" y2="1">
                <stop offset="0%" stopColor="#2D5A45" stopOpacity="0.35" />
                <stop offset="100%" stopColor="#2D5A45" stopOpacity="0" />
              </linearGradient>
            </defs>
            <polygon points={area} fill="url(#glowArea)" />
            <polyline
              points={points}
              fill="none"
              stroke="#2D5A45"
              strokeWidth="2.5"
              strokeLinecap="round"
              strokeLinejoin="round"
              className="dark:stroke-moss-400"
            />
            {history.map((h, i) => {
              const x = (i / Math.max(history.length - 1, 1)) * 280 + 10;
              const y = 110 - (h.score / maxScore) * 90;
              return (
                <circle
                  key={`${h.date}-${i}`}
                  cx={x}
                  cy={y}
                  r="3.5"
                  className="fill-forest-700 dark:fill-moss-400"
                />
              );
            })}
          </svg>
        </Card>

        <Card>
          <h3 className="font-semibold">Aderenza per giorno</h3>
          <div className="mt-4 space-y-3">
            {[...state.dayHistory]
              .sort((a, b) => b.date.localeCompare(a.date))
              .slice(0, 7)
              .map((d) => (
                <div key={d.date}>
                  <div className="mb-1 flex justify-between text-xs">
                    <span>{d.date}</span>
                    <span className="tabular-nums">
                      {d.completedCount}/{d.totalCount} · {d.adherence}%
                    </span>
                  </div>
                  <div className="h-2 overflow-hidden rounded-full bg-stone-200 dark:bg-stone-800">
                    <div
                      className="h-full rounded-full bg-forest-600/80 dark:bg-moss-400/80"
                      style={{ width: `${d.adherence}%` }}
                    />
                  </div>
                </div>
              ))}
          </div>
        </Card>

        <Card>
          <p className="text-sm font-semibold">Insight della settimana</p>
          <p className="mt-2 text-sm leading-relaxed text-stone-600 dark:text-stone-300">
            {avgAdherence >= 70
              ? 'La tua aderenza reale è solida: il glow score segue la consistenza, non i picchi.'
              : 'Il collo di bottiglia è l’aderenza reale. Chiudi poche task chiave prima di aggiungere stack.'}
          </p>
        </Card>

        <Card>
          <div className="mb-3 flex items-center justify-between">
            <h3 className="font-semibold">Prima / Dopo</h3>
            <Camera className="h-4 w-4 text-stone-400" />
          </div>
          {!before || !after ? (
            <div className="grid grid-cols-2 gap-3">
              <button
                type="button"
                onClick={() => beforeRef.current?.click()}
                className="flex h-28 flex-col items-center justify-center rounded-2xl border border-dashed border-stone-300 text-sm dark:border-stone-600"
              >
                Carica prima
              </button>
              <button
                type="button"
                onClick={() => afterRef.current?.click()}
                className="flex h-28 flex-col items-center justify-center rounded-2xl border border-dashed border-stone-300 text-sm dark:border-stone-600"
              >
                Carica dopo
              </button>
            </div>
          ) : (
            <div className="relative h-56 overflow-hidden rounded-2xl bg-stone-900">
              <img src={after} alt="Dopo" className="absolute inset-0 h-full w-full object-cover" />
              <div className="absolute inset-0 overflow-hidden" style={{ width: `${slider}%` }}>
                <img
                  src={before}
                  alt="Prima"
                  className="absolute left-0 top-0 h-full object-cover"
                  style={{ width: `${(100 / slider) * 100}%`, maxWidth: 'none' }}
                />
              </div>
              <div
                className="pointer-events-none absolute inset-y-0 w-0.5 bg-white/90 shadow"
                style={{ left: `${slider}%` }}
              />
              <input
                type="range"
                min={5}
                max={95}
                value={slider}
                onChange={(e) => setSlider(Number(e.target.value))}
                className="absolute bottom-3 left-4 right-4"
                aria-label="Slider prima dopo"
              />
            </div>
          )}
          <input
            ref={beforeRef}
            type="file"
            accept="image/*"
            className="hidden"
            onChange={(e) => {
              const f = e.target.files?.[0];
              if (!f) return;
              const r = new FileReader();
              r.onload = () => setBefore(r.result as string);
              r.readAsDataURL(f);
            }}
          />
          <input
            ref={afterRef}
            type="file"
            accept="image/*"
            className="hidden"
            onChange={(e) => {
              const f = e.target.files?.[0];
              if (!f) return;
              const r = new FileReader();
              r.onload = () => setAfter(r.result as string);
              r.readAsDataURL(f);
            }}
          />
        </Card>
      </div>
    </Screen>
  );
}
