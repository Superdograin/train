package cn.qiluno.train.common.resp;

import java.io.Serializable;
import java.util.List;

public class PageResp <T> implements Serializable {

    /** 总条数 */
    private long total;

    /** 当前页的列表 */
    private List<T> list;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageResp{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
