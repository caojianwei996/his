package learn.caojw.his.gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * 资源服务器安全配置类
 *
 * @author 曹健伟
 */
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfiguration {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        // @formatter:off
        return http
                .authorizeExchange(exchange -> exchange.anyExchange().authenticated())
                .cors(cors -> {
                            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                            CorsConfiguration corsConfiguration = new CorsConfiguration();
                            corsConfiguration.addAllowedOrigin("*");
                            corsConfiguration.addAllowedHeader("*");
                            corsConfiguration.addAllowedMethod("*");
                            source.registerCorsConfiguration("/**", corsConfiguration);
                            cors.configurationSource(source);
                        }
                )
                .oauth2ResourceServer(server -> server.jwt(Customizer.withDefaults()))
                .build();
        // @formatter:on
    }
}
