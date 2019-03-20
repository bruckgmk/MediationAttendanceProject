package edu.mum.controller;

import edu.mum.domain.Block;
import edu.mum.domain.Location;
import edu.mum.service.Impl.SessionServiceImpl;
import edu.mum.service.LocationService;
import edu.mum.service.ScheduleService;
import edu.mum.service.SectionService;
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
public class LocationController {
    @Autowired
    LocationService locationService;
    @Autowired
    SectionService sectionService;
    @Autowired
    SessionService sessionService;
    @Autowired
    ScheduleService scheduleService;
    @RequestMapping(value="/admin/location/locationlist", method= RequestMethod.GET)
    public String locations(Model model) {

        List<Location> locations = locationService.findAll();
        model.addAttribute("locations", locations);
        return "/admin/location/list";
    }

    @RequestMapping(value="/admin/location/location_save", method = RequestMethod.GET)
    public String locationRegistrationForm(@ModelAttribute("location") Location location, Model model){
        model.addAttribute("sessions", sessionService.findAll());
        model.addAttribute("sections", sessionService.findAll());
        model.addAttribute("schedules", scheduleService.findAll());
        return "/admin/location/new";
    }

    @RequestMapping(value = "/admin/location/location_save", method = RequestMethod.POST)
    public String registerNewLocation(@Valid @ModelAttribute("location") Location location,
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/admin/location/new";
        }
        location = locationService.save(location);
        return "redirect:/admin/location/locationlist";
    }

    @RequestMapping(value="/admin/location/locationedit/{id}", method = RequestMethod.GET)
    public String editBlock(@PathVariable Long id, Model model){
        Location location = locationService.findById(id);
        if (location != null) {
            model.addAttribute("location", location);
            model.addAttribute("sessions", sessionService.findAll());
            model.addAttribute("sections", sessionService.findAll());
            model.addAttribute("schedules", scheduleService.findAll());
            locationService.deleteById(id);
            return "/admin/location/edit";
        }
        return "/admin/location/list";
    }

    @RequestMapping(value = "/admin/location/locationedit", method = RequestMethod.POST)
    public String updateBlock(@Valid @ModelAttribute("location") Location location,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/admin/location/edit";
        }
        location = locationService.save(location);
        return "redirect:/admin/location/locationlist";
    }
    @RequestMapping(value="/admin/location/locationdelete/{id}", method=RequestMethod.GET)
    public String deleteBlock(@PathVariable("id") Long id){
        locationService.deleteById(id);
        return "/admin/location/list";
    }
}
