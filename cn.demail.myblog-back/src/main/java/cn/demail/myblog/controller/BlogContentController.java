package cn.demail.myblog.controller;

import cn.demail.myblog.service.IModuleService;
import cn.demail.myblog.service.IMyBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.u8u.base.service.IMemcachedService;
import top.u8u.base.vo.Message;
import top.u8u.base.vo.Page;
import top.u8u.db.domain.BlogContent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dim on 17-9-2.
 */
@RestController
@RequestMapping("blogContent")
public class BlogContentController {
    @Autowired
    private IMyBlogService myBlogService;
    @Autowired
    private IModuleService moduleService;

    @RequestMapping("create")
    public Message create(BlogContent blogContent){
        Message message = new Message();
        blogContent.setDate(new Date());
        boolean b = moduleService.saveOrUpdateBlogContent(blogContent);
        if (!b) {
            message.setCode(Message.Code.ERROR.ordinal());
        }
        return message;
    }

    @RequestMapping("update")
    public Message update(BlogContent blogContent){
        Message message = new Message();
        boolean b = moduleService.saveOrUpdateBlogContent(blogContent);
        if (!b) {
            message.setCode(Message.Code.ERROR.ordinal());
        }
        return message;
    }

    @RequestMapping("deletesById/{ids}")
    public Message deletesById(@PathVariable("ids") List<Long> ids){
        Message message = new Message();
        boolean b = myBlogService.deletesBlogContentById(ids);
        if (!b) {
            message.setCode(Message.Code.ERROR.ordinal());
        }
        return message;
    }

    @RequestMapping("list")
    public Message list(BlogContent blogContent){
        Message message = new Message();
        Page<BlogContent> blogContentPage = myBlogService.getBlogContentList(blogContent);
        message.add(blogContentPage);
        return message;
    }

    @RequestMapping("getById/{id}")
    public Message getById(@PathVariable("id") long id){
        Message message = new Message();
        BlogContent blogContent = moduleService.getBlogContentById(id);
        message.add(blogContent);
        return message;
    }

    @RequestMapping("publish/{ids}")
    public Message publish(@PathVariable("ids") List<Long> ids){
        Message message = new Message();
        List<String> urls = moduleService.publishBlogContents(ids);
        message.add(urls);
        return message;
    }
}
