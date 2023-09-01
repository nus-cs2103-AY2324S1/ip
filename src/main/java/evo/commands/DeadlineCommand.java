package evo.commands;

import evo.storage.Storage;
import evo.tasks.Deadline;
import evo.tasks.TaskList;
import evo.ui.Ui;

/**
 * The DeadlineCommand class represents a command to create and add a deadline task to a TaskList.
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
     * Executes the DeadlineCommand to create a deadline task from user input, add it to the TaskList, and update the UI.
     *
     * @param tasksList The TaskList containing the tasks to be managed.
     * @param ui The user interface for displaying messages to the user.
     * @param storage The storage component for interacting with task data storage.
     */
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
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
        ui.showText("Got it. I've added this task:");
        ui.showText("  " + deadline.toString());
        if (tasksList.tasksListLength() <= 1) {
            ui.showText("Now you have " + tasksList.tasksListLength() + " task in the list.");
            ui.showNewLine();
        } else {
            ui.showText("Now you have " + tasksList.tasksListLength() + " tasks in the list.");
            ui.showNewLine();
        }
    }
}
