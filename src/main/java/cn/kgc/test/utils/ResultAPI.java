package cn.kgc.test.utils;

/**
 * 统一返回结果集
 *
 * @Author Administrator
 * @date 11:19
 */
public class ResultAPI {
    private Integer code;

    private String msg;

    private Object data;

    public ResultAPI() {
    }

    public ResultAPI(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultAPI(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
