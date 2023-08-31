public class MarkTaskCommand implements Command {
    private int id;
    public MarkTaskCommand(int id) {
        this.id = id;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markTask(id);
            ui.printTaskMarkedMessage(tasks.getTask(id));
        } catch (RuntimeException e) {
            throw new DukeException("Index out of bounds. Expected: unmark {id}");
        }
    }
}
