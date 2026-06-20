# GitHub and GitHub Actions Integration

Status: `PRODUCTION` for source control and `STAGING` for CI.

## Purpose

Provide the technical source of truth, branch isolation, pull-request review, automated validation, issue tracking, release tags and auditable changes.

## Repository

- Repository: `CedricVz/AAA`
- Protected source branch target: `master`
- Active rebuild branch: `rebuild-acoustic-ai-assistant-v2`
- Active rebuild pull request: `#3`
- Remote sprint issue: `#4`

## Delivery policy

```text
feature branch
  -> pull request
    -> typecheck
      -> production build
        -> tests
          -> human review
            -> merge
              -> Hostinger deployment
                -> tagged checkpoint
```

No direct production-code changes are executed from Telegram.

## GitHub Actions

Current workflow:

- `.github/workflows/ci.yml`
- Node.js 20
- Dependency installation
- TypeScript validation
- Production build

Future required checks:

- Unit tests.
- Integration tests.
- End-to-end smoke tests.
- Dependency and secret scanning.
- Accessibility checks.

## Permissions

- CI workflow uses read-only repository contents unless a separately reviewed workflow needs more.
- Hostinger GitHub App access must be limited to the required repository.
- Automated dependency-fix pull requests require review before merge.
- No workflow receives production credentials unless it genuinely needs them.

## Branch and merge rules

Before production launch, configure branch protection for `master`:

- Require pull request.
- Require CI checks.
- Block force pushes.
- Block deletion.
- Require conversation resolution.
- Prefer squash or reviewed merge strategy consistently.

## Secrets

GitHub Actions secrets are used only for CI or release operations that cannot be handled in Hostinger. Runtime secrets remain in the hosting environment.

Never store:

- Telegram bot token.
- WordPress Application Password.
- Google private credentials.
- Amazon credentials.
- Mobile signing material.

in files, issues, PR comments or ordinary workflow output.

## Release checkpoints

Each production phase should end with:

- Clean approved branch state.
- Passing CI.
- Merge commit recorded.
- Version tag.
- Deployment commit SHA.
- Runbook update.
- Known limitations and rollback target.

## Failure and rollback

- Failed CI blocks merge.
- Failed deployment does not trigger an emergency unreviewed patch.
- Revert the faulty change or redeploy the last tagged commit.
- Preserve logs and issue references for root-cause analysis.

## Open decisions

- Final branch-protection settings.
- Versioning scheme before first public release.
- Preview deployment mechanism.
- Automated dependency update provider.
- Signed mobile-release workflow in a later phase.
