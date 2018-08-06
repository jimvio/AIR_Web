package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jimvio on 2018/7/16.
 */
public class Msg {
    //状态码
    private int code;
    //提示信息
    private String mseesage;
    //数据
    private Map<String,Object> data = new HashMap<>();

    public static Msg success(){
        Msg msg = new Msg();
        msg.setCode(StatusCode.SUCCESS_CODE);
        return msg;
    }

    public static Msg empty(){
        Msg msg  = new Msg();
        msg.setCode(StatusCode.EMPTY_CODE);
        return msg;
    }

    public static Msg fail(){
        Msg msg  = new Msg();
        msg.setCode(StatusCode.FAIL_CODE);
        return msg;
    }

    public Msg add(String key,Object value){
        this.getData().put(key,value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMseesage() {
        return mseesage;
    }

    public void setMseesage(String mseesage) {
        this.mseesage = mseesage;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
