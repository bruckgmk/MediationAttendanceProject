package edu.mum.controller;

import edu.mum.domain.Entry;
import edu.mum.domain.Faculty;
import edu.mum.domain.Section;
import edu.mum.repository.SectionRepository;
import edu.mum.service.FacultyService;
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
public class FacultyController {
    @Autowired
    FacultyService facultyService;
    @Autowired
    SectionRepository sectionRepository;
    @RequestMapping(value="/facultylist", method= RequestMethod.GET)
    public String faculties(Model model) {

        List<Faculty> faculties = facultyService.findAll();
        model.addAttribute("faculties", faculties);
        return "faculties/list";
    }

    @RequestMapping(value="/faculty_save", method = RequestMethod.GET)
    public String facultyRegistrationForm(@ModelAttribute("faculty") Faculty faculty, Model model){
        List<Section> sections= (List<Section>)sectionRepository.findAll();
        model.addAttribute("sections", sections);
        return "faculties/new";
    }

    @RequestMapping(value = "/faculty_save", method = RequestMethod.POST)
    public String registerNewFaculty(@Valid @ModelAttribute("faculty") Faculty faculty,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "faculties/new";
        }
        faculty = facultyService.save(faculty);
        return "redirect:/facultylist";
    }

    @RequestMapping(value="/facultyedit/{id}", method = RequestMethod.GET)
    public String editFaculty(@PathVariable Long id, Model model){
        Faculty faculty = facultyService.findById(id);
        if (faculty != null) {
            model.addAttribute("faculty", faculty);
            facultyService.deleteById(id);
            return "faculties/edit";
        }
        return "faculties/list";
    }

    @RequestMapping(value = "/facultyedit", method = RequestMethod.POST)
    public String updateFaculty(@Valid @ModelAttribute("faculty") Faculty faculty,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "faculties/edit";
        }
        faculty = facultyService.save(faculty);
        return "redirect:facultylist";
    }
    @RequestMapping(value="/facultydelete/{id}", method=RequestMethod.GET)
    public String deleteFaculty(@PathVariable("id") Long id){
        facultyService.deleteById(id);
        return "homepage/index";
    }
}
