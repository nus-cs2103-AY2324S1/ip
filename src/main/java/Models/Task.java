package Models;

public class Task {
    Boolean isMarked;
    String name;

    public Task(String name, Boolean marked) {
        this.name = name;
        this.isMarked = marked;
    }

    public void markAsDone() {
        this.isMarked = true;
    }

    public void markAsUndone() {
        this.isMarked = false;
    }

    public String getTaskDetails() {
        return "Model.Task," + this.name + "," + this.isMarked;
    }

    public String toString() {
        if (this.isMarked) {
            return "[X] " + this.name;
        }

        return "[ ] " + this.name;
    }
}
