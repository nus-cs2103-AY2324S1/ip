package Frenchie;

/**
 * Represents a ToDo that inherits from the Task superclass.
 * <p>
 * The ToDo class is indicated with a [T] in its toString() method.
 * <p>
 */
public class ToDo extends Task {

    /**
     *  Constructs a new ToDo object, with a default false value for isCompleted
     *  as tasks inputted into the task list are incomplete.
     *  Takes in a String which is the name of the task.
     */
    ToDo(String name) {
        this.taskName = name;
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
