package edu.mum.controller;

import edu.mum.service.DatabaseLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {
    @Autowired
    DatabaseLoaderService dbService;
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "src/main/resources/barcodes/";

    @GetMapping("/admin/upload")
    public String index() {
        return "admin/upload";
    }

    @PostMapping("/admin/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            System.out.println("The file path is "+path);
            try {
                dbService.loadScannedBarcodesToDatabase();
                //bService.getBarcodeRecordsList();
            } catch (Exception e) {
                e.printStackTrace();
            }
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded and imported the session data to the database'" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/admin/uploadStatus";
    }

    @GetMapping("/admin/uploadStatus")
    public String uploadStatus() {
        return "admin/uploadStatus";
    }

}