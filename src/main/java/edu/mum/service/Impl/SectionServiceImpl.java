package edu.mum.service.Impl;

import edu.mum.domain.Section;
import edu.mum.repository.SectionRepository;
import edu.mum.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SectionServiceImpl implements SectionService {
    @Autowired
    SectionRepository sectionRepository;
    @Override
    public List<Section> findAll() {
        return (List<Section>) sectionRepository.findAll();
    }

    @Override
    public Section save(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public Section findById(Long sectionId) {

        return sectionRepository.findById(sectionId).get();
    }

    @Override
    public void deleteById(Long id) {
     sectionRepository.deleteById(id);
    }
}
