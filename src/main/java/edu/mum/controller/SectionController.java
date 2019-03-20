package edu.mum.controller;

import edu.mum.domain.Faculty;
import edu.mum.domain.Section;
import edu.mum.service.FacultyService;
import edu.mum.service.LocationService;
import edu.mum.service.SectionService;
import edu.mum.service.StudentService;
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
public class SectionController {
    @Autowired
    SectionService sectionService;
    @Autowired
    StudentService studentService;
    @Autowired
    FacultyService facultyService;
    @Autowired
    LocationService locationService;
    @RequestMapping(value="/admin/section/sectionlist", method= RequestMethod.GET)
    public String sections(Model model) {

        List<Section> sections = sectionService.findAll();
        model.addAttribute("sections", sections);
        return "admin/sections/list";
    }

    @RequestMapping(value="/admin/section/section_save", method = RequestMethod.GET)
    public String facultyRegistrationForm(@ModelAttribute("section") Section section, Model model){
model.addAttribute("students", studentService.findAll());
model.addAttribute("faculties", facultyService.findAll());
model.addAttribute("locations", locationService.findAll());
        return "admin/sections/new";
    }

    @RequestMapping(value = "/admin/section/section_save", method = RequestMethod.POST)
    public String registerNewFaculty(@Valid @ModelAttribute("section") Section section,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "admin/sections/new";
        }
        section = sectionService.save(section);
        return "redirect:/admin/section/sectionlist";
    }

    @RequestMapping(value="/admin/section/sectionedit/{id}", method = RequestMethod.GET)
    public String editFaculty(@PathVariable Long id, Model model){
        Section section = sectionService.findById(id);
        if (section != null) {
            model.addAttribute("section", section);
            model.addAttribute("students", studentService.findAll());
            model.addAttribute("faculties", facultyService.findAll());
            model.addAttribute("locations", locationService.findAll());
            sectionService.deleteById(id);
            return "admin/sections/edit";
        }
        return "admin/sections/list";
    }

    @RequestMapping(value = "/admin/section/sectionedit", method = RequestMethod.POST)
    public String updateFaculty(@Valid @ModelAttribute("section") Section section,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "admin/sections/edit";
        }
        section = sectionService.save(section);
        return "redirect:/admin/section/sectionlist";
    }
    @RequestMapping(value="/admin/section/sectiondelete/{id}", method=RequestMethod.GET)
    public String deleteFaculty(@PathVariable("id") Long id){
        sectionService.deleteById(id);
        return "admin/dashboard";
    }
}
