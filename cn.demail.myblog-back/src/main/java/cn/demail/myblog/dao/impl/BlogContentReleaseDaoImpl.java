package cn.demail.myblog.dao.impl;

import cn.demail.myblog.dao.IBlogContentReleaseDao;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.stereotype.Repository;
import top.u8u.base.dao.Impl.BaseDaoMybatisImpl;
import top.u8u.base.vo.Page;
import top.u8u.db.domain.BlogContent;
import top.u8u.db.domain.BlogContentRelease;
import top.u8u.db.mapper.BlogContentReleaseMapper;


/**
 * Created by dim on 17-9-2.
 */
@Repository("blogContentReleaseDao")
public class BlogContentReleaseDaoImpl extends BaseDaoMybatisImpl<BlogContentReleaseMapper,BlogContentRelease> implements IBlogContentReleaseDao {
    @Override
    public Page<BlogContentRelease> getAllPage(BlogContentRelease blogContentRelease){
        Wrapper<BlogContentRelease> wrapper = new EntityWrapper<>();
        if(blogContentRelease.getReaderCount()==-1)
            wrapper.orderBy("READER_COUNT",false);
        wrapper.orderBy("C_TIME",false);
        if (blogContentRelease.getTypeId()>0)
            wrapper.eq("TYPE_ID",blogContentRelease.getTypeId());
        if (blogContentRelease.getDateId()>0)
            wrapper.eq("DATE_ID",blogContentRelease.getDateId());
        if (blogContentRelease.getOriginal()!=null)
            wrapper.eq("ORIGINAL",blogContentRelease.getOriginal());

        if (blogContentRelease.getTitle()!=null)
            wrapper.like("TITLE",blogContentRelease.getTitle());
        if (blogContentRelease.getKeyWord()!=null)
            wrapper.like("KEY_WORD",blogContentRelease.getKeyWord());
        if (blogContentRelease.getDigest()!=null)
            wrapper.like("DIGEST",blogContentRelease.getDigest());
        return super.getAllPage(blogContentRelease,wrapper);
    }
    @Override
    public long getCount(BlogContentRelease blogContentRelease) {
        Wrapper<BlogContentRelease> wrapper = new EntityWrapper<>();
        if(blogContentRelease.getDateId()>0){
            wrapper.eq("DATE_ID",blogContentRelease.getDateId());
        }
        if(blogContentRelease.getTypeId()>0){
            wrapper.eq("TYPE_ID",blogContentRelease.getTypeId());
        }
        return super.selectCount(wrapper);
    }

    @Override
    public void addReaderCount(long id,int count) {
        baseMapper.addReaderCount(id,count);
    }

    @Override
    public boolean updateByIdPhysics(BlogContentRelease blogContentRelease) {
        return baseMapper.updateByIdPhysics(blogContentRelease);
    }

    @Override
    public BlogContentRelease getByBlogContenId(long id) {
        return baseMapper.getByBlogContenId(id);
    }
}
