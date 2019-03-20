package edu.mum.controller;


import edu.mum.domain.*;
import edu.mum.repository.LocationRepository;
import edu.mum.repository.ScheduleRepository;
import edu.mum.service.DatabaseLoaderService;
import edu.mum.service.Impl.RoleServiceImpl;
import edu.mum.service.RoleService;
import edu.mum.service.SessionService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import edu.mum.service.UserService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.GrantedAuthority;
@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    DatabaseLoaderService dbService;
    @Autowired
    ScheduleRepository tiRepository;

    @Autowired
    SessionService bService;
    @Autowired
    LocationRepository locRepository;


    @RequestMapping(value={"/login"}, method= RequestMethod.GET)
    public String loginScreen(){

        return "login";
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        //------------------------------------- **** -------------------------------------------------//

        System.out.println("ProblemWWW!!!*****************");
        try {
            Schedule time1 = new Schedule("1","AM",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("2013-04-26 08:00:55.705"), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("2013-04-26 08:20:55.705"));
            tiRepository.save(time1);
            Location location = new Location("1", "DB","Argiro","R12",250);
            locRepository.save(location);
        } catch (ParseException e) {
            System.out.println("Problem!!!*****************");
            e.printStackTrace();
        }
        try {
            Schedule time2 = new Schedule("2","EAM",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("2013-04-26 08:45:55.705"), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("2013-04-26 09:10:55.705"));
            tiRepository.save(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //------------------------------------- **** -------------------------------------------------//

        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
/*        Role facultyRole = new Role();
        facultyRole.setId(5);
        facultyRole.setRole("Faculty");
        roleService.save(facultyRole);*/
        List<Role> roles = new ArrayList<>();
        roles = roleService.listRoles();
        modelAndView.addObject("roles", roles);
        List<String> nationality = new ArrayList<>();
        nationality.add("Ethiopian");
        nationality.add("Eritrean");
        nationality.add("Haiti");

        modelAndView.addObject("nationalities", nationality);

        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {


        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("/registration");

        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("Testing the result....");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
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
            String name = auth.getName();
            User user = userService.findUserByUsername(name);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("userNames", "Welcome " + user.getStudent().getFirstName() + " " + user.getStudent().getLastName());
            System.out.println(name+" andm HAHAHAHAHA.....");
            System.out.println(user.getStudent().getFirstName() +"WOWOWOWOWO.....");
            return "redirect:/admin/dashboard";
        }else{
            //System.out.println(request.get)
            return "redirect:/student/dashboard";
        }

    }
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

}
