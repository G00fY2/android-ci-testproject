import com.github.benmanes.gradle.versions.reporter.result.DependencyOutdated
import com.github.benmanes.gradle.versions.reporter.result.Result

plugins {
  id(Plugins.Android.application) version Versions.androidGradle apply false
  kotlin(Plugins.Kotlin.androidGradle) version Versions.kotlin apply false
  id(Plugins.Misc.nanoGiantsVersioning) version Versions.nanoGiantsVersioning apply false
  id(Plugins.Misc.gradleVersions) version Versions.gradleVersions
  id(Plugins.Quality.sonarqube) version Versions.sonarqube
  id("io.github.g00fy2.github-issue-plugin")
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
  outputFormatter = closureOf<Result> {
    val outputLines = mutableListOf<String>()
    if (gradle.current.isUpdateAvailable && gradle.current > gradle.running) {
      outputLines.add("Gradle: [${gradle.running.version} -> ${gradle.current.version}]")
    }
    outdated.dependencies.filterIsInstance<DependencyOutdated>().forEach {
      outputLines.add("${it.group}:${it.name} [${it.version} -> ${it.available.milestone}]")
    }
    file(outputDir).mkdirs()
    file(File(outputDir, "ci-report.txt")).writeText(outputLines.joinToString("\n"))
    println(outputLines.joinToString("\n"))
  }
}

tasks.register<Delete>("clean") {
  delete(buildDir)
}