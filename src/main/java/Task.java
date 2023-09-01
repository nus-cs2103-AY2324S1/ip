public class Task {
    Boolean isMarked;
    String name;

    public Task(String name, Boolean marked) {
        this.name = name;
        this.isMarked = marked;
    }
    public Boolean isDone() {
        return this.isMarked;
    }

    public void markAsDone() {
        this.isMarked = true;
    }

    public void markAsUndone() {
        this.isMarked = false;
    }

    public String getName() { return this.name; }

    public String getType() { return "Task"; }

    public Boolean getIsMarked() { return this.isMarked; }

    public String getTaskDetails() {
        return "Task," + this.name + "," + this.isMarked;
    }

    public String toString() {
        if (this.isMarked) {
            return "[X] " + this.name;
        }

        return "[ ] " + this.name;
    }
}
