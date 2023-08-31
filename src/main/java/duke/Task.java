package duke;

public class Task{
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String toFileString() {
        String isDone;
        if (this.isDone) {
            isDone = "T";
        } else {
            isDone = "F";
        }
        return " | " + isDone + " | " + this.description;
    }

    @Override
    public String toString() {
        return (
                isDone
                    ? "[" + "X" + "] " + description
                    : "[" + " " + "] " + description
        );
    }
}
