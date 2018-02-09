package top.u8u.sso.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.u8u.db.domain.User;
import top.u8u.sso.dao.IUserDao;
import top.u8u.sso.service.ISsoService;

/**
 * Created by dim on 17-10-3.
 */
@Transactional(readOnly = true)
@Service("ssoService")
public class SsoServiceImpl implements ISsoService {
    private static Log log = LogFactory.getLog(SsoServiceImpl.class);
    @Autowired
    IUserDao usersDao;

    @Override
    public User getUsersByLoginNameAndPassword(User user) {
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
        return usersDao.getByLoginNameAndPassword(user);
    }
}
