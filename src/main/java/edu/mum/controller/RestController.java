package edu.mum.controller;

import edu.mum.domain.Block;
import edu.mum.domain.Session;
import edu.mum.repository.SessionRepository;
import edu.mum.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RestController {
    @Autowired
    BlockService blockService;
    @Autowired
    SessionRepository sessionRepository;
    @RequestMapping(value="/student/selectblock/{userId}/{userBadge}", method = RequestMethod.GET)
    public String display(@PathVariable String userId, @PathVariable String userBadge, Model model) {
        model.addAttribute("blocks", blockService.findAll());

        System.out.println("First.....+++++");
        System.out.println(userBadge+" Seeing...");
        model.addAttribute("userBadge",userBadge);
        model.addAttribute("userId",userId);
        return "student/new";
    }

    @RequestMapping(value = "/student/selectblock/{userBadge}", method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute(value="block") Block block, BindingResult bindingResult, @PathVariable String userBadge, Model model)  {
        System.out.println("CHECKING INt>>>> "+Integer.parseInt(userBadge) );
        System.out.println(block.getBlockName() +"   YEPPIIII");
        List<Session> queryData = sessionRepository.findSessionByBlockNameAndId(userBadge);

        if(queryData.size()> 0){
            for(Session sec:queryData){
                System.out.println("Found ---- "+sec.getBarcode() + " Date is "+ sec.getSessionDate());
            }
        }else{
            System.out.println("NULLLLLL Why????");
        }
        List<Session> qData = new ArrayList<>();
        for(int i=0;i<queryData.size();i=i+2){
            qData.add(queryData.get(i));
        }
        System.out.println(qData.size() + "----------- SIze of Query Data");
        model.addAttribute("sessions", qData);
        return "/student/showattendance";
    }



}
