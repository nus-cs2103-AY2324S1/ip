public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String writeTaskToFile() {
        return String.format("%s | %s | %s", "T",
                this.getIsDone() ? 1 : 0,
                this.getDescription());
    }

    public static Todo readTaskFromFile(String[] args) {
        Todo newTodoTask = new Todo(args[2]);
        if (args[1].equals("1")) {
            newTodoTask.markAsDone();
        }
        return newTodoTask;
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
