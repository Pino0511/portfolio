import type { ReactNode } from 'react';

interface OptionCardProps {
  title: string;
  description?: string;
  icon?: ReactNode;
  selected?: boolean;
  onClick: () => void;
}

export function OptionCard({ title, description, icon, selected, onClick }: OptionCardProps) {
  return (
    <button
      type="button"
      onClick={onClick}
      className={`flex w-full items-start gap-3 rounded-3xl border p-4 text-left transition-all active:scale-[0.99] min-h-[72px] ${
        selected
          ? 'border-forest-600 bg-forest-50 ring-2 ring-forest-600/30 dark:border-moss-400 dark:bg-ink-800 dark:ring-moss-400/30'
          : 'border-stone-200 bg-white hover:border-stone-300 dark:border-ink-700 dark:bg-ink-900 dark:hover:border-ink-600'
      }`}
    >
      {icon && (
        <span
          className={`mt-0.5 flex h-11 w-11 shrink-0 items-center justify-center rounded-2xl ${
            selected
              ? 'bg-forest-700 text-white dark:bg-moss-400 dark:text-ink-950'
              : 'bg-stone-100 text-ink-600 dark:bg-ink-800 dark:text-stone-300'
          }`}
        >
          {icon}
        </span>
      )}
      <span className="min-w-0 flex-1">
        <span className="block text-[15px] font-semibold text-ink-900 dark:text-stone-50">{title}</span>
        {description && (
          <span className="mt-1 block text-sm leading-snug text-ink-500 dark:text-stone-400">
            {description}
          </span>
        )}
      </span>
      <span
        className={`mt-1 h-5 w-5 shrink-0 rounded-full border-2 ${
          selected
            ? 'border-forest-700 bg-forest-700 dark:border-moss-400 dark:bg-moss-400'
            : 'border-stone-300 dark:border-ink-600'
        }`}
      />
    </button>
  );
}
