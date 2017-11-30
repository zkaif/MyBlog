package top.u8u.db.mapper;

import org.apache.ibatis.annotations.Param;
import top.u8u.base.db.mapper.BaseMapper;
import top.u8u.db.domain.ContactMessage;

import java.util.List;

/**
 * Created by dim on 17-9-2.
 */
public interface ContactMessageMapper extends BaseMapper<ContactMessage> {
    boolean read(@Param("ids") List<Long> ids);
}
