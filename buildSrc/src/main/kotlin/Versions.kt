object Versions {

  const val androidMinSdk = 21
  const val androidCompileSdk = 31
  const val androidTargetSdk = 31
  const val androidBuildTools = "31.0.0"

  // plugins
  const val androidGradle = "7.0.3"
  const val kotlin = "1.6.0"

  const val nanoGiantsVersioning = "2.4.0"
  const val gradleVersions = "0.39.0"
  const val sonarqube = "3.3"

  // android
  const val appcompat = "1.4.0"
  const val constraintLayout = "2.1.2"

  fun maturityLevel(version: String): Int {
    val levels = listOf("alpha", "beta", "m", "rc")
    levels.forEachIndexed { index, s ->
      if (version.matches(".*[.\\-]$s[.\\-\\d]*".toRegex(RegexOption.IGNORE_CASE))) return index
    }
    return levels.size
  }
}