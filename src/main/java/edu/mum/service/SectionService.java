package edu.mum.service;

import edu.mum.domain.Faculty;
import edu.mum.domain.Section;

import java.util.List;

public interface SectionService {
    public List<Section> findAll();

    public Section save(Section section);

    public Section findById(Long sectionId);

    public void deleteById(Long id);
}
