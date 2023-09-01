package evo.commands;

import evo.storage.Storage;
import evo.tasks.Event;
import evo.tasks.TaskList;
import evo.ui.Ui;

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
     * Executes the EventCommand to create an event task from user input, add it to the TaskList, and update the UI.
     *
     * @param tasksList The TaskList containing the tasks to be managed.
     * @param ui The user interface for displaying messages to the user.
     * @param storage The storage component for interacting with task data storage.
     */
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
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
        ui.showText("Got it. I've added this task:");
        ui.showText("  " + event.toString());
        if (tasksList.tasksListLength() <= 1) {
            ui.showText("Now you have " + tasksList.tasksListLength() + " task in the list.");
            ui.showNewLine();
        } else {
            ui.showText("Now you have " + tasksList.tasksListLength() + " tasks in the list.");
            ui.showNewLine();
        }
    }
}