package juke.commands;

import juke.exceptions.storage.JukeStorageException;
import juke.tasks.TaskList;

/**
 * Action that marks a {@code JukeTask} as done.
 */
public class JukeMarkTaskDoneCommand extends JukeCommand {
    /** {@code TaskList} that manages the tasks. */
    private final TaskList taskList;

    /** The index of the task of interest. */
    private final int index;

    /**
     * Creates an instance of {@code JukeMarkTaskDoneCommand}.
     *
     * @param taskList {@code TaskList} that manages the tasks
     * @param index Index of the Task to act on
     */
    public JukeMarkTaskDoneCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Carries out an action when the command is executed.
     *
     * @throws JukeStorageException if there are any errors encountered when reading the data
     */
    @Override
    public void execute() {
        this.taskList.setAsComplete(this.index);
        System.out.print("Task Marked as Done!\n" + this.taskList.getTaskInformation(this.index));
    }
}
