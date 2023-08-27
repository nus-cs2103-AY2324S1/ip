public class UnmarkDoneCommand extends Command {
    private int index;

    public UnmarkDoneCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.unmarkTask(index);
        ui.printMessage("Beep Boop NICE! I've unmarked this task as done:\n\t\t" +
                tasks.get(index));
        storage.save(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
