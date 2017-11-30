package cn.demail.myblog.controller;

import cn.demail.myblog.service.IModuleService;
import cn.demail.myblog.service.IMyBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.u8u.base.vo.Message;
import top.u8u.base.vo.Page;
import top.u8u.db.domain.Comment;

import java.util.List;

/**
 * Created by dim on 17-9-15.
 */
@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private IMyBlogService myBlogService;
    @Autowired
    private IModuleService moduleService;

    @RequestMapping("create")
    public Message create(@Validated Comment comment, BindingResult bindingResult){
        Message message = new Message();
        if (bindingResult.hasErrors()) {
            message.setCode(Message.Code.ERROR.ordinal());
            message.setDes(bindingResult.getFieldError().getDefaultMessage());
            return message;
        }
        if(comment == null){
            message.setCode(Message.Code.ERROR.ordinal());
            message.setDes("contactMessage is null");
            return message;
        }
        moduleService.createComment(comment);
        return message;
    }

    @RequestMapping("list")
    public Message list(Comment comment){
        Message message = new Message();
        Page<Comment> comments = myBlogService.getCommentList(comment);
        message.add(comments);
        return message;
    }

    @RequestMapping("deletesById/{ids}")
    public Message deleteById(@PathVariable("ids") List<Long> id){
        Message message = new Message();
        boolean b = myBlogService.deletesCommentById(id);
        if (!b) {
            message.setCode(Message.Code.ERROR.ordinal());
        }
        return message;
    }


    @RequestMapping("listByBlogId/{blogId}")
    public Message listByBlogId(@PathVariable("blogId") long blogId){
        Message message = new Message();
        List<Comment> comments = moduleService.getCommentListByBlogId(blogId);
        message.add(comments);
        return message;
    }
}
