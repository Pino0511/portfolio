import { useMemo, useState } from 'react';
import {
  Sparkles,
  Bone,
  Brain,
  HeartPulse,
  Zap,
  Salad,
  Scale,
  ChevronLeft,
} from 'lucide-react';
import { Button } from '../ui/Button';
import { ProgressBar } from '../ui/ProgressBar';
import { Screen } from '../ui/Screen';
import { OptionCard } from '../onboarding/OptionCard';
import { useApp } from '../../context/AppContext';
import type { PrimaryGoal } from '../../types';
import { t } from '../../i18n';

const TOTAL_STEPS = 6;

export function OnboardingFlow() {
  const { state, updateAnswers, generateAndShowResult, setPhase, startDemo } = useApp();
  const [step, setStep] = useState(1);
  const a = state.answers;

  const progress = (step / TOTAL_STEPS) * 100;

  const canContinue = useMemo(() => {
    if (step === 1) return !!a.goal;
    if (step === 2) return !!a.name && a.age !== null && !!a.sex;
    if (step === 3) return !!a.energy && !!a.sleep;
    if (step === 4) return !!a.stress && !!a.training;
    if (step === 5) return true;
    if (step === 6) return !!a.budget;
    return false;
  }, [step, a]);

  const skip = () => {
    updateAnswers({ skippedSteps: [...a.skippedSteps, step] });
    if (step < TOTAL_STEPS) setStep(step + 1);
    else generateAndShowResult();
  };

  // Quick skip all and use demo answers
  const quickStart = () => {
    startDemo();
  };

  const next = () => {
    if (step < TOTAL_STEPS) setStep(step + 1);
    else generateAndShowResult();
  };

  const back = () => {
    if (step === 1) setPhase('welcome');
    else setStep(step - 1);
  };

  const toggleFocus = (g: PrimaryGoal) => {
    const exists = a.focusAreas.includes(g);
    updateAnswers({
      focusAreas: exists ? a.focusAreas.filter((x) => x !== g) : [...a.focusAreas, g].slice(0, 3),
    });
  };

  const toggleLimit = (id: string) => {
    const exists = a.limitations.includes(id);
    updateAnswers({
      limitations: exists ? a.limitations.filter((x) => x !== id) : [...a.limitations, id],
    });
  };

  return (
    <Screen>
      <div className="safe-top px-5 pt-4">
        <div className="mb-3 flex items-center justify-between">
          <button
            type="button"
            onClick={back}
            className="flex h-11 w-11 items-center justify-center rounded-2xl bg-stone-100 dark:bg-ink-800"
            aria-label="Indietro"
          >
            <ChevronLeft className="h-5 w-5" />
          </button>
          <p className="text-sm font-medium text-ink-500 dark:text-stone-400">{t('onboarding_step').replace('{step}', String(step)).replace('{total}', String(TOTAL_STEPS))}</p>
          <button
            type="button"
            onClick={() => startDemo()}
            className="text-sm font-medium text-forest-700 dark:text-moss-400"
            aria-label={t('start_quick')}
          >
            {t('start_quick')}
          </button>
          {step < TOTAL_STEPS && step !== 1 && step !== 6 ? (
            <button type="button" onClick={skip} className="text-sm font-medium text-forest-700 dark:text-moss-400">
              Salta
            </button>
          ) : (
            <span className="w-11" />
          )}
        </div>
        <ProgressBar value={progress} />
      </div>

      <div className="flex-1 overflow-y-auto px-5 py-6 animate-fade-in" key={step}>
        {step === 1 && (
          <>
            <h2 className="text-2xl font-semibold tracking-tight">{t('onboarding_what_first')}</h2>
            <p className="mt-2 text-sm text-ink-500 dark:text-stone-400">
              Scegli un obiettivo: il protocollo partirà da lì. Potrai aggiungere focus dopo.
            </p>
            <div className="mt-6 space-y-3">
              {(
                [
                  { id: 'skincare', title: 'Skincare & Glow', desc: 'Pelle, texture, anti-aging quotidiano', icon: <Sparkles className="h-5 w-5" /> },
                  { id: 'posture', title: 'Postura & Mewing', desc: 'Allineamento e consapevolezza orale', icon: <Bone className="h-5 w-5" /> },
                  { id: 'focus', title: 'Focus & Performance', desc: 'Chiarezza mentale e deep work', icon: <Brain className="h-5 w-5" /> },
                  { id: 'longevity', title: 'Longevità', desc: 'Recupero, abitudini e salute di lungo periodo', icon: <HeartPulse className="h-5 w-5" /> },
                  { id: 'testosterone', title: 'Vitalità', desc: 'Energia, forza e drive (non terapia)', icon: <Zap className="h-5 w-5" /> },
                  { id: 'digestion', title: 'Digestione', desc: 'Comfort e regolarità quotidiana', icon: <Salad className="h-5 w-5" /> },
                  { id: 'bodycomp', title: 'Composizione corporea', desc: 'Movimento, proteine, consistenza', icon: <Scale className="h-5 w-5" /> },
                ] as const
              ).map((o) => (
                <OptionCard
                  key={o.id}
                  title={o.title}
                  description={o.desc}
                  icon={o.icon}
                  selected={a.goal === o.id}
                  onClick={() => updateAnswers({ goal: o.id })}
                />
              ))}
            </div>
          </>
        )}

        {step === 2 && (
          <>
            <h2 className="text-2xl font-semibold tracking-tight">{t('onboarding_name_title')}</h2>
            <p className="mt-2 text-sm text-ink-500 dark:text-stone-400">
              Nome, età e sesso sono importanti per personalizzare il protocollo. Compilali per un piano migliore.
            </p>
            <div className="mt-6 space-y-4">
              <label className="block">
                <span className="mb-1.5 block text-sm font-medium">Nome</span>
                <input
                  value={a.name}
                  onChange={(e) => updateAnswers({ name: e.target.value })}
                  autoFocus
                  placeholder="Es. Alex"
                  className="h-14 w-full rounded-2xl border border-stone-200 bg-white px-4 text-base outline-none focus:border-forest-600 dark:border-ink-700 dark:bg-ink-900"
                />
              </label>
              <label className="block">
                <span className="mb-1.5 block text-sm font-medium">Età (opzionale)</span>
                <input
                  type="number"
                  min={16}
                  max={90}
                  value={a.age ?? ''}
                  onChange={(e) =>
                    updateAnswers({ age: e.target.value ? Number(e.target.value) : null })
                  }
                  placeholder="Es. 28"
                  className="h-14 w-full rounded-2xl border border-stone-200 bg-white px-4 text-base outline-none focus:border-forest-600 dark:border-ink-700 dark:bg-ink-900"
                />
              </label>
              <div>
                <p className="mb-2 text-sm font-medium">Sesso (opzionale)</p>
                <div className="grid grid-cols-2 gap-2">
                  {(
                    [
                      ['male', 'Uomo'],
                      ['female', 'Donna'],
                      ['other', 'Altro'],
                      ['skip', 'Preferisco non dirlo'],
                    ] as const
                  ).map(([id, label]) => (
                    <button
                      key={id}
                      type="button"
                      onClick={() => updateAnswers({ sex: id })}
                      className={`min-h-12 rounded-2xl border px-3 text-sm font-medium ${
                        a.sex === id
                          ? 'border-forest-600 bg-forest-50 dark:border-moss-400 dark:bg-ink-800'
                          : 'border-stone-200 dark:border-ink-700'
                      }`}
                    >
                      {label}
                    </button>
                  ))}
                </div>
              </div>
            </div>
          </>
        )}

        {step === 3 && (
          <>
            <h2 className="text-2xl font-semibold tracking-tight">Energia e sonno</h2>
            <p className="mt-2 text-sm text-ink-500 dark:text-stone-400">
              Due segnali che guidano recupero e carico del protocollo.
            </p>
            <p className="mt-6 text-sm font-semibold">Come ti senti di energia?</p>
            <div className="mt-3 space-y-2">
              {(
                [
                  ['low', 'Bassa', 'Fatico a partire'],
                  ['medium', 'Media', 'Ok, ma oscillante'],
                  ['high', 'Alta', 'Stabile e presente'],
                ] as const
              ).map(([id, title, desc]) => (
                <OptionCard
                  key={id}
                  title={title}
                  description={desc}
                  selected={a.energy === id}
                  onClick={() => updateAnswers({ energy: id })}
                />
              ))}
            </div>
            <p className="mt-6 text-sm font-semibold">Qualità del sonno</p>
            <div className="mt-3 space-y-2">
              {(
                [
                  ['poor', 'Scarsa', 'Mi sveglio stanco'],
                  ['ok', 'Discreta', 'A volte frammentato'],
                  ['good', 'Buona', 'Mi riposo davvero'],
                ] as const
              ).map(([id, title, desc]) => (
                <OptionCard
                  key={id}
                  title={title}
                  description={desc}
                  selected={a.sleep === id}
                  onClick={() => updateAnswers({ sleep: id })}
                />
              ))}
            </div>
          </>
        )}

        {step === 4 && (
          <>
            <h2 className="text-2xl font-semibold tracking-tight">Stress e movimento</h2>
            <p className="mt-2 text-sm text-ink-500 dark:text-stone-400">
              Così calibramo intensità e finestre di recupero.
            </p>
            <p className="mt-6 text-sm font-semibold">Livello di stress</p>
            <div className="mt-3 space-y-2">
              {(
                [
                  ['high', 'Alto', 'Sempre “on”'],
                  ['medium', 'Medio', 'Gestibile'],
                  ['low', 'Basso', 'Abbastanza calmo'],
                ] as const
              ).map(([id, title, desc]) => (
                <OptionCard
                  key={id}
                  title={title}
                  description={desc}
                  selected={a.stress === id}
                  onClick={() => updateAnswers({ stress: id })}
                />
              ))}
            </div>
            <p className="mt-6 text-sm font-semibold">Allenamento</p>
            <div className="mt-3 space-y-2">
              {(
                [
                  ['none', 'Quasi mai', 'Partiamo soft'],
                  ['light', 'Leggero', '1–2 sessioni'],
                  ['regular', 'Regolare', '3–4 sessioni'],
                  ['intense', 'Intenso', 'Alto volume'],
                ] as const
              ).map(([id, title, desc]) => (
                <OptionCard
                  key={id}
                  title={title}
                  description={desc}
                  selected={a.training === id}
                  onClick={() => updateAnswers({ training: id })}
                />
              ))}
            </div>
          </>
        )}

        {step === 5 && (
          <>
            <h2 className="text-2xl font-semibold tracking-tight">Focus extra e limiti</h2>
            <p className="mt-2 text-sm text-ink-500 dark:text-stone-400">
              Opzionale: fino a 3 aree secondarie. Meno è meglio.
            </p>
            <div className="mt-5 flex flex-wrap gap-2">
              {(
                [
                  ['skincare', 'Glow'],
                  ['posture', 'Postura'],
                  ['focus', 'Focus'],
                  ['longevity', 'Longevity'],
                  ['testosterone', 'Vitalità'],
                  ['digestion', 'Digestione'],
                  ['bodycomp', 'Body comp'],
                ] as const
              ).map(([id, label]) => (
                <button
                  key={id}
                  type="button"
                  onClick={() => toggleFocus(id)}
                  className={`min-h-11 rounded-full px-4 text-sm font-medium ${
                    a.focusAreas.includes(id)
                      ? 'bg-forest-700 text-white dark:bg-moss-400 dark:text-ink-950'
                      : 'bg-stone-100 dark:bg-ink-800'
                  }`}
                >
                  {label}
                </button>
              ))}
            </div>
            <p className="mt-8 text-sm font-semibold">Preferenze / limiti</p>
            <div className="mt-3 space-y-2">
              {(
                [
                  ['no_supplements', 'Preferisco poche integrazioni'],
                  ['sensitive_skin', 'Pelle sensibile'],
                  ['busy', 'Poco tempo (<15 min/giorno)'],
                  ['budget_strict', 'Budget stretto'],
                ] as const
              ).map(([id, title]) => (
                <OptionCard
                  key={id}
                  title={title}
                  selected={a.limitations.includes(id)}
                  onClick={() => toggleLimit(id)}
                />
              ))}
            </div>
          </>
        )}

        {step === 6 && (
          <>
            <h2 className="text-2xl font-semibold tracking-tight">Budget per lo stack</h2>
            <p className="mt-2 text-sm text-ink-500 dark:text-stone-400">
              Usiamo il budget solo per raccomandare prodotti Amazon coerenti. Le abitudini restano free.
            </p>
            <div className="mt-6 space-y-3">
              {(
                [
                  ['low', 'Essenziale', '~30€/mese — basi ad alto impatto'],
                  ['medium', 'Bilanciato', '~65€/mese — stack completo senza eccessi'],
                  ['elite', 'Performance', '120€+/mese — tool e formule premium'],
                ] as const
              ).map(([id, title, desc]) => (
                <OptionCard
                  key={id}
                  title={title}
                  description={desc}
                  selected={a.budget === id}
                  onClick={() => updateAnswers({ budget: id })}
                />
              ))}
            </div>
          </>
        )}
      </div>

        <div className="safe-bottom sticky bottom-0 border-t border-stone-200/70 bg-canvas px-5 py-4 backdrop-blur shadow-sm dark:border-ink-800 dark:bg-ink-950/95 dark:shadow-2xl dark:shadow-black/35 dark:rounded-t-3xl">
        <Button fullWidth disabled={!canContinue} onClick={next}>
          {step === TOTAL_STEPS ? t('generate_protocol') : t('continue')}
        </Button>
      </div>
    </Screen>
  );
}
