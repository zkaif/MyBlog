package top.u8u.db.mapper;

import org.apache.ibatis.annotations.Select;
import top.u8u.base.db.mapper.BaseMapper;
import top.u8u.db.domain.User;

/**
 * Created by dim on 17-10-17.
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM USER WHERE DEL_FLAG = 0 AND LOGIN_NAME = #{loginName} AND PASSWORD = #{password}")
    User getByLoginNameAndPassword(User user);
}
