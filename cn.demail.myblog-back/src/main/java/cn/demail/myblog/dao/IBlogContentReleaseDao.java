package cn.demail.myblog.dao;

import top.u8u.base.dao.IBaseDao;
import top.u8u.db.domain.BlogContentRelease;

/**
 * Created by dim on 17-9-2.
 */
public interface IBlogContentReleaseDao extends IBaseDao<BlogContentRelease>{
    BlogContentRelease getByBlogContenId(long id);
    long getCount(BlogContentRelease blogContentRelease);
    void addReaderCount(long id,int count);
    boolean updateByIdPhysics(BlogContentRelease blogContentRelease);
}
