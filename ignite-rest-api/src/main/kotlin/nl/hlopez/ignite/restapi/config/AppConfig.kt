package nl.hlopez.ignite.restapi.config

import nl.hlopez.ignite.config.IgniteConfig
import org.apache.ignite.IgniteSpringBean
import org.apache.ignite.configuration.IgniteConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Import(IgniteConfig::class)
@Configuration
class AppConfig {

    @Bean
    fun ignite(igniteConfiguration: IgniteConfiguration): IgniteSpringBean {
        igniteConfiguration.isClientMode = true
        return IgniteSpringBean().apply {
            this.configuration = igniteConfiguration
        }
    }
}