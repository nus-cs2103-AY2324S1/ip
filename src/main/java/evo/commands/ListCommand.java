package evo.commands;

import evo.storage.Storage;
import evo.tasks.TaskList;
import evo.ui.Ui;

import java.util.ArrayList;

/**
 * The ListCommand class represents a command to list tasks from a TaskList.
 * When executed, it displays the list of tasks to the user through the UI.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {
    }

    /**
     * Executes the ListCommand to list tasks from a taskLists and display them through the UI.
     *
     * @param tasksList The tasksList containing the tasks to be listed.
     * @param ui The user interface for displaying messages to the user.
     * @param storage The storage component for interacting with task data storage.
     */
    @Override
    public String execute(TaskList tasksList, Ui ui, Storage storage) {
        ArrayList<String> responses = new ArrayList<>();
        if (tasksList.isEmpty()) {
            responses.add(ui.showText("Here are the tasks in your list:"));
        } else {
            responses.add(ui.showText("Here are the tasks in your list:\n"));
            for (int i = 0; i < tasksList.tasksListLength(); i++) {
                if (i == tasksList.tasksListLength() - 1) {
                    responses.add(ui.showText(i + 1 + ". " + tasksList.get(i).toString()));
                } else {
                    responses.add(ui.showText(i + 1 + ". " + tasksList.get(i).toString() + "\n"));
                }
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
