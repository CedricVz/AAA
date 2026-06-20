# Telegram Bot API Integration

Status: `IMPLEMENTED_DISABLED`

Checked against Telegram Bot API 10.1 documentation on 2026-06-21.

## Purpose

Provide Cedric with a private mobile operator for status, analytics, drafts, affiliate catalogue maintenance and controlled approvals.

## Official reference

- https://core.telegram.org/bots/api

## Transport

- Telegram sends HTTPS POST requests to the configured webhook URL.
- Webhook path: `/api/telegram/webhook`.
- `getUpdates` and webhooks are mutually exclusive.
- Configure only the update types required by AAA.
- Duplicate or out-of-order updates must be handled using `update_id` when stateful actions are introduced.

## Authentication and authorization

Four controls are required:

1. Bot token authenticates AAA when calling Telegram.
2. Webhook `secret_token` is validated from `X-Telegram-Bot-Api-Secret-Token`.
3. Numeric Telegram user ID must match the owner allowlist.
4. Numeric private chat ID must match the authorized chat.

Telegram user and chat identifiers must be stored as 64-bit-safe values or strings.

## Environment variables

- `TELEGRAM_BOT_TOKEN`
- `TELEGRAM_WEBHOOK_SECRET`
- `TELEGRAM_OWNER_USER_ID`
- `TELEGRAM_OWNER_CHAT_ID`
- `TELEGRAM_WRITES_ENABLED=false`

## Command risk classes

### Read-only

- `/status`
- `/help`
- `/analytics`
- `/drafts`
- `/products`
- `/health`

### Reversible write with confirmation

- Create or edit a draft.
- Update a product record.
- Pause an affiliate recommendation.
- Schedule approved content.

### Destructive or high risk

- Publish immediately.
- Delete content.
- Bulk modifications.
- Change recommendation rules.
- Trigger a deployment.

High-risk commands require a preview, explicit confirmation token, expiry time and immutable audit result. Telegram must never push code directly to production.

## Webhook registration data

- URL: `https://{domain}/api/telegram/webhook`
- `secret_token`: generated random value using only allowed characters.
- `allowed_updates`: initially `message` and later `callback_query`.
- `drop_pending_updates`: true for a clean first staging registration.

## Staging test checklist

1. Missing secret returns HTTP 401.
2. Wrong secret returns HTTP 401.
3. Unauthorized user receives no operational data.
4. Authorized owner can run `/status`.
5. Unsupported message is acknowledged safely.
6. No token, ID or internal error appears in responses or logs.
7. Telegram retries do not duplicate actions.
8. Webhook status is verified with `getWebhookInfo`.

## Failure behaviour

- Missing configuration: return controlled service-unavailable behaviour and perform no action.
- Telegram delivery failure: record the error without exposing the bot token.
- CMS or analytics unavailable: respond with a temporary failure and no partial write.
- Ambiguous natural-language request: create a preview or ask for a bounded choice; never guess a destructive action.

## Owner-provided information

- Bot username created through BotFather.
- Bot token entered directly in Hostinger.
- Cedric's numeric user ID.
- Authorized private chat ID.
- Random webhook secret.

## Future capabilities

Telegram Bot API 10.1 includes rich-message functionality, but AAA should first use ordinary text and inline keyboards for reliability. Rich responses may be evaluated later for analytics summaries and structured approval cards.
