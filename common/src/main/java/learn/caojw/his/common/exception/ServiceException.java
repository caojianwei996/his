package learn.caojw.his.common.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
    private final ServiceCode serviceCode;

    public ServiceException(String message) {
        this.serviceCode = ServiceCode.unsuccessful(message);
    }
}
