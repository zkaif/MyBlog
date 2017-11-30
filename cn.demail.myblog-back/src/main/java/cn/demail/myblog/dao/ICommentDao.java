package cn.demail.myblog.dao;

import top.u8u.base.dao.IBaseDao;
import top.u8u.db.domain.Comment;

import java.util.List;

/**
 * Created by dim on 17-9-15.
 */
public interface ICommentDao extends IBaseDao<Comment> {
    List<Comment> getListByBlogId(long blogId);
}
