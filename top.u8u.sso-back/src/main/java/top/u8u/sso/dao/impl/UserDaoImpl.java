package top.u8u.sso.dao.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.stereotype.Repository;
import top.u8u.base.dao.Impl.BaseDaoMybatisImpl;
import top.u8u.base.vo.Page;
import top.u8u.db.domain.User;
import top.u8u.db.mapper.UserMapper;
import top.u8u.sso.dao.IUserDao;

/**
 * Created by dim on 17-10-17.
 */
@Repository("userDaoImpl")
public class UserDaoImpl extends BaseDaoMybatisImpl<UserMapper,User> implements IUserDao {
    @Override
    public Page<User> getAllPage(User user) {
        Wrapper<User> wrapper = new EntityWrapper<User>();
        return getAllPage(user,wrapper);
    }

    @Override
    public User getByLoginNameAndPassword(User user) {
        return baseMapper.getByLoginNameAndPassword(user);
    }
}
