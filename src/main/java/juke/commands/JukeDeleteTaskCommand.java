package juke.commands;

import juke.commons.exceptions.storage.JukeStorageException;
import juke.responses.Dialog;
import juke.responses.Response;
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
     * Invokes an action when the command is executed.
     *
     * @param response {@code Response} object that contains response from Juke and the user
     * @return {@code Response} object composed with response from Juke or the user
     * @throws JukeStorageException if there are any errors encountered when reading the data file
     */
    @Override
    public Response execute(Response response) {
        String taskInfo = this.taskList.getTaskInformation(this.task);
        this.taskList.deleteTask(this.task);

        return response
                .with(Dialog.ofJuke("Task deleted: " + taskInfo))
                .with(Dialog.ofJuke(this.taskList.toString()));
    }
}
