package cn.demail.myblog.service;

import top.u8u.base.vo.Page;
import top.u8u.db.domain.*;

import java.util.List;

/**
 * Created by dim on 17-9-2.
 */
public interface IMyBlogService {

    void createContactMessage(ContactMessage message);

    Page<BlogType> getBlogTypeList(BlogType blogType);

    List<DateType> getDateTypeList();

    Page<BlogContent> getBlogContentList(BlogContent blogContent);

    BlogContent getBlogContentById(long id);

    boolean saveOrUpdateBlogContent(BlogContent blogContent);

    boolean deletesBlogContentById(List<Long> id);

    BlogType getBlogTypeById(long id);

    boolean saveOrUpdateBlogType(BlogType blogType);

    boolean deletesBlogTypeById(List<Long> id);

    boolean deletesContactMessageById(List<Long> id);

    Page<ContactMessage> getContactMessageList(ContactMessage contactMessage);

    ContactMessage getContactMessageById(long id);

    boolean createComment(Comment comment);

    Page<Comment> getCommentList(Comment comment);

    boolean deletesCommentById(List<Long> id);

    List<Comment> getCommentListByBlogId(long blogId);

    boolean readContactMessageById(List<Long> id);

    Page<BlogContentRelease> getBlogContentReleaseList(BlogContentRelease blogContentRelease);

    BlogContentRelease getBlogContentReleaseById(long id);

    boolean saveOrUpdateBlogContentRelease(BlogContentRelease blogContentRelease);

    boolean deletesBlogContentReleaseById(List<Long> ids);

    BlogContentRelease getBlogContentReleaseByBlogContenId(long id);

    BlogContentTemplate getblogContentTemplateById(long templateId);

    void addReaderCount(long htmlContentId,int count);

    boolean saveOrUpdateBlogContentReleasePhysics(BlogContentRelease blogContentRelease);

    long getReaderCount(long id);
}
