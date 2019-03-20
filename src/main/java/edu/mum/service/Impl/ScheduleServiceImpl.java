package edu.mum.service.Impl;

import edu.mum.domain.Schedule;
import edu.mum.repository.ScheduleRepository;
import edu.mum.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Override
    public List<Schedule> findAll() {
        return (List<Schedule>) scheduleRepository.findAll();
    }

    @Override
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule findById(String scheduleId) {
        return scheduleRepository.findById(scheduleId).get();
    }

    @Override
    public void deleteById(String id) {
      scheduleRepository.deleteById(id);
    }
}
