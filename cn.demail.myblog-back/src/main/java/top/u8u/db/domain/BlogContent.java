package top.u8u.db.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;
import top.u8u.base.db.domain.BaseDomain;

import java.util.Date;

@TableName("BLOG_CONTENT")
public class BlogContent extends BaseDomain {
	private static final long serialVersionUID = 8980764141103124173L;
	@TableField("TITLE")
	private String title;
	@TableField("DIGEST")
	private String digest;
	@TableField("DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date date;
	@TableField("TYPE_ID")
	private long typeId;
	@TableField("ORIGINAL")
	private Boolean original;
	@TableField("DATE_ID")
	private long dateId;
	@TableField("KEY_WORD")
	private String keyWord;
	@TableField("CONTENT_ID")
	private long contentId;
	@TableField("TEMPLATE_ID")
	private long templateId;
	@TableField(exist = false)
	private String content;
	@TableField(exist = false)
	private BlogType blogType;

	public long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}

	public long getContentId() {
		return contentId;
	}

	public void setContentId(long contentId) {
		this.contentId = contentId;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public Boolean getOriginal() {
		return original;
	}

	public void setOriginal(Boolean original) {
		this.original = original;
	}

	public long getDateId() {
		return dateId;
	}

	public void setDateId(long dateId) {
		this.dateId = dateId;
	}

	public BlogType getBlogType() {
		return blogType;
	}

	public void setBlogType(BlogType blogType) {
		this.blogType = blogType;
	}
}
