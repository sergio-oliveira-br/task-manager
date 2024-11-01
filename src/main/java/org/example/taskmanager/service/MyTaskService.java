package org.example.taskmanager.service;

import org.example.taskmanager.model.MyTask;
import org.example.taskmanager.repository.MyTaskRespository;
import org.springframework.stereotype.Service;

@Service
public class MyTaskService {

    private final MyTaskRespository myTaskRespository;

    public MyTaskService(MyTaskRespository myTaskRespository) {
        this.myTaskRespository = myTaskRespository;
    }

}
