package edu.mum.service;

import edu.mum.domain.Block;

import java.util.List;

public interface BlockService {

    public List<Block> findAll();

    public Block save(Block block);

    public Block findById(Long blockId);

    public void deleteById(Long id);
}
