package evo.commands;

import evo.storage.Storage;
import evo.tasks.TaskList;
import evo.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The MarkCommand class represents a command to mark a task as done in a TaskList.
 * When executed, it sets the specified task as completed and displays a confirmation message to the user.
 */
public class MarkCommand extends Command {

    /**
     * The task number (in string format) to be marked as done.
     */
    protected String taskNumberToMark;

    /**
     * Constructs a MarkCommand with the specified task number to mark as done.
     *
     * @param taskNumberToMark The task number (in string format) to be marked as done.
     */
    public MarkCommand(String taskNumberToMark) {
        this.taskNumberToMark = taskNumberToMark;
    }

    /**
     * Executes the MarkCommand, marking the specified task as done in the TaskList.
     *
     * @param tasksList The tasksList in which the task will be marked as done.
     * @param ui The Ui component for user interface interactions.
     * @param storage The Storage component for data storage operations.
     * @return A confirmation message indicating the task has been marked as done or an error message if an
     *     exception occurs.
     */
    @Override
    public String execute(TaskList tasksList, Ui ui, Storage storage) {
        ArrayList<String> responses = new ArrayList<>();
        try {
            // Marks a task as done
            int taskNumberInList = Integer.parseInt(this.taskNumberToMark) - 1;
            tasksList.get(taskNumberInList).markAsDone();
            storage.saveTasksInFile(tasksList);
            responses.add(ui.showText("Nice! I've marked this task as done:\n"));
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
