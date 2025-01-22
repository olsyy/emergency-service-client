pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "OfficersClient"
include(":app")
include(":domain")
include(":data")
include(":common")
include(":presentation")
include(":presentation:zone")
include(":presentation:incident")
include(":core")
include(":presentation:home")
include(":presentation:zone-details")
include(":presentation:incident-details")
include(":presentation:incident-details")
