package top.u8u.base.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dim on 17-9-2.
 */
public class Message {
    private int code = Code.SUCCESS.ordinal();
    private List<Object> body = new ArrayList<>();
    private Map<String,Object> mgs = new HashMap<>();
    private String des;

    public void add(Object o){
        body.add(o);
    }

    public void put(String key,Object o){
        mgs.put(key,o);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Object> getBody() {
        return body;
    }

    public void setBody(List<Object> body) {
        this.body = body;
    }

    public Map<String, Object> getMgs() {
        return mgs;
    }

    public void setMgs(Map<String, Object> mgs) {
        this.mgs = mgs;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
    public static enum Code{
        SUCCESS,ERROR
    }
}
