package edu.mum.controller;

import edu.mum.domain.Entry;
import edu.mum.domain.Faculty;
import edu.mum.domain.Section;
import edu.mum.repository.SectionRepository;
import edu.mum.service.FacultyService;
import edu.mum.service.SectionService;
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
    SectionService sectionService;
    @RequestMapping(value="/admin/faculty/facultylist", method= RequestMethod.GET)
    public String faculties(Model model) {

        List<Faculty> faculties = facultyService.findAll();
        model.addAttribute("faculties", faculties);
        return "admin/faculties/list";
    }

    @RequestMapping(value="/admin/faculty/faculty_save", method = RequestMethod.GET)
    public String facultyRegistrationForm(@ModelAttribute("faculty") Faculty faculty, Model model){
        /*List<Section> sections= (List<Section>)sectionService.findAll();
        model.addAttribute("sections", sections);*/
        return "admin/faculties/new";
    }

    @RequestMapping(value = "/admin/faculty/faculty_save", method = RequestMethod.POST)
    public String registerNewFaculty(@Valid @ModelAttribute("faculty") Faculty faculty,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "admin/faculties/new";
        }
        faculty = facultyService.save(faculty);
        return "redirect:/admin/faculty/facultylist";
    }

    @RequestMapping(value="/admin/faculty/facultyedit/{id}", method = RequestMethod.GET)
    public String editFaculty(@PathVariable Long id, Model model){
        Faculty faculty = facultyService.findById(id);
        if (faculty != null) {
            model.addAttribute("faculty", faculty);
            //List<Section> sections= (List<Section>)sectionService.findAll();
            facultyService.deleteById(id);
            return "admin/faculties/edit";
        }
        return "admin/faculties/list";
    }

    @RequestMapping(value = "/admin/faculty/facultyedit", method = RequestMethod.POST)
    public String updateFaculty(@Valid @ModelAttribute("faculty") Faculty faculty,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "admin/faculty/faculties/edit";
        }
        faculty = facultyService.save(faculty);
        return "redirect:/admin/faculty/facultylist";
    }
    @RequestMapping(value="/admin/faculty/facultydelete/{id}", method=RequestMethod.GET)
    public String deleteFaculty(@PathVariable("id") Long id){
        facultyService.deleteById(id);
        return "admin/dashboard";
    }
}
