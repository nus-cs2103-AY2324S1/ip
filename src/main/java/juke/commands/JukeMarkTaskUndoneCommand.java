package juke.commands;

import juke.Juke;
import juke.exceptions.storage.JukeStorageException;
import juke.responses.Response;
import juke.tasks.TaskList;
import juke.utils.StringUtils;

/**
 * Action that marks a {@code JukeTask} as undone.
 */
public class JukeMarkTaskUndoneCommand extends JukeCommand {
    /** {@code TaskList} that manages the tasks. */
    private final TaskList taskList;

    /** The index of the task of interest. */
    private final int index;

    /**
     * Creates an instance of {@code JukeMarkTaskUndoneCommand}.
     *
     * @param taskList {@code TaskList} that manages the tasks
     * @param index Index of the Task to act on
     */
    public JukeMarkTaskUndoneCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
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
        this.taskList.setAsIncomplete(this.index);
        return response.withJuke(
                StringUtils.wrap("Task marked as undone: " + this.taskList.getTaskInformation(this.index),
                                 Juke.MAX_STRING_LENGTH));
    }
}
