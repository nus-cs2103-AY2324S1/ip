package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class UnmarkCommand extends Command {
    private int num;

    /**
     * Constructor of the UnmarkCommand class
     * @param num The index of the Task in the TaskList.
     */
    public UnmarkCommand(int num) {
        this.num = num;
    }


    /**
     * To Unmark the Task.
     * @param taskList The TaskList that stores the Task.
     * @param ui The Ui instance that will interact with the user.
     * @param storage The Storage instance that will update the file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.unmark(num);
        ui.printMarkMessage(task);
        String toBeWritten = taskList.toString();
        storage.write(toBeWritten);
    }

    /**
     * To check whether the user wanted to exit the program.
     * @return Boolean value representing whether the user wanted to exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
