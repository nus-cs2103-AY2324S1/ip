package juke.commands;

import juke.exceptions.storage.JukeStorageException;
import juke.tasks.JukeTask;
import juke.tasks.TaskList;

/**
 * Action that adds a Task to the {@code TaskList}.
 */
public class JukeAddTaskCommand extends JukeCommand {
    /** TaskList to manage all tasks. */
    private final TaskList taskList;

    /** JukeTask to add. */
    private final JukeTask task;

    /**
     * Constructor for {@code JukeAddTaskAction}
     *
     * @param taskList {@code TaskList} object
     * @param task {@code JukeTask} to add
     */
    public JukeAddTaskCommand(TaskList taskList, JukeTask task) {
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
        this.taskList.addTask(this.task);
        System.out.print("Task added: " + this.task);
    }
}
