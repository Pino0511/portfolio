import { Bell, Scan, Sunrise, Moon } from 'lucide-react';
import { Modal } from '../ui/Modal';
import { useApp } from '../../context/AppContext';

export function NotificationsDrawer() {
  const { state, setOverlay, setReminders, updateReminderPrefs } = useApp();
  const open = state.uiOverlay === 'notifications';

  return (
    <Modal open={open} title="Promemoria e Notifiche" onClose={() => setOverlay('none')}>
      <p className="text-sm text-stone-600 dark:text-stone-400">
        Reminder simulati sul dispositivo. Nessuna push server: restano in locale.
      </p>

      <div className="mt-5 space-y-3">
        <label className="flex items-center justify-between rounded-2xl border border-stone-200 bg-stone-50 px-4 py-3 dark:border-stone-800 dark:bg-stone-950">
          <span className="flex items-center gap-3 text-sm font-medium text-stone-900 dark:text-stone-100">
            <Bell className="h-4 w-4 text-forest-700 dark:text-moss-400" />
            Promemoria attivi
          </span>
          <input
            type="checkbox"
            checked={state.remindersEnabled}
            onChange={(e) => setReminders(e.target.checked)}
            className="h-5 w-5 accent-forest-700"
          />
        </label>

        <div className="rounded-2xl border border-stone-200 px-4 py-3 dark:border-stone-800">
          <div className="flex items-center gap-2 text-sm font-medium text-stone-900 dark:text-stone-100">
            <Sunrise className="h-4 w-4" /> Sveglia routine mattina
          </div>
          <input
            type="time"
            value={state.reminderMorning}
            disabled={!state.remindersEnabled}
            onChange={(e) => updateReminderPrefs({ reminderMorning: e.target.value })}
            className="mt-2 h-11 w-full rounded-xl border border-stone-200 bg-white px-3 text-sm dark:border-stone-700 dark:bg-stone-900 dark:text-stone-100"
          />
        </div>

        <div className="rounded-2xl border border-stone-200 px-4 py-3 dark:border-stone-800">
          <div className="flex items-center gap-2 text-sm font-medium text-stone-900 dark:text-stone-100">
            <Moon className="h-4 w-4" /> Reminder routine sera
          </div>
          <input
            type="time"
            value={state.reminderEvening}
            disabled={!state.remindersEnabled}
            onChange={(e) => updateReminderPrefs({ reminderEvening: e.target.value })}
            className="mt-2 h-11 w-full rounded-xl border border-stone-200 bg-white px-3 text-sm dark:border-stone-700 dark:bg-stone-900 dark:text-stone-100"
          />
        </div>

        <label className="flex items-center justify-between rounded-2xl border border-stone-200 px-4 py-3 dark:border-stone-800">
          <span className="flex items-center gap-3 text-sm font-medium text-stone-900 dark:text-stone-100">
            <Scan className="h-4 w-4" /> Reminder scan settimanale
          </span>
          <input
            type="checkbox"
            checked={state.reminderScan}
            disabled={!state.remindersEnabled}
            onChange={(e) => updateReminderPrefs({ reminderScan: e.target.checked })}
            className="h-5 w-5 accent-forest-700"
          />
        </label>
      </div>

      <p className="mt-4 text-xs text-stone-500 dark:text-stone-500">
        Prossimo ping simulato: mattina {state.reminderMorning} · sera {state.reminderEvening}
        {state.reminderScan ? ' · scan domenica 10:00' : ''}.
      </p>
    </Modal>
  );
}
