package bob.task;

/**
 * Parent class for 3 different types of Tasks: Todo, Events, and Deadlines
 */
public class Task {

    protected String name;
    protected boolean done;

    protected Task(String name) {
        this.name = name;
        this.done = false;
    }
    
    public String getName() {
        return this.name;
    }

    public void setDoneOrNot(boolean doneOrNot) {
        this.done = doneOrNot;
    }

    /**
     * Parses Task from string to Task object
     * @param description is in the form e.g. "event | 0 | read book | 2pm | 4pm"
     * @return Task object
     * @throws IndexOutOfBoundsException when parsing fails, indicates corrupt file.
     */
    public static Task parse(String description) throws IndexOutOfBoundsException {
        String[] split = description.split(" \\| ", 2);

        String type = split[0];
        String details = split[1];

        switch (Enum.valueOf(TaskType.class, type)) {
        case todo:
            return Todo.parseTodo(details);
        case event:
            return Event.parseEvent(details);
        case deadline:
            return Deadline.parseDeadline(details);
        default:
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Converts object to string, to be overridden
     * @return string representation
     */
    public String toTxt() {
        return "";
    }
}

