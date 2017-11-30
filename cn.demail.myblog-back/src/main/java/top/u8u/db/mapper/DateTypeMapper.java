package top.u8u.db.mapper;

import top.u8u.base.db.mapper.BaseMapper;
import top.u8u.db.domain.DateType;

import java.util.Date;

/**
 * Created by dim on 17-9-2.
 */
public interface DateTypeMapper extends BaseMapper<DateType> {
    DateType getByDate(Date date);
}
