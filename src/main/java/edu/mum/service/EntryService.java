package edu.mum.service;

import edu.mum.domain.Entry;

import java.util.List;

public interface EntryService {
    public List<Entry> findAll();

    public Entry save(Entry entry);

    public Entry findById(Long entryId);

    public void deleteById(Long id);
}
