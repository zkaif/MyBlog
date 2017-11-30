package cn.demail.myblog.dao;

import top.u8u.base.dao.IBaseDao;
import top.u8u.db.domain.DateType;

import java.util.Date;

/**
 * Created by dim on 17-9-2.
 */
public interface IDateTypeDao extends IBaseDao<DateType>{
    DateType getByDate(Date date);
}
