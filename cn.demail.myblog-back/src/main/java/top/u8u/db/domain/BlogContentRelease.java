package top.u8u.db.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;
import top.u8u.base.db.domain.BaseDomain;

import java.util.Date;

/**
 * Created by dim on 17-10-14.
 */
@TableName("BLOG_CONTENT_RELEASE")
public class BlogContentRelease extends BaseDomain {
    private static final long serialVersionUID = 7967680418457782424L;
    @TableField("TITLE")
    private String title;
    @TableField("DIGEST")
    private String digest;
    @TableField("KEY_WORD")
    private String keyWord;
	@TableField("READER_COUNT")
	private int readerCount;
    @TableField("TYPE_ID")
    private long typeId;
    @TableField("DATE_ID")
    private long dateId;
    @TableField("C_TIME")
    private Date cTime;
    @TableField("ORIGINAL")
    private Boolean original;
    @TableField("BLOG_CONTENT_ID")
    private long blogContentId;
    @TableField("HTML_CONTENT_ID")
    private long htmlContentId;
    @TableField("URI")
    private String uri;
    @TableField(exist = false)
    private BlogContent blogContent;

    public Boolean getOriginal() {
        return original;
    }

    public void setOriginal(Boolean original) {
        this.original = original;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public long getDateId() {
        return dateId;
    }

    public void setDateId(long dateId) {
        this.dateId = dateId;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public long getHtmlContentId() {
        return htmlContentId;
    }

    public void setHtmlContentId(long htmlContentId) {
        this.htmlContentId = htmlContentId;
    }

    public BlogContent getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(BlogContent blogContent) {
        this.blogContent = blogContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getReaderCount() {
        return readerCount;
    }

    public void setReaderCount(int readerCount) {
        this.readerCount = readerCount;
    }

    public long getBlogContentId() {
        return blogContentId;
    }

    public void setBlogContentId(long blogContentId) {
        this.blogContentId = blogContentId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
