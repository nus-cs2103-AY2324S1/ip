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

        Task taskToUnmark = tasks.getAllTasks().get(this.unmarkIndex);
        tasks.markAsDone(taskToUnmark);

        // print message when unmarking
        ui.printSuccessfulUnmarkResponse(taskToUnmark);
    }
}
