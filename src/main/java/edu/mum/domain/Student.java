package edu.mum.domain;

import java.util.Date;
import java.util.List;

public class Student {
    private String firstName;
    private String lastName;
    private int studentId;
    private long badgeCode;
    private String email;
    private String nationality;
    private String phone;
    private Date birthDate;
    private List<Entry> entries;
    private List<Role> roles;
    private List<Section> sections;
    private Session session;
    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studentId=" + studentId +
                ", badgeCode=" + badgeCode +
                ", email='" + email + '\'' +
                ", nationality='" + nationality + '\'' +
                ", phone='" + phone + '\'' +
                ", birthDate=" + birthDate +
                '}';
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

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public long getBadgeCode() {
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

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    public String getFirstName() {
        return firstName;
    }

}
