package top.u8u.db.mapper;

import org.apache.ibatis.annotations.Param;
import top.u8u.base.db.mapper.BaseMapper;
import top.u8u.db.domain.Content;

import java.util.List;

/**
 * Created by dim on 17-9-2.
 */
public interface ContentMapper extends BaseMapper<Content>{
    boolean deletePhysics(@Param("ids") List<Long> ids);
}
