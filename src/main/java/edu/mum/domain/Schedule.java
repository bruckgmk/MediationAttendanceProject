package edu.mum.domain;


import javax.persistence.*;
import java.sql.Time;
import java.util.List;
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Time time;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Block> blocks;
    @OneToOne(cascade = CascadeType.ALL)
    private Location location;
    @OneToOne(cascade = CascadeType.ALL)
    private Session session;

}
