package edu.mum.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import javax.validation.Valid;
import java.util.Collection;

import edu.mum.service.UserService;
import org.springframework.web.servlet.ModelAndView;
import edu.mum.domain.User;
import org.springframework.security.core.GrantedAuthority;
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value={"/login"}, method= RequestMethod.GET)
    public String loginScreen(){

        return "login";
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("Testing the result....");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        //modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @RequestMapping(value={"/admin/dashboard"}, method = RequestMethod.GET)
    public String adminDashboard(){
        return "/admin/dashboard";
    }
    @RequestMapping(value={"/student/dashboard"}, method = RequestMethod.GET)
    public String studentDashboard(){
        return "/student/dashboard";
    }
    @RequestMapping(value={"/dashboard"})
    public String dashboardFilter(HttpServletRequest request){
        Collection<? extends GrantedAuthority> authorities;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        authorities = auth.getAuthorities();
        String myRole = authorities.toArray()[0].toString();
        String admin = "admin";

        if(myRole.equals("ADMIN")){
            return "redirect:/admin/dashboard";
        }else{
            //System.out.println(request.get)
            return "redirect:/students/dashboard";
        }

    }
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

}
