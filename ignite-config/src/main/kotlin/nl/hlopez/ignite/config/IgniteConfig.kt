package nl.hlopez.ignite.config

import org.apache.ignite.configuration.DataStorageConfiguration
import org.apache.ignite.configuration.IgniteConfiguration
import org.apache.ignite.kubernetes.configuration.KubernetesConnectionConfiguration
import org.apache.ignite.logger.slf4j.Slf4jLogger
import org.apache.ignite.spi.communication.tcp.TcpCommunicationSpi
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi
import org.apache.ignite.spi.discovery.tcp.ipfinder.TcpDiscoveryIpFinder
import org.apache.ignite.spi.discovery.tcp.ipfinder.kubernetes.TcpDiscoveryKubernetesIpFinder
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class IgniteConfig {

    @Bean
    @ConfigurationProperties(prefix = "ignite")
    fun igniteConfiguration(ipFinder: TcpDiscoveryIpFinder): IgniteConfiguration {
        return IgniteConfiguration().apply {
            gridLogger = Slf4jLogger()
            metricsLogFrequency = 0
            discoverySpi = TcpDiscoverySpi().apply {
                this.ipFinder = ipFinder
            }
            dataStorageConfiguration = DataStorageConfiguration()
            communicationSpi = TcpCommunicationSpi()
        }
    }

    @Bean
    @Profile("!kubernetes")
    @ConfigurationProperties(prefix = "ignite.discovery-spi.vm-ip-finder")
    fun tcpDiscoveryVmIpFinder(): TcpDiscoveryIpFinder {
        return TcpDiscoveryVmIpFinder()
    }

    @Bean
    @Profile("kubernetes")
    @ConfigurationProperties(prefix = "ignite.discovery-spi.kubernetes-ip-finder.connection-configuration")
    fun kubernetesConnectionConfiguration(): KubernetesConnectionConfiguration {
        return KubernetesConnectionConfiguration()
    }

    @Bean
    @Profile("kubernetes")
    @ConfigurationProperties(prefix = "ignite.discovery-spi.kubernetes-ip-finder")
    fun tcpDiscoveryKubernetesIpFinder(connectionConfiguration: KubernetesConnectionConfiguration): TcpDiscoveryKubernetesIpFinder {
        return TcpDiscoveryKubernetesIpFinder(connectionConfiguration)
    }

}