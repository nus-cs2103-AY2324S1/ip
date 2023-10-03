package duke.command;

import duke.Duplicate.Duplicate;
import duke.exception.DukeDuplicatesException;
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
     * Method to execute the task mechanism.
     *
     * @param taskList The used TaskList
     * @param ui The ui object
     * @param storage The storage used
     * @return Message response from running the command.
     * @throws Exception If createTaskType and writeFile throw Exception
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, Duplicate duplicate) throws Exception {
        Task newTask = Task.createTaskType(splitTask);

        if (duplicate.isDuplicates(newTask, taskList)) {
            throw new DukeDuplicatesException(""); //change to there is duplicate;
        }

        taskList.add(newTask);

        storage.writeFile(taskList);

        return ui.printAddTask(newTask, taskList.size());
    }
}
