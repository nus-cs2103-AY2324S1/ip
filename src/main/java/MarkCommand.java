import duke.exceptions.TaskIndexOutOfBoundsException;

public class MarkCommand extends Command {
    protected boolean done;
    protected int taskIndex;
    public MarkCommand(boolean done, int taskIndex) {
        super();
        this.done = done;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        taskList.setTaskDone(taskIndex, done);
        System.out.println(taskList);
    }
}
