package Jarvis;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public void stringToDate() {}

    public String toString() { // generates the string of marking and task
        String marking = "";
        if (this.isDone) {
            marking = "[X]";
        } else {
            marking = "[ ]";
        }
        return marking + " " + this.description;
    }
}
