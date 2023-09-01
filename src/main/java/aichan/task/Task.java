package aichan.task;

import aichan.AiChanException;

public class Task {
    private String taskName;
    private boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

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

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public String toString() {
        String status = this.done? "[X]" : "[ ]";
        return status + " " + this.taskName;
    }

    public String toFileLine() {
        int zeroOrOne = this.done? 1: 0;
        return zeroOrOne + " | " + this.taskName;
    }

}
