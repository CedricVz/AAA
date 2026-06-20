# Hostinger Node.js Integration

Status: `DESIGNED`

Checked against official Hostinger documentation on 2026-06-21.

## Purpose

Host the public Next.js application, provide HTTPS, runtime environment variables, deployment logs, restart controls and resource monitoring.

## Official references

- https://www.hostinger.com/support/how-to-deploy-a-nodejs-website-in-hostinger/

## Confirmed platform capabilities

- Business hosting supports Node.js web applications.
- Next.js is listed as a supported frontend and backend framework.
- Supported Node.js versions include 18, 20, 22 and 24.
- Deployment can import a GitHub repository.
- hPanel exposes deployments, environment variables, settings, redeploy, file manager and resource graphs.
- Server-side Next.js applications can be restarted without a full redeployment.
- Hostinger runs npm build commands during deployment; Business and Cloud plans do not expose those npm commands through SSH.

## Proposed configuration

- Framework: Next.js server application.
- Runtime: Node.js 20 until a later controlled upgrade.
- Source: `CedricVz/AAA` through Hostinger GitHub integration.
- Production branch: `master` only after reviewed PR merge.
- Staging branch or preview environment: to be confirmed based on available Hostinger website slots.
- Build command: `npm run build`.
- Start command: `npm run start` or Hostinger-detected standalone start configuration.
- Build output: `.next` / standalone output, subject to deployment validation.

## Required owner actions

- Select the final domain and staging strategy.
- Authorize the Hostinger GitHub App for `CedricVz/AAA`.
- Select the repository and production branch in hPanel.
- Add environment variables directly in hPanel.
- Confirm the deployment screenshot and public HTTPS URL.

## Environment variables

Hostinger will hold production values for:

- `APP_ENV`
- `APP_BASE_URL`
- `TELEGRAM_BOT_TOKEN`
- `TELEGRAM_WEBHOOK_SECRET`
- `TELEGRAM_OWNER_USER_ID`
- `TELEGRAM_OWNER_CHAT_ID`
- `WORDPRESS_BASE_URL`
- `WORDPRESS_USERNAME`
- `WORDPRESS_APPLICATION_PASSWORD`
- Google and affiliate variables introduced by later approved phases.

No secret value belongs in GitHub.

## Deployment checklist

1. CI passes on the exact commit.
2. PR is reviewed and merged to the approved deployment branch.
3. Required variables exist in hPanel.
4. Hostinger build succeeds.
5. Health endpoint returns success.
6. Home page, diagnostic flow and metadata load over HTTPS.
7. Telegram webhook remains disabled until its dedicated test passes.
8. Deployment commit SHA is recorded in the checkpoint.

## Monitoring

Review in hPanel:

- Last deployment status and logs.
- CPU, RAM and I/O usage.
- Vulnerability reports for npm packages.
- Application availability and restart behaviour.

## Rollback

Preferred rollback is a Git revert or redeployment of a known tagged commit. Removing the Hostinger website is not a normal rollback because Hostinger documents that removing the website deletes associated deployments and configuration.

## Open decisions

- Whether Business hosting provides enough separate website slots for a permanent staging deployment.
- Exact start command detected for Next.js standalone output.
- Domain and DNS configuration.
- Health-check route and uptime monitor provider.
