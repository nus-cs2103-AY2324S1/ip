package evo.commands;

import evo.storage.Storage;
import evo.tasks.TaskList;
import evo.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to find and display tasks that match a specified keyword.
 * When executed, this command searches the task list for tasks containing the specified keyword
 * in their descriptions and displays the matching tasks to the user.
 */
public class FindCommand extends Command {

    /** The keyword that will or not match the task's description. */
    protected String keyword;

    /** Keep track the index number once a task's description with matched keyword was found. */
    private int listIndexNumber = 1;

    /**
     * Constructs a new FindCommand with the specified keyword.
     *
     * @param keyword The keyword to match tasks against.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand by searching for tasks matching the specified keyword in the taskLists
     * and displaying the matching tasks to the user.
     *
     * @param tasksList The task list containing all the tasks.
     * @param ui The user interface component for displaying messages to the user.
     * @param storage The storage component for saving and loading tasks from a file.
     */
    @Override
    public String execute(TaskList tasksList, Ui ui, Storage storage) {
        ArrayList<String> responses = new ArrayList<>();

        responses.add(ui.showText("Here are the matching tasks in your list:\n"));
        for (int i = 0; i < tasksList.tasksListLength(); i++) {
            if (tasksList.getDescription(i).contains(this.keyword)) {
                responses.add(ui.showText(listIndexNumber + ". " + tasksList.get(i).toString()));
                listIndexNumber++;
            }
            if (i == tasksList.tasksListLength() - 1 && listIndexNumber == 1) {
                responses.add(ui.showText("[Sorry. No matched task was found with " + this.keyword + " keyword.]"));
            }
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
