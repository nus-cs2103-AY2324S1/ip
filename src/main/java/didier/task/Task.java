package didier.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import didier.exception.FileCorruptedException;

/**
 * The Task class encapsulates a real-life task. A basic task has a description and keeps track of
 * whether it is completed or not. It is declared abstract as as task must take one of three concrete forms:
 * ToDo, Deadline or Event.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for the Task object. Task is marked as undone by default at the start.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for the Task object that allows user to specify if the task is done or not initially.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is done or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks this task object to be done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task object to be undone.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.description);
    }

    /**
     * Composes this task object and returns it in a String format suitable to be written into a file.
     * This is used to save the task in the local memory.
     *
     * @return The task composed as a String.
     */
    public String composeToFileString() {
        return String.format("%s|%s", this.isDone ? "1" : "0", this.description);
    }

    /**
     * Parses the given string obtained from a file into a Task object.
     *
     * @param fileString The String to be parsed.
     * @return The corresponding Task object.
     * @throws FileCorruptedException If the file is corrupted and so fileString cannot be parsed.
     */
    public static Task parseFileString(String fileString) throws FileCorruptedException {
        try {
            String[] taskDetails = fileString.split("\\|");
            String taskType = taskDetails[0];
            boolean taskIsDone = Integer.parseInt(taskDetails[1]) != 0;
            String taskDescription = taskDetails[2];
            switch (taskType) {
            case "T":
                return new ToDo(taskDescription, taskIsDone);
            case "D":
                LocalDate by = LocalDate.parse(taskDetails[3]);
                return new Deadline(taskDescription, by, taskIsDone);
            case "E":
                LocalDate from = LocalDate.parse(taskDetails[3]);
                LocalDate to = LocalDate.parse(taskDetails[4]);
                return new Event(taskDescription, from, to, taskIsDone);
            default:
                throw new FileCorruptedException();
            }
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new FileCorruptedException();
        }
    }

    /**
     * Returns whether the description of this task contains the keyword or not.
     *
     * @param keyword The keyword to search for in the task description.
     * @return True if the task description contains the keyword else false.
     */
    public boolean descriptionHasKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task task = (Task) obj;
            return this.description.equals(task.description);
        }
        return false;
    }
}
