package duke.command;

import duke.Keyword;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.ManipulateException;

public class DeleteCommand extends Command {

    private String commandBody;

    public DeleteCommand(String commandBody) {
        super(false);
        this.commandBody = commandBody;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String err = "OOPS!!! The command for delete task is invalid.";
        int taskNum;
        try {
            if (!commandBody.equals("all")) {
                taskNum = Integer.parseInt(commandBody);
            } else {
                taskList.manipulateAllTask(Keyword.DELETE, ui);
                storage.changeFile(Keyword.DELETE, -1);
                return;
            }
        } catch (NumberFormatException e) {
            throw new ManipulateException(err, "delete");
        }
        taskList.deleteTask(taskNum - 1, ui);
        storage.changeFile(Keyword.DELETE, taskNum - 1);
    }
}
