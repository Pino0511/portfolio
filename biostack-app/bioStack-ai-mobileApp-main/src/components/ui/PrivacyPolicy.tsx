import { Modal } from './Modal';
import type { Dispatch, SetStateAction } from 'react';
import { t } from '../../i18n';

export function PrivacyPolicy({ open, onClose }: { open: boolean; onClose: () => void }) {
  // Load full privacy text from project root if available (fallback to inline summary)
  const fallback = (
    <div className="space-y-4 text-sm text-stone-700 dark:text-stone-300">
      <p>
        BioStack è un'app educativa che fornisce suggerimenti basati su evidenze. Le immagini caricate per
        l’analisi rimangono sul tuo dispositivo o in uno storage protetto solo se acconsenti. Non condividiamo
        dati personali con terze parti senza il tuo esplicito consenso.
      </p>
      <section>
        <h3 className="font-semibold">Cosa raccogliamo</h3>
        <ul className="mt-2 list-disc pl-5 text-sm">
          <li>Dati anonimi di utilizzo (errori e metriche performance)</li>
          <li>Immagini caricate per analisi se acconsentite</li>
          <li>Dati di onboarding (nome, età, preferenze) per personalizzare protocolli</li>
        </ul>
      </section>
      <section>
        <h3 className="font-semibold">Diritti</h3>
        <p className="mt-1 text-sm">Puoi chiedere esportazione o cancellazione dei tuoi dati contattandoci dall'app.</p>
      </section>
      <p className="text-xs text-stone-500">Questa è una bozza: adattare il testo con consulenza legale prima della pubblicazione.</p>
    </div>
  );

  return (
    <Modal open={open} title={t('privacy_title')} onClose={onClose}>
      {fallback}
      <div className="mt-4 text-xs text-stone-500">
        <p>Full policy file included in the project at <strong>PRIVACY_POLICY.md</strong>. Replace placeholders before publishing.</p>
      </div>
    </Modal>
  );
}
