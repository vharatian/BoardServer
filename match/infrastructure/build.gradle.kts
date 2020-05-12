
group = "com.vahid.board.match.infrastructure"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")

    implementation(project(":match:domain"))
    implementation(project(":score:application"))
}