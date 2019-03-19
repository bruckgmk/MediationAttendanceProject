package edu.mum.domain;

import javax.persistence.*;
import java.util.List;
@Entity
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int year;
    private int month;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Section> sections;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", year=" + year +
                ", month=" + month +
                '}';
    }
}
