package com.mimi.tasks;
import com.mimi.main.InvalidTask;

import java.time.format.DateTimeFormatter;

public class Task {

    private String taskName;
    private boolean isDone = false;

    Task(String taskName) {
        this.taskName = taskName;
    }



    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : "";
    }


    public boolean isDone() {
        return this.isDone;
    }

    public String eventCode() {
        return "";
    }

    //returns the event string without the prefix word
    public String taskName() {
        return this.taskName;
    }

    public String taskStartTime() {
        return "";
    }

    public String taskEndTime() {
        return "";
    }

    public String writeFormat() {
        return this.taskName();
    }
}
