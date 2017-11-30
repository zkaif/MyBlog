package cn.demail.myblog.service.impl;

import cn.demail.myblog.dao.*;
import cn.demail.myblog.service.IMyBlogService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.u8u.base.utils.DBUtil;
import top.u8u.base.vo.Page;
import top.u8u.db.domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dim on 17-9-2.
 */
@Service("myBlogService")
@Transactional(readOnly = true)
public class MyBlogServiceImpl implements IMyBlogService {
    private static Log log = LogFactory.getLog(MyBlogServiceImpl.class);
    @Autowired
    private IBlogContentDao blogContentDao;
    @Autowired
    private IBlogContentReleaseDao blogContentReleaseDao;
    @Autowired
    private IBlogTypeDao blogTypeDao;
    @Autowired
    private IDateTypeDao dateTypeDao;
    @Autowired
    private IContactMessageDao contactMessageDao;
    @Autowired
    private ICommentDao commentDao;

    @Transactional(readOnly = false)
    @Override
    public void createContactMessage(ContactMessage message) {
        contactMessageDao.save(message);
    }
    @Override
    public Page<BlogType> getBlogTypeList(BlogType blogType) {
        if(blogType==null){
            log.warn("blogType is null");
            return new Page<BlogType>();
        }
        Page<BlogType> blogTypes = blogTypeDao.getAllPage(blogType);
        BlogContentRelease blogContentRelease = new BlogContentRelease();
        for (BlogType blogTypeTmp : blogTypes.getData()) {
            blogContentRelease.setTypeId(blogTypeTmp.getId());
            long count = blogContentReleaseDao.getCount(blogContentRelease);
            blogTypeTmp.setBlogCount(count);
        }
        return blogTypes;
    }

    @Override
    public List<DateType> getDateTypeList() {
        List<DateType> dateTypes = dateTypeDao.getAll(null);
        BlogContentRelease blogContentRelease = new BlogContentRelease();
        for (DateType dateType : dateTypes) {
            blogContentRelease.setDateId(dateType.getId());
            long count = blogContentReleaseDao.getCount(blogContentRelease);
            dateType.setBlogCount(count);
        }
        return dateTypes;
    }

    @Override
    public Page<BlogContent> getBlogContentList(BlogContent blogContent) {
        Page<BlogContent> blogContentPage = blogContentDao.getAllPage(blogContent);
        List<BlogContent> blogContents = blogContentPage.getData();
        for (BlogContent blogContent1 : blogContents) {
            BlogType blogType = blogTypeDao.getById(blogContent1.getTypeId());
            blogContent1.setBlogType(blogType);
        }
        return blogContentPage;
    }

    @Override
    public BlogContent getBlogContentById(long id) {
        if (!DBUtil.idLegal(id))
            return null;
        BlogContent blogContent = blogContentDao.getById(id);
        if (blogContent == null) {
            return null;
        }
        BlogType blogType = blogTypeDao.getById(blogContent.getTypeId());
        blogContent.setBlogType(blogType);
        return blogContent;
    }

    @Transactional(readOnly = false)
    @Override
    public boolean saveOrUpdateBlogContent(BlogContent blogContent) {
        DateType dateType = dateTypeDao.getByDate(blogContent.getDate());
        if (dateType == null) {
            dateType = new DateType();
            dateType.setDate(blogContent.getDate());
            dateTypeDao.save(dateType);
        }
        blogContent.setDateId(dateType.getId());

        return blogContentDao.saveOrUpdate(blogContent);
    }

    @Transactional(readOnly = false)
    @Override
    public boolean deletesBlogContentById(List<Long> id) {
        if (id == null || id.isEmpty()) {
            return false;
        }
        return blogContentDao.deletesById(id);
    }

    @Override
    public BlogType getBlogTypeById(long id) {
        if (!DBUtil.idLegal(id)) {
            return null;
        }
        return blogTypeDao.getById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public boolean saveOrUpdateBlogType(BlogType blogType) {
        return blogTypeDao.saveOrUpdate(blogType);
    }

    @Transactional(readOnly = false)
    @Override
    public boolean deletesBlogTypeById(List<Long> id) {
        if (id == null || id.isEmpty()) {
            return false;
        }
        return blogTypeDao.deleteBatchIds(id);
    }

    @Transactional(readOnly = false)
    @Override
    public boolean deletesContactMessageById(List<Long> id) {
        if (id == null || id.isEmpty()) {
            return false;
        }
        return contactMessageDao.deleteBatchIds(id);
    }

    @Override
    public Page<ContactMessage> getContactMessageList(ContactMessage contactMessage) {
        return contactMessageDao.getAllPage(contactMessage);
    }

    @Override
    public ContactMessage getContactMessageById(long id) {
        if (!DBUtil.idLegal(id)) {
            return null;
        }
        return contactMessageDao.getById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public boolean createComment(Comment comment) {
        if (comment == null) {
            return false;
        }
        comment.setDate(new Date());
        return commentDao.save(comment);
    }

    @Override
    public Page<Comment> getCommentList(Comment comment) {
        return commentDao.getAllPage(comment);
    }

    @Transactional(readOnly = false)
    @Override
    public boolean deletesCommentById(List<Long> id) {
        if (id == null || id.isEmpty()) {
            return false;
        }
        return commentDao.deletesById(id);
    }

    @Override
    public List<Comment> getCommentListByBlogId(long blogId) {
        if (!DBUtil.idLegal(blogId)) {
            return new ArrayList<>();
        }
        return commentDao.getListByBlogId(blogId);
    }

    @Transactional(readOnly = false)
    @Override
    public boolean readContactMessageById(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return false;
        }
        return contactMessageDao.read(ids);
    }

    @Override
    public Page<BlogContentRelease> getBlogContentReleaseList(BlogContentRelease blogContentRelease) {
        if (blogContentRelease == null) {
            log.warn("blogContentRelease is null");
            return new Page<BlogContentRelease>();
        }
        if (blogContentRelease.getPageD() < 1) {
            blogContentRelease.setPageD(1);
        }
        if (blogContentRelease.getCountD() < 1) {
            blogContentRelease.setCountD(Integer.MAX_VALUE);
        }
        Page<BlogContentRelease> blogContentReleasePage = blogContentReleaseDao.getAllPage(blogContentRelease);
        List<BlogContentRelease> blogContentReleases = blogContentReleasePage.getData();
        for (BlogContentRelease blogContentReleaseTmp : blogContentReleases) {
            if (blogContentReleaseTmp.getBlogContentId() >= 0) {
                BlogContent blogContent = this.getBlogContentById(blogContentReleaseTmp.getBlogContentId());
                blogContentReleaseTmp.setBlogContent(blogContent);
            }
        }
        return blogContentReleasePage;
    }

    @Override
    public BlogContentRelease getBlogContentReleaseById(long id) {
        if (id <= 0) {
            log.warn("id is < 0");
            return null;
        }
        BlogContentRelease blogContentRelease = blogContentReleaseDao.getById(id);
        if (blogContentRelease.getBlogContentId() >= 0) {
            BlogContent blogContent = this.getBlogContentById(blogContentRelease.getBlogContentId());
            blogContentRelease.setBlogContent(blogContent);
        }
        return blogContentRelease;
    }

    @Transactional(readOnly = false)
    @Override
    public boolean saveOrUpdateBlogContentRelease(BlogContentRelease blogContentRelease) {
        if (blogContentRelease == null) {
            log.warn("blogContentRelease is null");
            return false;
        }
        return blogContentReleaseDao.saveOrUpdate(blogContentRelease);
    }

    @Transactional(readOnly = false)
    @Override
    public boolean deletesBlogContentReleaseById(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            log.warn("ids is null");
            return false;
        }
        return blogContentReleaseDao.deletesById(ids);
    }

    @Override
    public BlogContentRelease getBlogContentReleaseByBlogContenId(long id) {
        if (id <= 0) {
            log.warn("id is < =0");
            return null;
        }
        return blogContentReleaseDao.getByBlogContenId(id);
    }

    @Override
    public BlogContentTemplate getblogContentTemplateById(long templateId) {
        return new BlogContentTemplate();
    }

    @Transactional(readOnly = false)
    @Override
    public void addReaderCount(long id,int count) {
        if (id <= 0) {
            log.warn("htmlContentId is < =0");
            return;
        }
        blogContentReleaseDao.addReaderCount(id,count);
    }

    @Transactional(readOnly = false)
    @Override
    public boolean saveOrUpdateBlogContentReleasePhysics(BlogContentRelease blogContentRelease) {
        if (blogContentRelease == null) {
            log.warn("blogContentRelease is null");
            return false;
        }
        if (blogContentRelease.getId() == null || blogContentRelease.getId() <= 0) {
            return blogContentReleaseDao.save(blogContentRelease);
        } else {
            return blogContentReleaseDao.updateByIdPhysics(blogContentRelease);
        }
    }

    @Override
    public long getReaderCount(long id) {
        if (id <= 0) {
            log.warn("id is < 0");
            return 0;
        }
        BlogContentRelease blogContentRelease = blogContentReleaseDao.getById(id);
        return blogContentRelease.getReaderCount();
    }
}
