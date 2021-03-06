package edu.mum.service;

import edu.mum.domain.Session;
import edu.mum.domain.Student;

import java.time.LocalDate;
import java.util.List;

public interface SessionService {
    public List<Session> findAll();

    public Session save(Session student);

    public Session findById(Long studentId);

    public void deleteById(Long id);
    List<Session> getBarcodeRecordsList();
    //public void getBarcodeRecordsList() throws Exception;

    List<Session> getBarcodeRecordsList(LocalDate startDate, LocalDate endDate);

}
