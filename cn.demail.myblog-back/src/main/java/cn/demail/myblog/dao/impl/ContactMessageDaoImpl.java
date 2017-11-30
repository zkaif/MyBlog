package cn.demail.myblog.dao.impl;

import cn.demail.myblog.dao.IContactMessageDao;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Repository;
import top.u8u.base.dao.Impl.BaseDaoMybatisImpl;
import top.u8u.base.vo.Page;
import top.u8u.db.domain.ContactMessage;
import top.u8u.db.mapper.ContactMessageMapper;

import java.util.List;


/**
 * Created by dim on 17-9-2.
 */

@Repository("contactMessageDao")
public class ContactMessageDaoImpl extends BaseDaoMybatisImpl<ContactMessageMapper,ContactMessage> implements IContactMessageDao {

    @Override
    public Page<ContactMessage> getAllPage(ContactMessage contactMessage) {
        return getAllPage(contactMessage, new EntityWrapper<>());
    }

    @Override
    public boolean read(List<Long> ids) {
        return baseMapper.read(ids);
    }
}
