plugins {
  id(Plugins.Android.application) version Versions.androidGradle apply false
  kotlin(Plugins.Kotlin.androidGradle) version Versions.kotlin apply false
  id(Plugins.Misc.nanoGiantsVersioning) version Versions.nanoGiantsVersioning apply false
  id(Plugins.Misc.gradleVersions) version Versions.gradleVersions
  id(Plugins.Quality.sonarqube) version Versions.sonarqube
}

sonarqube {
  properties {
    property("sonar.projectKey", "G00fY2_android-ci-testproject")
    property("sonar.organization", "g00fy2")
    property("sonar.host.url", "https://sonarcloud.io")
  }
}

tasks.dependencyUpdates.configure {
  gradleReleaseChannel = "current"
  rejectVersionIf {
    (Versions.maturityLevel(candidate.version) < Versions.maturityLevel(currentVersion)) ||
      candidate.module == "org.jacoco.ant"
  }
}

tasks.register<Delete>("clean") {
  delete(buildDir)
}