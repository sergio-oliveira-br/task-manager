package org.example.taskmanager.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class MyTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID TaskId;
    private String taskTitle;
    private String taskDescription;
    private String taskStatus;
    private LocalDateTime taskCreatedAt;

    //Vincula uma tarefa a um usuário, e permite que um usuário tenha várias tarefas.
    @ManyToOne
    private MyUser myUser;

    public MyUser getUser() {
        return myUser;
    }

    public void setUser(MyUser myUser) {
        this.myUser = myUser;
    }


    public UUID getTaskId() {
        return TaskId;
    }

    public void setTaskId(UUID taskId) {
        TaskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public LocalDateTime getTaskCreatedAt() {
        return taskCreatedAt;
    }

    public void setTaskCreatedAt(LocalDateTime taskCreatedAt) {
        this.taskCreatedAt = taskCreatedAt;
    }
}
