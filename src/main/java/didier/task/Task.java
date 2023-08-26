package didier.task;

import didier.exception.FileCorruptedException;

import java.time.LocalDate;

/**
 * The didier.task.Task class encapsulates a real-life didier.task. A basic didier.task has a description and keeps track of
 * whether it is completed or not. It is declared abstract as as didier.task must take one of three forms:
 * didier.task.ToDo, didier.task.Deadline or didier.task.Event.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
       return String.format("[%s] %s", this.isDone ? "X" : " ", this.description);
    }

    /**
     * Composes the didier.task and returns it in a String format suitable to be written into a file.
     * This is used to save the didier.task to a file.
     *
     * @return The composed didier.task as a String.
     */
    public String composeToFileString() {
        return String.format("%s|%s", this.isDone ? "1" : "0", this.description);
    }

    /**
     * Parses the given string obtained from a file into a didier.task.Task.
     *
     * @param fileString The String to be parsed.
     * @return The corresponding didier.task.
     */
    public static Task parseFileString(String fileString) throws FileCorruptedException {
        String[] task = fileString.split("\\|");
        if (task[0].equals("T")) {
            return new ToDo(task[2], Integer.parseInt(task[1]) != 0);
        } else if (task[0].equals("D")) {
            return new Deadline(task[2], LocalDate.parse(task[3]), Integer.parseInt(task[1]) != 0);
        } else if (task[0].equals("E")){
            return new Event(task[2], LocalDate.parse(task[3]),
                    LocalDate.parse(task[4]), Integer.parseInt(task[1]) != 0);
        }
        throw new FileCorruptedException();
    }
}
