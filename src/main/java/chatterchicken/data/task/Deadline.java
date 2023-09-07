package chatterchicken.data.task;

import java.time.LocalDate;

/**
 * The Deadline class represents a deadline task in the task management application.
 * It extends the parent Task class and includes information about the task's due date.
 * Deadline tasks are used to track tasks with specific deadlines.
 */
public class Deadline extends Task {
    private final LocalDate dueDate; //the due date of the deadline task

    /**
     * Constructs a Deadline object with the provided task description, task name, and due date.
     *
     * @param taskDescription The description of the deadline task.
     * @param name The name or title of the task.
     * @param dueDate The due date of the deadline task.
     */
    public Deadline(String taskDescription, String name, LocalDate dueDate) {
        super(taskDescription, name);
        this.dueDate = dueDate;
    }

    /**
     * Generates the formatted representation of the deadline task for display purposes.
     * The format includes the task status, task type, description, and due date.
     *
     * @return The formatted representation of the deadline task.
     */
    @Override
    public String getTaskForPrinting() {
        return String.format("[%s][D] %s (by: %s)", super.checkDone(), super.getName(), super.formatDate(dueDate));
    }

    /**
     * Gets the input representation of the deadline task.
     * The input format is suitable for creating and parsing deadline tasks.
     *
     * @return The input representation of the deadline task.
     */
    public String getInput() {
        return "deadline " + super.getTaskDescription();
    }
}