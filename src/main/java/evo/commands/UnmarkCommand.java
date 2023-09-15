package evo.commands;

import evo.storage.Storage;
import evo.tasks.TaskList;
import evo.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The UnmarkCommand class represents a command to mark a task as not done in a TaskList.
 * When executed, it sets the specified task as not completed and displays a confirmation message to the user.
 */
public class UnmarkCommand extends Command {

    /**
     * The task number (in string format) to be marked as not done.
     */
    protected String taskNumberToUnmark;

    /**
     * Constructs an UnmarkCommand with the specified task number to mark as not done.
     *
     * @param taskNumberToUnmark The task number (in string format) to be marked as not done.
     */
    public UnmarkCommand(String taskNumberToUnmark) {
        this.taskNumberToUnmark = taskNumberToUnmark;
    }

    /**
     * Executes the UnmarkCommand, marking the specified task as not done in the tasksList.
     *
     * @param tasksList The tasksList in which the task will be marked as not done.
     * @param ui The Ui component for user interface interactions.
     * @param storage The Storage component for data storage operations.
     * @return A confirmation message indicating the task has been marked as not done or an error message if an
     *     exception occurs.
     */
    @Override
    public String execute(TaskList tasksList, Ui ui, Storage storage) {
        ArrayList<String> responses = new ArrayList<>();
        try {
            // Mark a task as not done
            int taskNumberInList = Integer.parseInt(this.taskNumberToUnmark) - 1;
            tasksList.get(taskNumberInList).markAsNotDone();
            storage.saveTasksInFile(tasksList);
            responses.add(ui.showText("OK, I've marked this task as not done yet:\n"));
            responses.add(ui.showText("  " + tasksList.get(taskNumberInList).toString()));
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
