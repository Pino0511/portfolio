import type { ReactNode } from 'react';
import { Button } from './Button';

interface EmptyStateProps {
  icon?: ReactNode;
  title: string;
  description: string;
  actionLabel?: string;
  onAction?: () => void;
}

export function EmptyState({ icon, title, description, actionLabel, onAction }: EmptyStateProps) {
  return (
    <div className="flex flex-col items-center text-center px-6 py-12 animate-fade-in">
      {icon && (
        <div className="mb-4 flex h-14 w-14 items-center justify-center rounded-2xl bg-stone-100 text-forest-700 dark:bg-ink-800 dark:text-moss-400">
          {icon}
        </div>
      )}
      <h3 className="text-lg font-semibold text-ink-900 dark:text-stone-50">{title}</h3>
      <p className="mt-2 max-w-xs text-sm leading-relaxed text-ink-500 dark:text-stone-400">{description}</p>
      {actionLabel && onAction && (
        <div className="mt-6 w-full max-w-xs">
          <Button fullWidth onClick={onAction}>
            {actionLabel}
          </Button>
        </div>
      )}
    </div>
  );
}
