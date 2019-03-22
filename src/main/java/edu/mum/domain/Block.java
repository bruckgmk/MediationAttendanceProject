package edu.mum.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @DateTimeFormat(pattern="MM-dd-yyyy")
    private LocalDate blockName;
    @OneToMany
    private List<Session> sessions = new ArrayList<>();

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public LocalDate getBlockName() {
        return blockName;
    }

    public void setBlockName(LocalDate blockName) {
        this.blockName = blockName;
    }

    @NotNull
    @DateTimeFormat(pattern="MM-dd-yyyy")
    private Date startDate;
    @NotNull
    @DateTimeFormat(pattern="MM-dd-yyyy")
    private Date endingDate;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Section> sections;

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }


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

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }
}
