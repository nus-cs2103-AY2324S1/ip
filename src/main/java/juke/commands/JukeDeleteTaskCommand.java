package juke.commands;

import juke.Juke;
import juke.exceptions.storage.JukeStorageException;
import juke.responses.Response;
import juke.tasks.JukeTask;
import juke.tasks.TaskList;
import juke.utils.StringUtils;

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
     * @param response {@code Response} object that contains response from Juke and the user
     * @throws JukeStorageException if there is an issue with storing changes
     * @return {@code Response} object that contains response from Juke and the user
     */
    @Override
    public Response execute(Response response) {
        JukeTask jt = this.taskList.deleteTask(this.task);
        return response.withJuke(
                StringUtils.wrap("Task deleted: " + jt, Juke.MAX_STRING_LENGTH));
    }
}
