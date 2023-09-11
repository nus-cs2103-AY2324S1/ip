package duke;

/**
 * DoneTask command that contains index of Task we want to mark done
 *
 * @author wj331
 */
public class DoneTask extends Command {
    private int taskDone;
    private String notMarkedProperly = "Task is not marked completed properly";

    /**
     * Constructor for DoneTask
     * @param taskDone the index of the task in the TaskList we want to mark done
     */
    public DoneTask(int taskDone) {
        this.taskDone = taskDone;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        if (taskDone > tasks.size()) {
            throw new InvalidInputException("OOPS!!! Too few tasks");
        }
        tasks.get(this.taskDone).setCompleted();
        assert tasks.get(this.taskDone).getCheckbox().equals("[X] ") : this.notMarkedProperly;
        try {
            storage.saveTasks(tasks);
        } catch (InvalidInputException e) {
            ui.printException(e.getMessage());
        }
        return ui.completedMessage(this.taskDone, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
