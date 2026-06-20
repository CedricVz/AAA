# Google Platform Integrations

Status: `DISCOVERY`

Checked against official Google developer and AdSense documentation on 2026-06-21.

This document covers Google Analytics 4, Search Console and AdSense. AdMob remains a separate future mobile-distribution integration.

## 1. Google Analytics 4

### Purpose

Measure tool usage, content engagement, PWA adoption and affiliate funnel events.

Official references:

- https://developers.google.com/analytics/devguides/collection/ga4
- https://developers.google.com/analytics/devguides/reporting/data/v1

The GA4 Data API can generate custom, batch, pivot and realtime reports and can support dashboards and automated reporting.

### Required owner-provided information

- GA4 Measurement ID.
- GA4 Property ID.
- Google account or service identity with the minimum reporting access.
- Google Cloud project used for the Data API.
- Consent implementation decision for EEA/UK traffic.

### Proposed environment variables

- `NEXT_PUBLIC_GA4_MEASUREMENT_ID`
- `GA4_PROPERTY_ID`
- Server-side Google credential variables selected during implementation.
- `GOOGLE_REPORTING_ENABLED=false`

Only the Measurement ID may be exposed to the browser. Reporting credentials remain server-side.

### Initial event taxonomy

- `diagnosis_started`
- `diagnosis_completed`
- `treatment_plan_viewed`
- `sound_test_started`
- `sound_test_completed`
- `affiliate_product_viewed`
- `affiliate_link_clicked`
- `article_viewed`
- `pwa_install_prompt_shown`
- `pwa_installed`

Event parameters must avoid raw audio, room addresses, message text and unnecessary personal data.

### Telegram reporting scope

Read-only summaries may include:

- Active users.
- Tool starts and completions.
- Completion rate.
- Top landing pages.
- Top countries and devices at an aggregate level.
- Affiliate click events.

## 2. Google Search Console

### Purpose

Identify SEO opportunities, monitor indexing and automate search-performance summaries.

Official reference:

- https://developers.google.com/webmaster-tools/v1/api_reference_index

The Search Console API provides Search Analytics, Sitemap, Site and URL Inspection services.

### Approved initial use

Read-only:

- Query clicks, impressions, CTR and average position.
- Group by date, query, page, country and device.
- List sitemap status.
- Inspect selected important URLs.

Write operations such as submitting or deleting sitemaps remain disabled until separately approved.

### Required owner-provided information

- Verified Search Console property.
- Property URL format used by the API.
- Google identity with access to the property.
- Google Cloud project and enabled API.

### Proposed environment variables

- `SEARCH_CONSOLE_SITE_URL`
- Shared or dedicated server-side Google credential variables.
- `SEARCH_CONSOLE_WRITES_ENABLED=false`

### Telegram reporting opportunities

- Queries gaining impressions.
- Pages with high impressions and low CTR.
- Pages close to first-page positions.
- Indexing problems on priority URLs.
- Sitemap errors.

## 3. Google AdSense

### Purpose

Monetize eligible editorial and secondary tool-result pages without obstructing the core acoustic experience.

Official references:

- https://support.google.com/adsense/answer/9274019
- https://support.google.com/adsense/answer/12171612
- https://developers.google.com/adsense/management

Google documents separate code paths for Auto ads and individual ad units. For Auto ads, the AdSense code is inserted in the page head. AdSense also strongly recommends an `ads.txt` file at the site root containing the account's publisher record.

### Required owner-provided information

- Approved AdSense account.
- Site added and accepted in AdSense.
- Publisher ID.
- Auto ads decision or approved ad-unit IDs.
- Consent-management platform decision for regulated regions.
- Seller-information and ads.txt status reviewed.

### Proposed environment variables

- `NEXT_PUBLIC_ADSENSE_PUBLISHER_ID`
- `ADSENSE_ENABLED=false`
- Approved ad-unit identifiers if manual units are used.
- Server-side reporting credentials only if the AdSense Management API is later enabled.

### Placement policy for AAA

Do not place advertising:

- Before microphone permission disclosure.
- Inside the primary measurement controls.
- Where it can be mistaken for a recommended acoustic action.
- Between a safety disclaimer and its result.
- In a way that causes accidental clicks on mobile.

Preferred initial placements:

- Editorial guides.
- Comparison pages.
- Below completed tool results.
- Non-blocking in-content placements after sufficient original content.

### ads.txt requirement

Serve `/ads.txt` from the production domain root. The exact publisher line must come from the AdSense account and must not be guessed or committed before the real publisher ID is available.

### Reporting

The AdSense Management API can retrieve earnings reports and manage inventory, but it is not required for launch. Begin with dashboard review and add read-only API reporting only when Telegram revenue summaries become useful.

## Consent, privacy and data governance

Before production analytics or advertising:

- Decide consent categories and default states.
- Prevent optional tags from loading before required consent where applicable.
- Document cookie and local-storage usage.
- Provide withdrawal and preference-management paths.
- Keep analytics identifiers separate from acoustic measurement data.
- Validate the implementation against current Google requirements and applicable EEA/UK law.

## Combined staging checklist

1. GA4 debug mode receives only approved events.
2. No raw audio or free-text Telegram content is sent to GA4.
3. Data API identity can read only the intended property.
4. Search Console identity can read only the intended property.
5. Search queries and page reports are aggregated safely.
6. AdSense code remains disabled until account/site approval.
7. `/ads.txt` is not published with placeholder data.
8. Consent choices control optional tags correctly.
9. Telegram reporting is read-only and handles unavailable APIs safely.
10. Revoking Google credentials disables reports without breaking the public tools.

## Open decisions

- Consent-management solution.
- GA4 client-side tagging versus Tag Manager.
- Google credential strategy for server-side reporting.
- Auto ads versus manually controlled units.
- Earliest traffic/content threshold for submitting the site to AdSense.
- Whether AdSense revenue reports should appear in Telegram during the first release.
