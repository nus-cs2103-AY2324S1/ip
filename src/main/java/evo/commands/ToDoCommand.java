package evo.commands;

import evo.storage.Storage;
import evo.tasks.ToDo;
import evo.tasks.TaskList;
import evo.ui.Ui;

/**
 * The ToDoCommand class represents a command to add a "To-Do" task to a TaskList.
 * When executed, it creates a new "To-Do" task with the specified description and adds it to the TaskList.
 */
public class ToDoCommand extends Command {
    /**
     * The array of action type and task description extracted from the user command.
     */
    protected String[] actionType;

    /**
     * Constructs a ToDoCommand with the specified action type and task description.
     *
     * @param actionType The array containing the action type and task description.
     */
    public ToDoCommand(String[] actionType) {
        this.actionType = actionType;
    }

    /**
     * Executes the ToDoCommand to add a "To-Do" task to the TaskList, updates the UI, and optionally the storage.
     *
     * @param tasksList The TaskList containing the tasks to be managed.
     * @param ui The user interface for displaying messages to the user.
     * @param storage The storage component for interacting with task data storage.
     */
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
        String taskDescription = "";
        for (int i = 1; i < this.actionType.length; i++) {
            if (i == this.actionType.length - 1) {
                taskDescription += this.actionType[i];
            } else {
                taskDescription += this.actionType[i] + " ";
            }
        }

        ToDo toDo = new ToDo(taskDescription);
        tasksList.addTask(toDo);
        ui.showText("Got it. I've added this task:");
        ui.showText("  " + toDo.toString());
        if (tasksList.tasksListLength() <= 1) {
            ui.showText("Now you have " + tasksList.tasksListLength() + " task in the list.");
            ui.showNewLine();
        } else {
            ui.showText("Now you have " + tasksList.tasksListLength() + " tasks in the list.");
            ui.showNewLine();
        }
    }
}

