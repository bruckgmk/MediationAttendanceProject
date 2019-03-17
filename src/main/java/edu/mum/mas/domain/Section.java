package edu.mum.mas.domain;

import java.util.Date;
import java.util.List;

public class Section {
    private long id;
    private Date startDate;
    private Date endDate;
    private List<Student> students;
    private Course course;
    private Block block;
    private Faculty faculty;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
