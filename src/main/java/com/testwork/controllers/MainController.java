package com.testwork.controllers;

import com.testwork.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/mainpage")
    public String get_mainpage() {
        return "/mainpage";
    }

    @PostMapping("/mainpage")
    public String post_mainpage(
            @RequestParam(name = "array1", defaultValue = "") String array1,
            @RequestParam(name = "array2", defaultValue = "") String array2,
            @RequestParam(name = "task", defaultValue = "task1") String check,
            Map<String, Object> model) {
        Task task = null;
        if ("task1".equals(check)) {
            task = new Task(check, array1, array2);
        } else if ("task2".equals(check)) {
            task = new Task(check, array1);
        }
        if (task != null) {
            task.task();
            model.put("answ", task.getAnswer());
        }
        return "answer";
    }

    @GetMapping("/")
    public ResponseEntity<String> helloPage() {
        return ResponseEntity.ok("Hello world!!!");
    }

   @GetMapping
   public ResponseEntity<String> controllerForTest() {
        return ResponseEntity.ok("Test");
   }

}