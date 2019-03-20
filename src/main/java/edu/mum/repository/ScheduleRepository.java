package edu.mum.repository;

import edu.mum.domain.Schedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, String> {

    @Query("select s from Schedule s where s.title=?1")
    Schedule findByTitle(String title);
}

