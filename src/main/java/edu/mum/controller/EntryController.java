package edu.mum.controller;

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

import javax.validation.Valid;
import java.util.List;

@Controller
public class EntryController {
    @Autowired
    private EntryService entryService;

    @RequestMapping(value="/admin/entry/entrylist", method= RequestMethod.GET)
    public String entries(Model model) {

        List<Entry> entries = entryService.findAll();
        model.addAttribute("entries", entries);
        return "/admin/entries/list";
    }

    @RequestMapping(value="/admin/entry/entry_save", method = RequestMethod.GET)
    public String studentRegistrationForm(@ModelAttribute("entry") Entry entry){

        return "/admin/entries/new";
    }

    @RequestMapping(value = "/admin/entry/entry_save", method = RequestMethod.POST)
    public String registerNewEntry(@Valid @ModelAttribute("entry") Entry entry,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/admin/entries/new";
        }
        entry = entryService.save(entry);
        return "redirect:/admin/entry/entrylist";
    }

    @RequestMapping(value="/admin/entry/entryedit/{id}", method = RequestMethod.GET)
    public String editEntry(@PathVariable Long id, Model model){
        Entry entry = entryService.findById(id);
        if (entry != null) {
            model.addAttribute("entry", entry);
            entryService.deleteById(id);
            return "/admin/entries/edit";
        }
        return "/admin/entries/list";
    }

    @RequestMapping(value = "/admin/entry/entryedit", method = RequestMethod.POST)
    public String updateEntry(@Valid @ModelAttribute("entry") Entry entry,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/admin/entries/edit";
        }
        entry = entryService.save(entry);
        return "redirect:/admin/entry/entrylist";
    }
    @RequestMapping(value="/admin/entry/entrydelete/{id}", method=RequestMethod.GET)
    public String deleteEntry(@PathVariable("id") Long id){
        entryService.deleteById(id);
        return "admin/dashboard";
    }
}
