import { Home, ListChecks, LineChart, Layers, User } from 'lucide-react';
import type { NavTab } from '../../types';
import { useApp } from '../../context/AppContext';

const tabs: { id: NavTab; label: string; icon: typeof Home }[] = [
  { id: 'home', label: 'Home', icon: Home },
  { id: 'protocol', label: 'Protocol', icon: ListChecks },
  { id: 'insights', label: 'Insights', icon: LineChart },
  { id: 'stack', label: 'Stack', icon: Layers },
  { id: 'pro', label: 'Profilo', icon: User },
];

export function BottomNav() {
  const { state, setNavTab, setProSection } = useApp();

  return (
    <nav className="safe-bottom fixed bottom-0 left-0 right-0 z-40 border-t border-stone-200 bg-white/95 backdrop-blur-lg shadow-sm dark:border-stone-700 dark:bg-stone-950/95 dark:shadow-[0_-14px_28px_rgba(0,0,0,0.28)]">
      <div className="mx-auto flex max-w-md items-stretch justify-between px-2 pb-1 pt-1.5">
        {tabs.map(({ id, label, icon: Icon }) => {
          const active = state.navTab === id;
          return (
            <button
              key={id}
              type="button"
              onClick={() => {
                if (id === 'pro') setProSection('settings');
                setNavTab(id);
              }}
              className={`flex min-h-12 min-w-[64px] flex-1 flex-col items-center justify-center gap-0.5 rounded-xl transition-colors ${
                active ? 'text-forest-700 dark:text-moss-400' : 'text-stone-400 dark:text-stone-500'
              }`}
              aria-current={active ? 'page' : undefined}
            >
              <Icon className={`h-5 w-5 ${active ? 'stroke-[2.25]' : 'stroke-[1.75]'}`} />
              <span className="text-[10px] font-medium tracking-wide">{label}</span>
            </button>
          );
        })}
      </div>
    </nav>
  );
}
