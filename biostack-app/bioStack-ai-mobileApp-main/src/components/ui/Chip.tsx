import type { ReactNode } from 'react';

interface ChipProps {
  children: ReactNode;
  active?: boolean;
  onClick?: () => void;
}

export function Chip({ children, active, onClick }: ChipProps) {
  return (
    <button
      type="button"
      onClick={onClick}
      className={`shrink-0 rounded-full px-3.5 py-2 text-sm font-medium transition-colors min-h-10 ${
        active
          ? 'bg-forest-700 text-white dark:bg-moss-400 dark:text-ink-950'
          : 'bg-stone-100 text-ink-700 dark:bg-ink-800 dark:text-stone-200'
      }`}
    >
      {children}
    </button>
  );
}
