package com.example.demo.common;
/**
 * 异常枚举
 * @author Administrator
 *
 */
public enum ExceptionEnum {
	
	UNKNOW_ERROR(-1,"未知错误"),
    USER_NOT_FIND(-101,"用户不存在"),
    SUCCESS(0,"成功"),
    ERROR(1,"失败")
    ;
	
	private Integer code;
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
    
}
