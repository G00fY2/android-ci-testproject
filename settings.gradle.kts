include(":app")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
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
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android") useModule("com.android.tools.build:gradle:${requested.version}")
        }
    }
}