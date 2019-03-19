package edu.mum.controller;

import edu.mum.domain.Faculty;
import edu.mum.domain.Section;
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
public class SectionController {
    @Autowired
    SectionService sectionService;
    @RequestMapping(value="/sectionlist", method= RequestMethod.GET)
    public String sections(Model model) {

        List<Section> sections = sectionService.findAll();
        model.addAttribute("sections", sections);
        return "sections/list";
    }

    @RequestMapping(value="/section_save", method = RequestMethod.GET)
    public String facultyRegistrationForm(@ModelAttribute("section") Section section){
        return "sections/new";
    }

    @RequestMapping(value = "/section_save", method = RequestMethod.POST)
    public String registerNewFaculty(@Valid @ModelAttribute("section") Section section,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "sections/new";
        }
        section = sectionService.save(section);
        return "redirect:/sectionlist";
    }

    @RequestMapping(value="/sectionedit/{id}", method = RequestMethod.GET)
    public String editFaculty(@PathVariable Long id, Model model){
        Section section = sectionService.findById(id);
        if (section != null) {
            model.addAttribute("section", section);
            sectionService.deleteById(id);
            return "sections/edit";
        }
        return "sections/list";
    }

    @RequestMapping(value = "/sectionedit", method = RequestMethod.POST)
    public String updateFaculty(@Valid @ModelAttribute("section") Section section,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "sections/edit";
        }
        section = sectionService.save(section);
        return "redirect:sectionlist";
    }
    @RequestMapping(value="/sectiondelete/{id}", method=RequestMethod.GET)
    public String deleteFaculty(@PathVariable("id") Long id){
        sectionService.deleteById(id);
        return "homepage/index";
    }
}
