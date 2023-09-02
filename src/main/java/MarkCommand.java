public class MarkCommand extends Command {
    boolean isExit = false;
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        taskList.markAsDone(index);
        storage.saveAllTasks(taskList);
        Ui.reply("Well done! I've marked this task as done:\n" + taskList.get(index).toString());
    }
}
