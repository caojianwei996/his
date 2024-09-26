package learn.caojw.his.common.exception;

public record ServiceCode(int code, String message) {
    public static final ServiceCode SUCCESSFUL = new ServiceCode(20000, "操作成功");

    public static ServiceCode unsuccessful(String message) {
        return new ServiceCode(50000, message);
    }
}
