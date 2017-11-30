package top.u8u.base.db.domain;

import com.baomidou.mybatisplus.annotations.TableField;

/**
 * Created by dim on 17-9-10.
 */
public class BaseDomainTree extends BaseDomain {

    private static final long serialVersionUID = 7080580573409875135L;
    @TableField("P_ID")
    private long pId;

    public long getpId() {
        return pId;
    }

    public void setpId(long pId) {
        this.pId = pId;
    }
}
