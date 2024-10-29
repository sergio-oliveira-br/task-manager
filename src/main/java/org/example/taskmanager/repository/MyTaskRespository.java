package org.example.taskmanager.repository;

import org.example.taskmanager.model.MyTask;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MyTaskRespository extends JpaRepository<MyTask, Long> {

}
