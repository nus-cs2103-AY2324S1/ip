package Task;

import DukeException.*;

public abstract class Task {
    final private String name;
    private boolean completed = false;

    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    protected String insertColonInTime(String time) {
        return time.replaceFirst(" ", ": ");
    }
    public Task(String name) throws EmptyTaskDescException {
        if(name.isBlank()) {
            throw new EmptyTaskDescException("Name cannot be blank.");
        }
        this.name = name;
    }

    public static Task Of(String task, TaskType taskType) throws EmptyTaskDescException, InvalidTaskFormatException {
        switch (taskType) {
            case TODO:
                return new Todo(task);
            case DEADLINE:
                return new Deadline(task);
            case EVENT:
                return new Event(task);
            default:
                return null;
        }
    }
    public void SetCompleted(){
        completed = true;
    }
    public void SetUncompleted(){
        completed = false;
    }
    public String toString() {

        return completed ? "[X] " + name : "[ ] " + name;

    }

    public String toSaveFormat(){
        return name + "|" + completed;
    }
}
