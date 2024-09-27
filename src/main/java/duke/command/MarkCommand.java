package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class MarkCommand extends Command {

    private int number;

    /**
     * Constructor of the MarkCommand class
     * @param number The index of the Task in the TaskList.
     */
    public MarkCommand(int number) {
        this.number = number;
    }


    /**
     * To Mark the Task.
     * @param taskList The TaskList that stores the Task.
     * @param ui The Ui instance that will interact with the user.
     * @param storage The Storage instance that will update the file.
     */
    @Override
    public String executeTask(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.mark(number);
        String toBeWritten = taskList.toString();
        storage.write(toBeWritten);
        return ui.printMarkMessage(task);
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
