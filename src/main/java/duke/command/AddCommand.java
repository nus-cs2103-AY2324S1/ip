package duke.command;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class AddCommand extends Command {
    Task task;

    /**
     * Constructor of AddCommand class.
     * @param task The task to be added into the TaskList.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * To ass the Task to the TaskList.
     * @param taskList The TaskList that stores the Task.
     * @param ui The Ui instance that will interact with the user.
     * @param storage The Storage instance that will update the file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // add task into tasks, input coming from Parse class, Parse class is supposed to make the String into a duke.task.Task
        // print in ui
        // write in storage
        taskList.add(this.task);
        ui.printAddedTask(this.task, taskList.size());
        String toBeWritten = taskList.toWrite();
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
