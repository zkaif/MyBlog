package top.u8u.storage.dao;

import top.u8u.base.dao.IBaseDao;
import top.u8u.db.domain.Content;

import java.util.List;

/**
 * Created by dim on 17-10-14.
 */
public interface IContentDao extends IBaseDao<Content> {
    boolean deletePhysics(List<Long> ids);
}
