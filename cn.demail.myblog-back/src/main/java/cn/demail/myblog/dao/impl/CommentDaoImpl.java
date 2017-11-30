package cn.demail.myblog.dao.impl;

import cn.demail.myblog.dao.ICommentDao;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.stereotype.Repository;
import top.u8u.base.dao.Impl.BaseDaoMybatisImpl;
import top.u8u.base.vo.Page;
import top.u8u.db.domain.Comment;
import top.u8u.db.mapper.CommentMapper;

import java.util.List;


/**
 * Created by dim on 17-9-15.
 */

@Repository("commentDao")
public class CommentDaoImpl extends BaseDaoMybatisImpl<CommentMapper,Comment> implements ICommentDao {
    @Override
    public Page<Comment> getAllPage(Comment comment) {
        Wrapper<Comment> wrapper = new EntityWrapper<>();
        if (comment.getBlogContentId()>0)
            wrapper.eq("BLOG_CONTENT_ID",comment.getBlogContentId());
        return getAllPage(comment,wrapper);
    }

    @Override
    public List<Comment> getListByBlogId(long blogId) {
        Wrapper<Comment> commentWrapper = new EntityWrapper<>();
        commentWrapper.eq("BLOG_CONTENT_ID",blogId);
        return super.getAll(commentWrapper);
    }
}
