package cn.demail.myblog.controller;

import cn.demail.myblog.service.IModuleService;
import cn.demail.myblog.service.IMyBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.u8u.base.vo.Message;
import top.u8u.base.vo.Page;
import top.u8u.db.domain.BlogType;

import java.util.List;

/**
 * Created by dim on 17-9-4.
 */
@RestController
@RequestMapping("blogType")
public class BlogTypeController {
    @Autowired
    private IMyBlogService myBlogService;
    @Autowired
    private IModuleService moduleService;
    @RequestMapping("create")
    public Message create(BlogType blogType){
        Message message = new Message();
        boolean b = myBlogService.saveOrUpdateBlogType(blogType);
        if (!b) {
            message.setCode(Message.Code.ERROR.ordinal());
        }
        return message;
    }

    @RequestMapping("update")
    public Message update(BlogType blogType){
        Message message = new Message();
        boolean b = myBlogService.saveOrUpdateBlogType(blogType);
        if (!b) {
            message.setCode(Message.Code.ERROR.ordinal());
        }
        return message;
    }

    @RequestMapping("deletesById/{id}")
    public Message deletesById(@PathVariable("id") List<Long> id){
        Message message = new Message();
        boolean b = myBlogService.deletesBlogTypeById(id);
        if (!b) {
            message.setCode(Message.Code.ERROR.ordinal());
        }
        return message;
    }
    @RequestMapping("list")
    public Message getBlogTypeList(BlogType blogType){
        Message message = new Message();
        Page<BlogType> blogTypes = moduleService.getBlogTypeList(blogType);
        message.add(blogTypes);
        return message;
    }

    @RequestMapping("getById/{id}")
    public Message getById(@PathVariable("id") long id){
        Message message = new Message();
        BlogType blogType = myBlogService.getBlogTypeById(id);
        message.add(blogType);
        return message;
    }
}
