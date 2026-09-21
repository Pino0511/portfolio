import { Shield, Sparkles, Timer, Lock } from 'lucide-react';
import { useState } from 'react';
import { t } from '../../i18n';
import { Button } from '../ui/Button';
import { Screen } from '../ui/Screen';
import { BrandLogo } from '../ui/BrandLogo';
import { supabase, isSupabaseConfigured } from '../../lib/supabase';
import { useApp } from '../../context/AppContext';

export function WelcomeScreen() {
  const { setPhase, startDemo } = useApp();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [isNewUser, setIsNewUser] = useState(false);
  const [authMessage, setAuthMessage] = useState<string | null>(null);
  const [authLoading, setAuthLoading] = useState(false);

  const handleGoogleLogin = async () => {
    if (!supabase) {
      alert('Supabase non configurato. Controlla il file .env!');
      return;
    }

    const { error } = await supabase.auth.signInWithOAuth({
      provider: 'google',
      options: {
        redirectTo: window.location.origin,
        queryParams: {
          prompt: 'select_account',
        },
      },
    });

    if (error) {
      console.error('Errore durante il login:', error.message);
      setAuthMessage('Impossibile completare l’accesso con Google. Riprova.');
    }
  };

  const handleEmailSubmit = async () => {
    if (!supabase) {
      setAuthMessage('Supabase non configurato. Verifica la tua configurazione.');
      return;
    }

    if (!email || !password) {
      setAuthMessage('Inserisci email e password.');
      return;
    }

    if (password.length < 6) {
      setAuthMessage('La password deve contenere almeno 6 caratteri.');
      return;
    }

    setAuthLoading(true);
    setAuthMessage(null);

    try {
      if (isNewUser) {
        const { data, error } = await supabase.auth.signUp(
          { email, password },
          { emailRedirectTo: window.location.origin }
        );

        if (error) {
          setAuthMessage(error.message);
        } else if (data?.session) {
          setAuthMessage('Account creato e autenticato con successo.');
          setPhase('onboarding');
        } else {
          // Try to sign in immediately as fallback; if not possible, offer magic link resend
          const { error: signinError } = await supabase.auth.signInWithPassword({ email, password });
          if (!signinError) {
            setAuthMessage('Account creato e autenticato con successo.');
            setPhase('onboarding');
          } else {
            setAuthMessage(
              'Registrazione avviata. Controlla la tua email (anche spam). Se non arriva, invia un link di accesso alternativo.'
            );
          }
        }
      } else {
        const { error } = await supabase.auth.signInWithPassword({ email, password });
        if (error) {
          setAuthMessage(error.message);
        } else {
          setPhase('onboarding');
        }
      }
    } catch (error) {
      setAuthMessage('Errore imprevisto durante l’autenticazione.');
    } finally {
      setAuthLoading(false);
    }
  };

  const handleSendMagicLink = async () => {
    if (!supabase) return setAuthMessage('Supabase non configurato.');
    try {
      const { error } = await supabase.auth.signInWithOtp({ email });
      if (error) setAuthMessage(error.message);
      else setAuthMessage('Link di accesso inviato. Controlla la tua casella (anche spam).');
    } catch (e) {
      setAuthMessage('Impossibile inviare il link. Riprova più tardi.');
    }
  };

  return (
    <Screen className="relative overflow-hidden">
      <div className="pointer-events-none absolute inset-0 bg-hero-glow" />
      <div className="relative flex flex-1 flex-col px-6 pb-8 pt-10">
        <BrandLogo className="mx-auto w-full max-w-[140px] -mb-2" alt="BioStack AI logo" />
        <p className="text-xs font-semibold uppercase tracking-[0.22em] text-forest-700 dark:text-moss-400">
          BioStack AI
        </p>
        <h1 className="mt-3 max-w-[16ch] text-[34px] font-semibold leading-[1.12] tracking-tight text-ink-900 dark:text-stone-50">
          {t('welcome_title')}
        </h1>
        <p className="mt-4 max-w-[32ch] text-[15px] leading-relaxed text-ink-600 dark:text-stone-300">
          {t('welcome_sub')}
        </p>

        <div className="mt-8 grid grid-cols-2 gap-3">
          {[
            { icon: Sparkles, label: t('feature_personalized'), sub: 'sul tuo obiettivo' },
            { icon: Timer, label: t('feature_time'), sub: 'per iniziare' },
            { icon: Shield, label: t('feature_evidence'), sub: 'niente promesse magiche' },
            { icon: Lock, label: t('feature_privacy'), sub: 'dati sul tuo device' },
          ].map(({ icon: Icon, label, sub }) => (
            <div
              key={label}
              className="rounded-2xl border border-stone-200/70 bg-white/70 p-3.5 dark:border-ink-700 dark:bg-ink-900/60"
            >
              <Icon className="h-4 w-4 text-forest-700 dark:text-moss-400" />
              <p className="mt-2 text-sm font-semibold text-ink-900 dark:text-stone-50">{label}</p>
              <p className="text-xs text-ink-500 dark:text-stone-400">{sub}</p>
            </div>
          ))}
        </div>

        <div className="mt-auto space-y-4 pt-10">
          <div className="rounded-3xl border border-stone-200/70 bg-white/80 p-5 shadow-soft backdrop-blur dark:border-ink-700 dark:bg-ink-900/80">
            <div className="mb-5">
              <p className="text-sm font-semibold uppercase tracking-[0.18em] text-forest-700 dark:text-moss-300">
                Accesso
              </p>
              <p className="mt-2 text-sm font-semibold text-ink-900 dark:text-stone-100">{t('access_sub')}</p>
            </div>

            <div className="grid gap-3">
              <label className="block text-xs font-medium text-stone-600 dark:text-stone-300">
                Email
                <input
                  type="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  className="mt-2 h-11 w-full rounded-xl border border-stone-200 bg-white px-3 text-sm text-stone-900 outline-none transition focus:border-forest-700 focus:ring-2 focus:ring-forest-200 dark:border-stone-700 dark:bg-stone-950 dark:text-stone-100 dark:focus:border-moss-300 dark:focus:ring-moss-300"
                  placeholder={t('email_placeholder')}
                />
              </label>

              <label className="block text-xs font-medium text-stone-600 dark:text-stone-300">
                Password
                <input
                  type="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  className="mt-2 h-11 w-full rounded-xl border border-stone-200 bg-white px-3 text-sm text-stone-900 outline-none transition focus:border-forest-700 focus:ring-2 focus:ring-forest-200 dark:border-stone-700 dark:bg-stone-950 dark:text-stone-100 dark:focus:border-moss-300 dark:focus:ring-moss-300"
                  placeholder={t('password_placeholder')}
                />
              </label>

              <div className="grid gap-3 sm:grid-cols-[1fr_auto]">
                <Button
                  fullWidth
                  variant="secondary"
                  onClick={handleEmailSubmit}
                  disabled={authLoading || !email || !password}
                  className="min-h-[44px]"
                >
                  {isNewUser ? t('register') : t('login')}
                </Button>
                <button
                  type="button"
                  onClick={() => setIsNewUser(!isNewUser)}
                  className="text-sm font-semibold text-forest-700 underline-offset-4 transition hover:text-forest-900 dark:text-moss-300 dark:hover:text-moss-200"
                >
                  {isNewUser ? t('have_account') : t('create_account')}
                </button>
              </div>

              {authMessage ? (
                <div className="flex flex-col gap-2">
                  <p className="text-sm text-rose-600">{authMessage}</p>
                  {isNewUser && email ? (
                    <button
                      type="button"
                      onClick={handleSendMagicLink}
                      className="self-start rounded-md bg-forest-700 px-3 py-1 text-xs font-semibold text-white dark:bg-moss-400 dark:text-ink-950"
                    >
                      Invia link di accesso
                    </button>
                  ) : null}
                </div>
              ) : null}

              <div className="mt-4 border-t border-stone-200 pt-4 dark:border-stone-700">
                {isSupabaseConfigured() ? (
                  <Button fullWidth variant="secondary" onClick={handleGoogleLogin} className="flex items-center justify-center gap-3">
                    <svg width="18" height="18" viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg" aria-hidden>
                      <path d="M44.5 20H24v8.5h11.9C34.2 32.6 29.6 36 24 36c-7 0-12.6-5.6-12.6-12.6S17 10.8 24 10.8c3.2 0 5.9 1.1 8 2.9l6-6C34.6 6 29.7 4 24 4 12.9 4 4 12.9 4 24s8.9 20 20 20c11 0 20-8.9 20-20 0-1.3-.1-2.6-.5-3.7z" fill="#EA4335"/>
                    </svg>
                    Continua con Google
                  </Button>
                ) : (
                  <p className="text-xs text-rose-600">Provider Google non configurato. Controlla .env e Supabase.</p>
                )}
              </div>
            </div>
          </div>

          <Button fullWidth variant="primary" onClick={() => setPhase('onboarding')}>
            {t('start_protocol')}
          </Button>

          <Button fullWidth variant="ghost" onClick={startDemo}>
            {t('watch_demo')}
          </Button>

          <p className="text-center text-[11px] text-ink-400 dark:text-stone-500">{t('not_medical')}</p>
        </div>
      </div>
    </Screen>
  );
}