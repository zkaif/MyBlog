package top.u8u.db.mapper;

import org.apache.ibatis.annotations.Param;
import top.u8u.base.db.mapper.BaseMapper;
import top.u8u.db.domain.BlogContent;
import top.u8u.db.domain.BlogContentRelease;

/**
 * Created by dim on 17-9-2.
 */
public interface BlogContentReleaseMapper extends BaseMapper<BlogContentRelease> {
    BlogContentRelease getByBlogContenId(@Param("id") long id);
    void addReaderCount(@Param("id") long id,@Param("count") int count);
    boolean updateByIdPhysics(BlogContentRelease blogContentRelease);
}
