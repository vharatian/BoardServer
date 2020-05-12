
group = "com.vahid.board.score.application"

dependencies{
    implementation(project(":score:domain"))

    implementation("org.springframework:spring-context")
    implementation("javax.transaction:javax.transaction-api")
}