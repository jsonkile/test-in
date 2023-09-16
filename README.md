# Android Testing Exhibition
A sample repo to demonstrate testing in Android using guidelines from Google's [documentation](https://developer.android.com/training/testing)

<img width="250" alt="Screenshot 2023-09-16 at 14 35 38" src="https://github.com/jsonkile/test-in/assets/20788593/cb9b7583-2414-4262-8025-fceaeec3c447">
<img width="250" alt="Screenshot 2023-09-16 at 14 36 04" src="https://github.com/jsonkile/test-in/assets/20788593/fb3e8233-5b2d-48d8-afb1-2d2e884ae0c6">


## Architecture
The app follows Google's official architecture [guidance](https://developer.android.com/topic/architecture)

## Overview
The app uses dependency injection with Hilt to facilitate the testing of components.

Most data layer components are defined as interfaces. Then, concrete implementations (with various dependencies) are bound to provide those interfaces to other components in the app. In tests, the app does not use any mocking libraries. Instead, the production implementations can be replaced with test doubles using Hilt's testing APIs (or via manual constructor injection).

These test doubles implement the same interface as the production implementations and generally provide a simplified (but still realistic) implementation with additional testing hooks. This results in less brittle tests that may exercise more production code, instead of just verifying specific calls against mocks.

## Key Tools and Libraries 
1. Dependency Injection - **[Hilt](https://developer.android.com/training/dependency-injection/hilt-android)**
2. User Interface - **[Jetpack Compose](https://developer.android.com/jetpack/compose)**
3. Network - **[Ktor](https://ktor.io)**
4. Caching - **[Room](https://developer.android.com/training/data-storage/room)**
5. Navigation - **[Navigation Component for Compose](https://developer.android.com/jetpack/compose/navigation)**
6. Screenshot Testing - **[Roborazzi](https://github.com/takahirom/roborazzi)**

## Breakdown
### Local Tests
Unit tests can be found [here](https://github.com/jsonkile/test-in/tree/main/app/src/test/java/com/jsonkile/testin)  
No mocking library was used. Dependencies are provided via fakes instead.  
Includes: repositories test, view model tests, ktor client tests, data sources tests, room tests...

### Instrumented Tests
Instrumented tests can be found [here](https://github.com/jsonkile/test-in/tree/main/app/src/androidTest/java/com/jsonkile/testin)  
Hilt is used to provide fakes.  
Includes: compose UI tests, integration tests, navigation tests...

### End-to-end Tests
User flow tests can be found [here](https://github.com/jsonkile/test-in/tree/main/app/src/androidTest/java/com/jsonkile/testin/endtoend)  
Hilt is used to provide fakes.  
Used [UIAutomator](https://developer.android.com/training/testing/other-components/ui-automator) for writing automated tests.

### Screenshot Tests
Roborazzi is used to do screenshot tests of screens and components.  
To run these tests, run the `verifyRoborazziDemoDebug` or `recordRoborazziDemoDebug` tasks. Note that screenshots are recorded on CI, using Linux, and other platforms might generate slightly different images, making the tests fail.

## Highlights
Some important things to note about my approach 
1. No mocks just fakes
2. No actual network calls are made during tests, thanks to ktor mock engine
3. Single Activity
4. Single Module with only data and UI packages
