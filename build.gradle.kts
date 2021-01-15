val pluginGroup: String by project
val pluginName_: String by project
val pluginVersion: String by project

group = pluginGroup
version = pluginVersion


plugins {
    id("org.jetbrains.changelog") version "0.6.2"
}

changelog {
    path = "docs/CHANGELOG.md"
    groups = listOf("Added", "Changed", "Fixed", "Removed")
}
