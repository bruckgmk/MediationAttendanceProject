package edu.mum.service;

import edu.mum.domain.Location;
import edu.mum.domain.Schedule;

import java.util.List;

public interface ScheduleService {
    public List<Schedule> findAll();

    public Schedule save(Schedule schedule);

    public Schedule findById(String scheduleId);

    public void deleteById(String id);
}
