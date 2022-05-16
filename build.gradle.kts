import com.github.benmanes.gradle.versions.reporter.result.DependencyOutdated
import com.github.benmanes.gradle.versions.reporter.result.Result

plugins {
  id(Plugins.Android.application) version Versions.androidGradle apply false
  kotlin(Plugins.Kotlin.androidGradle) version Versions.kotlin apply false
  id(Plugins.GradleVersions.gradleVersions) version Versions.gradleVersions
  id(Plugins.Quality.sonarqube) version Versions.sonarqube
  id("io.github.g00fy2.github-issue-plugin")
}

sonarqube {
  properties {
    property("sonar.projectKey", "G00fY2_android-ci-testproject")
    property("sonar.organization", "g00fy2")
    property("sonar.host.url", "https://sonarcloud.io")
    property("sonar.projectDescription", "Sample project to test continuous integration platforms")
    property("sonar.links.homepage", "https://github.com/G00fY2/android-ci-testproject")
    property("sonar.links.ci", "https://github.com/G00fY2/android-ci-testproject/actions")
    property("sonar.links.issue", "https://github.com/G00fY2/android-ci-testproject/issues")
    property("sonar.links.scm", "https://github.com/G00fY2/android-ci-testproject")
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