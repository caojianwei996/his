package learn.caojw.his.common.entity;

import learn.caojw.his.common.exception.ServiceCode;

public record Response<T>(int code, String message, T data) {
    private Response(ServiceCode serviceCode) {
        this(serviceCode.code(), serviceCode.message(), null);
    }

    private Response(ServiceCode serviceCode, T data) {
        this(serviceCode.code(), serviceCode.message(), data);
    }

    public static Response<Void> ok() {
        return new Response<>(ServiceCode.SUCCESSFUL);
    }

    public static <T> Response<T> ok(T data) {
        return new Response<>(ServiceCode.SUCCESSFUL, data);
    }

    public static Response<Void> error(ServiceCode serviceCode) {
        return new Response<>(serviceCode);
    }
}
