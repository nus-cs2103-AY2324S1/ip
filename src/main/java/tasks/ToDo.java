package tasks;

import java.time.LocalDate;

/**
 * The tasks of type "To-Do."
 */
public class ToDo extends Task implements Comparable<Task> {
    private static final LocalDate localDate = LocalDate.now();

    private String name;

    public ToDo(String content) {
        super(content, false);
        this.name = content.split(" ", 2)[1];
    }

    /**
     * Constructs a new To-Do task with the specified user input and whether it is marked
     *
     * @param content The description of the to-do task.
     * @param status  marked or unmarked
     */
    public ToDo(String content, boolean status) {
        super(content, status);
        this.name = content.split(" ", 2)[1];
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
        return "____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + toString() + "\n"
                + String.format("Now you have %d tasks in the list,\n", listSize)
                + "____________________________________________________________";
    }

    public LocalDate getDateTime() {
        return localDate;
    }

    /**
     * ToString method
     *
     * @return a String describing the status of the toDo task
     */
    public String toString() {
        if (!super.isMarked()) {
            return String.format("[T][ ] %s", name);
        } else {
            return String.format("[T][X] %s", name);
        }
    }
}
