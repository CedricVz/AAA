# Mobile Distribution and AdMob Integration

Status: `NOT_STARTED`

Checked against official Capacitor, Google Play, Android, Apple and AdMob documentation on 2026-06-21.

## Purpose

Package the validated web application for Android and iOS while preserving one shared product core and introducing native capabilities only where they add measurable value.

## Official references

- https://capacitorjs.com/docs
- https://support.google.com/googleplay/android-developer/answer/6112435
- https://developer.android.com/studio/publish/app-signing
- https://developer.apple.com/programs/
- https://developer.apple.com/help/account/identifiers/register-an-app-id/
- https://developers.google.com/admob/android/quick-start
- https://developers.google.com/admob/ios/quick-start

## Packaging decision

Use Capacitor after the PWA and responsive web product are validated. Capacitor provides a native container for modern web applications and access to native SDKs through plugins.

Do not maintain separate feature implementations for web, Android and iOS unless platform policy or hardware behaviour requires it.

## Release order

1. Responsive web application.
2. Installable PWA.
3. Android internal testing.
4. Android production after policy and device validation.
5. iOS TestFlight.
6. iOS production after Android and revenue evidence.

## Identifiers requiring owner approval

Suggested values must be reviewed before registration because store identifiers are long-lived:

- Android application ID, for example `com.cevecom.acousticaiassistant`.
- Apple bundle identifier using the same reverse-domain convention.
- Public developer name shown in each store.
- Support URL, privacy URL and marketing URL.

## Google Play requirements

- Google Play Console developer account.
- Developer Distribution Agreement acceptance.
- One-time registration fee and identity verification.
- Organization versus personal account decision.
- New personal accounts may have additional testing and device-verification requirements.
- Android App Bundle and signing configuration.
- Store listing, privacy declarations, data safety form and content rating.

## Android signing policy

- Use Play App Signing where appropriate.
- Keep the upload key outside GitHub.
- Document key custodian, encrypted backup and recovery process.
- CI may use an encrypted signing secret only after the release workflow is separately approved.
- Debug keys must never sign production artifacts.

## Apple requirements

- Active Apple Developer Program membership.
- Registered App ID and bundle identifier.
- App Store Connect application record.
- Signing certificates and provisioning configuration.
- Privacy details, screenshots, support information and review notes.
- TestFlight testing before public release.

Certificates, private keys and provisioning secrets never enter ordinary repository files or chat.

## Native microphone and local AI

Before replacing browser audio APIs with native plugins, test:

- Permission prompts and denial handling.
- Sample-rate consistency.
- Background and interruption behaviour.
- Device-to-device variance.
- Local model performance, memory and battery use.
- Privacy disclosures and store data declarations.

The same educational limitation remains: mobile microphones are not calibrated professional sound-level meters.

## AdMob strategy

Use AdMob only in packaged mobile apps. Web AdSense code must not simply be embedded as the mobile-app advertising implementation.

Required information:

- AdMob account status.
- Android App ID and ad-unit IDs.
- iOS App ID and ad-unit IDs.
- Consent and privacy configuration.
- Test device configuration.

Proposed variables or native configuration placeholders:

- `ADMOB_ENABLED=false`
- `ADMOB_ANDROID_APP_ID`
- `ADMOB_IOS_APP_ID`
- Platform-specific ad-unit IDs.

Use Google's test ads during development. Production ad units remain disabled until store, consent and placement review are complete.

## Advertising placement rules

Never place mobile ads:

- Adjacent to microphone start/stop controls.
- Where a tap can be mistaken for an acoustic recommendation.
- During a timed sound measurement.
- Before the user understands microphone and privacy behaviour.
- In a way that obscures safety or calibration disclaimers.

## Store staging checklist

1. PWA workflow is stable first.
2. Capacitor version is pinned and documented.
3. Android and iOS builds use the same approved web release.
4. Microphone permission is contextual and reversible.
5. Offline and poor-network states are tested.
6. External affiliate links open safely and comply with store and affiliate rules.
7. Test ads only are used in non-production builds.
8. Privacy labels and data safety declarations match actual behaviour.
9. Signing material is recoverable but inaccessible from the repository.
10. Release artifact maps to a Git tag and commit SHA.

## Open decisions

- Final package and bundle identifiers.
- Developer account type and public developer identity.
- Whether organization verification uses CEVECOM LLC or another entity.
- Native audio plugin requirements.
- AdMob launch timing.
- CI/CD provider for signed mobile releases.
