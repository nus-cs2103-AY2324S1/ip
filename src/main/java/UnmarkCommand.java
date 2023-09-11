import java.io.IOException;

public class UnmarkCommand extends Command {
    private int taskIndex;
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            System.out.println("Executing Unmark Command");;
            Task unmarkedTask = taskList.unmarkTask(taskIndex);
            ui.showUnmarkedTask(unmarkedTask);
            storage.saveTasksToDisk(taskList);
        } catch (IOException e) {
            System.out.println("Error in Unmark Command");
        }
    }
}
