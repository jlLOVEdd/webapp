package com.wdd.test.comm.page;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 10:44 2018/9/29
 */
public class Page implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 145646464664632L;

    private int pageNow = 1; // 当前页数

    private int pageSize = 10; // 每页显示记录的条数

    private int totalCount; // 总的记录条数

    private int totalPageCount; // 总的页数

    private String queryName = "query";

    private List<Integer> pageNos = new ArrayList<>();

    public List<Integer> getPageNos() {
        int start = (pageNow % 10 == 0) ? ((pageNow / 10 - 1) * 10 + 1) : (pageNow / 10 * 10 + 1);
        for (int i = 0; i < 10; i++) {
            if (start + i <= this.getTotalPageCount()) {
                pageNos.add(start + i);
            }
        }
        return pageNos;
    }

    @SuppressWarnings("unused")
    private int startPos; // 开始位置，从0开始

    @SuppressWarnings("unused")
    private boolean hasFirst;// 是否有首页

    @SuppressWarnings("unused")
    private boolean hasPre;// 是否有前一页

    @SuppressWarnings("unused")
    private boolean hasNext;// 是否有下一页

    @SuppressWarnings("unused")
    private boolean hasLast;// 是否有最后一页

    public Page() {
    }

    public Page(int pageNow, int pageSize, int totalCount) {
        this.pageNow = pageNow;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    public Page(int pageNow) {
        this.pageNow = pageNow;
    }

    public Page(int totalCount, int pageNow) {
        this.totalCount = totalCount;
        this.pageNow = pageNow;
    }

    public int getTotalPageCount() {
        totalPageCount = getTotalCount() / getPageSize();
        return (totalCount % pageSize == 0) ? totalPageCount
                : totalPageCount + 1;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Page setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getStartPos() {
        return (pageNow - 1) * pageSize;
    }

    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    /**
     * 是否是第一页
     *
     * @return
     */
    public boolean isHasFirst() {
        return (pageNow == 1) ? false : true;
    }

    public void setHasFirst(boolean hasFirst) {
        this.hasFirst = hasFirst;
    }

    public boolean isHasPre() {
        // 如果有首页就有前一页，因为有首页就不是第一页
        return isHasFirst() ? true : false;
    }

    public void setHasPre(boolean hasPre) {
        this.hasPre = hasPre;
    }

    public boolean isHasNext() {
        // 如果有尾页就有下一页，因为有尾页表明不是最后一页
        return isHasLast() ? true : false;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasLast() {
        // 如果不是最后一页就有尾页
        return (pageNow == getTotalPageCount()) ? false : true;
    }

    public void setHasLast(boolean hasLast) {
        this.hasLast = hasLast;
    }

    public String getQueryName() {
        return queryName;
    }

    public Page setQueryName(String queryName) {
        this.queryName = queryName;
        return this;
    }
}
