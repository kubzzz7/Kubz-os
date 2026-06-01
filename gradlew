#!/usr/bin/env sh
# Minimal gradlew that delegates to system Gradle (provided by gradle action) or falls back to wrapper jar if present.
if command -v gradle >/dev/null 2>&1; then
  exec gradle "$@"
fi
if [ -f "gradle/wrapper/gradle-wrapper.jar" ]; then
  exec java -jar "gradle/wrapper/gradle-wrapper.jar" "$@"
fi
echo "No Gradle executable or wrapper found. Please install Gradle or add gradle/wrapper/gradle-wrapper.jar" >&2
exit 1
