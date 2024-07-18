import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    application
    idea
    eclipse
    alias(libs.plugins.spotless)
    alias(libs.plugins.shadow)
    alias(libs.plugins.micronaut.application)
    alias(libs.plugins.micronaut.aot)
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(libs.lombok)
    annotationProcessor(libs.lombok)
    testImplementation(libs.lombok)
    testAnnotationProcessor(libs.lombok)
    implementation(libs.ulid)
    implementation(libs.guava)

    implementation("com.s-kugel.schneider:fasan-db:1.0.0")
    implementation("com.s-kugel.schneider:eule-db:1.0.0")
    implementation("com.s-kugel.schneider:enums:1.0.0")
    implementation("com.s-kugel.schneider:common:1.0.0")

    runtimeOnly("org.yaml:snakeyaml")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    compileOnly("io.micronaut:micronaut-http-client")
    implementation("io.micronaut.data:micronaut-data-jdbc")
    runtimeOnly("io.micronaut.sql:micronaut-jdbc-hikari")
    annotationProcessor("io.micronaut.data:micronaut-data-processor")
}

java {
    sourceCompatibility = JavaVersion.toVersion("21")
    targetCompatibility = JavaVersion.toVersion("21")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events(
            "SKIPPED",
            "PASSED",
            "FAILED",
            "STANDARD_ERROR",
        )
        exceptionFormat = TestExceptionFormat.FULL
    }
}

application {
    mainClass = "com.s_kugel.schneider.backoffice.Application"
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.s_kugel.schneider.eule.*")
    }
    aot {
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = true
    }
}

spotless {
    encoding("UTF-8")
    java {
        importOrder()
        formatAnnotations()
        indentWithSpaces()
        removeUnusedImports()
        trimTrailingWhitespace()
        endWithNewline()
        googleJavaFormat()
    }
}
