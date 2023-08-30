package duke;

public class AddTask extends Command {
    private Task task;

    public AddTask(Task task) {
        this.task = task;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.addedMessage(this.task, tasks);
        tasks.add(this.task);
        try {
            storage.saveTasks(tasks);
        } catch (InvalidInputException e) {
            ui.printException(e.getMessage());
        }
    }
    @Override
    public String toString() {
        return this.task.toString();
    }
}
