package edu.mum.domain;

import javax.persistence.*;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    /*@OneToOne(cascade = CascadeType.ALL)
    private Section section;
    @OneToOne(cascade=CascadeType.ALL)
    private Session session;
    @OneToOne(cascade=CascadeType.ALL)
    private Schedule schedule;*/

    /*public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
