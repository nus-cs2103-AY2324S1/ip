public class MarkCommand extends Command {
    private int index;

    MarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("OOPS! The index to mark is invalid!");
        }
        taskList.markTask(index);
        ui.printMessage("Nice! I've marked this task as done:\n" + taskList.get(index));
        storage.saveList(taskList.getAllTasks());
    }
}
