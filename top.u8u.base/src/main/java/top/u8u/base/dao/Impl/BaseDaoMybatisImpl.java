package top.u8u.base.dao.Impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import top.u8u.base.dao.IBaseDao;
import top.u8u.base.db.domain.BaseDomain;
import top.u8u.base.db.mapper.BaseMapper;

import java.util.List;

/**
 * Created by dim on 17-9-2.
 */
public abstract class BaseDaoMybatisImpl<BM extends BaseMapper<BD>,BD extends BaseDomain> extends ServiceImpl<BM,BD> implements IBaseDao<BD> {
    @Override
    public boolean save(BD domain) {
        return super.insert(domain);
    }

    @Override
    public boolean update(BD domain) {
        return super.updateById(domain);
    }

    @Override
    public boolean saveOrUpdate(BD domain) {
        Long id = domain.getId();
        if (id==null) {
            return save(domain);
        }else{
            return update(domain);
        }
    }

    @Override
    public boolean deleteById(long id) {
        return super.deleteById(id);
    }

    @Override
    public boolean deletesById(List<Long> id) {
        return super.deleteBatchIds(id);
    }

    @Override
    public BD getById(long id) {
        return super.selectById(id);
    }

    @Override
    public List<BD> getAll(Wrapper<BD> wrapper) {
        return super.selectList(wrapper);
    }

    @Override
    public long getCount(Wrapper<BD> wrapper) {
        return super.selectCount(wrapper);
    }

    @Override
    public top.u8u.base.vo.Page<BD> getAllPage(BD bd, Wrapper<BD> wrapper) {
        if (bd.getPageD()<=0){
            bd.setPageD(1);
        }
        if (bd.getCountD()==0)
        {
            bd.setCountD(Integer.MAX_VALUE);
        }
        Page<BD> page = super.selectPage(new Page<>(bd.getPageD(),bd.getCountD()),wrapper);
        top.u8u.base.vo.Page<BD> myPage = new top.u8u.base.vo.Page<BD>();
        myPage.setPage(page.getCurrent());
        myPage.setCount(page.getSize());
        myPage.setTotal(page.getTotal());
        myPage.setTotalPage(page.getPages());
        myPage.setData(page.getRecords());
        return myPage;
    }
}
