package edu.aam.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class FileHandleController {

    @Value("${image.path}")
    private String UPLOADED_FOLDER;

    @RequestMapping(value = "/image/{avatar}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getImage(@PathVariable String avatar) throws IOException {
        File file = new File(UPLOADED_FOLDER, avatar);
        return Files.readAllBytes(file.toPath());
    }
}
