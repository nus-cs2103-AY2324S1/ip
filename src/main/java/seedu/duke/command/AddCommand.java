package seedu.duke.command;

import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskType;

public class AddCommand extends EditCommand {

    private static final String ADD_TASK_FAILED_MESSAGE = "☹ OOPS!!! Add task failed.";
    private String input;
    private TaskType taskType;

    public AddCommand(String input, TaskType taskType) {
        this.input = input;
        this.taskType = taskType;
    }

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

    public void generateResponse(String[] response, Task task, TaskList taskList) {
        if (task == null) {
            response[0] = AddCommand.ADD_TASK_FAILED_MESSAGE;
            return;
        }
        response[0] = taskList.add(task);
    }
}
