import type { ReactNode } from 'react';

interface CardProps {
  children: ReactNode;
  className?: string;
  padding?: 'none' | 'sm' | 'md' | 'lg';
  onClick?: () => void;
  selected?: boolean;
}

const pads = {
  none: '',
  sm: 'p-3',
  md: 'p-4',
  lg: 'p-5',
};

export function Card({ children, className = '', padding = 'md', onClick, selected }: CardProps) {
  const interactive = onClick
    ? 'cursor-pointer active:scale-[0.99] transition-transform'
    : '';
  const selectedCls = selected
    ? 'ring-2 ring-forest-600 border-forest-600 dark:ring-moss-400 dark:border-moss-400'
    : 'border-stone-200 dark:border-stone-800';

  return (
    <div
      role={onClick ? 'button' : undefined}
      tabIndex={onClick ? 0 : undefined}
      onClick={onClick}
      onKeyDown={
        onClick
          ? (e) => {
              if (e.key === 'Enter' || e.key === ' ') {
                e.preventDefault();
                onClick();
              }
            }
          : undefined
      }
      className={`rounded-3xl border border-stone-200/80 bg-white/95 text-stone-900 shadow-soft dark:border-stone-800 dark:bg-ink-950 dark:text-stone-100 ${pads[padding]} ${selectedCls} ${interactive} ${className}`}
    >
      {children}
    </div>
  );
}
