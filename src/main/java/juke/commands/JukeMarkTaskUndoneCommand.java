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
     * Constructor for {@code JukeMarkTaskUndoneCommand}.
     *
     * @param taskList {@code TaskList} that manages the tasks
     * @param index Index of the Task to act on
     */
    public JukeMarkTaskUndoneCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Necessary method that is invoked when the action is carried out.
     *
     * @throws JukeStorageException if there is an issue with storing changes
     */
    @Override
    public void complete() {
        this.taskList.markAsUndone(this.index);
        System.out.print("Task Marked as Undone!\n" + this.taskList.taskInformation(this.index));
    }
}
