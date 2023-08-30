package duke.command;

import duke.exception.DukeException;
import duke.exception.ManipulateException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Keyword;

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
        int task_num;
        try {
            if (!commandBody.equals("all")) {
                task_num = Integer.parseInt(commandBody);
            } else {
                taskList.manipulateAllTask(key, ui);
                storage.changeFile(key, -1);
                return;
            }
        } catch (NumberFormatException e) {
            throw new ManipulateException(err, key.getKeyword());
        }
        taskList.markTask(task_num - 1, key.equals(Keyword.MARK), ui);
        storage.changeFile(key, task_num - 1);
    }
}
