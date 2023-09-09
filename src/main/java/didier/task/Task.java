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
            String[] task = fileString.split("\\|");
            switch (task[0]) {
            case "T":
                return new ToDo(task[2], Integer.parseInt(task[1]) != 0);
            case "D":
                return new Deadline(task[2], LocalDate.parse(task[3]), Integer.parseInt(task[1]) != 0);
            case "E":
                return new Event(task[2], LocalDate.parse(task[3]),
                        LocalDate.parse(task[4]), Integer.parseInt(task[1]) != 0);
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
     * @return Whether the task description contains the keyword or not.
     */
    public boolean descriptionHasKeyword(String keyword) {
        return this.description.contains(keyword);
    }
}
