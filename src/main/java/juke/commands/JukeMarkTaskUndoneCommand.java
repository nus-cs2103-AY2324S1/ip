package juke.commands;

import juke.exceptions.storage.JukeStorageException;
import juke.tasks.TaskList;

/**
 * Action that marks a {@code JukeTask} as undone.
 */
public class JukeMarkTaskUndoneCommand extends JukeCommand {
    /** {@code TaskList} that manages the tasks. */
    private final TaskList taskList;

    /** The index of the task of interest. */
    private final int index;

    /**
     * Creates an instance of {@code JukeMarkTaskUndoneCommand}.
     *
     * @param taskList {@code TaskList} that manages the tasks
     * @param index Index of the Task to act on
     */
    public JukeMarkTaskUndoneCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Carries out an action when the command is executed.
     *
     * @throws JukeStorageException if there is an issue with storing changes
     */
    @Override
    public void execute() {
        this.taskList.setAsIncomplete(this.index);
        System.out.print("Task Marked as Undone!\n" + this.taskList.getTaskInformation(this.index));
    }
}
