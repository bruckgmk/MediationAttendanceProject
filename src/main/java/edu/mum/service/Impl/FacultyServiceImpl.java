package edu.mum.service.Impl;

import edu.mum.domain.Faculty;
import edu.mum.repository.FacultyRepository;
import edu.mum.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class FacultyServiceImpl implements FacultyService {
    @Autowired
    FacultyRepository facultyRepository;
    @Override
    public List<Faculty> findAll() {
        return (List<Faculty>)facultyRepository.findAll();
    }

    @Override
    public Faculty save(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findById(Long facultyId) {
        return facultyRepository.findById(facultyId).get();
    }

    @Override
    public void deleteById(Long id) {
facultyRepository.deleteById(id);
    }
}
