package duke.command;

import java.util.ArrayList;
import javax.swing.JTextArea;

import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents a command to delete a task from the list.
 */
public class DeleteCommand extends Command {
    private int taskNum;

    /**
     * Constructs a DeleteCommand instance.
     *
     * @param taskNum The task number to be deleted.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the DeleteCommand, removing a task from the list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The data storage.
     * @param chatArea JTextArea for displaying messages in the GUI.
     */
    @Override
    public void doCommand(ArrayList<Task> tasks, Ui ui, Storage storage, JTextArea chatArea) {
        if (taskNum >= 1 && taskNum <= tasks.size()) {
            Task deletedTask = tasks.remove(taskNum - 1);

            chatArea.append("Noted. I've removed this task:\n" + "     ");
            deletedTask.display(chatArea);
            chatArea.append("Now you have " + tasks.size() + " tasks in the list.\n");
            storage.saveTasks(tasks, chatArea); // Save after deleting
        }
    }
}
