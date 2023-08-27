package juke.commands;

import juke.exceptions.storage.JukeStorageException;
import juke.tasks.JukeTask;
import juke.tasks.TaskList;

/**
 * Action that deletes a Task from the {@code TaskList}.
 */
public class JukeDeleteTaskCommand extends JukeCommand {
    /** {@code TaskList} to manage all tasks. */
    private final TaskList taskList;

    /** JukeTask to remove. */
    private final int task;

    /**
     * Creates an instance of {@code JukeDeleteTaskCommand}.
     *
     * @param taskList {@code TaskList} instance
     * @param task Index of task to delete
     */
    public JukeDeleteTaskCommand(TaskList taskList, int task) {
        this.taskList = taskList;
        this.task = task;
    }

    /**
     * Carries out an action when the command is executed.
     *
     * @throws JukeStorageException if there is an issue with storing changes
     */
    @Override
    public void execute() {
        JukeTask jt = this.taskList.deleteTask(this.task);
        System.out.print("Task deleted: " + jt);
    }
}
