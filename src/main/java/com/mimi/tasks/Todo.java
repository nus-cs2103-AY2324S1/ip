package com.mimi.tasks;

public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String eventCode() {
        return "T";
    }


    @Override
    public String toString() {
        return String.format("[%s][%s] %s",
                this.eventCode(),
                this.getStatusIcon(),
                this.taskName());
    }

}
