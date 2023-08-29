public class UnmarkCommand extends Command {
    protected int index;
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_SUCCESS = " OK, I've marked this task as not done yet:\n";
    protected boolean isExit;

    public UnmarkCommand(int index) {
        this.index = index;
        this.isExit = false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.index >= 0 && this.index < tasks.getSize()) {
            Task task = tasks.getTask(this.index);
            tasks.unmarkTask(task);
            storage.writeToFile(tasks.getList());
            ui.showMessage(MESSAGE_SUCCESS + "     " + task);
        } else { // user input is an integer bigger than size of task list
            String message = tasks.getSize() > 0
                    ? "No such task! Please enter a task ID between 1 and " + tasks.getSize()
                    : "You have no tasks! Please add some tasks first";
            throw new DukeException(message);
        }
    }
}
