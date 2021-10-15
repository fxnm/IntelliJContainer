fun properties(key: String) = project.findProperty(key).toString()

group = properties("group")
version = properties("version")


plugins {
    id("org.jetbrains.changelog") version "1.3.1"
}

changelog {

    path.set("${project.projectDir}/docs/CHANGELOG.md")
    groups.set(listOf("Added", "Changed", "Fixed", "Removed"))
    version.set(properties("version"))
}
