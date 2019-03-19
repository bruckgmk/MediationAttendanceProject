package edu.mum.service.Impl;

import edu.mum.domain.Student;
import edu.mum.repository.StudentRepository;
import edu.mum.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findById(Long studentId) {
        return studentRepository.findById(studentId).get();
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }


}