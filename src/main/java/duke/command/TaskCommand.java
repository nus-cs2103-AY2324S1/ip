package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A Command Class that creates a class
 *
 * @author marioalvaro
 */
public class TaskCommand extends Command {
    private String[] splitTask;

    /**
     * A Constructor that create TaskCommand.
     *
     * @param splitTask Array of String that contains the index for the mark
     */
    public TaskCommand(String[] splitTask) {
        this.splitTask = splitTask;
    }

    /**
     * Method to execute the delet mechanism.
     *
     * @param taskList The used TaskList
     * @param ui The ui object
     * @param storage The storage used
     * @throws Exception If createTaskType and writeFile throw Exception
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        Task newTask = Task.createTaskType(splitTask);
        taskList.add(newTask);
        int i = taskList.indexOf(newTask);

        ui.printAddTask(newTask, i + 1);
        storage.writeFile(taskList);
    }
}
