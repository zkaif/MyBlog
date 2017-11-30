package cn.demail.myblog.service.impl;

import cn.demail.myblog.service.IModuleService;
import cn.demail.myblog.service.IMyBlogService;
import cn.demail.myblog.util.Markdown;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.u8u.base.service.IMemcachedService;
import top.u8u.base.utils.HtmlUtil;
import top.u8u.base.vo.Page;
import top.u8u.db.domain.*;
import top.u8u.storage.service.IStroageService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dim on 17-9-2.
 */
@Service("moduleService")
public class ModuleServiceImpl implements IModuleService {
    @Autowired
    private IMyBlogService myBlogService;
    @Autowired
    private IStroageService stroageService;
    @Autowired
    private IMemcachedService memcachedService;

    private static Log log = LogFactory.getLog(ModuleServiceImpl.class);

    @Override
    public boolean saveOrUpdateBlogContent(BlogContent blogContent) {
        if (blogContent == null) {
            log.warn("blogContent is null");
        }
        Content content = stroageService.getContentById(blogContent.getContentId());
        if (content == null) {
            content = stroageService.saveStringToContent(blogContent.getContent(), "blogContent", ".md");
        } else {
            content = stroageService.updateStringToContent(blogContent.getContent(), content);
        }
        if (content == null) {
            log.error("content save fail!!!!!!");
            return false;
        }
        blogContent.setContentId(content.getId());
        return myBlogService.saveOrUpdateBlogContent(blogContent);
    }

    @Override
    public BlogContent getBlogContentById(long id) {
        BlogContent blogContent = myBlogService.getBlogContentById(id);
        Content content = stroageService.getContentById(blogContent.getContentId());
        if (content == null) {
            return blogContent;
        }
        String contentStr = stroageService.getObjectStrByContent(content);
        blogContent.setContent(contentStr);
        return blogContent;
    }

    @Override
    public String publishBlogContent(long id) {
        if (id <= 0) {
            log.warn("id is < 0");
            return null;
        }
        BlogContent blogContent = this.getBlogContentById(id);
        BlogContentTemplate blogContentTemplate = myBlogService.getblogContentTemplateById(blogContent.getTemplateId());
        String htmlTemplate = blogContentTemplate.getContent();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, String> data = new HashMap<String, String>();
        String htmlContent = Markdown.toHtml(blogContent.getContent());
        data.put("content", htmlContent);
        data.put("title", blogContent.getTitle());
        data.put("digest", blogContent.getDigest());
        data.put("date", dateFormat.format(blogContent.getDate()));
        data.put("typeName", blogContent.getBlogType().getName());
        data.put("typeId", String.valueOf(blogContent.getTypeId()));
        data.put("original", String.valueOf(blogContent.getOriginal()));
        data.put("originalStr", String.valueOf(blogContent.getOriginal() ? "原创" : "转载"));
        data.put("dateId", String.valueOf(blogContent.getDateId()));
        data.put("keyWord", blogContent.getKeyWord());
        BlogContentRelease blogContentRelease;
        blogContentRelease = myBlogService.getBlogContentReleaseByBlogContenId(blogContent.getId());
        if (blogContentRelease == null) {
            blogContentRelease = new BlogContentRelease();
            myBlogService.saveOrUpdateBlogContentRelease(blogContentRelease);
        }
        data.put("id", String.valueOf(blogContentRelease.getId()));
        String html = HtmlUtil.createHtml(htmlTemplate, data);
        Content content = stroageService.getContentById(blogContentRelease.getHtmlContentId());
        if (content == null) {
            content = stroageService.saveStringToContent(html, "blogHtml", ".html");
            if (content == null) {
                log.error("content save fail!!!!!!");
                return null;
            }
            stroageService.downloadContent(content, "blog/" + content.getId() + ".html");
        } else {
            content = stroageService.updateStringToContent(html, content);
        }
        blogContentRelease.setBlogContentId(blogContent.getId());
        blogContentRelease.setOriginal(blogContent.getOriginal());
        blogContentRelease.setTypeId(blogContent.getTypeId());
        blogContentRelease.setDateId(blogContent.getDateId());
        blogContentRelease.setcTime(new Date());
        blogContentRelease.setDigest(blogContent.getDigest());
        blogContentRelease.setDelFlag(false);
        blogContentRelease.setKeyWord(blogContent.getKeyWord());
        blogContentRelease.setTitle(blogContent.getTitle());
        blogContentRelease.setHtmlContentId(content.getId());
        blogContentRelease.setUri(content.getUri());
        myBlogService.saveOrUpdateBlogContentReleasePhysics(blogContentRelease);
        return content.getUri();
    }

    @Override
    public List<String> publishBlogContents(List<Long> ids) {
        if (ids == null | ids.isEmpty()) {
            log.warn("ids is null");
            return new ArrayList<String>();
        }
        List<String> uris = new ArrayList<String>();
        for (Long id : ids) {
            String url = this.publishBlogContent(id);
            if (StringUtils.isEmpty(url)) {
                uris.add(url);
            }
            uris.add(url);
        }
        return uris;
    }

    @Override
    public List<DateType> getDateTypeList() {
        String key = "myBlog_getDateTypeList=";
        List<DateType> dateTypes;
        dateTypes = (List<DateType>) memcachedService.get(key);
        if (dateTypes==null) {
            dateTypes = myBlogService.getDateTypeList();
            memcachedService.set(key,dateTypes,600);
        }
        return dateTypes;
    }

    @Override
    public List<Comment> getCommentListByBlogId(long blogId) {
        if (blogId<=0) {
            return new ArrayList<>();
        }
        String key = "myBlog_getCommentListByBlogId="+blogId;

        List<Comment> comments;
        comments = (List<Comment>) memcachedService.get(key);
        if (comments==null) {
            comments = myBlogService.getCommentListByBlogId(blogId);
            memcachedService.set(key,comments,420);
        }
        return comments;
    }

    @Override
    public Page<BlogType> getBlogTypeList(BlogType blogType) {
        if(blogType==null){
            log.warn("blogType is null");
            return new Page<BlogType>();
        }
        String key = "myBlog_getBlogTypeList=";
        Page<BlogType> blogTypePage;
        blogTypePage = (Page<BlogType>) memcachedService.get(key);
        if (blogTypePage == null) {
            blogTypePage = myBlogService.getBlogTypeList(blogType);
            memcachedService.set(key,blogTypePage,540);
        }
        List<BlogType> blogTypes = blogTypePage.getData();
        if (blogTypes!=null){
            Collections.sort(blogTypes);
        }
        return blogTypePage;
    }

    @Override
    public Page<BlogContentRelease> getBlogContentReleaseList(BlogContentRelease blogContentRelease) {
        if (blogContentRelease == null) {
            log.warn("blogContentRelease is null");
            return new Page<BlogContentRelease>();
        }
        StringBuilder keyBuilder = new StringBuilder("myBlog_getBlogContentReleaseList=");
        keyBuilder.append(blogContentRelease.getPageD()).append("_");
        keyBuilder.append(blogContentRelease.getCountD()).append("_");
        keyBuilder.append(blogContentRelease.getOriginal()).append("_");
        keyBuilder.append(blogContentRelease.getDateId()).append("_");
        keyBuilder.append(blogContentRelease.getTypeId()).append("_");
        keyBuilder.append(blogContentRelease.getReaderCount()).append("_");
        String key = keyBuilder.toString();
        Page<BlogContentRelease> blogContentReleasePage;
        blogContentReleasePage = (Page<BlogContentRelease>) memcachedService.get(key);
        if (blogContentReleasePage == null) {
            blogContentReleasePage = myBlogService.getBlogContentReleaseList(blogContentRelease);
            memcachedService.set(key,blogContentReleasePage,480);
        }
        return blogContentReleasePage;
    }

    @Override
    public BlogContentRelease getBlogContentReleaseById(long id) {
        if (id<=0) {
            return null;
        }
        String key = "myBlog_getBlogContentReleaseById="+id;

        BlogContentRelease blogContentRelease;
        blogContentRelease = (BlogContentRelease) memcachedService.get(key);
        if (blogContentRelease==null) {
            blogContentRelease = myBlogService.getBlogContentReleaseById(id);
            memcachedService.set(key,blogContentRelease,480);
        }
        return blogContentRelease;
    }

    @Override
    public void addReaderCount(Long id) {
        myBlogService.addReaderCount(id,1);
    }

    @Override
    public long getReaderCount(long id) {
        if (id<=0) {
            return 0;
        }
        String key = "myBlog_getReaderCount="+id;

        Long readerCount;
        readerCount = (Long) memcachedService.get(key);
        if (readerCount==null) {
            readerCount = myBlogService.getReaderCount(id);
            memcachedService.set(key,readerCount,60);
        }
        return readerCount;
    }

    @Override
    public boolean createComment(Comment comment) {
        String html = Markdown.toHtml(comment.getContent());
        comment.setContent(html);
        boolean b = myBlogService.createComment(comment);
        String key = "myBlog_getCommentListByBlogId="+comment.getBlogContentId();
        memcachedService.delete(key);
        return b;
    }

    @Override
    public void flushAll(){
        memcachedService.flushAll();
    }

}
