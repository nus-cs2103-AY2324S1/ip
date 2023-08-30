public class MarkCommand extends Command {
    String commandType;
    boolean isExit;
    int markIndex;

    MarkCommand(int markIndex) {
        this.commandType = "Mark";
        this.isExit = false;
        this.markIndex = markIndex;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {

        try {
            Task taskToMark = tasks.getTask(this.markIndex);
            tasks.markAsDone(taskToMark);

            // print message when marking
            ui.printSuccessfulMarkResponse(taskToMark);
        } catch (TaskNotFoundException e) {
            ui.printTaskNotFoundExceptionResponse();
            return;
        }

    }
}
