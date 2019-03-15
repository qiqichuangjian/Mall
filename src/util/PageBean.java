package util;

import java.util.List;

/**
 * @Acthor:孙琪; date:2019/3/15;
 */
public class PageBean<T> {
    private int count;
    private int pageCount;
    private int pageIndex;
    private List<T> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    //总页数
    public int getPages() {
        return (count+pageCount-1)/pageCount;
        //return count%papageCount==0?count/papageCount:(count/papageCount+1);
    }

   //数据索引值
    public int getIndex() {
        return (pageIndex-1)*pageCount;
    }
}
