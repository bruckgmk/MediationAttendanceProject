package edu.mum.service;

import edu.mum.domain.Entry;
import edu.mum.domain.Faculty;

import java.util.List;

public interface FacultyService {
    public List<Faculty> findAll();

    public Faculty save(Faculty faculty);

    public Faculty findById(Long facultyId);

    public void deleteById(Long id);
}
