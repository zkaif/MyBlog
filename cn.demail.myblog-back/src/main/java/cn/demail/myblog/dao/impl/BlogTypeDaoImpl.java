package cn.demail.myblog.dao.impl;

import cn.demail.myblog.dao.IBlogTypeDao;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.stereotype.Repository;
import top.u8u.base.dao.Impl.BaseDaoMybatisImpl;
import top.u8u.base.vo.Page;
import top.u8u.db.domain.BlogType;
import top.u8u.db.mapper.BlogTypeMapper;


/**
 * Created by dim on 17-9-2.
 */

@Repository("blogTypeDao")
public class BlogTypeDaoImpl extends BaseDaoMybatisImpl<BlogTypeMapper,BlogType> implements IBlogTypeDao {

    @Override
    public Page<BlogType> getAllPage(BlogType blogContent) {
        Wrapper<BlogType> wrapper = new EntityWrapper<BlogType>();
        return getAllPage(blogContent,wrapper);
    }
}
