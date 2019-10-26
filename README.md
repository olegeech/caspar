# Caspar-Health Selenium + Selenide multi-platform UI/API functional test framework
_Implemented only flow described in the test task (only Web test option using Chrome browser implemented so far)._

## Run tests
Tests can be run using commands `./gradlew test` for Linux, MacOS and `gradlew.bat test` for Windows.

## Config
Tests can be configured using the `../src/test/resources/config.properties` (so far only Web base URL and platform)

## Test structure
Test with the flow can be found here - `../src/test/kotlin/com/caspar/health/ui/CasparHealthUiTests.kt`