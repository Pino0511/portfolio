import { Loader2 } from 'lucide-react';
import { Screen } from '../ui/Screen';
import { useApp } from '../../context/AppContext';

export function GeneratingScreen() {
  const { state } = useApp();
  const name = state.answers.name || 'il tuo';

  return (
    <Screen className="items-center justify-center px-8 text-center">
      <div className="relative">
        <div className="absolute inset-0 animate-pulse-soft rounded-full bg-forest-500/20 blur-2xl" />
        <Loader2 className="relative h-12 w-12 animate-spin text-forest-700 dark:text-moss-400" />
      </div>
      <h2 className="mt-8 text-2xl font-semibold tracking-tight">
        Stiamo componendo {name === 'il tuo' ? 'il tuo protocollo' : `il protocollo di ${name}`}
      </h2>
      <p className="mt-3 text-sm leading-relaxed text-ink-500 dark:text-stone-400">
        Priorità, routine giornaliera e stack allineati a obiettivo, sonno e stress.
      </p>
      <div className="mt-8 w-full max-w-xs space-y-2 text-left text-sm text-ink-600 dark:text-stone-300">
        {['Analisi segnali base', 'Selezione priorità', 'Calibrazione stack'].map((t, i) => (
          <div key={t} className="flex items-center gap-2 rounded-xl bg-white/70 px-3 py-2 dark:bg-ink-900/70">
            <span className="h-1.5 w-1.5 rounded-full bg-forest-600 dark:bg-moss-400 animate-pulse" style={{ animationDelay: `${i * 200}ms` }} />
            {t}
          </div>
        ))}
      </div>
    </Screen>
  );
}
