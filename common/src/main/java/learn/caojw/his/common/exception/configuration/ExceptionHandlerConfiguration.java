package learn.caojw.his.common.exception.configuration;

import learn.caojw.his.common.exception.handler.ReactiveExceptionHandler;
import learn.caojw.his.common.exception.handler.ServletExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

public class ExceptionHandlerConfiguration {
    @Bean
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    public ServletExceptionHandler servletExceptionHandler() {
        return new ServletExceptionHandler();
    }

    @Bean
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
    public ReactiveExceptionHandler reactiveExceptionHandler() {
        return new ReactiveExceptionHandler();
    }
}
