package duke.command;

import duke.exception.DukeInvalidMarkException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A Command Class that helps to find tasks according to the keyword
 *
 * @author marioalvaro
 */
public class FindCommand extends Command {
    private String[] splitTask;

    /**
     * Constructor for FindCommand.
     *
     * @param splitTask array of String that contains the keyword
     */
    public FindCommand(String[] splitTask) {
        this.splitTask = splitTask;

    }

    /**
     * Execute and find the task.
     *
     * @param taskList The original TaskList
     * @param ui The Ui
     * @param storage The storage object used
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList findTasks = new TaskList();

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.contains(this.splitTask[1])) {
                findTasks.add(task);
            }
        }

        ui.printTaskList(findTasks);
    }
}
