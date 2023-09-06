package tasks;

/**
 * The tasks of type "To-Do."
 */
public class ToDo extends Task {
    public ToDo(String content) {
        super(content, false);
    }

    /**
     * Constructs a new To-Do task with the specified user input and whether it is marked
     *
     * @param content The description of the to-do task.
     * @param status marked or unmarked
     */
    public ToDo(String content, boolean status) {
        super(content, status);
    }

    /**
     * @return the toDo task as marked
     */
    public ToDo mark() {
        return new ToDo(super.getContent(), true);
    }

    /**
     * @return the toDo task as unmarked
     */
    public ToDo unmark() {
        return new ToDo(super.getContent());
    }

    /**
     * @param listSize size of the TaskList
     * @return a String message displayed when adding a toDo task to a TaskList
     */
    public String addTask(int listSize) {
        return "____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                toString() + "\n" +
                String.format("Now you have %d tasks in the list,\n", listSize) +
                "____________________________________________________________";
    }

    /**
     * ToString method
     * @param listSize size of the TaskList
     * @return a String describing the status of the toDo task
     */
    public String toString() {
        if (!super.isMarked()) {
            return String.format("[T][ ] %s", super.getContent());
        } else {
            return String.format("[T][X] %s", super.getContent());
        }
    }
}
