package duke.command;

import duke.Keyword;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.ManipulateException;

public class MarkCommand extends Command {

    private Keyword key;
    private String commandBody;

    public MarkCommand(Keyword key, String commandBody) {
        super(false);
        this.key = key;
        this.commandBody = commandBody;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String err = String.format("OOPS!!! The command for %s task is invalid.", key.getKeyword());
        int taskNum;
        try {
            if (!commandBody.equals("all")) {
                taskNum = Integer.parseInt(commandBody);
            } else {
                taskList.manipulateAllTask(key, ui);
                storage.changeFile(key, -1);
                return;
            }
        } catch (NumberFormatException e) {
            throw new ManipulateException(err, key.getKeyword());
        }
        taskList.markTask(taskNum - 1, key, ui);
        storage.changeFile(key, taskNum - 1);
    }
}
