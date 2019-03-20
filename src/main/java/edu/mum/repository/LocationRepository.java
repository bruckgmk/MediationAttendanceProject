package edu.mum.repository;

import edu.mum.domain.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.Position;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    @Query("select l from Location l where l.name = :name")
    public Location findByName(String name);
}
