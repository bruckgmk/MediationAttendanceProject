package edu.mum.service.Impl;

import edu.mum.domain.Entry;
import edu.mum.repository.EntryRepository;
import edu.mum.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class EntryServiceImpl implements EntryService {

    @Autowired
    EntryRepository entryRepository;
    @Override
    public List<Entry> findAll() {

        return (List<Entry>) entryRepository.findAll();
    }

    @Override
    public Entry save(Entry entry) {

        return entryRepository.save(entry);
    }

    @Override
    public Entry findById(Long entryId) {

        return entryRepository.findById(entryId).get();
    }

    @Override
    public void deleteById(Long id) {
      entryRepository.deleteById(id);
    }
}
