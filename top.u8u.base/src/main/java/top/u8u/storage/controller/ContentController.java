package top.u8u.storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.u8u.base.vo.Message;
import top.u8u.base.vo.Page;
import top.u8u.db.domain.Content;
import top.u8u.storage.service.IStroageService;

import java.util.List;

/**
 * Created by dim on 17-10-14.
 */
@RestController
@RequestMapping("content")
public class ContentController {
    @Autowired
    private IStroageService stroageService;

    @RequestMapping("list")
    public Message list(Content content){
        Message message = new Message();
        Page<Content> contentPage = stroageService.getContentList(content);
        message.add(contentPage);
        return message;
    }

    @RequestMapping("delete/{ids}")
    public Message list(@PathVariable("ids") List<Long> ids){
        Message message = new Message();
        boolean b = stroageService.deleteContentByIds(ids);
        message.add(b);
        return message;
    }

}
