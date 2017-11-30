package cn.demail.myblog.dao;

import top.u8u.base.dao.IBaseDao;
import top.u8u.db.domain.ContactMessage;

import java.util.List;

/**
 * Created by dim on 17-9-2.
 */
public interface IContactMessageDao extends IBaseDao<ContactMessage>{
    boolean read(List<Long> ids);
}
