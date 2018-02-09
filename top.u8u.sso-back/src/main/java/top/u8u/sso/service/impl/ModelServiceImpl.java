package top.u8u.sso.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.u8u.base.service.IMemcachedService;
import top.u8u.base.utils.JavaUtil;
import top.u8u.db.domain.User;
import top.u8u.sso.service.IModelService;
import top.u8u.sso.service.ISsoService;
import top.u8u.vo.UserInfo;

import java.util.UUID;

/**
 * Created by dim on 17-10-3.
 */
@Service("modelService")
public class ModelServiceImpl implements IModelService {
    private static Log log = LogFactory.getLog(ModelServiceImpl.class);
    @Autowired
    private ISsoService ssoService;
    @Autowired
    private IMemcachedService memcachedService;
    @Override
    public String loginByLoginNameAndPassword(User user) {
        if (user==null){
            log.warn("users is null");
            return null;
        }
        if (StringUtils.isEmpty(user.getLoginName())){
            log.warn("userName is null");
            return null;
        }
        if (StringUtils.isEmpty(user.getPassword())){
            log.warn("password is null");
            return null;
        }
        user.setPassword(JavaUtil.md5(user.getPassword()));
        user = ssoService.getUsersByLoginNameAndPassword(user);
        if(user==null){
            return null;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setLoginName(user.getLoginName());
        userInfo.setuStatus(0);
        userInfo.setsId("user-"+UUID.randomUUID().toString());
        memcachedService.set(userInfo.getsId(),userInfo,3600);
        return userInfo.getsId();
    }

    @Override
    public void logout(String sId) {
        memcachedService.delete(sId);
    }
}
