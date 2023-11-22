val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val postgres_version : String by project
val h2_version : String by project
val exposed_version : String by project

val koin_version: String by project
val hikaricp_version: String by project

plugins {
    kotlin("jvm") version "1.8.20"
    id("io.ktor.plugin") version "2.2.4"
                id("org.jetbrains.kotlin.plugin.serialization") version "1.8.20"
}

group = "com.mrdip"
version = "0.0.1"
application {
    mainClass.set("com.mrdip.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("org.postgresql:postgresql:$postgres_version")
    implementation("com.h2database:h2:$h2_version")
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("io.ktor:ktor-server-auth-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jwt-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    implementation("com.zaxxer:HikariCP:$hikaricp_version")
    implementation("io.insert-koin:koin-ktor:$koin_version")

    implementation("de.mkammerer.snowflake-id:snowflake-id:0.0.2")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposed_version")
}