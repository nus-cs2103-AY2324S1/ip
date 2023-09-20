package juke.commands;

import juke.commons.exceptions.storage.JukeStorageException;
import juke.responses.Dialog;
import juke.responses.Response;
import juke.tasks.JukeTask;
import juke.tasks.TaskList;

/**
 * Action that adds a Task to the {@code TaskList}.
 */
public class JukeAddTaskCommand extends JukeCommand {
    /** {@code TaskList} to manage all tasks. */
    private final TaskList taskList;

    /** {@code JukeTask} to add. */
    private final JukeTask task;

    /**
     * Creates an instance of {@code JukeAddTaskAction}
     *
     * @param taskList {@code TaskList} object
     * @param task {@code JukeTask} to add
     */
    public JukeAddTaskCommand(TaskList taskList, JukeTask task) {
        this.taskList = taskList;
        this.task = task;
    }

    /**
     * Invokes an action when the command is executed.
     *
     * @param response {@code Response} object that contains response from Juke and the user
     * @return {@code Response} object composed with response from Juke or the user
     * @throws JukeStorageException if there are any errors encountered when reading the data file
     */
    @Override
    public Response execute(Response response) {
        this.taskList.addTask(this.task);
        return response
                .with(Dialog.ofJuke("Task added: " + this.task))
                .with(Dialog.ofJuke("Your current tasks:\n" + this.taskList));
    }
}
