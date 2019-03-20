package edu.mum.service.Impl;

import edu.mum.domain.Location;
import edu.mum.repository.LocationRepository;
import edu.mum.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {
    @Autowired
    LocationRepository locationRepository;


    @Override
    public List<Location> findAll() {
        return (List<Location>)locationRepository.findAll();
    }

    @Override
    public Location save(Location position) {
        return locationRepository.save(position);
    }

    @Override
    public Location findById(Long positionId) {
        return locationRepository.findById(positionId).get();
    }

    @Override
    public void deleteById(Long id) {
    locationRepository.deleteById(id);
    }
}
