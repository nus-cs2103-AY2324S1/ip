package dan.task;

import dan.task.Task;

public class ToDo extends Task {

    // Methods


    // toString
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveToString() {
        return "toDo," + super.saveToString();
    }

    // Constructor
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String des, int mark) {
        super(des, mark != 0);
    }
}
