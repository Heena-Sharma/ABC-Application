# README #

This README would normally document whatever steps are necessary to get application up and running.

### Assignment ###

* This application is sample app for jetpack compose components. this projects has
single list of data with carousel with search bar and a floating action button to show data in a bottom sheet Dialog.
* Version- 1.0
* Reference- https://developer.android.com/develop/ui/compose
* Reference- https://developer.android.com/develop/ui/compose/setup


### How do I get set up? ###
This project uses the Gradle build system. To build this project
* Summary of set up
- this project setup with MVVM and hilt and ksp, Used json data from assets folder named with "testdata.json".

### Dependencies###-

Here are some of the core Jetpack Compose dependencies, along with their usage:
Core Dependencies
[androidx.compose.ui:ui]: The foundation of Jetpack Compose. Provides the basic building blocks for creating UI elements like Text, Image, Button, and layout components like Column, Row, and Box.
implementation("androidx.compose.ui:ui:1.3.0")
- [androidx.compose.material:material]: Provides Material Design components for Jetpack Compose, including pre-built UI elements like AppBar, FloatingActionButton, BottomNavigation, and more.
  implementation("androidx.compose.material:material:1.3.0") // Replace with the latest version
 - [androidx.compose.ui:ui-tooling-preview]: Enables previewing composables directly within Android Studio.
- implementation("androidx.compose.ui:ui-tooling-preview:1.3.0") // Replace with the latest version
  [androidx.compose.material3:material3]: Provides Material Design 3 components for Jetpack Compose, offering an updated look and feel compared to Material Design 2.
  implementation("androidx.compose.material3:material3:1.0.0") // Replace with the latest version
- [androidx.compose.ui:ui-test-junit4]: Provides testing utilities for Jetpack Compose UIs.
- androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.3.0") // Replace with the latest version
  [androidx.compose.runtime:runtime-livedata]: Enables integration between Jetpack Compose and LiveData, allowing you to observe LiveData objects and trigger recomposition when their values change.
- implementation("androidx.compose.runtime:runtime-livedata:1.3.0") // Replace with the latest version
- [androidx.navigation:navigation-compose]: Enables navigation between different composable screens using the Jetpack Navigation component.
- implementation("androidx.navigation:navigation-compose:2.5.0") // Replace with the latest version
  [androidx.hilt:hilt-navigation-compose]: Provides integration between Hilt and Jetpack Compose Navigation, allowing you to inject dependencies into your composable screens.
  implementation("androidx.hilt:hilt-navigation-compose:1.0.0") // Replace with the latest version
- [androidx.compose.ui:ui-util]: Provides utility functions and classes for Jetpack Compose, such as rememberSaveable for preserving state across configuration changes.
- implementation("androidx.compose.ui:ui-util:1.3.0") // Replace with the latest version- 
- [GSON]- Used for converting Json to POJO .
'com.google.code.gson:gson:2.8.6'
-[Lifecycles] - Create a UI that automatically responds to lifecycle events.
'androidx.lifecycle.lifecycle-runtime-ktx-2.6.1'
- [Coil]- for image loading
  io.coil-kt:coil-compose:2.4.0




### Screenshots###

-[screenview1](screenshots/Screenview1.png "A list of Data")
-[screenview2](screenshots/screenview2.png "Collapsed View ")
-[screenview3](screenshots/screenview3.png "Filter Data")
-[screenview4](screenshots/screenview3.png "Bottom sheet dialogue")
