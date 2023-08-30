package duke;

/**
 * DoneTask command that contains index of Task we want to mark done
 *
 * @author wj331
 */
public class DoneTask extends Command {
    private int taskDone;

    /**
     * Constructor for DoneTask
     * @param taskDone the index of the task in the TaskList we want to mark done
     */
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
