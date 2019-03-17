package edu.mum.config.domain;

import java.util.Date;
import java.util.List;

public class Session {
    private long id;
    private Date sessionDate;
    private boolean attended;
    private Location location;
    private List<Student> students;
}
