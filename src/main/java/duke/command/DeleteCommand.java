package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Task;
import java.util.ArrayList;

public class DeleteCommand extends Command {
    private int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void doCommand(ArrayList<Task> tasks, Ui ui, Storage storage) {
        if (taskNum >= 1 && taskNum <= tasks.size()) {
            Task deletedTask = tasks.remove(taskNum - 1);

            Ui.showHorizontalLine();
            System.out.println("    Noted. I've removed this task:\n" + "     " + deletedTask.toString());
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
            Ui.showHorizontalLine();
            storage.saveTasks(tasks); // Save after deleting
        }
    }
}
