package cn.demail.myblog.controller;

import cn.demail.myblog.service.IModuleService;
import cn.demail.myblog.service.IMyBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.u8u.base.vo.Message;
import top.u8u.db.domain.DateType;

import java.util.List;

/**
 * Created by dim on 17-9-4.
 */
@RestController
@RequestMapping("dateType")
public class DateTypeController {
    @Autowired
    private IModuleService moduleService;

    @RequestMapping("list")
    public Message getDateTypeList(){
        Message message = new Message();
        List<DateType> dateTypes = moduleService.getDateTypeList();
        message.add(dateTypes);
        return message;
    }
}
