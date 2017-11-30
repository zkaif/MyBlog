package cn.demail.myblog.dao.impl;

import cn.demail.myblog.dao.IDateTypeDao;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Repository;
import top.u8u.base.dao.Impl.BaseDaoMybatisImpl;
import top.u8u.base.vo.Page;
import top.u8u.db.domain.DateType;
import top.u8u.db.mapper.DateTypeMapper;

import java.util.Date;


/**
 * Created by dim on 17-9-2.
 */

@Repository("dateTypeDao")
public class DateTypeDaoImpl extends BaseDaoMybatisImpl<DateTypeMapper,DateType> implements IDateTypeDao {

    @Override
    public DateType getByDate(Date date) {
        DateType dateType = baseMapper.getByDate(date);
        return dateType;
    }

    @Override
    public Page<DateType> getAllPage(DateType dateType) {
        return getAllPage(dateType,new EntityWrapper<>());
    }
}
