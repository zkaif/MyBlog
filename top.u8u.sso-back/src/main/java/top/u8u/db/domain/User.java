package top.u8u.db.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import top.u8u.base.db.domain.BaseDomain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by dim on 17-10-17.
 */
@TableName("USER")
public class User extends BaseDomain {
    @NotNull(message="user.loginName.null")
    @Size(min=3,max=255,message="user.loginName.length")
    @TableField("LOGIN_NAME")
    private String loginName;
    @NotNull(message="user.password.null")
    @Size(min=1,max=255,message="user.password.length")
    @TableField("PASSWORD")
    private String password;
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
