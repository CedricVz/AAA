# Acoustic AI Assistant — Architecture v1

## 1. Architectural decision

AAA will begin as a **modular monolith**, not as a collection of microservices.

This gives the project one deployable application, low operating cost and simple debugging while preserving strict internal boundaries so individual modules can be extracted later if traffic or workload justifies it.

The repository is the technical source of truth. Production behaviour must be reproducible from committed code, documented environment variables, migrations, tests and runbooks.

## 2. Core principles

1. **Feature-first structure:** code is grouped by business capability, not only by technical file type.
2. **Clean boundaries:** domain logic cannot depend on Next.js, WordPress, Telegram, Amazon or a specific database.
3. **Ports and adapters:** external services are accessed through interfaces so they can be replaced without rewriting the core.
4. **Local-first AI:** core acoustic intelligence must not require a paid AI API.
5. **Progressive complexity:** no queue, microservice or database is introduced before the product needs it.
6. **Mobile operation:** routine content and catalogue work must be possible through Telegram or the CMS.
7. **Safe delivery:** content changes, configuration changes and code changes follow separate approval paths.
8. **Observable behaviour:** important actions produce structured logs and measurable events.
9. **Privacy by default:** raw audio stays on the device whenever technically possible.
10. **Documented operations:** no critical deployment or recovery step may exist only in memory.

## 3. System shape

```text
User browser / installed PWA
        |
        v
Next.js application (web, SEO, tools, BFF API)
        |
        +--> Acoustic domain engine
        +--> Local AI workers in the browser
        +--> Content port ------> WordPress headless adapter
        +--> Affiliate port ----> Amazon / future merchant adapters
        +--> Analytics port ----> GA4 / Search Console adapter
        +--> Telegram port -----> Telegram Bot API adapter
        +--> Persistence port --> local storage first, server DB later
```

## 4. Deployable units

### 4.1 Public application

One Next.js application provides:

- Marketing and SEO pages.
- Acoustic calculators and diagnostic tools.
- PWA shell.
- Backend-for-frontend API routes.
- Secure Telegram webhook.
- Server-rendered metadata and structured data.

Initial deployment target: Hostinger managed Node.js using standalone Next.js output.

### 4.2 Headless CMS

WordPress is used only for operational content:

- Articles and guides.
- Product catalogue.
- Affiliate destinations by market.
- Editorial disclosures.
- Recommendation content that should not require a code deployment.

The public application accesses WordPress through a content interface. WordPress-specific objects must not leak into the acoustic domain.

### 4.3 Local intelligence

The browser performs the computational work whenever practical:

- Audio feature extraction.
- Environmental sound classification.
- Guided clap-test analysis.
- Before-and-after comparisons.
- Lightweight visual room analysis in a later phase.

Heavy model execution must use Web Workers so the interface remains responsive.

## 5. Business modules

The target application is divided into these bounded modules:

- **acoustics:** room calculations, diagnosis, scoring and treatment planning.
- **measurements:** microphone sessions, samples, comparisons and device capability checks.
- **local-ai:** model loading, inference workers and confidence handling.
- **content:** articles, guides, categories and SEO metadata.
- **affiliate:** products, markets, disclosures, recommendations and click events.
- **analytics:** product events, tool usage, Search Console insights and reporting.
- **telegram:** commands, authorization, confirmations and action logs.
- **identity:** optional user accounts and saved rooms when justified by demand.
- **shared:** primitives that are genuinely reused across modules.

A module may expose public functions and types through a single `index.ts`. Other modules must not import its internal files directly.

## 6. Target repository structure

```text
AAA/
├── app/                         # Next.js routes and composition only
│   ├── (marketing)/             # Home, guides and commercial pages
│   ├── (tools)/                 # Acoustic tools and result pages
│   └── api/                     # BFF endpoints and webhooks
├── modules/
│   ├── acoustics/
│   │   ├── domain/              # Pure rules, entities and calculations
│   │   ├── application/         # Use cases and orchestration
│   │   ├── infrastructure/      # External adapters
│   │   ├── ui/                  # Module-specific components
│   │   └── index.ts             # Public module contract
│   ├── measurements/
│   ├── local-ai/
│   ├── content/
│   ├── affiliate/
│   ├── analytics/
│   └── telegram/
├── shared/
│   ├── config/
│   ├── errors/
│   ├── logging/
│   ├── security/
│   ├── types/
│   └── ui/
├── tests/
│   ├── unit/
│   ├── integration/
│   ├── e2e/
│   └── fixtures/
├── docs/
│   ├── adr/                     # Architecture decision records
│   ├── runbooks/                # Deploy, rollback, recovery and operations
│   ├── product/                 # Scope, roadmap and acceptance criteria
│   └── seo/                     # Information architecture and content system
├── scripts/                     # Repeatable maintenance commands
├── public/
├── legacy/                      # Historical project after an approved migration
└── .github/workflows/           # CI and deployment checks
```

The current academic files remain untouched until a dedicated, reviewed migration moves them under `legacy/`.

## 7. Dependency rules

Allowed dependency direction:

```text
UI / routes
   -> application use cases
      -> domain

infrastructure adapters
   -> application ports
```

Forbidden:

- Domain code importing Next.js or React.
- Acoustic calculations calling WordPress or Telegram directly.
- UI components containing affiliate or diagnostic business rules.
- Telegram commands editing GitHub production branches directly.
- External API response shapes becoming internal domain entities.

## 8. Data strategy

### Phase 1

- Anonymous room settings and draft measurements: browser storage.
- Articles and affiliate catalogue: WordPress.
- Usage analytics: analytics provider events.
- No user account database.

### Phase 2

When saved cross-device histories or subscriptions are justified, introduce a server database through a persistence interface. The domain will not depend on whether the adapter uses PostgreSQL, MySQL or another supported store.

Every schema change must have a migration and rollback note.

## 9. Telegram architecture

Telegram is an operator interface, not a shell.

Command flow:

```text
Telegram update
  -> webhook authentication
  -> owner allowlist
  -> command parser
  -> application use case
  -> preview and confirmation when required
  -> adapter execution
  -> immutable action result
```

Direct actions are limited to low-risk content and catalogue operations. Publishing, deletion, bulk modification and recommendation-rule changes require confirmation. Code requests become tracked GitHub work and never patch production directly.

## 10. AI architecture without variable API cost

- Deterministic expert rules remain the authoritative baseline.
- Open models run locally using browser-compatible runtimes.
- Model files are versioned separately from business rules.
- Confidence thresholds are explicit and tested.
- Low-confidence classifications fall back to guided questions.
- A model result never overrides safety disclaimers or deterministic validation.
- Paid APIs may be optional enhancements later, never a requirement for the core product.

## 11. Testing strategy

- **Unit tests:** calculations, scoring, recommendation rules and command parsing.
- **Integration tests:** WordPress, Telegram and affiliate adapters with mocked external services.
- **Contract tests:** external payload shapes and environment configuration.
- **E2E tests:** principal user journeys on mobile and desktop.
- **Regression fixtures:** known room inputs and expected diagnoses.
- **Accessibility checks:** keyboard, labels, contrast and reduced-motion behaviour.

No pull request may merge when typecheck, build or critical tests fail.

## 12. Environments and delivery

```text
feature branch
   -> pull request
      -> automated checks
         -> preview/staging
            -> human validation
               -> production
                  -> tagged checkpoint
```

Required environments:

- **local:** development and tests.
- **staging:** Telegram, CMS and deployment verification with test credentials.
- **production:** protected secrets and approved releases only.

Secrets live in environment configuration, never in the repository. Each production phase ends with a clean working tree, a documented checkpoint and a version tag.

## 13. Observability

The system must record:

- Tool started and completed.
- Diagnosis category and confidence without raw private audio.
- Affiliate recommendation displayed and clicked.
- Telegram action requested, confirmed, rejected or failed.
- External adapter errors.
- Deployment version and environment.

Logs must avoid credentials, raw audio and unnecessary personal data.

## 14. Scaling path

The modular monolith remains the default. A module is extracted only when there is evidence such as:

- Independent scaling requirements.
- Long-running audio processing.
- Queue-backed workloads.
- Different security boundaries.
- Deployment frequency that materially blocks the rest of the application.

Likely first extraction candidates, if ever needed, are audio-processing jobs and analytics aggregation. Content, affiliate logic and the public web application should remain together as long as that is operationally simpler.

## 15. Delivery phases

1. Foundation, typecheck, build and deterministic room diagnosis.
2. Repository restructuring into the approved module boundaries.
3. SEO pages, legal pages, structured data and analytics events.
4. Headless content and affiliate catalogue.
5. Telegram operator with previews, confirmations and audit records.
6. Local sound classification and guided clap test.
7. PWA hardening, offline behaviour and installability.
8. Android packaging and store validation.
9. iOS packaging after product and revenue validation.

This architecture is intentionally scalable without being overengineered: one clear product, one repository, one initial deployment and strong internal boundaries.