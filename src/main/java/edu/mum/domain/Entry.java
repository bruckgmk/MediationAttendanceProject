package edu.mum.domain;

public class Entry {
    private Integer id;
    private int month;
    private int year;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Entry(Integer id, int month, int year) {
        this.id = id;
        this.month = month;
        this.year = year;
    }
}
