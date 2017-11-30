package top.u8u.base.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dim on 17-9-2.
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 1639453766133182212L;
    private List<T> data = new ArrayList<>();
    /** 每页显示的条数 */
    private int count;
    /** 数据库中总的数据量 */
    private int total;
    /** 当前的页数 */
    private int page;
    /** 总页数 */
    private int totalPage;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
