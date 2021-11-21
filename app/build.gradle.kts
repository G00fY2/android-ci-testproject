plugins {
  id(Plugins.Android.application)
  id(Plugins.Kotlin.android)
  id(Plugins.Misc.nanoGiantsVersioning)
}

android {
  compileSdk = Versions.androidCompileSdk
  buildToolsVersion = Versions.androidBuildTools
  defaultConfig {
    applicationId = "io.github.g00fy2.quickiesample"
    minSdk = Versions.androidMinSdk
    targetSdk = Versions.androidTargetSdk
    versionCode = versioning.getVersionCode()
    versionName = versioning.getVersionName()
    setProperty("archivesBaseName", "testproject")
  }
  buildTypes {
    getByName("release") {
      isShrinkResources = true
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {
  implementation(Deps.AndroidX.appcompat)
  implementation(Deps.AndroidX.constraintLayout)
}