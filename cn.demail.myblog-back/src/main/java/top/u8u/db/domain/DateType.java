package top.u8u.db.domain;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import top.u8u.base.db.domain.BaseDomain;

import java.util.Date;

@TableName("DATE_TYPE")
public class DateType extends BaseDomain {
	private static final long serialVersionUID = -8317099881776204083L;
	@TableField("DATE")
	private Date date;
	@TableField(exist = false)
	private long blogCount;

	public long getBlogCount() {
		return blogCount;
	}

	public void setBlogCount(long blogCount) {
		this.blogCount = blogCount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
