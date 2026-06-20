# Amazon Associates and Creators API Integration

Status: `DISCOVERY`

Checked against official Amazon affiliate and Product Advertising API documentation on 2026-06-21.

## Purpose

Attribute qualifying product referrals, maintain relevant acoustic-product recommendations and optionally synchronize product metadata where programme access and policy permit it.

## Critical platform decision

Do not build new code against Product Advertising API 5.0.

Amazon's official PA-API documentation states that PA-API was deprecated on 2026-05-15 and directs developers to migrate to the Amazon Creators API. The older documentation is explicitly marked as no longer maintained.

Official references:

- https://affiliate-program.amazon.com/creatorsapi/docs/en-us/introduction
- https://affiliate-program.amazon.com/
- https://webservices.amazon.com/paapi5/documentation/ (deprecated reference only)

## Launch strategy

### Phase 1 — compliant manual catalogue

Use administrator-managed affiliate destination URLs and Associate tags without depending on a product-data API.

Store:

- Internal product ID.
- Merchant and marketplace.
- Product title written editorially by AAA.
- Destination URL.
- Associate tag.
- Supported country.
- Problem solved.
- Suitable room uses.
- Price tier rather than a hard-coded live price.
- Active/paused status.
- Last editorial review date.

### Phase 2 — Creators API adapter

Add a server-side adapter only after:

- The relevant Associates account is approved.
- Creators API eligibility is confirmed.
- Current official credential and quota documentation is accessible.
- Terms allow the intended caching, display and refresh behaviour.

The adapter must remain optional; absence of API access must not break recommendations.

## Required owner-provided information

For every launch marketplace:

- Marketplace country and domain.
- Associates account approval status.
- Associate tag/tracking ID.
- Creators API eligibility status.
- Credentials entered directly into the production secret store if issued.
- Tax and payment setup completed in the Associates dashboard.
- Approved affiliate disclosure wording.

## Proposed environment variables

Names are placeholders until current Creators API authentication is confirmed:

- `AMAZON_AFFILIATE_ENABLED=false`
- `AMAZON_DEFAULT_MARKETPLACE`
- `AMAZON_ASSOCIATE_TAG_ES`
- `AMAZON_ASSOCIATE_TAG_US`
- `AMAZON_ASSOCIATE_TAG_UK`
- `AMAZON_CREATORS_API_ENABLED=false`
- Provider-issued credential variables defined only after official confirmation.

## Marketplace routing

Recommendation resolution order:

1. User-selected country.
2. Remembered country preference.
3. Request locale or coarse country signal where legally and technically appropriate.
4. Default marketplace.

Never silently attach the wrong country's tag to another marketplace.

## Product-ranking rules

Rank products by:

1. Acoustic suitability.
2. Room use and diagnosed problem.
3. Budget tier.
4. Availability confidence.
5. Editorial quality and recency.
6. Commercial performance only after relevance thresholds pass.

Commission must never be the primary ranking factor.

## Disclosure and policy controls

- Display a clear affiliate disclosure near commercial recommendations and in the site-wide legal information.
- Do not present editorial estimates as live Amazon prices.
- Do not copy restricted product content or images unless allowed by the current programme/API terms.
- Do not cloak destinations in a way prohibited by programme rules.
- Keep merchant, marketplace and tag auditable for every click.
- Review programme terms before enabling email, push, PDF or in-app placements.

## Tracking model

Record internally before redirecting:

- Recommendation ID.
- Product ID.
- Marketplace.
- Placement.
- Diagnosed problem category.
- Timestamp.
- Anonymous session identifier where consent permits.

Do not append personal acoustic data to Amazon URLs.

## Failure behaviour

- Missing product-data API: continue with editorial catalogue.
- Missing marketplace destination: show a neutral recommendation without a broken purchase button.
- Product suspected unavailable: pause it and notify the Telegram operator.
- Missing disclosure: commercial block must not render.
- Missing Associate tag: do not claim the link is monetized.

## Staging checklist

1. Links resolve to the correct marketplace.
2. Correct Associate tag is present.
3. Disclosure is visible and understandable.
4. Click event is recorded once.
5. No personal or raw measurement data appears in the destination URL.
6. Paused products cannot be recommended.
7. Missing marketplace falls back safely.
8. Product ranking tests prove suitability precedes commercial performance.

## Open decisions

- Launch marketplaces and order.
- Approval and access status for Amazon Creators API.
- Current Creators API authentication, quotas and content-refresh requirements.
- Whether non-Amazon affiliate merchants should be supported from launch.
- Legal wording in English and Spanish.
