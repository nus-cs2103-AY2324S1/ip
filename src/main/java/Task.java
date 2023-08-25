/**
 * The Task class encapsulates a real-life task. A basic task has a description and keeps track of
 * whether it is completed or not. It is declared abstract as as task must take one of three forms:
 * ToDo, Deadline or Event.
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
     * Composes the task and returns it in a String format suitable to be written into a file.
     * This is used to save the task to a file.
     *
     * @return The composed task as a String.
     */
    public String composeToFileString() {
        return String.format("%s|%s", this.isDone ? "1" : "0", this.description);
    }

    /**
     * Parses the given string obtained from a file into a Task.
     *
     * @param fileString The String to be parsed.
     * @return The corresponding task.
     */
    public static Task parseFileString(String fileString) {
        String[] task = fileString.split("\\|");
        if (task[0].equals("T")) {
            return new ToDo(task[2], Integer.parseInt(task[1]) != 0);
        } else if (task[0].equals("D")) {
            return new Deadline(task[2], task[3], Integer.parseInt(task[1]) != 0);
        } else if (task[0].equals("E")){
            return new Event(task[2], task[3], task[4], Integer.parseInt(task[1]) != 0);
        }
        return null;
    }
}
