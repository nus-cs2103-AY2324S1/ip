package duke;

public class Task {
    private boolean isDone;
    private String name;

    Task(String name) {
        this.name = name.trim();
        this.isDone = false;
    }

    Task(String name, boolean isDone) {
        this.name = name.trim();
        this.isDone = isDone;
    }

    public void setDone(boolean isDone) throws LukeException {
        if (this.isDone == isDone) {
            throw new LukeException("Task is already marked as done");
        }
        this.isDone = isDone;
    }

    public String toSaveStr() {
        return (isDone ? "Done" : "Not Done")
                + " | " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Task) {
            Task taskObj = (Task) o;

            return this.name.equals(taskObj.name)
                    && this.isDone == taskObj.isDone;
        }

        return false;
    }

    @Override
    public String toString() {
        String doneIndicator = isDone ? "x" : " ";
        return "[" + doneIndicator + "] " + name;
    }
}
