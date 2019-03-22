package edu.mum.controller;

import edu.mum.domain.Section;
import edu.mum.domain.Session;
import edu.mum.service.*;
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
public class SessionController {
    @Autowired
    SessionService sessionService;
    @Autowired
    StudentService studentService;
    @Autowired
    LocationService locationService;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    private BlockService blockService;
    @RequestMapping(value="/admin/session/sessionlist", method= RequestMethod.GET)
    public String sessions(Model model) {

        List<Session> sessions = sessionService.findAll();
        model.addAttribute("sessions", sessions);
        return "/admin/sessions/list";
    }

    @RequestMapping(value="/admin/session/session_save", method = RequestMethod.GET)
    public String facultyRegistrationForm(@ModelAttribute("session") Session session, Model model){
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("schedules", scheduleService.findAll());
        model.addAttribute("blocks", blockService.findAll());
        return "admin/sessions/new";
    }

    @RequestMapping(value = "/admin/session/session_save", method = RequestMethod.POST)
    public String registerNewFaculty(@Valid @ModelAttribute("session") Session session,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/admin/sessions/new";
        }
        session = sessionService.save(session);
        return "redirect:/admin/session/sessionlist";
    }

    @RequestMapping(value="/admin/session/sessionedit/{id}", method = RequestMethod.GET)
    public String editSession(@PathVariable Long id, Model model){
        Session session = sessionService.findById(id);
        if (session != null) {
            model.addAttribute("session", session);
            model.addAttribute("students", studentService.findAll());
            model.addAttribute("locations", locationService.findAll());
            model.addAttribute("schedules", scheduleService.findAll());
            model.addAttribute("blocks", blockService.findAll());
            sessionService.deleteById(id);
            return "/admin/sessions/edit";
        }
        return "/admin/sessions/list";
    }

    @RequestMapping(value = "admin/session/sessionedit", method = RequestMethod.POST)
    public String updateFaculty(@Valid @ModelAttribute("session") Session session,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/admin/sessions/edit";
        }
        session = sessionService.save(session);
        return "redirect:/admin/session/sessionlist";
    }
    @RequestMapping(value="/admin/session/sessiondelete/{id}", method=RequestMethod.GET)
    public String deleteFaculty(@PathVariable("id") Long id){
        sessionService.deleteById(id);
        return "admin/dashboard";
    }
}
