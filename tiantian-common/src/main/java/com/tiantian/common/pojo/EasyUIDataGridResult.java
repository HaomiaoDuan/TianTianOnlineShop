package com.tiantian.common.pojo;

import java.util.List;

public class EasyUIDataGridResult {

    //总的记录数
    private long total;
    //每页显示的记录数
    private List<?> rows;
    
    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public List<?> getRows() {
        return rows;
    }
    public void setRows(List<?> rows) {
        this.rows = rows;
    }
    
    
}
