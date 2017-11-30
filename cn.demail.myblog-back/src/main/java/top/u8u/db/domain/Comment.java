package top.u8u.db.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.transaction.annotation.Transactional;
import top.u8u.base.db.domain.BaseDomainTree;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by dim on 17-9-15.
 */

@Transactional
@TableName("COMMENT")
public class Comment extends BaseDomainTree{
    private static final long serialVersionUID = 8923659825765771254L;
    @TableField("DATE")
    private Date date;
    @NotNull(message = "Comment.name.null")
    @Size(min = 1,max = 128,message = "Comment.name.length")
    @TableField("NAME")
    private String name;
    @NotNull(message = "Comment.content.null")
    @Size(min = 1,max = 2048,message = "Comment.content.length")
    @TableField("CONTENT")
    private String content;
    @TableField("BLOG_CONTENT_ID")
    private long blogContentId;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getBlogContentId() {
        return blogContentId;
    }

    public void setBlogContentId(long blogContentId) {
        this.blogContentId = blogContentId;
    }
}
