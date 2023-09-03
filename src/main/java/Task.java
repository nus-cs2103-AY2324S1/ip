import java.util.*;

public class Task {
    boolean done;
    String name;

    Task(boolean done, String name) {
        this.done = done;
        this.name = name;
    }

    /*
     * Create a Task object with default false
     * done status
     */
    Task(String name) {
        this(false, name);
    }

    public String taskType() {
        return " ";
    }

    public void mark() {
        this.done = true;
        }

    public void unmark() {
        this.done = false;
    }
    @Override
    public String toString() {
        if (done) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

    public String fileToString() {
        String finalOut = "";
        finalOut += this.taskType() + "|";
        finalOut += this.done ? "1" : "0";
        finalOut += "|";
        finalOut += this.name;

        return finalOut;
    }
}

