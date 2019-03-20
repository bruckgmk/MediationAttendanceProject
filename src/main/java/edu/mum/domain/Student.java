package edu.mum.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotNull
    private Integer studentId;
    @NotNull
    private Long badgeCode;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String nationality;
    @NotEmpty
    private String phone;
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Role> roles;
    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date birthDate;
    @ManyToOne(cascade = CascadeType.ALL)
    private Entry entry;
  @ManyToOne(cascade=CascadeType.ALL)
    private Section section;
    @ManyToOne(cascade= CascadeType.ALL)
    private Session session;
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

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Long getBadgeCode() {
        return badgeCode;
    }

    public void setBadgeCode(long badgeCode) {
        this.badgeCode = badgeCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public void setBadgeCode(Long badgeCode) {
        this.badgeCode = badgeCode;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
