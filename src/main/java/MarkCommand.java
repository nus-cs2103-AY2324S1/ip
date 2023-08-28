public class MarkCommand extends Command {
    public MarkCommand(String fullCommand) {
        super(fullCommand);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int taskId = Integer.parseInt(this.getCommand().substring(5)) - 1;
            taskList.mark(taskId);
            ui.showTaskMarked(taskList.get(taskId));
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please ensure task exists.");
        }
    }
}
