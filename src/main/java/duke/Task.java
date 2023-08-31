package duke;

public class Task {
    Boolean done = false;
    String task;
    static String line = "______________________________________________________________________________________\n";

    public Task(String task) {
        this.task = task;
    }

    public Boolean isMarked() {
        return this.done;
    }

    public void mark() {
        this.done = true;
    }
    public void unMark() {
        this.done = false;
    }

    public String line() {
        return line;
    }

    public String getTask() {
        return "." + this.toString();
    }
    public String toString() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        return checkbox + task;
    }
}
