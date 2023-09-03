package command;

import duke.Ui;
import duke.TaskList;

/**
 * The Command to indicate that the user wants the chatbot to list out the current
 * tasks in the task list.
 */
public class ListCommand extends Command {
    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor of ListCommand.
     *
     * @param taskList The task list which the command would modify when tasked.
     * @param ui The ui of the chatbot to get the input of the user.
     */
    public ListCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.listTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}