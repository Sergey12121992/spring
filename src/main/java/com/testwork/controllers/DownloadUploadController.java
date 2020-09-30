package com.testwork.controllers;

import com.testwork.Task;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

@Controller
public class DownloadUploadController {

    @PostMapping(value = "/download")
    public ResponseEntity<Object> downloadFile(@RequestParam(name = "darray1", defaultValue = "") String array1,
                                               @RequestParam(name = "darray2", defaultValue = "") String array2,
                                               @RequestParam(name = "dtask", defaultValue = "task1") String check) {
        String body = check + "\n" + array1 + "\n" + array2;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", "parameters"));
        ResponseEntity<Object> responseEntity = ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(body.length())
                .contentType(MediaType.parseMediaType("text/plain"))
                .body(body.getBytes());
        return responseEntity;
    }

    @PostMapping(value = "/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             Map<String, Object> model) {
        if (!file.isEmpty()) {
            try {
                Scanner sc = new Scanner(file.getInputStream());
                ArrayList<String> parametrs = new ArrayList<String>();
                while (sc.hasNext()) {
                    parametrs.add(sc.nextLine());
                }
                sc.close();
                Task task = null;
                if ("task1".equals(parametrs.get(0))) {
                    if (parametrs.size() > 2)
                        task = new Task(parametrs.get(0), parametrs.get(1), parametrs.get(2));
                } else if ("task2".equals(parametrs.get(0))) {
                    if (parametrs.size() > 1)
                        task = new Task(parametrs.get(0), parametrs.get(1));
                }
                if (task != null) {
                    task.task();
                    model.put("answ", task.getAnswer());
                } else {
                    model.put("answ", "Введены не корректные данные");
                }
            } catch (IOException e) {
               // to need to add a log
                e.printStackTrace();
            }
        }
        return "answer";
    }

}
