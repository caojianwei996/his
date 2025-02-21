package learn.caojw.his.common.exception.handler;

import learn.caojw.his.common.entity.Response;
import learn.caojw.his.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ServletExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public Response<Void> handleClientException(ServiceException exception) {
        return Response.error(exception.getServiceCode());
    }
}
