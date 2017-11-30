package top.u8u.base.dao;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import top.u8u.base.db.domain.BaseDomain;
import top.u8u.base.vo.Page;

import java.util.List;

/**
 * Created by dim on 17-9-2.
 */
public interface IBaseDao<BD extends BaseDomain> extends IService<BD> {
    boolean save(BD domain);
    boolean update(BD domain);
    boolean saveOrUpdate(BD domain);
    boolean deleteById(long id);
    boolean deletesById(List<Long> id);
    BD getById(long id);
    List<BD> getAll(Wrapper<BD> wrapper);
    long getCount(Wrapper<BD> wrapper);
    Page<BD> getAllPage(BD bd, Wrapper<BD> wrapper);

    Page<BD> getAllPage(BD bd);
}
