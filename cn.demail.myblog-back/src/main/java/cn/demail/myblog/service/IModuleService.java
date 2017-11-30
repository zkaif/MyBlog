package cn.demail.myblog.service;

import top.u8u.base.vo.Page;
import top.u8u.db.domain.*;

import java.util.List;

/**
 * Created by dim on 17-9-2.
 */
public interface IModuleService {
    boolean saveOrUpdateBlogContent(BlogContent blogContent);

    boolean createComment(Comment comment);

    BlogContent getBlogContentById(long id);

    String publishBlogContent(long id);

    List<String> publishBlogContents(List<Long> ids);

    List<DateType> getDateTypeList();

    List<Comment> getCommentListByBlogId(long blogId);

    Page<BlogType> getBlogTypeList(BlogType blogType);

    Page<BlogContentRelease> getBlogContentReleaseList(BlogContentRelease blogContentRelease);

    BlogContentRelease getBlogContentReleaseById(long id);

    void addReaderCount(Long id);

    long getReaderCount(long id);

    void flushAll();
}
