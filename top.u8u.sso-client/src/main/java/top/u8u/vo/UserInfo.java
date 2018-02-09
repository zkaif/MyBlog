package top.u8u.vo;

import java.io.Serializable;

/**
 * Created by dim on 17-10-16.
 */
public class UserInfo implements Serializable {
    private long userId;
    private int uStatus;
    private String loginName;
    private String email;
    private String phone;
    private String sId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getuStatus() {
        return uStatus;
    }

    public void setuStatus(int uStatus) {
        this.uStatus = uStatus;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }
}
