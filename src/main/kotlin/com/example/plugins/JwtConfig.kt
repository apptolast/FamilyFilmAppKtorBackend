import com.example.auth.models.JwtConfig


import io.ktor.server.application.*

fun Application.getJwtConfig(): JwtConfig {
    val secret = environment.config.property("jwt.secret").getString()
    val issuer = environment.config.property("jwt.issuer").getString()
    val audience = environment.config.property("jwt.audience").getString()
    val realm = environment.config.property("jwt.realm").getString()

    return JwtConfig(secret, issuer, audience, realm)
}