import type { ReactNode } from 'react';

interface ScreenProps {
  children: ReactNode;
  className?: string;
  withBottomNav?: boolean;
}

export function Screen({ children, className = '', withBottomNav }: ScreenProps) {
  return (
    <div
      className={`mx-auto flex min-h-[100dvh] w-full max-w-md flex-col bg-canvas text-stone-900 dark:bg-stone-950 dark:text-stone-100 ${withBottomNav ? 'pb-24' : ''} ${className}`}
    >
      {children}
    </div>
  );
}

export function ScreenHeader({
  title,
  subtitle,
  right,
}: {
  title: string;
  subtitle?: string;
  right?: ReactNode;
}) {
  return (
    <header className="safe-top sticky top-0 z-20 border-b border-stone-200 bg-canvas px-5 pb-3 pt-3 backdrop-blur-md shadow-sm text-stone-900 dark:text-stone-100 dark:border-stone-800 dark:bg-stone-900/95 dark:shadow-2xl dark:shadow-black/55">
      <div className="flex items-start justify-between gap-3">
        <div>
          <h1 className="text-[22px] font-semibold tracking-tight">{title}</h1>
          {subtitle && <p className="mt-0.5 text-sm text-ink-500 dark:text-stone-400">{subtitle}</p>}
        </div>
        {right}
      </div>
    </header>
  );
}
