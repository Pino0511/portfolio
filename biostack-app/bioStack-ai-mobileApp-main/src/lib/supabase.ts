import { createClient } from '@supabase/supabase-js'

const rawUrl = import.meta.env.VITE_SUPABASE_URL
const supabaseAnonKey = import.meta.env.VITE_SUPABASE_ANON_KEY || ''

function isValidHttpUrl(url: unknown): url is string {
  if (typeof url !== 'string') return false;
  try {
    const u = new URL(url);
    return u.protocol === 'http:' || u.protocol === 'https:';
  } catch {
    return false;
  }
}

let supabase: any = null;

if (isValidHttpUrl(rawUrl) && supabaseAnonKey) {
  supabase = createClient(rawUrl, supabaseAnonKey, {
    auth: {
      persistSession: true,
      autoRefreshToken: true,
      detectSessionInUrl: true
    }
  });
} else {
  // Avoid throwing during app bootstrap — log a helpful message instead.
  // Typical causes: env vars missing, misconfigured `.env`, or Vite not loading env.
  // This prevents the uncaught error that produced a white screen.
  // Developers should set VITE_SUPABASE_URL to a full http(s) URL.
  // Example: VITE_SUPABASE_URL=https://xyz.supabase.co
  // and VITE_SUPABASE_ANON_KEY=<anon-key>
  // Keep export as null when not configured.
  // eslint-disable-next-line no-console
  console.warn('Supabase non configurato o URL non valido. Verifica VITE_SUPABASE_URL e VITE_SUPABASE_ANON_KEY.');
}

export { supabase };
export const isSupabaseConfigured = () => !!supabase;
