public class ListCommand extends Command{
    private boolean isExit = false;
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String res = "Here are the tasks in your list:";

        for (int i = 1; i <= tasks.size(); i++) {
            res += "\n" + i + ". " + tasks.get(i - 1);
        }

        ui.printMessage(res);
    }
    @Override
    public boolean isExit() {
        return this.isExit;
    }
}
