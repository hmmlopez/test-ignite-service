package nl.hlopez.ignite.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties
@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class ServerNodeApplication {

}

fun main(args: Array<String>) {
    runApplication<ServerNodeApplication>(*args)
}