package duke.tasks;

import duke.UI;

public class Task {
    protected String taskName;
    protected boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public Task(String taskName, boolean done) {
        this.taskName = taskName;
        this.done = done;
    }

    //default constructor
    public Task() {
        this.taskName = "Untitled task";
        this.done = false;
    }

    public void mark() {
        UI.printLine();
        this.done = true;
        System.out.println("SIUUU! I've marked this task as done. We will make Saudi League number 1.\n [X] " + taskName);
        UI.printLine();
    }

    public void unmark() {
        UI.printLine();
        this.done = false;
        System.out.println("OK, I've marked this task as not done.\n [ ] " + taskName);
        UI.printLine();
    }

    @Override
    public String toString() {
        String status = this.done ? "[X]" : "[ ]";
        return status + " " + taskName;
    }

}
