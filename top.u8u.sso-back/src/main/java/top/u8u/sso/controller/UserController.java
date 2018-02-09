package top.u8u.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.u8u.base.vo.Message;
import top.u8u.db.domain.User;
import top.u8u.sso.service.IModelService;
import top.u8u.sso.service.ISsoService;

/**
 * Created by dim on 17-10-17.
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private ISsoService ssoService;
    @Autowired
    private IModelService modelService;

    @RequestMapping("login")
    public Message login(@Validated User users, BindingResult bindingResult){
        Message message = new Message();
        if (bindingResult.hasErrors()) {
            message.setCode(Message.Code.ERROR.ordinal());
            message.setDes(bindingResult.getFieldError().getDefaultMessage());
            return message;
        }
        String sId = modelService.loginByLoginNameAndPassword(users);
        message.add(sId);
        return message;
    }

    @RequestMapping("logout")
    public Message logout(@RequestParam("SID") String sId){
        Message message = new Message();
        modelService.logout(sId);
        return message;
    }
    @RequestMapping("isLogin")
    public Message isLogin(@RequestParam("SID") String sId){
        Message message = new Message();
        return message;
    }
}
