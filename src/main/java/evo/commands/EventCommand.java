package evo.commands;

import evo.storage.Storage;
import evo.tasks.Event;
import evo.tasks.TaskList;
import evo.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The EventCommand class represents a command to create and add an event task to a TaskList.
 * When executed, it parses user input to create an event task, adds it to the task list, and displays a
 * confirmation message to the user.
 */
public class EventCommand extends Command {

    /**
     * An array containing action and description components from user input.
     */
    protected String[] actionAndDesc;

    /**
     * An array containing action type and date/time components from user input.
     */
    protected String[] typeAndDates;

    /**
     * Constructs an EventCommand with the provided action and description components and action type and
     * date/time components.
     *
     * @param actionAndDesc An array containing action and description components from user input.
     * @param typeAndDates An array containing action type and date/time components from user input.
     */
    public EventCommand(String[] actionAndDesc, String[] typeAndDates) {
        this.actionAndDesc = actionAndDesc;
        this.typeAndDates = typeAndDates;
    }

    /**
     * Executes the EventCommand, creating an event task based on user input and adding it to the taskLists.
     *
     * @param tasksList The tasksList to which the event task will be added.
     * @param ui The Ui component for user interface interactions.
     * @param storage The Storage component for data storage operations.
     * @return A confirmation message indicating the task has been added or an error message if an exception occurs.
     */
    @Override
    public String execute(TaskList tasksList, Ui ui, Storage storage) {
        ArrayList<String> responses = new ArrayList<>();
        try {
            // Adds Event object to the taskList
            String[] datesFrom = typeAndDates[1].split(" ");
            String[] datesTo = typeAndDates[2].split(" ");
            String taskDescription = "";
            // Constructs the description of the event task from the user input
            for (int i = 1; i < this.actionAndDesc.length; i++) {
                if (i == this.actionAndDesc.length - 1) {
                    taskDescription += this.actionAndDesc[i];
                } else {
                    taskDescription += this.actionAndDesc[i] + " ";
                }
            }
            // Constructs the task due date/time duration
            String taskDuration = "";
            for (int i = 0; i < datesFrom.length; i++) {
                if (i == 0) {
                    taskDuration += datesFrom[i] + ": ";
                } else {
                    taskDuration += datesFrom[i] + " ";
                }
            }
            for (int i = 0; i < datesTo.length; i++) {
                if (i == 0) {
                    taskDuration += datesTo[i] + ": ";
                } else {
                    taskDuration += datesTo[i];
                }
            }
            // Creates the event object with the taskDescription and taskDuration
            Event event = new Event(taskDescription, taskDuration);
            tasksList.addTask(event);
            storage.saveTasksInFile(tasksList);
            responses.add(ui.showText("Got it. I've added this task:\n"));
            responses.add(ui.showText("  " + event.toString() + "\n"));
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
