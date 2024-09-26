package learn.caojw.his.common.exception.handler;

import learn.caojw.his.common.entity.Response;
import learn.caojw.his.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class ReactiveExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public Mono<Response<Void>> handleClientException(ServiceException exception) {
        return Mono.just(Response.error(exception.getServiceCode()));
    }
}
