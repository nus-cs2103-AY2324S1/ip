package src.alpha;
public abstract class Command {

    protected TaskList taskList;
    protected FileHandler fileHandler;

    protected UI ui;

    protected boolean isExit;

    public Command(TaskList taskList, FileHandler fh, UI ui) {
        this.taskList = taskList;
        this.fileHandler = fh;
        this.ui = ui;
    }

    public abstract void execute();

    public boolean isExit() {
        return this.isExit;
    }
}
