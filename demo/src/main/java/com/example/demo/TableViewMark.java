package com.example.demo;

public class TableViewMark {
    private String docColumn, markColumn;

    public TableViewMark(String docColumn, String markColumn) {
        this.docColumn = docColumn;
        this.markColumn = markColumn;
    }

    public String getDocColumn() {
        return docColumn;
    }

    public void setDocColumn(String docColumn) {
        this.docColumn = docColumn;
    }

    public String getMarkColumn() {
        return markColumn;
    }

    public void setMarkColumn(String markColumn) {
        this.markColumn = markColumn;
    }
}
