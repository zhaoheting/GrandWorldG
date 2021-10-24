package com.example.GrandWorldG.model;

/**
 * TODO
 *
 * @author HeTing.Zhao
 * @since 2021/10/24
 **/
public class PageableModel<E> {
    private int pageNum;
    private int pageSize;
    /**
     * Filter condition.
     * 可传入一个对象类型，其属性作为sql查询的参数
     */
    private E condition;

    public E getCondition() {
        return condition;
    }

    public void setCondition(E condition) {
        this.condition = condition;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
