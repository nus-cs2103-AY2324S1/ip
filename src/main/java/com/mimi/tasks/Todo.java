package com.mimi.tasks;

public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Returns the string representation of the task type.
     * @return a string that represents what kind of task this is.
     */
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
