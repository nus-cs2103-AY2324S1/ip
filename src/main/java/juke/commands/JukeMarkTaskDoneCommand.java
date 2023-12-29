package juke.commands;

import juke.commons.exceptions.storage.JukeStorageException;
import juke.responses.Dialog;
import juke.responses.Response;
import juke.tasks.TaskList;

/**
 * Action that marks a {@code JukeTask} as done.
 */
public class JukeMarkTaskDoneCommand extends JukeCommand {
    /** {@code TaskList} that manages the tasks. */
    private final TaskList taskList;

    /** The index of the task of interest. */
    private final int index;

    /**
     * Creates an instance of {@code JukeMarkTaskDoneCommand}.
     *
     * @param taskList {@code TaskList} that manages the tasks
     * @param index Index of the Task to act on
     */
    public JukeMarkTaskDoneCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
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
        this.taskList.setAsComplete(this.index);
        return response
                .with(Dialog.ofJuke("Task marked as done: "
                                            + this.taskList.getTaskInformation(this.index)))
                .with(Dialog.ofJuke(this.taskList.toString()));
    }
}
