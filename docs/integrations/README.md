# Acoustic AI Assistant — Integration Registry

This directory is the source of truth for every external integration used by AAA.

The objective is to prevent development from depending on memory, private chat history or undocumented dashboard settings. Every integration must have an owner, purpose, authentication model, environment variables, permissions, test procedure, failure mode and rollback path before it is enabled in production.

## Status model

- `DISCOVERY`: requirements are being collected.
- `DESIGNED`: architecture and contracts are approved.
- `IMPLEMENTED_DISABLED`: code exists but production writes are disabled.
- `STAGING`: connected only to test resources.
- `PRODUCTION`: enabled with monitoring and runbook.
- `BLOCKED_OWNER_INPUT`: requires an account action or identifier from Cedric.
- `DEPRECATED`: must not be used for new work.

## Integration inventory

| Integration | Purpose | Current status | Authentication | Production write risk |
|---|---|---|---|---|
| GitHub | Source of truth, CI, PRs and releases | PRODUCTION | GitHub App / repository permissions | High |
| GitHub Actions | Typecheck, build and automated tests | STAGING | Repository workflow token | Medium |
| Hostinger Node.js | Next.js hosting and environment configuration | DESIGNED | Hostinger account + GitHub App | High |
| Telegram Bot API | Private mobile operator | IMPLEMENTED_DISABLED | Bot token, webhook secret, owner allowlist | High |
| WordPress REST API | Headless articles and affiliate catalogue | DISCOVERY | HTTPS + Application Password | High |
| Amazon Associates | Affiliate destinations and commission attribution | DISCOVERY | Associate tags by marketplace | Medium |
| Amazon Creators API | Product data and availability where permitted | DISCOVERY | Credentials issued by Amazon | Medium |
| Google Analytics 4 | Product analytics and conversion events | DISCOVERY | Measurement ID; Data API credentials for reports | Low |
| Google Search Console | Search queries, sitemap and indexing data | DISCOVERY | Google OAuth or service identity with property access | Medium |
| Google AdSense | Web advertising and revenue reporting | DISCOVERY | Publisher account and site approval | Medium |
| Google AdMob | Future Android/iOS advertising | NOT_STARTED | App IDs, ad unit IDs and store-linked account | Medium |
| Capacitor | Package web application for Android and iOS | DESIGNED | None for local build | Medium |
| Google Play Console | Android distribution | NOT_STARTED | Developer account and signing configuration | High |
| Apple Developer / App Store Connect | iOS distribution | NOT_STARTED | Developer account, certificates and identifiers | High |

## Required integration record

Every detailed integration document must contain:

1. Business purpose.
2. Official documentation links and date checked.
3. Data entering and leaving AAA.
4. Authentication and authorization method.
5. Required owner-provided identifiers.
6. Environment variable names without secret values.
7. Minimum permissions.
8. API endpoints, webhooks or SDK boundaries.
9. Rate limits and quotas when applicable.
10. Data retention and privacy implications.
11. Compliance and programme-policy requirements.
12. Local, staging and production test checklist.
13. Monitoring and alert conditions.
14. Failure behaviour and user-facing fallback.
15. Revocation and rollback procedure.
16. Current implementation status and unresolved decisions.

## Secret-management rules

- Secret values never enter GitHub, issues, documentation or chat transcripts.
- Documentation stores variable names, source account and rotation procedure only.
- Production and staging use different credentials whenever the provider supports it.
- Credentials must use the smallest permission scope possible.
- Revoked or rotated credentials must be recorded by date without retaining the previous value.
- Telegram, CMS and deployment credentials must never be exposed to browser-side JavaScript.

## Owner input register

The following information will eventually require Cedric to retrieve or create it from the corresponding dashboard. Values must be entered directly into Hostinger or the selected secret store, not pasted into repository files.

### Domain and Hostinger

- Final production domain and optional staging subdomain.
- Hostinger website slot selected for the Node.js application.
- Confirmation that Hostinger GitHub access includes `CedricVz/AAA`.
- Production branch to deploy after PR validation.

### Telegram

- Bot username.
- Bot token stored as `TELEGRAM_BOT_TOKEN`.
- Random webhook secret stored as `TELEGRAM_WEBHOOK_SECRET`.
- Cedric's numeric Telegram user ID stored as `TELEGRAM_OWNER_USER_ID`.
- Authorized private chat ID stored as `TELEGRAM_OWNER_CHAT_ID`.

### WordPress

- CMS base URL.
- Restricted service-user username.
- Application Password stored as `WORDPRESS_APPLICATION_PASSWORD`.
- Decision on standard posts versus custom post types for products and guides.

### Amazon

- Target marketplaces in launch order.
- Associate tag for each marketplace.
- Confirmation of Creators API eligibility and issued credential model.
- Affiliate disclosure wording approved for each language.

### Google

- GA4 Measurement ID and Property ID.
- Search Console property URL and verified ownership.
- Google Cloud project used for reporting APIs.
- AdSense publisher ID after site acceptance.
- Consent-management decision for EEA/UK users.

### Mobile distribution

- Android application ID.
- iOS bundle identifier.
- Google Play developer account status.
- Apple Developer account status.
- Signing-key and certificate custody decision.

## Immediate implementation order

1. GitHub Actions validation.
2. Hostinger staging deployment.
3. Telegram read-only commands.
4. WordPress read-only content adapter.
5. WordPress draft-writing adapter with confirmation.
6. Analytics event collection.
7. Search Console and GA4 reporting to Telegram.
8. Affiliate catalogue and click tracking.
9. AdSense only after content, policy and traffic readiness.
10. Native-store integrations only after the PWA is validated.

## Current critical decisions

- Use a modular monolith and adapter interfaces.
- Telegram is an operator interface, never a production shell.
- WordPress is a replaceable content adapter, not part of the acoustic domain.
- Amazon Product Advertising API 5.0 must not be used for new development because Amazon states it was deprecated on 2026-05-15; use the Amazon Creators API path or ordinary compliant affiliate links instead.
- Google reporting integrations are server-side and read-only by default.
- All integrations begin disabled and fail safely when configuration is absent.
