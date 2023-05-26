package nl.hlopez.ignite.server.config

import nl.hlopez.ignite.config.IgniteConfig
import nl.hlopez.ignite.server.service.StudentServiceImpl
import nl.hlopez.ignite.service.StudentService
import org.apache.ignite.IgniteSpringBean
import org.apache.ignite.configuration.IgniteConfiguration
import org.apache.ignite.services.ServiceConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Import(IgniteConfig::class)
@Configuration
class AppConfig {

    @Bean
    fun ignite(igniteConfiguration: IgniteConfiguration, serviceConfigurations: List<ServiceConfiguration>): IgniteSpringBean {
        igniteConfiguration.apply {
            setServiceConfiguration(*serviceConfigurations.toTypedArray())
        }
        return IgniteSpringBean().apply {
            this.configuration = igniteConfiguration
        }
    }

    @Bean
    fun studentServiceConfiguration(): ServiceConfiguration {
        return ServiceConfiguration().apply {
            name = StudentService::class.simpleName
            service = StudentServiceImpl()
            maxPerNodeCount = 1
        }
    }
}