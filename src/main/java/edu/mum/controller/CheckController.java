package edu.mum.controller;


import edu.mum.domain.Location;
import edu.mum.domain.Schedule;
import edu.mum.repository.LocationRepository;
import edu.mum.repository.ScheduleRepository;
import edu.mum.service.DatabaseLoaderService;
import edu.mum.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class CheckController {
    @Autowired
    DatabaseLoaderService dbService;
    @Autowired
    ScheduleRepository tiRepository;

    @Autowired
    SessionService bService;
    @Autowired
    LocationRepository locRepository;

    @RequestMapping(value={"/check"},method=RequestMethod.GET)
    public String one(){

        try {
            dbService.loadScannedBarcodesToDatabase();
            //bService.getBarcodeRecordsList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //dbService.loadScannedBarcodesToDatabase();
        return "check";
    }
    @RequestMapping(value = {"/demodata"}, method = RequestMethod.GET)
    public String two(){
        System.out.println("ProblemWWW!!!*****************");
/*        try {
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
        }*/
        return "check";
    }

/*    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public String onee(){
        return "fileupload";
    }


        @RequestMapping(value="/upload", method=RequestMethod.POST)
        public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
                                                     @RequestParam("file") MultipartFile file){
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    BufferedOutputStream stream =
                            new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                    stream.write(bytes);
                    stream.close();
                    return "You successfully uploaded " + name + " into " + name + "-uploaded !";
                } catch (Exception e) {
                    return "You failed to upload " + name + " => " + e.getMessage();
                }
            } else {
                return "You failed to upload " + name + " because the file was empty.";
            }
        }*/





}
