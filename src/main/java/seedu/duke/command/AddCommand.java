package seedu.duke.command;

import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskType;

/**
 * Represents the add task command input by user.
 *
 *  @author KAM JIA YUE
 *  @since 2023-09-12
 */
public class AddCommand extends EditCommand {

    private static final String ADD_TASK_FAILED_MESSAGE = "☹ OOPS!!! Add task failed.";
    private final String input;
    private final TaskType taskType;

    /**
     * The main constructor for this AddCommand class.
     *
     * @param input Input of user.
     * @param taskType Type of task to add to task list.
     */
    public AddCommand(String input, TaskType taskType) {
        this.input = input;
        this.taskType = taskType;
    }

    /**
     * {@inheritDoc}
     *
     * Executes this add command.
     *
     * @param taskList User's task list.
     * @param ui The ui of Duke chat bot.
     * @param response The String representation of Duke chat bot's response after adding this task.
     * @throws Exception If there are any user input errors.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String[] response) throws Exception {
        Task task;

        switch (this.taskType) {
        case TODO:
            task = ui.todo(this.input);
            break;
        case DEADLINE:
            task = ui.deadline(this.input);
            break;
        case EVENT:
            task = ui.event(this.input);
            break;
        default:
            task = null;
            break;
        }

        generateResponse(response, task, taskList);
    }

    /**
     * Generates Duke chat bot's response after adding this task.
     *
     * @param response The String representation of Duke chat bot's response after adding this task.
     * @param task The task to be added to the user's task list.
     * @param taskList User's task list.
     */
    public void generateResponse(String[] response, Task task, TaskList taskList) {
        assert task != null : AddCommand.ADD_TASK_FAILED_MESSAGE;
        if (task == null) {
            response[0] = AddCommand.ADD_TASK_FAILED_MESSAGE;
            return;
        }
        response[0] = taskList.add(task);
    }
}
