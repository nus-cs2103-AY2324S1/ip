package ruiz.task;

public class ToDos extends Task {
    /**
     * A constructor for the public ToDos class.
     *
     * @param description contains the description of the ToDo
     */
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String saveTaskString() {
        return "T" + super.saveTaskString();
    }

    /**
     * This method converts the value of an Event into a String type.
     *
     * @return the String representation of the ToDo with its type and completion status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
