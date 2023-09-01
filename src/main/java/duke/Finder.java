package duke;

public class Finder implements Executable {
    private String regex;
    private TaskList tasks;

    public Finder(TaskList tasks, String regex) {
        this.regex = regex;
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        return tasks.filteredToString(regex);
    }
}
