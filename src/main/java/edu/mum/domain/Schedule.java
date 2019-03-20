package edu.mum.domain;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
@Entity
public class Schedule {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date startingtime;
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date endingtime;
    @Column(nullable=false)
    private String title;
    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    public Schedule(){

    }
    public Schedule(String id, String title, Date startingtime, Date endingtime) {
        super();
        this.id = id;
        this.startingtime = startingtime;
        this.endingtime = endingtime;
        this.title = title;
    }

    public Date getStartingtime() {
        return startingtime;
    }

    public void setStartingtime(Date startingtime) {
        this.startingtime = startingtime;
    }

    public Date getEndingtime() {
        return endingtime;
    }

    public void setEndingtime(Date endingtime) {
        this.endingtime = endingtime;
    }


    /*@OneToOne(cascade = CascadeType.ALL)
    private Session session;*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
/* public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }*/
}
