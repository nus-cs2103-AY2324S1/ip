package juke.commands;

import juke.exceptions.storage.JukeStorageException;
import juke.tasks.TaskList;

/**
 * Action that marks a {@code JukeTask} as done.
 */
public class JukeMarkTaskDoneCommand extends JukeCommand {
    /** TaskList that manages the tasks. */
    private final TaskList taskList;

    /** The index of the task of interest. */
    private final int index;

    /**
     * Constructor for {@code JukeMarkTaskDoneCommand}.
     *
     * @param taskList {@code TaskList} that manages the tasks
     * @param index Index of the Task to act on
     */
    public JukeMarkTaskDoneCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Necessary method that is invoked when the action is carried out.
     *
     * @throws JukeStorageException if there are any errors encountered when reading the data
     */
    @Override
    public void complete() {
        this.taskList.markAsDone(this.index);
        System.out.print("Task Marked as Done!\n" + this.taskList.taskInformation(this.index));
    }
}
