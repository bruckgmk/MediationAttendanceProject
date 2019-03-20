package edu.mum.service;

import edu.mum.domain.Block;
import edu.mum.domain.Location;

import java.util.List;

public interface LocationService {
    public List<Location> findAll();

        public Location save(Location position);

    public Location findById(Long positionId);

    public void deleteById(Long id);
}
