package top.u8u.storage.dao.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.stereotype.Repository;
import top.u8u.base.dao.Impl.BaseDaoMybatisImpl;
import top.u8u.base.vo.Page;
import top.u8u.db.domain.Content;
import top.u8u.db.mapper.ContentMapper;
import top.u8u.storage.dao.IContentDao;

import java.util.List;

/**
 * Created by dim on 17-10-14.
 */
@Repository("contentDao")
public class ContentDaoImpl extends BaseDaoMybatisImpl<ContentMapper,Content> implements IContentDao{
    @Override
    public Page<Content> getAllPage(Content content) {
        Wrapper wrapper = new EntityWrapper();
        return super.getAllPage(content,wrapper);
    }

    @Override
    public boolean deletePhysics(List<Long> ids) {
        return baseMapper.deletePhysics(ids);
    }
}
