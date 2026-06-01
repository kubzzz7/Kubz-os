@echo off
rem Minimal gradlew.bat that delegates to system Gradle if available or falls back to wrapper jar.
where gradle >nul 2>&1
if %errorlevel%==0 (
  gradle %*
  exit /b %errorlevel%
)
if exist gradle\wrapper\gradle-wrapper.jar (
  java -jar gradle\wrapper\gradle-wrapper.jar %*
  exit /b %errorlevel%
)
echo No Gradle executable or wrapper found. Please install Gradle or add gradle\wrapper\gradle-wrapper.jar
exit /b 1
