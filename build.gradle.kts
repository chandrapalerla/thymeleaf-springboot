plugins {
    id("java")
    id("org.springframework.boot") version "2.7.14"
    id("io.spring.dependency-management") version "1.1.4"
}
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
group = "com.certificate-details"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation ("com.itextpdf:itextpdf:5.5.13.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    useJUnitPlatform()
}
tasks.wrapper {
    gradleVersion = "7.6.4"
}