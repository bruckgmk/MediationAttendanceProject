package edu.mum.controller;

import edu.mum.domain.Block;
import edu.mum.service.BlockService;
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
public class BlockController {
    @Autowired
    private BlockService blockService;

    @Autowired
    private SectionService sectionService;

    @RequestMapping(value="/admin/block/blocklist", method= RequestMethod.GET)
    public String blocks(Model model) {

        List<Block> blocks = blockService.findAll();
        model.addAttribute("blocks", blocks);
        return "/admin/block/list";
    }

    @RequestMapping(value="/admin/block/block_save", method = RequestMethod.GET)
    public String blockRegistrationForm(@ModelAttribute("block") Block block,Model model){
        model.addAttribute("sections", sectionService.findAll());
        return "/admin/block/new";
    }

    @RequestMapping(value = "/admin/block/block_save", method = RequestMethod.POST)
    public String registerNewBlock(@Valid @ModelAttribute("block") Block block,
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/admin/block/new";
        }
        block = blockService.save(block);
        return "redirect:/admin/block/blocklist";
    }

    @RequestMapping(value="/admin/block/blockedit/{id}", method = RequestMethod.GET)
    public String editBlock(@PathVariable Long id, Model model){
        Block block = blockService.findById(id);
        if (block != null) {
            model.addAttribute("block", block);
            blockService.deleteById(id);
            return "/admin/block/edit";
        }
        return "/admin/block/list";
    }

    @RequestMapping(value = "/admin/block/blockedit", method = RequestMethod.POST)
    public String updateBlock(@Valid @ModelAttribute("block") Block block,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("blocks", bindingResult.getAllErrors());
            return "/admin/block/edit";
        }
        block = blockService.save(block);
        return "redirect:/admin/blocklist";
    }
    @RequestMapping(value="/admin/block/blockdelete/{id}", method=RequestMethod.GET)
    public String deleteBlock(@PathVariable("id") Long id){
        blockService.deleteById(id);
        return "/admin/block/list";
    }
}
