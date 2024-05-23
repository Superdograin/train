package cn.qiluno.train.common.exception;

public enum BusinessExceptionEnum {

    MEMBER_MOBILE_EXIST("手机号已注册！");

    private String message;

    BusinessExceptionEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BusinessExceptionEnum{" +
                "message='" + message + '\'' +
                '}';
    }
}
