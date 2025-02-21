package learn.caojw.his.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.StringJoiner;

/**
 * 认证信息过滤器
 *
 * @author 曹健伟
 */
@Component
public class SecurityFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return ReactiveSecurityContextHolder
                .getContext()
                .flatMap(context -> {
                            Authentication authentication = context.getAuthentication();
                            StringJoiner joiner = new StringJoiner("&");
                            Map.of(
                                    "username", authentication.getName(),
                                    "authority", authentication
                                            .getAuthorities()
                                            .stream()
                                            .map(GrantedAuthority::getAuthority)
                                            .findFirst()
                                            .orElse("")
                                            .replace("SCOPE_", "")
                            ).forEach((key, value) -> joiner.add(String.format("%s=%s", key, value)));
                            return chain.filter(exchange
                                    .mutate()
                                    .request(builder -> builder
                                            .header("Authorization", joiner.toString())
                                            .build()
                                    )
                                    .build()
                            );
                        }
                );
    }
}
