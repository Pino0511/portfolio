Enable GA4 and Sentry

This project ships a lightweight analytics wrapper at `src/lib/analytics.ts` that will initialize Google Analytics (GA4) and Sentry when the corresponding environment variables are provided.

1) Install optional dependencies

```bash
# from project root
npm install @sentry/browser --save
# or with pnpm/yarn
pnpm add @sentry/browser
# yarn add @sentry/browser
```

2) Set environment variables (Vite)

Create a `.env` or `.env.local` at project root and add:

```
VITE_GA_MEASUREMENT_ID=G-XXXXXXXXXX
VITE_SENTRY_DSN=https://<public_key>@sentry.io/<project>
```

3) Rebuild / restart dev server

```bash
npm run dev
```

Notes
- Analytics and Sentry are optional; if env vars are absent the code stays silent and falls back to console logging.
- For production builds ensure DSN and GA ID are set in your CI/CD secrets and not committed to source.
