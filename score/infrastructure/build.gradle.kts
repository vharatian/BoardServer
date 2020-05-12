
group = "com.vahid.board.score.infrastructure"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")

    implementation(project(":score:domain"))
}