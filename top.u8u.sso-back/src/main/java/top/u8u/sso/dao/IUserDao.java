package top.u8u.sso.dao;

import top.u8u.base.dao.IBaseDao;
import top.u8u.db.domain.User;

/**
 * Created by dim on 17-10-17.
 */
public interface IUserDao extends IBaseDao<User>{
    User getByLoginNameAndPassword(User user);
}
