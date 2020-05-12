
group = "com.vahid.board.match.application"

dependencies{
    implementation(project(":match:domain"))

    implementation("org.springframework:spring-context")
    implementation("javax.transaction:javax.transaction-api")
}