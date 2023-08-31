public class UnmarkTaskCommand implements Command {
    private int id;
    public UnmarkTaskCommand(int id) {
        this.id = id;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.unmarkTask(id);
            ui.printTaskUnmarkedMessage(tasks.getTask(id));
        } catch (RuntimeException e) {
            throw new DukeException("Index out of bounds. Expected: unmark {id}");
        }
    }
}
