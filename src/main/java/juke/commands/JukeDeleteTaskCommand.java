package juke.commands;


import juke.exceptions.storage.JukeStorageException;
import juke.tasks.JukeTask;
import juke.tasks.TaskList;

/**
 * Action that deletes a Task from the {@code TaskList}.
 */
public class JukeDeleteTaskCommand extends JukeCommand {
    /** TaskList to manage all tasks. */
    private final TaskList taskList;

    /** JukeTask to remove. */
    private final int task;

    /**
     * Constructor for {@code JukeDeleteTaskCommand}.
     *
     * @param taskList {@code TaskList} instance
     * @param task Index of task to delete
     */
    public JukeDeleteTaskCommand(TaskList taskList, int task) {
        this.taskList = taskList;
        this.task = task;
    }

    /**
     * Necessary method that is invoked when the action is carried out.
     *
     * @throws JukeStorageException if there is an issue with storing changes
     */
    @Override
    public void complete() {
        JukeTask jt = this.taskList.deleteTask(this.task);
        System.out.print("Task deleted: " + jt);
    }
}
