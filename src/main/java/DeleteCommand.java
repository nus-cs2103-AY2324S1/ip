public class DeleteCommand extends Command {
    private int deleteTaskNumber;

    public DeleteCommand(int deleteTaskNumber) {
        this.deleteTaskNumber = deleteTaskNumber;
    }

    @Override
    public void executeCommand(UI ui, Actions actionList) throws DukeException {
        Task deletedTask = actionList.delete(deleteTaskNumber);
        if (deletedTask != null) {
            ui.lineSandwich(" Noted. I've removed this task:\n  " + deletedTask + "\n Now you have " + actionList.size() + " tasks in the list.");
        } else {
            throw new DukeException(" Task does not exist, please review input");
        }
    }
}
