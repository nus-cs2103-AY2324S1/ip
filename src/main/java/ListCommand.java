public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() == 0) {
            ui.displayEmptyList();
        } else {
            ui.displayList(tasks);
        }
    }
}