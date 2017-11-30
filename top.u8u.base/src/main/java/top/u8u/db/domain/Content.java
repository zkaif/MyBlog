package top.u8u.db.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import top.u8u.base.db.domain.BaseDomain;

import java.util.Date;

/**
 * Created by dim on 17-9-2.
 */
@TableName("CONTENT")
public class Content extends BaseDomain {
    @TableField("C_TIME")
    private Date cTime;
    @TableField("URI")
    private String uri;
    @TableField("URI_FLAG")
    private boolean uriFlag;
    @TableField("OSS_KEY")
    private String ossKey;
    @TableField("FILE_NAME")
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public String getOssKey() {
        return ossKey;
    }

    public void setOssKey(String ossKey) {
        this.ossKey = ossKey;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public boolean isUriFlag() {
        return uriFlag;
    }

    public void setUriFlag(boolean uriFlag) {
        this.uriFlag = uriFlag;
    }
}
