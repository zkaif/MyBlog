package top.u8u.db.domain;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import top.u8u.base.db.domain.BaseDomain;

import java.util.Comparator;

@TableName("BLOG_TYPE")
public class BlogType extends BaseDomain implements Comparable<BlogType> {
    private static final long serialVersionUID = 2100952399625289102L;
    @TableField("NAME")
    private String name;
    @TableField(exist = false)
    private long blogCount;

    public long getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(long blogCount) {
        this.blogCount = blogCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(BlogType o) {
        long l = this.getBlogCount()-o.getBlogCount();
        if (l<0){
            return 1;
        }else if (l>0){
            return -1;
        }else{
            return 0;
        }
    }
}
