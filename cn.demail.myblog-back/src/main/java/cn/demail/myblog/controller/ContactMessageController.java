package cn.demail.myblog.controller;

import cn.demail.myblog.service.IMyBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.u8u.base.vo.Message;
import top.u8u.base.vo.Page;
import top.u8u.db.domain.ContactMessage;

import java.util.List;

/**
 * Created by dim on 17-9-2.
 */
@RestController
@RequestMapping("contactMessage")
public class ContactMessageController {

    @Autowired
    private IMyBlogService myBlogService;

    @RequestMapping("create")
    public Message create(@Validated ContactMessage contactMessage, BindingResult bindingResult){
        Message message = new Message();
        if (bindingResult.hasErrors()) {
            message.setCode(Message.Code.ERROR.ordinal());
            message.setDes(bindingResult.getFieldError().getDefaultMessage());
            return message;
        }
        if(contactMessage == null){
            message.setCode(Message.Code.ERROR.ordinal());
            message.setDes("contactMessage is null");
            return message;
        }
        myBlogService.createContactMessage(contactMessage);
        return message;
    }


    @RequestMapping("deletesById/{ids}")
    public Message deleteById(@PathVariable("ids") List<Long> id){
        Message message = new Message();
        boolean b = myBlogService.deletesContactMessageById(id);
        if (!b) {
            message.setCode(Message.Code.ERROR.ordinal());
        }
        return message;
    }
    @RequestMapping("list")
    public Message getBlogTypeList(ContactMessage contactMessage){
        Message message = new Message();
        Page<ContactMessage> contactMessages = myBlogService.getContactMessageList(contactMessage);
        message.add(contactMessages);
        return message;
    }

    @RequestMapping("getById/{id}")
    public Message getById(@PathVariable("id") long id){
        Message message = new Message();
        ContactMessage contactMessage = myBlogService.getContactMessageById(id);
        message.add(contactMessage);
        return message;
    }

    @RequestMapping("read/{ids}")
    public Message read(@PathVariable("ids") List<Long> ids){
        Message message = new Message();
        boolean b = myBlogService.readContactMessageById(ids);
        if (!b) {
            message.setCode(Message.Code.ERROR.ordinal());
        }
        return message;
    }
}
