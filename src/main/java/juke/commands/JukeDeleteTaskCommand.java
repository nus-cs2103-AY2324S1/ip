package main.java.juke.commands;

import main.java.juke.exceptions.storage.JukeStorageException;
import main.java.juke.tasks.TaskList;
import main.java.juke.tasks.JukeTask;

/**
 * Action that deletes a Task from the Task Manager.
 */
public class JukeDeleteTaskCommand extends JukeCommand {
    /** TaskList to manage all tasks. */
    private final TaskList taskList;

    /** JukeTask to remove. */
    private final int task;

    /**
     * Constructor for JukeDeleteTaskAction.
     * @param taskList TaskList instance
     * @param task Index of task to delete
     */
    public JukeDeleteTaskCommand(TaskList taskList, int task) {
        this.taskList = taskList;
        this.task = task;
    }

    /**
     * Necessary method that is invoked when the action is carried out.
     * @throws {@code JukeStorageException} If there is an issue with storing changes
     */
    @Override
    public void complete() throws JukeStorageException {
        JukeTask jt = this.taskList.deleteTask(this.task);
        System.out.print("Task deleted: " + jt);
    }
}
