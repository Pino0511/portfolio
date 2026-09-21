// Lightweight analytics / error reporting stubs.
// Replace with real implementations (GA4, Sentry) before production.
type Analytics = {
  init: () => void;
  track: (event: string, props?: Record<string, unknown>) => void;
  identify: (id: string, traits?: Record<string, unknown>) => void;
};

const GA_MEASUREMENT_ID = import.meta.env.VITE_GA_MEASUREMENT_ID;
const SENTRY_DSN = import.meta.env.VITE_SENTRY_DSN;

function loadGA(id?: string) {
  if (!id) return;
  (window as any).dataLayer = (window as any).dataLayer || [];
  function gtag(...args: unknown[]) {
    (window as any).dataLayer.push(args);
  }
  (window as any).gtag = gtag;
  const script = document.createElement('script');
  script.async = true;
  script.src = `https://www.googletagmanager.com/gtag/js?id=${id}`;
  document.head.appendChild(script);
  gtag('js', new Date());
  gtag('config', id);
}

let Sentry: any = null;

const analytics: Analytics = {
  async init() {
    try {
      if (SENTRY_DSN) {
        try {
          const mod = await import('@sentry/browser');
          Sentry = (mod && (mod as any).init) ? mod : mod.default ?? mod;
          // If module provides init as function, call init on module directly
          if (Sentry && typeof Sentry.init === 'function') {
            Sentry.init({ dsn: SENTRY_DSN, environment: import.meta.env.MODE });
          }
          console.info('[analytics] Sentry initialized');
        } catch (err) {
          // Sentry not installed — warn but continue
          // eslint-disable-next-line no-console
          console.warn('[analytics] Sentry import failed (not installed?)', err);
          Sentry = null;
        }
      }

      if (GA_MEASUREMENT_ID) {
        loadGA(GA_MEASUREMENT_ID);
        console.info('[analytics] GA4 initialized');
      }
    } catch (e) {
      // eslint-disable-next-line no-console
      console.warn('[analytics] init failed', e);
    }
  },
  track(event, props) {
    try {
      if ((window as any).gtag) (window as any).gtag('event', event, props ?? {});
      if (Sentry && typeof Sentry.captureMessage === 'function') Sentry.captureMessage(`event:${event}`);
    } catch (e) {
      // eslint-disable-next-line no-console
      console.warn('[analytics] track failed', e);
    }
  },
  identify(id, traits) {
    try {
      if (Sentry && typeof Sentry.setUser === 'function') Sentry.setUser({ id, ...(traits as any) });
      if ((window as any).gtag) (window as any).gtag('set', { user_id: id });
    } catch (e) {
      // eslint-disable-next-line no-console
      console.warn('[analytics] identify failed', e);
    }
  },
};

export default analytics;
