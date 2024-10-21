package learn.caojw.his.common.openfeign.configuration;

import feign.RequestInterceptor;
import learn.caojw.his.common.openfeign.HeaderInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

@ConditionalOnClass(RequestInterceptor.class)
public class HeaderInterceptorConfiguration {
    @Bean
    public HeaderInterceptor headerInterceptor() {
        return new HeaderInterceptor();
    }
}
