package org.example.taskmanager.controller;

import org.example.taskmanager.model.MyTask;
import org.example.taskmanager.service.MyTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/my-task")
public class MyTaskController {

    private final MyTaskService myTaskService;

    public MyTaskController(MyTaskService myTaskService) {
        this.myTaskService = myTaskService;
    }

}
