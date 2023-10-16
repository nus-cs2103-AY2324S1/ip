package cringebot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to create a Todo.
 */
public class Todo extends Task {

    private LocalDate date;

    /**
     * Constructor for the Todo class.
     *
     * @param name name of the todo.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructor for the Todo class.
     *
     * @param name name of the todo.
     * @param date date of the todo.
     */
    public Todo(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    /**
     * Return string representation of the todo task.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        String checkBox;
        char taskType1 = 'T';
        String taskType = String.format("[%c]", taskType1);

        if (super.isMarked()) {
            checkBox = "[X]";
        } else {
            checkBox = "[ ]";
        }
        return this.date == null
                ? String.format("%s%s %s", taskType, checkBox, super.getName())
                : String.format(
                        "%s%s %s created on: %s",
                        taskType,
                        checkBox,
                        super.getName(),
                        date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                );
    }
}
