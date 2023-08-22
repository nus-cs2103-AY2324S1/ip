package command;

public abstract class TaskCommand extends Command {
    private final String title;

    public TaskCommand(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
