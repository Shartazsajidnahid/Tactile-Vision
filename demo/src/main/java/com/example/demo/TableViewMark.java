package com.example.demo;

public class TableViewMark {
    private Integer docColumn, markColumn;

    public TableViewMark(Integer docColumn, Integer markColumn) {
        this.docColumn = docColumn;
        this.markColumn = markColumn;
    }

    public Integer getDocColumn() {
        return docColumn;
    }

    public void setDocColumn(Integer docColumn) {
        this.docColumn = docColumn;
    }

    public Integer getMarkColumn() {
        return markColumn;
    }

    public void setMarkColumn(Integer markColumn) {
        this.markColumn = markColumn;
    }
}
