import org.gradle.api.JavaVersion

object Configs {
    const val minSdk = 33
    const val compileSdk = 35
    const val targetSdk = 35
    const val versionCode = 1
    const val versionName = "1.0"
    const val namespace = "com.example.officersclient"
    const val applicationId = "com.example.officersclient"
    const val jvmTarget = "11"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    val sourceCompatibility = JavaVersion.VERSION_11
    val targetCompatibility = JavaVersion.VERSION_11
}