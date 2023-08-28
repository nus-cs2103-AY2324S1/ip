public class DeleteCommand extends Command {

    public DeleteCommand(String command) {
        super(command);
    }
    
    public void execute(TaskManager taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int taskId = Integer.parseInt(this.getCommand().substring(7)) - 1;
            ui.showTaskDeleted(taskList.get(taskId), taskList.size());
            taskList.delete(taskId);
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please ensure task exists.");
        }
    }
}
