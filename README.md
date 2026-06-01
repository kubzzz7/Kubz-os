# KubzOS Launcher (MVP)

KubzOS is a next-generation Android personalization platform with a Liquid Glass interface, deep customization, AI tools, and an adaptive home experience.

This repository contains a minimal Kotlin + Jetpack Compose launcher MVP:
- HOME launcher Activity
- Home screen app grid
- App drawer with search
- Theme toggle + simple glass demo placeholder

Minimum SDK: 26

How to get an APK (no Android Studio required):
1. After this branch is pushed, a GitHub Actions workflow will run and build a debug APK automatically.
2. Visit: https://github.com/kubzzz7/Kubz-os/actions → select the latest workflow run → Artifacts → download "kubz-os-debug-apk".

Local build (if you later want to build locally):
- ./gradlew assembleDebug
- The debug APK will be at app/build/outputs/apk/debug/app-debug.apk

License: MIT
