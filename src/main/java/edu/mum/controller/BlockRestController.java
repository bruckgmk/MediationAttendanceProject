package edu.mum.controller;

import edu.mum.domain.Block;

import edu.mum.domain.BlockException;
import edu.mum.service.BlockService;
import edu.mum.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
public class BlockRestController {
    @Autowired
    SectionService sectionService;
    @Autowired
    BlockService blockService;
    @RequestMapping(value = "/admin/block/blockrest", method = RequestMethod.GET)
    public String display(Model model) {
model.addAttribute("sections", sectionService.findAll());
        return "admin/blockrest";
    }

    @RequestMapping(value = "/admin/blockrest/addblock", method = RequestMethod.POST)
    public @ResponseBody
    Block add(@Valid @RequestBody Block block) {

        if (block.getBlockName().equals("Ex")) {
            throw new BlockException(block.getBlockName().toString(), "Unable to save block with blockName: ");
        }
        blockService.save(block);
        return block;

    }
}