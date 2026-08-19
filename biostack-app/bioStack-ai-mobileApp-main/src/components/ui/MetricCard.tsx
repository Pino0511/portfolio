interface MetricCardProps {
  label: string;
  value: number | string;
  hint?: string;
  tone?: 'default' | 'good' | 'warn';
}

export function MetricCard({ label, value, hint, tone = 'default' }: MetricCardProps) {
  const toneCls =
    tone === 'good'
      ? 'text-forest-700 dark:text-moss-400'
      : tone === 'warn'
        ? 'text-amber-700 dark:text-amber-400'
        : 'text-stone-900 dark:text-stone-100';

  return (
    <div className="rounded-2xl border border-stone-200 bg-white p-3.5 dark:border-stone-800 dark:bg-stone-900">
      <p className="text-[11px] font-medium uppercase tracking-wide text-stone-500 dark:text-stone-400">{label}</p>
      <p className={`mt-1 text-xl font-semibold tabular-nums ${toneCls}`}>{value}</p>
      {hint && <p className="mt-1 text-xs text-stone-500 dark:text-stone-400">{hint}</p>}
    </div>
  );
}
