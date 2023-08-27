package Commands;
import Tasks.Task;
import Utilities.Ui;
import Utilities.Storage;
import Utilities.TaskList;

public class TaskCommand extends Command {
    private Task task;

    public TaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.addTask(this.task);
        String output = Ui.divider + "\n"
                + "Got it. I've added this task:\n"
                + "    " + task.toString()
                + "\nNow you have " + taskList.size()
                + " tasks in the list.\n"
                + Ui.divider + "\n";
        System.out.println(output);
    }
}
