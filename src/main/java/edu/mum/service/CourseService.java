package edu.mum.service;

import edu.mum.domain.Course;
import edu.mum.domain.Entry;

import java.util.List;

public interface CourseService {
    public List<Course> findAll();

    public Course save(Course course);

    public Course findById(Long courseId);

    public void deleteById(Long id);
}
