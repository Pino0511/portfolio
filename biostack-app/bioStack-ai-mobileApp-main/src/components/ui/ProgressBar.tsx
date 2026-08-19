export function ProgressBar({ value, className = '' }: { value: number; className?: string }) {
  return (
    <div className={`h-2 w-full overflow-hidden rounded-full bg-stone-200 dark:bg-ink-700 ${className}`}>
      <div
        className="h-full rounded-full bg-forest-600 dark:bg-moss-400 transition-all duration-500"
        style={{ width: `${Math.min(100, Math.max(0, value))}%` }}
      />
    </div>
  );
}
