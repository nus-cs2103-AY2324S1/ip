package duke;

import duke.Task;

public class Todo extends Task{
    public Todo(String description) {
        //no extra information for todolist
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + super.description;
    }

    @Override
    public String stringInFile() {
        int status = super.isDone ? 1 : 0;
        return "T | " + status + " | " + this.description;
    }
}
