public class Todo extends Task {
    public Todo (String name) {
        super(name);
    }

    public String writeTaskToFile() {
        return String.format("%s | %s | %s", "T",
                this.isDone() ? 1 : 0,
                this.getName());
    }

    public static Todo readTaskFromFile(String[] args) {
        Todo newTodoTask = new Todo(args[2]);
        if (args[1].equals("1")) {
            newTodoTask.markDone();
        }
        return newTodoTask;
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
