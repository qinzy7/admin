package com.qinzy.admin.server.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 自定义分页对象
 *
 * @author wu
 * @since 2019年3月22日21:22:14
 */
/*content: [,…]
first: true
last: false
number: 1
numberOfElements: 3
pages: 3
searchCount: true
size: 3
totalElements: 9
totalPages: 3*/

/*序列化排除字段,适应这坑爹的前端框架!!!*/
@JsonIgnoreProperties(value = {"records", "total", "current"})
@SuppressWarnings("all")
public class MPage<T> extends Page<T> {

    private static final long serialVersionUID = 3804188647433308937L;

    public MPage<T> setPageNum(long pageNum) {
        super.setCurrent(pageNum);
        return this;
    }

    public MPage<T> setPageSize(long pageSize) {
        super.setSize(pageSize);
        return this;
    }

    public MPage() {
    }

    public MPage(long current, long size) {
        super(current, size);
    }

    public List<T> getContent() {
        return super.getRecords();
    }

    public long getNumber() {
        return super.getCurrent();
    }

    public long getNumberOfElements() {
        return super.getRecords().size();
    }

    public long getTotalElements() {
        return super.getTotal();
    }

    public long getTotalPages() {
        return this.getPages();
    }
}
