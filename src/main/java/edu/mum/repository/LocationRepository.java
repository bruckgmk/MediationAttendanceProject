package edu.mum.repository;

import edu.mum.domain.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.Position;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
}
