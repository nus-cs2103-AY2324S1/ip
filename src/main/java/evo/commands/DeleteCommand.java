package evo.commands;

import evo.storage.Storage;
import evo.tasks.Task;
import evo.tasks.TaskList;
import evo.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The DeleteCommand class represents a command to delete a task from a TaskList.
 * When executed, it removes the specified task from the TaskList and displays a confirmation message to the user.
 */
public class DeleteCommand extends Command {

    /**
     * The task number (in string format) to be deleted.
     */
    protected String taskNumberToDelete;

    /**
     * Constructs a DeleteCommand with the specified task number to delete.
     *
     * @param taskNumberToDelete The task number (in string format) to be deleted.
     */
    public DeleteCommand(String taskNumberToDelete) {
        this.taskNumberToDelete = taskNumberToDelete;
    }

    /**
     * Executes the DeleteCommand, removing the specified task from the tasksList.
     *
     * @param tasksList The TaskList from which the task will be deleted.
     * @param ui The Ui component for user interface interactions.
     * @param storage The Storage component for data storage operations.
     * @return A confirmation message indicating the task has been removed or an error message if an exception occurs.
     */
    @Override
    public String execute(TaskList tasksList, Ui ui, Storage storage) {
        ArrayList<String> responses = new ArrayList<>();
        try {
            // Deletes a task from taskList
            int taskNumberToDelete = Integer.parseInt(this.taskNumberToDelete) - 1;
            Task deletedTask = tasksList.get(taskNumberToDelete);
            tasksList.deleteTask(taskNumberToDelete);
            storage.saveTasksInFile(tasksList);
            responses.add(ui.showText("Noted. I've removed this task:\n"));
            responses.add(ui.showText("  " + deletedTask.toString() + "\n"));
            if (tasksList.tasksListLength() <= 1) {
                responses.add(ui.showText("Now you have " + tasksList.tasksListLength() + " task in the list."));
            } else {
                responses.add(ui.showText("Now you have " + tasksList.tasksListLength() + " tasks in the list."));
            }
        } catch (IOException e) {
            responses.add(ui.showText("Something went wrong: " + e.getMessage()));
        }
        return concatenateString(responses);
    }

    /**
     * Concatenates a list of response strings into a single string.
     *
     * @param responses The list of response strings to concatenate.
     * @return The concatenated response string.
     */
    public String concatenateString(ArrayList<String> responses) {
        String textToRespond = "";
        for (int i = 0; i < responses.size(); i++) {
            textToRespond += responses.get(i);
        }
        return textToRespond;
    }
}
