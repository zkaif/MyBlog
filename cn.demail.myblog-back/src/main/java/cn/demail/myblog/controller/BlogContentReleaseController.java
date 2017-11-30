package cn.demail.myblog.controller;

import cn.demail.myblog.service.IModuleService;
import cn.demail.myblog.service.IMyBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.u8u.base.vo.Message;
import top.u8u.base.vo.Page;
import top.u8u.db.domain.BlogContent;
import top.u8u.db.domain.BlogContentRelease;

import java.util.List;

/**
 * Created by dim on 17-9-2.
 */
@RestController
@RequestMapping("blogContentRelease")
public class BlogContentReleaseController {
    @Autowired
    private IMyBlogService myBlogService;
    @Autowired
    private IModuleService moduleService;

    @RequestMapping("deletesById/{ids}")
    public Message deletesById(@PathVariable("ids") List<Long> ids) {
        Message message = new Message();
        boolean b = myBlogService.deletesBlogContentReleaseById(ids);
        if (!b) {
            message.setCode(Message.Code.ERROR.ordinal());
        }
        return message;
    }

    @RequestMapping("list")
    public Message list(BlogContentRelease blogContentRelease) {
        Message message = new Message();
        Page<BlogContentRelease> blogContentReleasePage = moduleService.getBlogContentReleaseList(blogContentRelease);
        message.add(blogContentReleasePage);
        return message;
    }

    @RequestMapping("getById/{id}")
    public Message getById(@PathVariable("id") long id) {
        Message message = new Message();
        BlogContentRelease blogContentRelease = moduleService.getBlogContentReleaseById(id);
        message.add(blogContentRelease);
        return message;
    }

    @RequestMapping("addReaderCount/{id}")
    public Message addReaderCount(@PathVariable("id") long id) {
        Message message = new Message();
        moduleService.addReaderCount(id);
        return message;
    }

    @RequestMapping("getReaderCount/{id}")
    public Message getReaderCount(@PathVariable("id") long id) {
        Message message = new Message();
        long readerCount = moduleService.getReaderCount(id);
        message.add(readerCount);
        return message;
    }

}