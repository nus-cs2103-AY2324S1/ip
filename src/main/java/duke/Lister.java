package duke;

public class Lister implements Executable {
    private TaskList tasks;

    public Lister(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        return tasks.toString();
    }
}
