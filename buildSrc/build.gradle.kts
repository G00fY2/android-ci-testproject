import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  `kotlin-dsl`
}

gradlePlugin {
  plugins {
    register("gitHubIssue") {
      id = "io.github.g00fy2.github-issue-plugin"
      implementationClass = "buildplugins.GitHubIssuePlugin"
    }
  }
}

repositories {
  mavenCentral()
}

tasks.withType<KotlinCompile>().configureEach {
  kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<JavaCompile>().configureEach {
  sourceCompatibility = "1.8"
  targetCompatibility = "1.8"
}