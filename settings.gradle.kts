rootProject.name = "android-ci-testproject"

include(":app")

dependencyResolutionManagement {
    repositories {
      google()
      gradlePluginPortal()
      mavenCentral()
    }
}

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
    }
}