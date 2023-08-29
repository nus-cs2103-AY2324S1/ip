public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String str = "Here are the tasks in your list:\n\t ";
        int count = 0;
        for (Task task : taskList.arrTask) {
            str += count + 1 + "." + task.toString() + "\n\t ";
            count++;
        }
        ui.sendMessage(str.substring(0, str.length() - 3));
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
