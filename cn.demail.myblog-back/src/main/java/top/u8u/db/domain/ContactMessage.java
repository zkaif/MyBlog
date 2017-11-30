package top.u8u.db.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import top.u8u.base.db.domain.BaseDomain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@TableName("CONTACT_MESSAGE")
public class ContactMessage extends BaseDomain{
	private static final long serialVersionUID = 147760358756728943L;
	@NotNull(message = "ContactMessage.name.null")
	@Size(min = 1,max = 256,message = "ContactMessage.name.length")
	@TableField("NAME")
	private String name;
	@NotNull(message = "ContactMessage.email.null")
	@Size(min = 1,max = 256,message = "ContactMessage.email.length")
	@TableField("EMAIL")
	private String email;
	@NotNull(message = "ContactMessage.title.null")
	@Size(min = 1,max = 256,message = "ContactMessage.title.length")
	@TableField("TITLE")
	private String title;
	@TableField("READ")
	private boolean read;
	@NotNull(message = "ContactMessage.content.null")
	@Size(min = 1,max = 2000,message = "ContactMessage.content.length")
	@TableField("CONTENT")
	private String content;

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
