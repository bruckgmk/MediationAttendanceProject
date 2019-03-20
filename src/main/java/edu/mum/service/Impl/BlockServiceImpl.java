package edu.mum.service.Impl;

import edu.mum.domain.Block;
import edu.mum.repository.BlockRepository;
import edu.mum.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlockServiceImpl implements BlockService {

    @Autowired
    private BlockRepository blockRepository;
    @Override
    public List<Block> findAll() {
        return (List<Block>)blockRepository.findAll();
    }

    @Override
    public Block save(Block block) {
        return blockRepository.save(block);
    }

    @Override
    public Block findById(Long blockId) {
        return blockRepository.findById(blockId).get();
    }

    @Override
    public void deleteById(Long id) {
        blockRepository.deleteById(id);
    }
}
