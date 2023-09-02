public class UnmarkCommand extends Command {

    int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        if (this.index > taskList.size() || this.index < 1) {
            throw new DukeException(String.format("Given index is out of range. Index range should be between" +
                            " 1 and %d.",
                    taskList.size()));
        }
        taskList.unmark(this.index);
        storage.saveList(taskList);
    }
}
