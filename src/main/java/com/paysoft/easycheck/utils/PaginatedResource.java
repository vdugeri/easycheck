package com.paysoft.easycheck.utils;

import java.util.List;

public class PaginatedResource<T> {

    PaginationMetadata meta;
    private List<T> data;


    public PaginationMetadata getMeta() {
        return meta;
    }

    public void setMeta(PaginationMetadata meta) {
        this.meta = meta;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
