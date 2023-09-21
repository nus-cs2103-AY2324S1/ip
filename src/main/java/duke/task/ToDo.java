package duke.task;

/**
 * Represents a basic task, which is a ToDo
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo
     * @param name Name of the ToDo
     * @param isDone Status of whether the ToDo is done
     */
    public ToDo(String name, String isDone) {
        super(name, isDone);
    }

    /**
     * Returns the string representation of the ToDo to be saved
     * @return String containing the status and name of the todo
     */
    @Override
    public String toDataString() {
        return super.toDataString();
    }

    /**
     * Returns the string representation of the ToDo to be printed
     * @return String containing the status and name of the todo
     */
    @Override
    public String toString() {
        String str = "[T] " + super.getStatus() + " " + super.name;
        return str;
    }



}
