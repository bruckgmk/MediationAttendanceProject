package edu.mum.service.Impl;

import edu.mum.domain.Course;
import edu.mum.repository.CourseRepository;
import edu.mum.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Override
    public List<Course> findAll() {

        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course findById(Long courseId) {
        return courseRepository.findById(courseId).get();
    }

    @Override
    public void deleteById(Long id) {
    courseRepository.deleteById(id);
    }
}
