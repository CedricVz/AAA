# WordPress REST API Integration

Status: `DISCOVERY`

Checked against the official WordPress REST API authentication handbook on 2026-06-21.

## Purpose

Provide a mobile-friendly editorial backend for articles, guides, product records, affiliate destinations and recommendation content without coupling the acoustic domain to WordPress.

## Official references

- https://developer.wordpress.org/rest-api/
- https://developer.wordpress.org/rest-api/using-the-rest-api/authentication/
- https://developer.wordpress.org/rest-api/reference/posts/
- https://developer.wordpress.org/rest-api/reference/media/

## Authentication decision

Use WordPress Application Passwords over HTTPS with a dedicated restricted service user.

WordPress includes Application Passwords from version 5.6. They are generated from the user's profile and can be used with HTTPS Basic Authentication. Do not use the separate Basic Authentication plugin in production.

## Required owner-provided information

- CMS base URL.
- WordPress version.
- Service-user username.
- Application Password entered directly into Hostinger.
- Confirmation of available roles and capabilities.
- Decision on content model.

## Environment variables

- `WORDPRESS_BASE_URL`
- `WORDPRESS_USERNAME`
- `WORDPRESS_APPLICATION_PASSWORD`
- `WORDPRESS_WRITES_ENABLED=false`

## Proposed content model

### Standard posts

Use for editorial articles and guides when standard WordPress categories, authors and revisions are sufficient.

### Custom post types

Evaluate for:

- `affiliate_product`
- `comparison`
- `recommendation_rule_content`

Custom types must explicitly expose REST API support. Product fields should be structured rather than embedded only in prose.

## Adapter boundary

AAA domain code must depend on a `ContentRepository` or equivalent interface. The WordPress adapter translates REST responses into internal entities.

Forbidden:

- Importing WordPress response types into acoustic-domain modules.
- Placing WordPress credentials in client-side code.
- Publishing directly from natural-language Telegram input without preview and confirmation.

## Minimum service-user permissions

Start with the smallest role that can:

- Read required posts and media.
- Create and edit drafts.
- Upload approved media.
- Avoid plugin, theme, user and site-setting administration.

Publishing and deletion permissions should be withheld until the staged workflow is validated.

## Initial API operations

Read-only phase:

- List published posts.
- Retrieve one post by ID or slug.
- List categories and tags.
- Retrieve media metadata.

Controlled-write phase:

- Create a draft.
- Update an existing draft.
- Upload media.
- Schedule or publish only after explicit confirmation.

## Staging test checklist

1. Public read operations work without credentials where intended.
2. Authenticated draft listing works over HTTPS.
3. Invalid Application Password returns an authorization failure.
4. Service user cannot manage plugins, themes or users.
5. Draft creation records the correct author and status.
6. Media upload validates MIME type and size.
7. Update conflicts do not overwrite a newer revision silently.
8. Telegram confirmation maps to exactly one CMS mutation.
9. Adapter failure produces no partial catalogue update.
10. Credential revocation immediately prevents writes.

## Privacy and content safety

- Do not send private audio or measurement data to WordPress.
- Strip unsafe HTML before rendering external content.
- Validate outbound affiliate URLs against approved merchant domains.
- Preserve WordPress revisions for reversible editorial changes.

## Monitoring

Record:

- Operation type.
- WordPress content ID.
- Draft or publish status.
- Actor and Telegram action ID when applicable.
- HTTP status and sanitized error category.

Never log the Authorization header or Application Password.

## Rollback and revocation

- Revoke the Application Password from the WordPress user profile.
- Set `WORDPRESS_WRITES_ENABLED=false`.
- Revert content through WordPress revisions or the AAA action log.
- Replace the WordPress adapter without changing the domain contracts.

## Open decisions

- Existing WordPress installation versus a dedicated CMS site.
- Product custom post type and field implementation.
- Editorial languages at launch.
- Image transformation and caching strategy.
- Whether WordPress remains on the same Hostinger plan or a separate site slot.
