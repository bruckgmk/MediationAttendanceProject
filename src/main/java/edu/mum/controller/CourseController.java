package edu.mum.controller;

import edu.mum.domain.Course;
import edu.mum.domain.Entry;
import edu.mum.domain.Section;
import edu.mum.service.CourseService;
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
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    SectionService sectionService;
    @RequestMapping(value="/admin/course/courselist", method= RequestMethod.GET)
    public String courses(Model model) {

        List<Course> courses = courseService.findAll();
        model.addAttribute("courses", courses);
        return "admin/courses/list";
    }

    @RequestMapping(value="/admin/course/course_save", method = RequestMethod.GET)
    public String studentRegistrationForm(@ModelAttribute("course") Course course, Model model){
       // List<Section> entrysections= (List<Section>)sectionService.findAll();
       // model.addAttribute("entrysections", entrysections);
        return "admin/courses/new";
    }

    @RequestMapping(value = "admin/course/course_save", method = RequestMethod.POST)
    public String registerNewEntry(@Valid @ModelAttribute("course") Course course,
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "admin/courses/new";
        }
        course = courseService.save(course);
        return "redirect:/admin/course/courselist";
    }

    @RequestMapping(value="/admin/course/courseedit/{id}", method = RequestMethod.GET)
    public String editEntry(@PathVariable Long id, Model model){
        Course course = courseService.findById(id);
        if (course != null) {
            model.addAttribute("course", course);
            courseService.deleteById(id);
           // List<Section> sections= (List<Section>)sectionService.findAll();
            //model.addAttribute("sections", sections);
            return "admin/courses/edit";
        }
        return "admin/courses/list";
    }

    @RequestMapping(value = "/admin/course/courseedit", method = RequestMethod.POST)
    public String updateEntry(@Valid @ModelAttribute("course") Course course,
                              BindingResult bindingResult, Model model) {
        List<Section> sections= (List<Section>)sectionService.findAll();
        model.addAttribute("sections", sections);
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());

            return "admin/courses/edit";
        }
        course = courseService.save(course);
        return "redirect:/admin/course/courselist";
    }
    @RequestMapping(value="/admin/course/coursedelete/{id}", method=RequestMethod.GET)
    public String deleteEntry(@PathVariable("id") Long id){
        courseService.deleteById(id);
        return "admin/dashboard";
    }
}
