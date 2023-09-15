package evo.commands;

import evo.storage.Storage;
import evo.tasks.DoAfter;
import evo.tasks.TaskList;
import evo.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a command for adding a DoAfter task to a task list. This command
 * takes user input and parses it to create a new DoAfter task, then adds it to
 * the task list and updates the storage. It also generates responses for user.
 */
public class DoAfterCommand extends Command {

    /**
     * An array containing the parsed user input, which typically includes the task
     * description and the specific task/period after which the task should be done.
     */
    protected String[] actionType;

    /**
     * Constructs a DoAfterCommand object.
     *
     * @param actionType The string array contains the full command input by the user.
     */
    public DoAfterCommand(String[] actionType) {
        this.actionType = actionType;
    }

    /**
     * Executes the DoAfterCommand by parsing user input, creating a new DoAfter task,
     * adding it to the task list, and updating storage. It generates responses to user.
     *
     * @param tasksList The task list to which the task will be added.
     * @param ui The user interface for displaying feedback.
     * @param storage The storage component for saving task data.
     * @return A string containing responses to the user's input.
     */
    @Override
    public String execute(TaskList tasksList, Ui ui, Storage storage) {
        ArrayList<String> responses = new ArrayList<>();
        try {
            String taskDescription = "";
            int indexOfAfter = 0;
            for (int i = 1; i < this.actionType.length; i++) {
                if (Objects.equals(this.actionType[i + 1], "after")) {
                    taskDescription += actionType[i];
                    indexOfAfter = i + 1;
                    break;
                }
                if (i == this.actionType.length - 1) {
                    taskDescription += actionType[i];
                } else {
                    taskDescription += this.actionType[i] + " ";
                }
            }
            // Constructs the task due date/time
            String period = "";
            for (int i = indexOfAfter + 1; i < this.actionType.length; i++) {
                if (i == this.actionType.length - 1) {
                    period += this.actionType[i];
                } else {
                    period += this.actionType[i] + " ";
                }
            }
            // Creates the deadline object with the taskDescription and taskBy
            DoAfter doAfter = new DoAfter(taskDescription, period);
            tasksList.addTask(doAfter);
            storage.saveTasksInFile(tasksList);

            responses.add(ui.showText("Got it. I've added this task:\n"));
            responses.add(ui.showText("  " + doAfter.toString() + "\n"));

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
