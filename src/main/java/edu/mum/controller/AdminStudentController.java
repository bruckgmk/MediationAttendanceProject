package edu.mum.controller;

import java.util.List;

import javax.validation.Valid;

import edu.mum.domain.Entry;
import edu.mum.domain.Student;
import edu.mum.service.EntryService;
import edu.mum.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminStudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private EntryService entryService;

    @RequestMapping(value="/admin/studentlist", method=RequestMethod.GET)
    public String students(Model model) {

        List<Student> students = (List<Student>)studentService.findAll();
        model.addAttribute("students", students);
        return "admin/list";

    }

    @RequestMapping(value="/admin/student_save", method = RequestMethod.GET)
    public String studentRegistrationForm(@ModelAttribute("student") Student student, Model model){
        model.addAttribute("entries", entryService.findAll());
        return "admin/new";
    }

    @RequestMapping(value = "/admin/student_save", method = RequestMethod.POST)
    public String registerNewStudent(@Valid @ModelAttribute("student") Student student,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "admin/new";
        }
        student = studentService.save(student);
        return "redirect:/admin/studentlist";
    }

    @RequestMapping(value="/admin/studentedit/{id}", method = RequestMethod.GET)
    public String editStudent(@PathVariable Long id, Model model){
        Student s = studentService.findById(id);
        if (s != null) {
            model.addAttribute("student", s);
            model.addAttribute("entries", (List<Entry>)entryService.findAll());
            studentService.deleteById(id);
            return "admin/edit";
        }
        return "admin/list";
    }
    /*@RequestMapping(value = "/studentedit", method = RequestMethod.GET)
    public String getStudentEditForm(@ModelAttribute("student") Student student, Model model){
        model.addAttribute("entries", (List<Entry>)entryService.findAll());
        return "students/edit";
    }*/

    @RequestMapping(value = "/admin/studentedit", method = RequestMethod.POST)
    public String updateStudent(@Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "admin/edit";
        }
        student = studentService.save(student);

        return "redirect:studentlist";
    }
    @RequestMapping(value="/admin/studentdelete/{id}", method=RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") Long id){
        studentService.deleteById(id);
        return "homepage/index";
    }
}
