package duke;

public class ToDoAdder implements Executable {
    private String name;
    private TaskList tasks;

    public ToDoAdder(TaskList tasks, String name) {
        this.name = name;
        this.tasks = tasks;
    }
    @Override
    public String execute() {
        Task task = new ToDo(name);
        tasks.add(task);
        return String.format("Got it! I've added this task:\n" +
                "%s\n" +
                "Now you have %d tasks in the list.",
                task.toString(),
                tasks.size());
    }
}
