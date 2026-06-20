# Integration Name

Status: `DISCOVERY`

Last verified against official documentation: YYYY-MM-DD.

## Purpose

Describe the business and technical reason for this integration.

## Official references

- Official documentation URL
- Policy or terms URL

## Data flow

Document data entering AAA, data leaving AAA and whether any personal or sensitive data is involved.

## Authentication and authorization

Describe credential type, owner, permission scope, rotation and revocation.

## Required owner-provided information

List account actions, identifiers and approvals. Never include secret values.

## Environment variables

List names only.

## Adapter contract

Define the internal interface and prevent provider-specific response types from leaking into the domain.

## Permissions

List the smallest required read and write scopes.

## Quotas and limits

Document current limits, retry strategy, timeout and backoff behaviour.

## Compliance and policy

Record programme rules, disclosure requirements, consent and retention constraints.

## Staging checklist

1. Authentication succeeds.
2. Invalid credentials fail safely.
3. Permissions are minimal.
4. Read operations are verified.
5. Writes are disabled until explicitly tested.
6. Logs contain no secrets.
7. Retry does not duplicate mutations.
8. Revocation is tested.

## Monitoring

Define success, latency, error and quota indicators.

## Failure behaviour

Define fallback behaviour and what the user or Telegram operator sees.

## Rollback and revocation

Describe how to disable the integration and recover affected data.

## Open decisions

List unresolved questions and owner actions.
