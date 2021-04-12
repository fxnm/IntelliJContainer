val pluginGroup: String by project
val pluginName_: String by project
val pluginVersion: String by project

group = pluginGroup
version = pluginVersion


plugins {
    id("org.jetbrains.changelog") version "1.1.2"
}

changelog {

    path = "${project.projectDir}/docs/CHANGELOG.md"
    groups = listOf("Added", "Changed", "Fixed", "Removed")
    version = pluginVersion
}
