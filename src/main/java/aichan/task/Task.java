package aichan.task;

import aichan.AiChanException;

/**
 * Represents a task.
 */
public class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Constructs a task object.
     * Initializes description and set isDone to false.
     *
     * @param taskName Description of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Converts a line in the file to its task.
     *
     * @param str String of one line in a file.
     * @return The corresponding task.
     * @throws AiChanException If the line has incorrect format.
     */
    public static Task stringToTask(String str) throws AiChanException {
        char type = str.charAt(0);
        boolean isMark = (str.charAt(4) == '1');
        if (str.length() < 8) {
            throw new AiChanException("incorrect format in the file");
        }
        String description = str.substring(8);
        if (type == 'T') {
            Task t = new ToDo(description);
            if (isMark) t.mark();
            return t;
        } else if (type == 'D') {
            String[] arr = description.split(" \\| ");
            Task t = new Deadline(arr);
            if (isMark) t.mark();
            return t;
        } else if (type == 'E') {
            String[] arr = description.split(" \\| | - ");
            Task t = new Event(arr);
            if (isMark) t.mark();
            return t;
        } else {
            return null;
        }
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks this task as have not done yet.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string indicates whether the task is done followed by its description.
     */
    @Override
    public String toString() {
        String status = this.isDone? "[X]" : "[ ]";
        return status + " " + this.taskName;
    }

    /**
     * Returns the line of the task to be saved in the file.
     *
     * @return A string indicates whether the task is done followed by its description.
     */
    public String toFileLine() {
        int zeroOrOne = this.isDone? 1: 0;
        return zeroOrOne + " | " + this.taskName;
    }
}
