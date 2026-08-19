type Locale = 'it' | 'en';

import en from './locales/en.json';
import it from './locales/it.json';

const DICT: Record<Locale, Record<string, string>> = { en, it };

function detectLocale(): Locale {
  try {
    const stored = localStorage.getItem('biostack_locale');
    if (stored === 'en' || stored === 'it') return stored;
  } catch {}
  const nav = navigator.language || navigator['userLanguage'] || 'en';
  return nav.startsWith('it') ? 'it' : 'en';
}

let locale: Locale = detectLocale();

export function setLocale(l: Locale) {
  locale = l;
  try {
    localStorage.setItem('biostack_locale', l);
  } catch {}
}

export function t(key: string, fallback?: string) {
  return DICT[locale][key] ?? fallback ?? key;
}

export { locale };
