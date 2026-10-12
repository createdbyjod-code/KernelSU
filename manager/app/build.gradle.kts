name: Build Manager

on:
  workflow_dispatch:
  push:
    branches:
      - main
    paths:
      - 'manager/**'
      - '.github/workflows/build-manager.yml'

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
        with:
          fetch-depth: 0

      - name: Set up JDK 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'

      - name: Setup Gradle
        uses: gradle/actions/setup-gradle@v3

      - name: Build with Gradle
        run: |
          cd manager
          ./gradlew assembleRelease

      - name: Upload Artifact
        uses: actions/upload-artifact@v4
        with:
          name: manager-release
          path: manager/app/build/outputs/apk/release/*.apk
