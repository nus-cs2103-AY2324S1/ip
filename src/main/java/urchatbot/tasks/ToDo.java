package urchatbot.tasks;

public class ToDo extends Task {
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }
    @Override
    public String toFileString() {
        if (this.isDone) {
            return "T | 1 | " + description;
        } else {
            return "T | 0 | " + description;
        }
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}