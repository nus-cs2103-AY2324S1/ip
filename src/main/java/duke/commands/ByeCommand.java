package duke.commands;

import duke.exceptions.DukeException;
import duke.filehandler.Storage;
import duke.tasks.TaskList;

/**
 * Represents Bye command to be executed
 */
public class ByeCommand extends Command {
    private boolean toStore;

    public ByeCommand(TaskList taskList, boolean toStore) {
        super(taskList);
        this.toStore = toStore;
    }

    /**
     * saves tasks to storage before exiting app
     *
     * @return "exit app" string which will signal Duke to stop
     * @throws DukeException
     */
    @Override
    public String execute() throws DukeException {
        if (toStore) {
            Storage.saveTasks(taskList.getTasks());
        }
        return "exit app";
    }
}
