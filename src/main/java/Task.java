public abstract class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    public abstract String writeTaskToFile();

    public static Task readTaskFromFile(String input) {
        String[] args = input.split(" \\| ");
        Task task;
        switch (args[0]) {
            case "T":
                task = Todo.readTaskFromFile(args);
                break;
            case "D":
                task = Deadline.readTaskFromFile(args);
                break;
            case "E":
                task = Event.readTaskFromFile(args);
                break;
            default:
                task = null;
                break;
        }
        return task;
    }

    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return "[" + status + "] " + this.name;
    }
}
