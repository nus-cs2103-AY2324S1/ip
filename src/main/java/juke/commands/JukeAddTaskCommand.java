package juke.commands;

import juke.Juke;
import juke.exceptions.storage.JukeStorageException;
import juke.responses.Response;
import juke.tasks.JukeTask;
import juke.tasks.TaskList;
import juke.utils.StringUtils;

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
     * Carries out an action when the command is executed.
     *
     * @param response {@code Response} object that contains response from Juke and the user
     * @throws JukeStorageException if there is an issue with storing changes
     * @return {@code Response} object that contains response from Juke and the user
     */
    @Override
    public Response execute(Response response) {
        this.taskList.addTask(this.task);
        return response.withJuke(
                StringUtils.wrap("Task added: " + this.task, Juke.MAX_STRING_LENGTH));
    }
}
