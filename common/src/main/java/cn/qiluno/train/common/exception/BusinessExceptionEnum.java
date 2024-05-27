package cn.qiluno.train.common.exception;

public enum BusinessExceptionEnum {

    MEMBER_MOBILE_EXIST("手机号已注册！"),
    MEMBER_MOBILE_NOT_EXIST("请先获取短信验证码"),
    MEMBER_MOBILE_CODE_ERROR("短信验证码错误");

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
