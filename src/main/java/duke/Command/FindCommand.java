package duke.Command;

import java.io.IOException;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Class that handles the find command.
 */
public class FindCommand extends Command {
    private String input;
    /**
     * Constructor for find command.
     * @param input input passed in.
     */
    public FindCommand(String input) {
        this.input = input;
    }
    /**
     * Executes the command.
     * @param ui the ui class used.
     * @param storage the storage that is used.
     * @param tasks the tasklist that is used.
     * @return String representation of the execution.
     * @throws IOException IOException
     * @throws DukeException DukeException
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws DukeException, IOException {
        String keyword = input.substring(5).trim();
        TaskList foundTasks = new TaskList();
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(keyword)) {
                foundTasks.addTask(task);
            }
        }
        if (foundTasks.getSize() > 0) {
            storage.writeTasksToFile(tasks);
            return ui.printFindTask(foundTasks);
        } else {
            return ui.printNoFoundTask(keyword);
        }

    }

}
