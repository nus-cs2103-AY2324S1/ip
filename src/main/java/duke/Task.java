package duke;

public abstract class Task {

    protected String description;
    protected boolean isDone = false;

    public static Task create(Commands command, String rawLine) throws DukeException {
        switch (command) {
        case todo:
            return ToDo.create(rawLine);
        case deadline:
            return Deadline.create(rawLine);
        case event:
            return Event.create(rawLine);
        default:
            throw new RuntimeException("Command not recognized"); //literally should not run
        }
    }

    public Task(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public abstract String fileString();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
