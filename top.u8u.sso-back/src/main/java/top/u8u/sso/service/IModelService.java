package top.u8u.sso.service;


import top.u8u.db.domain.User;

/**
 * Created by dim on 17-10-3.
 */
public interface IModelService {
    String loginByLoginNameAndPassword(User users);

    void logout(String sId);
}
