import { useEffect, useRef, useState } from 'react';
import {
  Crown,
  Moon,
  Sun,
  LogOut,
  Shield,
  Bell,
  Scan,
  Upload,
  Loader2,
  CheckCircle2,
  Settings,
  BookOpen,
} from 'lucide-react';
import { Screen, ScreenHeader } from '../ui/Screen';
import { Modal } from '../ui/Modal';
import { PrivacyPolicy } from '../ui/PrivacyPolicy';
import { Card } from '../ui/Card';
import { Button } from '../ui/Button';
import { Chip } from '../ui/Chip';
import { useApp } from '../../context/AppContext';
import { generateFacialReport } from '../../lib/scan';
import type { SubscriptionPlan } from '../../types';
import { t } from '../../i18n';

export function ProProfileScreen() {
  const {
    state,
    setDarkMode,
    setReminders,
    setPremium,
    setFacialReport,
    resetAll,
    setPhase,
    updateAnswers,
    setProSection,
    setOverlay,
  } = useApp();

  const section = state.proSection;
  const [plan, setPlan] = useState<SubscriptionPlan>('annual');
  const [paying, setPaying] = useState(false);
  const [front, setFront] = useState<string | null>(null);
  const [profile, setProfile] = useState<string | null>(null);
  const [scanning, setScanning] = useState(false);
  const [progress, setProgress] = useState(0);
  const [scanError, setScanError] = useState<string | null>(null);
  const [consentGiven, setConsentGiven] = useState<boolean>(() => {
    try {
      return localStorage.getItem('biostack_consent_photos') === '1';
    } catch {
      return false;
    }
  });
  const [countdown, setCountdown] = useState(599);
  const frontRef = useRef<HTMLInputElement>(null);
  const profileRef = useRef<HTMLInputElement>(null);
  const [showPrivacy, setShowPrivacy] = useState(false);

  useEffect(() => {
    const id = window.setInterval(() => setCountdown((c) => (c > 0 ? c - 1 : 599)), 1000);
    return () => window.clearInterval(id);
  }, []);

  const formatCd = (s: number) =>
    `${Math.floor(s / 60).toString().padStart(2, '0')}:${(s % 60).toString().padStart(2, '0')}`;

  const readFile = (file: File, setter: (v: string) => void) => {
    const r = new FileReader();
    r.onload = () => setter(r.result as string);
    r.readAsDataURL(file);
  };

  const validateAndRead = (file: File | null, setter: (v: string) => void) => {
    if (!file) return;
    const maxBytes = 5 * 1024 * 1024; // 5MB
    if (!file.type.startsWith('image/')) {
      setScanError('File non valido: carica solo immagini (jpg, png).');
      return;
    }
    if (file.size > maxBytes) {
      setScanError('File troppo grande: riduci le foto sotto 5MB.');
      return;
    }
    setScanError(null);
    readFile(file, setter);
  };

  const startScan = async () => {
    if (!front || !profile) return;
    if (!consentGiven) {
      setScanError('Devi accettare il consenso per poter effettuare lo scan.');
      return;
    }
    setScanning(true);
    setScanError(null);
    setProgress(0.15);
    const start = Date.now();
    const tick = window.setInterval(() => {
      setProgress(Math.min(0.85, (Date.now() - start) / 3500));
    }, 50);
    try {
      const report = await generateFacialReport(front, profile);
      setProgress(1);
      setFacialReport(report);
    } catch {
      setScanError('Analisi fallita. Riprova con foto più chiare (fronte + profilo).');
    } finally {
      window.clearInterval(tick);
      setScanning(false);
    }
  };

  const pay = () => {
    setPaying(true);
    window.setTimeout(() => {
      setPaying(false);
      setPremium(true);
    }, 1600);
  };

  const report = state.facialReport;

  return (
    <Screen withBottomNav>
      <ScreenHeader
        title={t('profile_pro')}
        subtitle="Impostazioni, scan AI, BioWiki"
        right={
          <div className="flex max-w-[160px] gap-1 overflow-x-auto">
            <Chip active={section === 'settings'} onClick={() => setProSection('settings')}>
              {t('profile_button')}
            </Chip>
            <Chip active={section === 'scan'} onClick={() => setProSection('scan')}>
              Scan
            </Chip>
          </div>
        }
      />
      <PrivacyPolicy open={showPrivacy} onClose={() => setShowPrivacy(false)} />

      <div className="space-y-4 px-5 py-5 animate-fade-in" key={section}>
          <Modal
          open={section === 'scan' && !consentGiven}
          title={t('consent_modal_title')}
          onClose={() => {
            // politely prevent closing without consent — keep modal open
          }}
        >
          <div className="space-y-4 text-sm text-stone-700 dark:text-stone-300">
            <p>
              Per eseguire lo scan facciale abbiamo bisogno del tuo consenso per processare le foto. Le
              immagini verranno analizzate per produrre insight e non saranno condivise senza il tuo
              permesso.
            </p>
            <p className="text-xs text-stone-500">Puoi rimuovere il consenso in qualsiasi momento dalle impostazioni.</p>
            <div className="mt-4 flex justify-end">
              <button
                type="button"
                className="rounded-2xl bg-stone-100 px-4 py-2 text-sm dark:bg-stone-800"
                onClick={() => {
                  setConsentGiven(true);
                  try {
                    localStorage.setItem('biostack_consent_photos', '1');
                  } catch {}
                }}
              >
                {t('consent_accept')}
              </button>
            </div>
          </div>
        </Modal>
        {section === 'scan' && (
          <>
            {!state.isPremium && (
              <Card className="border-amber-500/30 bg-gradient-to-br from-stone-900 to-forest-950 text-stone-50">
                <div className="flex items-center gap-2 text-amber-300">
                  <Crown className="h-5 w-5" />
                  <span className="text-sm font-semibold">Sblocca lo scan multi-angolo</span>
                </div>
                <p className="mt-2 text-sm text-stone-300">
                  Analisi da pixel reali (luminosità, contrasto, bilanciamento). Offerta{' '}
                  <span className="font-mono text-amber-300">{formatCd(countdown)}</span>
                </p>
                <div className="mt-4 grid grid-cols-2 gap-2">
                  <button
                    type="button"
                    onClick={() => setPlan('annual')}
                    className={`rounded-2xl border p-3 text-left ${
                      plan === 'annual' ? 'border-amber-400 bg-white/10' : 'border-white/15'
                    }`}
                  >
                    <p className="text-xs text-amber-300">Miglior valore</p>
                    <p className="font-semibold">59,99€/anno</p>
                  </button>
                  <button
                    type="button"
                    onClick={() => setPlan('monthly')}
                    className={`rounded-2xl border p-3 text-left ${
                      plan === 'monthly' ? 'border-amber-400 bg-white/10' : 'border-white/15'
                    }`}
                  >
                    <p className="text-xs text-stone-400">Mensile</p>
                    <p className="font-semibold">9,99€/mese</p>
                  </button>
                </div>
                <Button
                  fullWidth
                  className="mt-4 !bg-amber-400 !text-stone-950 hover:!bg-amber-300"
                  onClick={pay}
                  disabled={paying}
                >
                  {paying ? (
                    <>
                      <Loader2 className="h-4 w-4 animate-spin" /> Attivazione…
                    </>
                  ) : (
                    'Attiva BioStack Pro'
                  )}
                </Button>
              </Card>
            )}

            <Card>
              <div className="flex items-center gap-2">
                <Scan className="h-5 w-5 text-forest-700 dark:text-moss-400" />
                <h3 className="font-semibold">Scan facciale multi-angolo</h3>
              </div>
              <p className="mt-2 text-sm text-stone-500 dark:text-stone-400">
                Carica fronte + profilo. L’analisi legge dimensioni e pixel via Canvas — educativa, non
                diagnostica.
              </p>
              <div className="mt-4 grid grid-cols-2 gap-3">
                <button
                  type="button"
                  onClick={() => frontRef.current?.click()}
                  className="relative flex h-36 flex-col items-center justify-center overflow-hidden rounded-2xl border border-dashed border-stone-300 dark:border-stone-600"
                  aria-label="Carica foto fronte"
                >
                  {front ? (
                    <img src={front} alt="Fronte" className="absolute inset-0 h-full w-full object-cover" />
                  ) : (
                    <>
                      <Upload className="h-5 w-5" />
                      <span className="mt-2 text-xs">Fronte</span>
                    </>
                  )}
                  {scanning && front && (
                    <div className="biometric-grid absolute inset-0">
                      <div className="scan-line" />
                    </div>
                  )}
                </button>
                <button
                  type="button"
                  onClick={() => profileRef.current?.click()}
                  className="relative flex h-36 flex-col items-center justify-center overflow-hidden rounded-2xl border border-dashed border-stone-300 dark:border-stone-600"
                  aria-label="Carica foto profilo"
                >
                  {profile ? (
                    <img src={profile} alt="Profilo" className="absolute inset-0 h-full w-full object-cover" />
                  ) : (
                    <>
                      <Upload className="h-5 w-5" />
                      <span className="mt-2 text-xs">Profilo</span>
                    </>
                  )}
                </button>
              </div>
              <input
                ref={frontRef}
                type="file"
                accept="image/*"
                className="hidden"
                aria-label="Carica foto fronte"
                onChange={(e) => {
                  const f = e.target.files?.[0] ?? null;
                  validateAndRead(f, setFront);
                  // reset input so same file can be reselected
                  if (e.target) e.currentTarget.value = '';
                }}
              />
              <input
                ref={profileRef}
                type="file"
                accept="image/*"
                className="hidden"
                aria-label="Carica foto profilo"
                onChange={(e) => {
                  const f = e.target.files?.[0] ?? null;
                  validateAndRead(f, setProfile);
                  if (e.target) e.currentTarget.value = '';
                }}
              />

              {scanError && (
                <div className="mt-4 rounded-md border border-rose-500/40 bg-rose-50/40 p-3 text-sm text-rose-700" role="alert">
                  {scanError}
                </div>
              )}

              {scanning && (
                <div className="mt-4">
                  <div className="h-2 w-full overflow-hidden rounded-full bg-stone-200 dark:bg-ink-800">
                    <div
                      className="h-2 bg-forest-700 dark:bg-moss-400"
                      style={{ width: `${Math.min(100, Math.round(progress * 100))}%` }}
                    />
                  </div>
                  <p className="mt-2 text-xs text-stone-600 dark:text-stone-400">Analisi in corso…</p>
                </div>
              )}
              {scanError && <p className="mt-3 text-sm text-rose-600">{scanError}</p>}
              <Button
                fullWidth
                className="mt-4"
                disabled={!front || !profile || scanning || !state.isPremium}
                onClick={() => void startScan()}
              >
                {scanning ? (
                  <>
                    <Loader2 className="h-4 w-4 animate-spin" /> Analisi pixel {Math.round(progress * 100)}%
                  </>
                ) : state.isPremium ? (
                  'Avvia analisi AI'
                ) : (
                  'Sblocca Pro per analizzare'
                )}
              </Button>
            </Card>

            {report && state.isPremium && (
              <Card className="animate-fade-in-scale">
                <div className="flex items-center gap-2 text-forest-700 dark:text-moss-400">
                  <CheckCircle2 className="h-5 w-5" />
                  <p className="font-semibold">Report · Glow {report.glowUpScore}</p>
                </div>
                <div className="mt-4 grid grid-cols-2 gap-3 text-sm">
                  <div>
                    <p className="text-xs text-stone-500">Forma (aspect)</p>
                    <p className="font-medium capitalize">{report.faceShape}</p>
                  </div>
                  <div>
                    <p className="text-xs text-stone-500">Simmetria L/R</p>
                    <p className="font-medium">{report.symmetry}%</p>
                  </div>
                  <div>
                    <p className="text-xs text-stone-500">Idratazione stimata</p>
                    <p className="font-medium">{report.hydration}%</p>
                  </div>
                  <div>
                    <p className="text-xs text-stone-500">Contrasto / luce</p>
                    <p className="font-medium">
                      {Math.round(report.contrastScore)} / {report.brightness}
                    </p>
                  </div>
                  <div>
                    <p className="text-xs text-stone-500">Angolo proxy</p>
                    <p className="font-medium">{report.jawAngle}°</p>
                  </div>
                  <div>
                    <p className="text-xs text-stone-500">Pelle</p>
                    <p className="font-medium">{report.skinQuality.replace('_', ' ')}</p>
                  </div>
                </div>
                <div className="mt-4 space-y-3 text-sm">
                  <div>
                    <p className="font-semibold">Taglio</p>
                    <p className="text-stone-600 dark:text-stone-300">{report.hairRecommendation}</p>
                  </div>
                  <div>
                    <p className="font-semibold">Barba</p>
                    <p className="text-stone-600 dark:text-stone-300">{report.beardRecommendation}</p>
                  </div>
                  <div>
                    <p className="font-semibold">Occhiali</p>
                    <p className="text-stone-600 dark:text-stone-300">{report.eyewearStyles.join(' · ')}</p>
                  </div>
                </div>
                {report.analysisNotes?.length > 0 && (
                  <ul className="mt-4 space-y-1 text-xs text-stone-500">
                    {report.analysisNotes.map((n) => (
                      <li key={n}>· {n}</li>
                    ))}
                  </ul>
                )}
              </Card>
            )}
          </>
        )}

        {section === 'settings' && (
          <>
            <Card>
              <div className="flex items-center gap-3">
                <div className="flex h-12 w-12 items-center justify-center rounded-2xl bg-forest-100 text-forest-800 dark:bg-stone-800 dark:text-moss-400">
                  <Settings className="h-5 w-5" />
                </div>
                <div>
                  <p className="font-semibold text-stone-900 dark:text-stone-100">
                    {state.answers.name || 'Utente BioStack'}
                  </p>
                  <p className="text-sm text-stone-600 dark:text-stone-300">
                    Obiettivo: {state.answers.goal ?? '—'}
                    {state.demoMode ? ' · Demo' : ''}
                  </p>
                </div>
              </div>
              <label className="mt-4 block">
                <span className="mb-1 block text-xs font-medium text-stone-600 dark:text-stone-300">Nome</span>
                <input
                  value={state.answers.name}
                  onChange={(e) => updateAnswers({ name: e.target.value })}
                  className="h-11 w-full rounded-xl border border-stone-200 bg-white px-3 text-sm text-stone-900 outline-none transition focus:border-forest-600 dark:border-stone-700 dark:bg-stone-950 dark:text-stone-100 dark:focus:border-moss-400"
                />
              </label>
            </Card>

            <Card
              onClick={() => setOverlay('wiki')}
              className="flex items-center justify-between gap-3"
            >
              <div className="flex items-center gap-3">
                <BookOpen className="h-5 w-5 text-forest-700 dark:text-moss-400" />
                <div>
                  <p className="font-semibold">BioWiki</p>
                  <p className="text-xs text-stone-500">Academy: postura, skincare, longevity, sonno</p>
                </div>
              </div>
              <span className="text-sm text-forest-700 dark:text-moss-400">Apri</span>
            </Card>

            <Card padding="sm">
              <button
                type="button"
                onClick={() => setDarkMode(!state.isDarkMode)}
                className="flex w-full items-center justify-between rounded-2xl border border-stone-200 bg-stone-50/90 px-3 py-3 text-left transition-colors dark:border-stone-700 dark:bg-stone-900/90"
              >
                <span className="flex items-center gap-3 text-sm font-medium text-stone-700 dark:text-stone-100">
                  {state.isDarkMode ? (
                    <Moon className="h-4 w-4 text-forest-700 dark:text-moss-400" />
                  ) : (
                    <Sun className="h-4 w-4 text-amber-600 dark:text-amber-400" />
                  )}
                  Dark mode
                </span>
                <span
                  className={`relative h-7 w-12 rounded-full border border-stone-300 bg-stone-200/90 shadow-inner transition-colors dark:border-stone-600 dark:bg-stone-800 ${
                    state.isDarkMode ? 'bg-forest-700 dark:bg-moss-400' : ''
                  }`}
                >
                  <span
                    className={`absolute top-0.5 h-6 w-6 rounded-full bg-white shadow-sm ring-1 ring-stone-200 transition-transform dark:bg-stone-100 dark:ring-stone-700 ${
                      state.isDarkMode ? 'translate-x-5' : 'translate-x-0.5'
                    }`}
                  />
                </span>
              </button>
              <button
                type="button"
                onClick={() => setOverlay('notifications')}
                className="mt-2 flex w-full items-center justify-between border-t border-stone-200 px-2 py-3 text-left dark:border-stone-800"
              >
                <span className="flex items-center gap-3 text-sm font-medium text-stone-700 dark:text-stone-100">
                  <Bell className="h-4 w-4 text-forest-700 dark:text-moss-400" /> Promemoria e notifiche
                </span>
                <span className="text-xs text-stone-600 dark:text-stone-300">
                  {state.remindersEnabled ? 'On' : 'Off'}
                </span>
              </button>
              <button
                type="button"
                onClick={() => setReminders(!state.remindersEnabled)}
                className="flex w-full items-center justify-between border-t border-stone-200 px-2 py-3 text-left dark:border-stone-800"
              >
                <span className="text-sm font-medium text-stone-700 dark:text-stone-100">Toggle reminder rapido</span>
                <span className="text-xs text-stone-600 dark:text-stone-300">
                  {state.remindersEnabled ? 'Disattiva' : 'Attiva'}
                </span>
              </button>
              <div className="flex items-start gap-3 border-t border-stone-200 px-2 py-3 dark:border-stone-800">
                <Shield className="mt-0.5 h-4 w-4 shrink-0 text-forest-700 dark:text-moss-400" />
                <p className="text-xs leading-relaxed text-stone-600 dark:text-stone-300">
                  Privacy: dati in localStorage. Tema salvato separatamente. Non è un dispositivo medico.
                </p>
              </div>
            </Card>

              <Card>
                <div className="flex items-center justify-between">
                  <div>
                    <p className="font-semibold">Privacy e Consensi</p>
                    <p className="text-xs text-stone-500">Leggi la nostra informativa e gestisci i consensi</p>
                  </div>
                  <div className="flex gap-2">
                    <button
                      type="button"
                      onClick={() => setShowPrivacy(true)}
                      className="rounded-2xl border border-stone-200 px-3 py-2 text-sm dark:border-stone-700"
                    >
                      Visualizza
                    </button>
                  </div>
                </div>
              </Card>

            <Button
              fullWidth
              variant="secondary"
              onClick={async () => {
                  try {
                    // Sign out from Supabase if available
                    // eslint-disable-next-line @typescript-eslint/no-var-requires
                    const { supabase } = await import('../../lib/supabase');
                    if (supabase) await supabase.auth.signOut();
                  } catch {}
                  // Preserve persisted onboarding answers so user doesn't have to re-enter them after logout
                  setPhase('welcome');
                }}
            >
              <LogOut className="h-4 w-4" /> Esci e resetta dati locali
            </Button>
          </>
        )}
      </div>
    </Screen>
  );
}
