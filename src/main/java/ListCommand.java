public class ListCommand extends Command{
    public void execute(TaskList tasks, Ui ui) {
        ui.showMessage("Here are the tasks in your list:");
        int size = tasks.getSize();
        for (int i = 1; i <= size; i++) {
            ui.showMessage(i + "." + tasks.getTask(i).toString());
        }
    }

    public boolean isExit(){
        return false;
    }
}
