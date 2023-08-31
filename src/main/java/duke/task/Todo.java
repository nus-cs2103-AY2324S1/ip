package duke.task;

/**
 * Represents a Todo task.
 */

public class Todo extends Task {
    public Todo(String name){
        super(name);
    }

    // Override toString method
    @Override public String toString(){
        return "[T] " + super.toString();
    }
}
