package edu.mum.domain;

import javax.persistence.*;
import java.text.ParseException;

@Entity
public class Location {
    @Id
  //  @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    @Column(nullable=false)
    private String building;

    @Column(nullable=true)
    private String room;

    @Column(nullable=false)
    private int capacity;

    public Location(String id, String name, String building, String room, int capacity) {
        this.id = id;
        this.name = name;
        this.building = building;
        this.room = room;
        this.capacity = capacity;
    }
public Location() throws ParseException {

}
    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
