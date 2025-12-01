# Contributing to the Gambling Plugin

Thank you for considering contributing! This document explains how to contribute code, report issues, and follow the project's standards.

---

## 📌 How to Contribute

### 1. Fork the Repository

Create a personal fork of the project to work on.

### 2. Create a New Branch

Use this format for branch names:
```
dev/<version>
dev/<version>/<feature>
```

Use a descriptive name, for example:

```
dev/0.6.0
dev/0.6.0/auto-save
```

### 3. Write Clean, Documented Code

* Use meaningful method and variable names.
* Add comments for complex logic.
* Keep classes focused and maintainable.
* Follow Java conventions (camelCase, PascalCase, etc.).

### 4. Open a Pull Request (PR)

Include in your PR:

* What you changed
* Why you changed it
* How you tested it
* Anything reviewers should know

---

## 📐 Code Style Guidelines

* Use standard Java formatting.
* Avoid unnecessary complexity.
* Keep spacing clean and consistent.


---

## 🔍 Reporting Bugs

Please include:

* Server version (e.g., Paper 1.20.4)
* Plugin version
* Steps to reproduce
* Logs or error messages if available

---

## Branching Model

This project uses a simple and clear branching strategy to keep development organized.

### `main` branch
- Contains only versions that **run** (with or without minor bugs).
- No unfinished features.
- All official releases (stable or pre-release) are created from `main`.
- Hotfixes and small patches are applied directly to `main`.

### `dev/<version>` branches
- Used for developing the next milestone version.
- May contain unfinished, experimental, or unstable features.
- Merged into `main` only when all planned features for that version are complete and the plugin runs without crashing.

### Workflow
1. Create a new branch: `dev/<version>`
2. Implement features for that version
3. Ensure the plugin loads and runs
4. Merge `dev/<version>` → `main`
5. Delete the dev branch after merging

---

## Versioning Policy

This project follows semantic versioning-style principles, adapted for plugin development.

### Version Structure: `MAJOR.MINOR.PATCH`

- **MAJOR**: Big feature expansions or breaking changes.  
  Example: v2.0.0 (Stock/Crypto System)

- **MINOR**: New features or major gameplay additions.  
  Example: v0.5.0 (GUI selector)

- **PATCH**: Bug fixes and small improvements.  
  Example: v0.2.3 (Fixes coinflip payout bug)

---

## Release Strategy

Releases are created exclusively from the `main` branch.

### Pre-Releases
Used for:
- Early testing
- Milestone previews
- Unstable or experimental features

Marked as:
- SNAPSHOT (very early previews, features may be incomplete)
- alpha (features are complete but not tested yet)
- beta (second round of testing)

### Stable Releases
Created only when:
- Features for the version are complete
- Plugin runs without errors
- Breaking bugs are resolved

### Release Workflow
1. Merge `dev/<version>` → `main` (if not already done)
2. Create a GitHub Release
3. Create a new tag for the release (e.g., `v0.6.0`)
4. Upload the compiled .jar
5. Write clear release notes
6. Mark as pre-release if necessary
7. Publish the release


## ⚖️ Licensing

By contributing to this repository, you agree that your contributions are licensed under the same license as the project.

---

## 🤝 Thank You

Every contribution—big or small—is appreciated!
