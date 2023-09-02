package duke.task;

import duke.processors.FileHandler;
public class Task {
    protected String Description;
    protected boolean isDone;

    public Task(String Description) {
        this.Description = Description;
        this.isDone = false;
    }

    private String GetStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "]";
    }

    public void MarkAsDone(FileHandler fileHandler) {
        String oldLine = this.toString();
        this.isDone = true;
        String newLine = this.toString();
        fileHandler.updateFile(oldLine, newLine);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " + this);
    }

    public void MarkAsUnDone(FileHandler fileHandler) {
        String oldLine = this.toString();
        this.isDone = false;
        String newLine = this.toString();
        fileHandler.updateFile(oldLine, newLine);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("    " + this);
    }

    public String toString() {

        return GetStatusIcon() + " " + Description;
    }
}
