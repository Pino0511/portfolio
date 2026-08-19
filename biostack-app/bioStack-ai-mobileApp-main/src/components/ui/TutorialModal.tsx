import type { ReactNode } from 'react';
import { getTutorial } from '../../lib/tutorials';
import type { TutorialKey } from '../../types';
import { Modal } from './Modal';

interface TutorialModalProps {
  tutorialKey: TutorialKey | null;
  onClose: () => void;
}

export function TutorialModal({ tutorialKey, onClose }: TutorialModalProps) {
  const t = tutorialKey ? getTutorial(tutorialKey) : null;

  return (
    <Modal open={!!t} title={t?.title ?? 'Guida Rapida'} onClose={onClose}>
      {t && (
        <div className="space-y-5 text-sm text-stone-700 dark:text-stone-300">
          <section>
            <h3 className="text-xs font-semibold uppercase tracking-wide text-forest-700 dark:text-moss-400">
              Cos’è
            </h3>
            <p className="mt-1.5 leading-relaxed">{t.what}</p>
          </section>
          <section>
            <h3 className="text-xs font-semibold uppercase tracking-wide text-forest-700 dark:text-moss-400">
              Benefici (evidence-informed)
            </h3>
            <ul className="mt-1.5 space-y-1.5">
              {t.benefits.map((b) => (
                <li key={b} className="flex gap-2">
                  <span className="mt-1.5 h-1.5 w-1.5 shrink-0 rounded-full bg-forest-600 dark:bg-moss-400" />
                  {b}
                </li>
              ))}
            </ul>
          </section>
          <section>
            <h3 className="text-xs font-semibold uppercase tracking-wide text-forest-700 dark:text-moss-400">
              Step-by-step
            </h3>
            <ol className="mt-1.5 list-decimal space-y-1.5 pl-4">
              {t.steps.map((s) => (
                <li key={s}>{s}</li>
              ))}
            </ol>
          </section>
          <section className="rounded-2xl border border-amber-500/30 bg-amber-50 p-3 dark:bg-amber-950/30">
            <h3 className="text-xs font-semibold uppercase tracking-wide text-amber-800 dark:text-amber-300">
              Errori comuni
            </h3>
            <ul className="mt-1.5 space-y-1">
              {t.mistakes.map((m) => (
                <li key={m}>· {m}</li>
              ))}
            </ul>
          </section>
          <p className="text-[11px] text-stone-500">
            Contenuto educativo. Non sostituisce parere medico o specialistico.
          </p>
        </div>
      )}
    </Modal>
  );
}

export function TutorialInfoButton({
  onClick,
  children,
}: {
  onClick: () => void;
  children?: ReactNode;
}) {
  return (
    <button
      type="button"
      onClick={(e) => {
        e.stopPropagation();
        onClick();
      }}
      className="inline-flex h-8 w-8 shrink-0 items-center justify-center rounded-full border border-stone-200 text-stone-600 dark:border-stone-700 dark:text-stone-300"
      aria-label="Apri tutorial"
    >
      {children ?? <span className="text-xs font-bold">i</span>}
    </button>
  );
}
