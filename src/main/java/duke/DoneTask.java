package duke;

import duke.Command;

public class DoneTask extends Command {
    private int taskDone;

    public DoneTask(int taskDone) {
        this.taskDone = taskDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.get(this.taskDone).setCompleted();
        ui.completedMessage(this.taskDone, tasks);
        try {
            storage.saveTasks(tasks);
        } catch (InvalidInputException e) {
            ui.printException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
