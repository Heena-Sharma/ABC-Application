# README #

This README would normally document whatever steps are necessary to get your application up and running.

### Assignment ###

* This application is for learning jetpack components like navigation, live Data, data binding. this projects has
single list of data which is showing using recycler view and a floating action button to show static data in a bottom sheet Dialog.
* Version- 1.0
* Reference- https://developer.android.com/jetpack/getting-started

### How do I get set up? ###
This project uses the Gradle build system. To build this project
* Summary of set up
- this project setup with MVVM, Data binding, Live Data, it Uses viewpager2 for setup Image Carousel. Recyclerview for List data and a search view to filter data in given list. To filter Data used Filter class for that.
 

### Dependencies###-
- [AppCompat] - Degrade gracefully on older versions of Android.
  'androidx.appcompat:appcompat:1.3.1'
- [Android KTX]  - Write more concise, idiomatic Kotlin code.
  'androidx.core:core-ktx:1.6.0'
- [View Binding] -  bind observable data to UI elements. enabled View Binding in gradle file.
   buildFeatures {
        viewBinding true        
    }- [Coil]- for image loading
  io.coil-kt:coil-compose:2.4.0
-[Material UI]- For creating UI using support material dependency.
'com.google.android.material:material:1.4.0'
-[Constraint Layout]- for Creating UI Used constraint layout for container in which we can add other views and widgets also.
'androidx.constraintlayout:constraintlayout:2.1.0'
-[Lifecycles] - Create a UI that automatically responds to lifecycle events.
'androidx.core:core-ktx:1.6.0'
- [LiveData]- Build data objects that notify views when the underlying database changes.
'androidx.core:core-ktx:1.6.0'
- [Hilt]- for [dependency injection]
"com.google.dagger:hilt-android:2.38.1"




### Screenshots###

-[screen view1](screenshots/screenview1.png "A list of Data")
-[screen view2](screenshots/screenview2.png "Collapsed View ")
-[screen view3](screenshots/screenview3.png "Filter Data")
-[screen view4](screenshots/screenview3.png "Bottom sheet dialogue")
