package Models;

public class Task {
    protected Boolean isMarked;
    protected String name;

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

    public boolean containsWord(String word) {
        return this.name.toLowerCase().contains(word.toLowerCase());
    }
    public String toString() {
        if (this.isMarked) {
            return "[X] " + this.name;
        }

        return "[ ] " + this.name;
    }
}
