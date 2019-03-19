package edu.mum.service;

import edu.mum.domain.Student;

import java.util.List;

public interface StudentService {
    public List<Student> findAll();

    public Student save(Student student);

    public Student findById(Long studentId);

    public void deleteById(Long id);
}
