import type { ButtonHTMLAttributes, ReactNode } from 'react';

type Variant = 'primary' | 'secondary' | 'ghost' | 'danger';
type Size = 'md' | 'lg' | 'sm';

interface Props extends ButtonHTMLAttributes<HTMLButtonElement> {
  variant?: Variant;
  size?: Size;
  fullWidth?: boolean;
  children: ReactNode;
}

const variants: Record<Variant, string> = {
  primary:
    'bg-forest-700 text-white hover:bg-forest-800 active:scale-[0.98] shadow-soft dark:bg-moss-400 dark:text-ink-950 dark:hover:bg-moss-300',
  secondary:
    'bg-stone-100 text-ink-900 border border-stone-200 hover:bg-stone-200 active:scale-[0.98] dark:bg-ink-800 dark:text-stone-100 dark:border-ink-700 dark:hover:bg-ink-700',
  ghost: 'bg-transparent text-ink-700 hover:bg-stone-100 dark:text-stone-200 dark:hover:bg-black dark:hover:text-white ghost-button-dark-hover',
  danger: 'bg-rose-600 text-white hover:bg-rose-700 active:scale-[0.98]',
};

const sizes: Record<Size, string> = {
  sm: 'h-10 px-4 text-sm rounded-xl',
  md: 'h-12 px-5 text-[15px] rounded-2xl',
  lg: 'h-14 px-6 text-base rounded-2xl font-semibold',
};

export function Button({
  variant = 'primary',
  size = 'lg',
  fullWidth,
  className = '',
  children,
  disabled,
  ...rest
}: Props) {
  return (
    <button
      className={`inline-flex items-center justify-center gap-2 font-medium transition-all duration-200 disabled:opacity-45 disabled:pointer-events-none ${variants[variant]} ${sizes[size]} ${fullWidth ? 'w-full' : ''} ${className}`}
      disabled={disabled}
      {...rest}
    >
      {children}
    </button>
  );
}
