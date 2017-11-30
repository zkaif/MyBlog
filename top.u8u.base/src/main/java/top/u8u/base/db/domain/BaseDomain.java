package top.u8u.base.db.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;

import java.io.Serializable;

/**
 * Created by dim on 17-9-2.
 */
public class BaseDomain implements Serializable {
    private static final long serialVersionUID = -5879876946467184307L;
    @TableId("ID")
    private Long id;
    @TableField("DEL_FLAG")
    @TableLogic
    private Boolean delFlag;
    @TableField(exist=false)
    private int pageD;
    @TableField(exist=false)
    private int countD;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public int getPageD() {
        return pageD;
    }

    public void setPageD(int pageD) {
        this.pageD = pageD;
    }

    public int getCountD() {
        return countD;
    }

    public void setCountD(int countD) {
        this.countD = countD;
    }
}
