package juke.commands;

import juke.exceptions.storage.JukeStorageException;
import juke.tasks.TaskList;

/**
 * Action that marks a JukeTask as undone.
 */
public class JukeMarkTaskUndoneCommand extends JukeCommand {
    /** TaskList that manages the tasks. */
    private final TaskList taskList;

    /** The index of the task of interest. */
    private final int index;

    /**
     * Constructor for JukeMarkTaskUndoneAction.
     * @param taskList TaskList that manages the tasks
     * @param index Index of the Task to act on
     */
    public JukeMarkTaskUndoneCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Necessary method that is invoked when the action is carried out.
     * @throws JukeStorageException If there is an issue with storing changes
     */
    @Override
    public void execute() throws JukeStorageException {
        this.taskList.setAsIncomplete(this.index);
        System.out.print("Task Marked as Undone!\n" + this.taskList.getTaskInformation(this.index));
    }
}
