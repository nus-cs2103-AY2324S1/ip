package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class MarkCommand extends Command {

    private int num;

    /**
     * Constructor of the MarkCommand class
     * @param num The index of the Task in the TaskList.
     */
    public MarkCommand(int num) {
        this.num = num;
    }


    /**
     * To Mark the Task.
     * @param taskList The TaskList that stores the Task.
     * @param ui The Ui instance that will interact with the user.
     * @param storage The Storage instance that will update the file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.mark(num);
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
