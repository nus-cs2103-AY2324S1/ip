package duke;

/** Task which contains only a description */
public class Todo extends Task {

    /**
     * Class constructor specifying the description of the _todo task.
     * @param description the string description of the _todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the information associated with the _todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Todo)) {
            return false;
        }
        Todo task = (Todo) obj;
        int indexOfTaskDescription = 7;
        if (this.toString().substring(indexOfTaskDescription).equals(
                task.toString().substring(indexOfTaskDescription))) {
            return true;
        }
        return false;
    }

    /**
     * Returns the information associated with the _todo to be stored on local hard disk.
     * @return the string representation of the _todo saved onto the local hard disk.
     */
    public String saveTask() {
        return "T|" + (this.isDone() ? "X|" : " |") + this.getDescription();
    }
}
