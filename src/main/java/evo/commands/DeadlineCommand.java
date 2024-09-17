package evo.commands;

import evo.storage.Storage;
import evo.tasks.Deadline;
import evo.tasks.TaskList;
import evo.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The DeadlineCommand class represents a command to create and add a deadline task to a tasksList.
 * When executed, it parses user input to create a deadline task, adds it to the task list, and displays a confirmation
 * message to the user.
 */
public class DeadlineCommand extends Command {

    /**
     * An array containing action and description components from user input.
     */
    protected String[] actionAndDesc;

    /**
     * An array containing action type and date/time components from user input.
     */
    protected String[] typeAndDates;

    /**
     * Constructs a DeadlineCommand with the provided action and description components and action type
     * and date/time components.
     *
     * @param actionAndDesc An array containing action and description components from user input.
     * @param typeAndDates An array containing action type and date/time components from user input.
     */
    public DeadlineCommand(String[] actionAndDesc, String[] typeAndDates) {
        this.actionAndDesc = actionAndDesc;
        this.typeAndDates = typeAndDates;
    }

    /**
     * Executes the DeadlineCommand, creating a deadline task based on user input and adding it to the task list.
     *
     * @param tasksList The TaskList to which the deadline task will be added.
     * @param ui The Ui component for user interface interactions.
     * @param storage The Storage component for data storage operations.
     * @return A confirmation message indicating the task has been added or an error message if an exception occurs.
     */
    @Override
    public String execute(TaskList tasksList, Ui ui, Storage storage) {
        ArrayList<String> responses = new ArrayList<>();
        try {
            // Constructs the description of the deadline task from the user input
            String taskDescription = "";
            for (int i = 1; i < actionAndDesc.length; i++) {
                if (i == actionAndDesc.length - 1) {
                    taskDescription += actionAndDesc[i];
                } else {
                    taskDescription += actionAndDesc[i] + " ";
                }
            }
            // Constructs the task due date/time
            String[] dates = this.typeAndDates[1].split(" ");
            String taskBy = "";
            for (int i = 1; i < dates.length; i++) {
                if (i == dates.length - 1) {
                    taskBy += dates[i];
                } else {
                    taskBy += dates[i] + " ";
                }
            }
            // Creates the deadline object with the taskDescription and taskBy
            Deadline deadline = new Deadline(taskDescription, taskBy);
            tasksList.addTask(deadline);
            storage.saveTasksInFile(tasksList);

            responses.add(ui.showText("Got it. I've added this task:\n"));
            responses.add(ui.showText("  " + deadline.toString() + "\n"));

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
