public class UnmarkCommand extends Command {
    String commandType;
    boolean isExit;
    int unmarkIndex;

    UnmarkCommand(int unmarkIndex) {
        this.commandType = "Unmark";
        this.isExit = false;
        this.unmarkIndex = unmarkIndex;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {

        try {
            Task taskToUnmark = tasks.getTask(this.unmarkIndex);
            tasks.markAsNotDone(taskToUnmark);

            // print message when unmarking
            ui.printSuccessfulUnmarkResponse(taskToUnmark);
        } catch (TaskNotFoundException e) {
            ui.printTaskNotFoundExceptionResponse();
            return;
        }
    }
}
