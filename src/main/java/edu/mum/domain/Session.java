package edu.mum.domain;

import edu.mum.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date sessionDate;
    @Column(nullable=false)
    @Temporal(TemporalType.TIME)
    private Date time;
    @ManyToOne
    private Location location;
    @ManyToOne
    private Schedule schedules;


    private String firstName;
    private String lastName;
    private Integer studentId;
    private String barcode;

    public Session(String barcode, LocalDate sessionDate, LocalTime time, Location location, Schedule schedules) {
        setSessionDate(sessionDate);

        setTime(time);

        this.location = location;
        this.schedules = schedules;
        this.barcode = barcode;
    }
public Session(){

}
    public Schedule getSchedules() {
        return schedules;
    }

    public void setSchedules(Schedule schedules) {
        this.schedules = schedules;
    }



    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = DateUtil.convertLocalDateToDate(sessionDate);
    }

/*    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }*/

    public Date getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time =  this.time = DateUtil.convertLocalTimeToDate(time);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

/*    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }*/
}
