import { useMemo, useState } from 'react';
import { ExternalLink, Info, Search, ShieldAlert } from 'lucide-react';
import { Screen, ScreenHeader } from '../ui/Screen';
import { Chip } from '../ui/Chip';
import { Card } from '../ui/Card';
import { EmptyState } from '../ui/EmptyState';
import { useApp } from '../../context/AppContext';
import type { ProductFilter, StackKind } from '../../types';

const KIND_LABEL: Record<StackKind, string> = {
  habit: 'Abitudine',
  nutrition: 'Nutrizione',
  supplement: 'Integratore',
  recovery: 'Recupero',
  skincare: 'Skincare',
  tool: 'Tool',
};

export function StackScreen() {
  const { state } = useApp();
  const [filter, setFilter] = useState<ProductFilter>('all');
  const [q, setQ] = useState('');
  const stack = state.protocol?.stack ?? [];

  const filtered = useMemo(() => {
    return stack.filter((s) => {
      const okFilter = filter === 'all' || s.kind === filter;
      const okQ =
        !q ||
        s.name.toLowerCase().includes(q.toLowerCase()) ||
        s.brand.toLowerCase().includes(q.toLowerCase());
      return okFilter && okQ;
    });
  }, [stack, filter, q]);

  if (!stack.length) {
    return (
      <Screen withBottomNav>
        <EmptyState
          title="Stack vuoto"
          description="Genera un protocollo per ricevere prodotti e abitudini allineati al budget."
        />
      </Screen>
    );
  }

  return (
    <Screen withBottomNav>
      <ScreenHeader
        title="Il tuo Stack"
        subtitle={`Budget ${state.answers.budget ?? 'medium'} · link affiliati Amazon`}
      />
      <div className="space-y-4 px-5 py-4">
        <div className="relative">
          <Search className="pointer-events-none absolute left-3.5 top-1/2 h-4 w-4 -translate-y-1/2 text-ink-400" />
          <input
            value={q}
            onChange={(e) => setQ(e.target.value)}
            placeholder="Cerca prodotto o brand"
            className="h-12 w-full rounded-2xl border border-stone-200 bg-white pl-10 pr-4 text-sm outline-none focus:border-forest-600 dark:border-ink-700 dark:bg-ink-900"
          />
        </div>
        <div className="flex gap-2 overflow-x-auto pb-1">
          {(
            [
              ['all', 'Tutti'],
              ['skincare', 'Skincare'],
              ['supplement', 'Integratori'],
              ['nutrition', 'Nutrizione'],
              ['tool', 'Tool'],
              ['habit', 'Abitudini'],
              ['recovery', 'Recupero'],
            ] as const
          ).map(([id, label]) => (
            <Chip key={id} active={filter === id} onClick={() => setFilter(id)}>
              {label}
            </Chip>
          ))}
        </div>

        <Card className="border-amber-500/20 bg-amber-50/80 dark:bg-amber-950/20">
          <div className="flex gap-2">
            <ShieldAlert className="mt-0.5 h-4 w-4 shrink-0 text-amber-700 dark:text-amber-400" />
            <p className="text-xs leading-relaxed text-amber-900 dark:text-amber-200/90">
              Gli integratori non sostituiscono una dieta equilibrata né un parere medico. Dove
              indicato, parlane con un professionista.
            </p>
          </div>
        </Card>

        <div className="space-y-3 animate-fade-in">
          {filtered.map((item) => (
            <Card key={item.id}>
              <div className="flex items-start justify-between gap-3">
                <div>
                  <span className="rounded-full bg-stone-100 px-2 py-0.5 text-[10px] font-semibold uppercase tracking-wide text-ink-500 dark:bg-ink-800 dark:text-stone-400">
                    {KIND_LABEL[item.kind]}
                  </span>
                  <h3 className="mt-2 font-semibold">{item.name}</h3>
                  <p className="text-xs text-ink-500 dark:text-stone-400">
                    {item.brand} · {item.timing}
                    {item.dosage ? ` · ${item.dosage}` : ''}
                  </p>
                </div>
                <p className="text-sm font-semibold tabular-nums">€{item.price.toFixed(2)}</p>
              </div>

              <div className="mt-3 rounded-2xl bg-stone-50 p-3 dark:bg-ink-800/80">
                <p className="flex items-center gap-1.5 text-xs font-semibold text-forest-800 dark:text-moss-400">
                  <Info className="h-3.5 w-3.5" /> Perché te lo consigliamo
                </p>
                <p className="mt-1 text-sm leading-snug text-ink-600 dark:text-stone-300">{item.why}</p>
              </div>

              {item.safetyNote && (
                <p className="mt-2 text-xs text-ink-400 dark:text-stone-500">{item.safetyNote}</p>
              )}

              {(item.kind === 'skincare' ||
                item.kind === 'supplement' ||
                item.kind === 'nutrition' ||
                item.kind === 'tool') && (
                <a
                  href={item.amazonUrl}
                  target="_blank"
                  rel="noreferrer"
                  className="mt-4 inline-flex h-11 w-full items-center justify-center gap-2 rounded-2xl bg-ink-900 text-sm font-semibold text-white dark:bg-moss-400 dark:text-ink-950"
                >
                  Vedi su Amazon <ExternalLink className="h-4 w-4" />
                </a>
              )}
            </Card>
          ))}

          {filtered.length === 0 && (
            <EmptyState title="Nessun risultato" description="Prova un altro filtro o termine di ricerca." />
          )}
        </div>
      </div>
    </Screen>
  );
}
