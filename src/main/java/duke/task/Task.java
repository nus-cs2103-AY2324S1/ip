package duke.task;

import duke.exceptions.DukeException;

/**
 * Represents a Task, a supertype of Event, Deadline and Todo
 * <code>isDone</code> shows whether the task is completed
 */
public abstract class Task {
    /**
     * name of task
     */
    private final String description;
    /**
     * whether the task is done
     */
    private boolean isDone;
    /**
     * constructor, should not be used because this is an abstract class. For initialising description
     * and isDone only
     * @param done whether the task is done
     * @param description name of task
     */
    public Task(boolean done, String description) {
        this.description = description;
        this.isDone = done;
    }

    /**
     * getter for boolean isDone
     * @return boolean isDone
     */
    public boolean getisDone() {
        return this.isDone;
    }

    /**
     * getter for descriptions
     * @return
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * marks the task as done, if it is undone
     * @throws DukeException if task is already done
     */
    public String mark() throws DukeException {
        if (this.isDone) {
            throw new DukeException("Task already done");
        }
        this.isDone = true;
        return ("Nice! I've marked this task as done:\n" + toString());
    }

    /**
     * unmarks the task as undone, if it is done
     * @throws DukeException if task is still undone
     */
    public String unmark() throws DukeException {
        if (!this.isDone) {
            throw new DukeException("Task still undone");
        }
        this.isDone = false;
        return "OK, I've marked this task as not done yet:\n" + toString();
    }
    /**
     * get String representation of whether the task is done
     * @return <code>"X"</code> for done, " " for undone
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    /**
     * String representation of task, including whether the task is done
     * @return string representation of task
     */
    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    /**
     * converts hard drive's String format of a task into an actual task item
     * @param text String from hard drive
     * @return task item
     * @throws DukeException if string cannot be recognised and cannot be parsed into a task
     */
    public static Task parse(String text) throws DukeException {
        String[] parts = text.split("\\|");
        String first = parts[0];
        Task task;
        boolean done = parts[1].equals("1"); // 1 = done, 0 = undone
        String description = parts[2];
        switch (first) {
        case "T":
            task = new Todo(done, "todo " + description);
            break;

        case "E":
            String from = parts[3];
            String to = parts[4];
            task = new Event(done, "event " + description + " /from " + from + " /to " + to);
            break;

        case "D":
            String by = parts[3];
            task = new Deadline(done, "deadline " + description + " /by " + by);
            break;

        default:
            throw new DukeException("Unable to parse from hard drive");
        }
        return task;
    }
}
