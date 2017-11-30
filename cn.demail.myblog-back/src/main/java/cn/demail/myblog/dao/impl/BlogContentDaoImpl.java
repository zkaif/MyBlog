package cn.demail.myblog.dao.impl;

import cn.demail.myblog.dao.IBlogContentDao;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.stereotype.Repository;
import top.u8u.base.dao.Impl.BaseDaoMybatisImpl;
import top.u8u.base.vo.Page;
import top.u8u.db.domain.BlogContent;
import top.u8u.db.mapper.BlogContentMapper;

import java.util.List;


/**
 * Created by dim on 17-9-2.
 */
@Repository("blogContentDao")
public class BlogContentDaoImpl extends BaseDaoMybatisImpl<BlogContentMapper,BlogContent> implements IBlogContentDao {
    @Override
    public Page<BlogContent> getAllPage(BlogContent blogContent){
        Wrapper<BlogContent> wrapper = new EntityWrapper<>();
        if(blogContent.getOriginal()!=null)
            wrapper.eq("ORIGINAL",blogContent.getOriginal());
        if(blogContent.getDateId()>0)
            wrapper.eq("DATE_ID",blogContent.getDateId());
        if(blogContent.getTypeId()>0)
            wrapper.eq("TYPE_ID",blogContent.getTypeId());
        wrapper.orderBy("DATE",false);
        return super.getAllPage(blogContent,wrapper);
    }
}
