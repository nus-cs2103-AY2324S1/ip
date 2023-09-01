package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class DeleteCommand extends Command {

    private int num;

    /**
     * Constructor of the DeleteCommand class.
     * @param num The index number of the Task in the TaskList that stores it.
     */
    public DeleteCommand(int num) {
        this.num = num;
    }

    /**
     * To delete the Task from the TaskList.
     * @param taskList The TaskList that stores the Task.
     * @param ui The Ui instance that will interact with the user.
     * @param storage The Storage instance that will update the file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.delete(this.num);
        ui.printDeletedTask(task, taskList.size());
        String toBeWritten = taskList.toString();
        storage.write(toBeWritten);
    }

    /**
     * To check whether the user wanted to exit the program.
     * @return Boolean value representing whether the user wanted to exit the program.
     */
    @Override
    public boolean isExit(){
        return false;
    }


}
