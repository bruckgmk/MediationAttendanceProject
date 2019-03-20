package edu.mum.controller;

import edu.mum.domain.Location;
import edu.mum.domain.Schedule;
import edu.mum.service.BlockService;
import edu.mum.service.LocationService;
import edu.mum.service.ScheduleService;
import edu.mum.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ScheduleController {
@Autowired
    ScheduleService scheduleService;
@Autowired
    BlockService blockService;
@Autowired
LocationService locationService;
@Autowired
    SessionService sessionService;

    @RequestMapping(value="/admin/schedule/schedulelist", method= RequestMethod.GET)
    public String locations(Model model) {

        List<Schedule> schedules = scheduleService.findAll();
        model.addAttribute("schedules", schedules);
        return "/admin/schedule/list";
    }

    @RequestMapping(value="/admin/schedule/schedule_save", method = RequestMethod.GET)
    public String locationRegistrationForm(@ModelAttribute("schedule") Schedule schedule, Model model){
       model.addAttribute("blocks", blockService.findAll());
       model.addAttribute("locations", locationService.findAll());
       model.addAttribute("sessions", sessionService.findAll());
        return "/admin/schedule/new";
    }

    @RequestMapping(value = "/admin/schedule/schedule_save", method = RequestMethod.POST)
    public String registerNewLocation(@Valid @ModelAttribute("schedule") Schedule schedule,
                                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/admin/schedule/new";
        }
        schedule = scheduleService.save(schedule);
        return "redirect:/admin/schedule/schedulelist";
    }

    @RequestMapping(value="/admin/schedule/scheduleedit/{id}", method = RequestMethod.GET)
    public String editShedule(@PathVariable Long id, Model model){
        Schedule schedule = scheduleService.findById(id);
        if (schedule != null) {
            model.addAttribute("schedule", schedule);
            model.addAttribute("blocks", blockService.findAll());
            model.addAttribute("locations", locationService.findAll());
            model.addAttribute("sessions", sessionService.findAll());
            scheduleService.deleteById(id);
            return "/admin/schedule/edit";
        }
        return "/admin/schedule/list";
    }

    @RequestMapping(value = "/admin/schedule/scheduleedit", method = RequestMethod.POST)
    public String updateBlock(@Valid @ModelAttribute("schedule") Schedule schedule,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/admin/schedule/edit";
        }
        schedule = scheduleService.save(schedule);
        return "redirect:/admin/schedule/schedulelist";
    }
    @RequestMapping(value="/admin/schedule/scheduledelete/{id}", method=RequestMethod.GET)
    public String deleteSchedule(@PathVariable("id") Long id){
        scheduleService.deleteById(id);
        return "/admin/schedule/list";
    }
}
