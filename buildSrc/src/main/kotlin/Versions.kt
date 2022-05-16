object Versions {

  const val androidMinSdk = 21
  const val androidCompileSdk = 32
  const val androidTargetSdk = 32
  const val androidBuildTools = "32.0.0"

  // plugins
  const val androidGradle = "7.2.0"
  const val kotlin = "1.6.21"

  const val gradleVersions = "0.42.0"
  const val sonarqube = "3.3"

  // android
  const val appcompat = "1.4.1"
  const val constraintLayout = "2.1.3"

  fun maturityLevel(version: String): Int {
    val levels = listOf("alpha", "beta", "m", "rc")
    levels.forEachIndexed { index, s ->
      if (version.matches(".*[.\\-]$s[.\\-\\d]*".toRegex(RegexOption.IGNORE_CASE))) return index
    }
    return levels.size
  }
}