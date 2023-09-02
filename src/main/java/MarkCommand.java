/**
 * Command that marks the given task as done.
 */
public class MarkCommand extends Command{
    private final int TASK_NUMBER;
    /**
     * Constructs a command containing the tasknumber in list to be deleted.
     * @param taskNumber The position of task in the list to be deleted.
     */
    public MarkCommand(int taskNumber){
        super();
        this.TASK_NUMBER = taskNumber;
    }

    /**
     * Executes the mark command.
     * @param tasklst list of tasks
     * @param ui ui component of the program
     * @param storage storage componenet of the program
     * @throws DukeException Error if the task number is outside the task list
     */
    public void execute(TaskList tasklst, Ui ui, Storage storage) throws DukeException{
        tasklst.markTask(TASK_NUMBER, ui);
        storage.rewriteFile(tasklst);
    }

    /**
     * Checks if the current task is an exit task.
     * @return false since task is not an exit task
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
