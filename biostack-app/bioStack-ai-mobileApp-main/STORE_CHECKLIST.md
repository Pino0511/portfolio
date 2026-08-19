BioStack — Store Readiness Checklist

Essential assets and checklist for publishing to App Store / Play Store / PWA

1) App metadata
- App name (localizations: IT, EN)
- Short description (80–120 chars)
- Full description (locale-aware, 300–400 chars)
- Keywords (App Store)
- Support URL and Privacy Policy URL

2) Visual assets
- App icons: 1024x1024 (App Store), multiple sizes for Android (48–512px) and web favicon
- Feature graphic (Google Play)
- 3–5 screenshots (9:16) for each store locale showing onboarding, scan, and results
- One short promo video (15–30s) in vertical portrait (9:16) for Play Store / App Store preview

3) Store listing texts
- Localized marketing copy (IT + EN)
- Release notes
- Age rating classification and content descriptors

4) Privacy & legal
- Finalized Privacy Policy URL (HTTPS)
- In-app consent flow for photo use
- Terms of service
- Contact email and data controller information

5) Technical readiness
- In-app purchases configured (if Pro is paid): SKUs, receipt validation
- Crash reporting & analytics integrated (Sentry, GA4 or alternatives)
- App binary signed and provisioned (iOS) / keystore for Android
- Performance testing on low-end devices
- Accessibility checks and basic automated tests

6) QA checklist
- No debug keys or test endpoints in release build
- All strings localized or fallbacks provided
- Offline behavior tested and graceful failures handled
- GDPR compliance: data export/delete tested

7) Launch plan
- Release notes & changelog ready
- Marketing assets (see `marketing/` folder)
- UTM parameters for campaign tracking

Optional
- Age-gated content handling
- Backend privacy audit report

