package duke.command;

import java.util.ArrayList;

import duke.DukeList;
import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.task.Task;

/**
 * The FindCommand class represents a command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {

    /**
     * Constructs a new FindCommand object with the specified command.
     *
     * @param command The input command string.
     */
    public FindCommand(String command) {
        super(command);
    }

    /**
     * Executes the find command, searching for tasks containing a specific keyword.
     *
     * @param tasks   The DukeList containing the list of tasks.
     * @param ui      The Ui object for user interaction.
     * @param storage The Storage object for file storage.
     * @throws DukeException If there is an error during execution or if no matching tasks are found.
     */
    @Override
    public void execute(DukeList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputs = this.command.split(" ", 2);
        ArrayList<Task> arrayList = new ArrayList<>(100);
        boolean found = false;
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(inputs[1])) {
                arrayList.add(task);
                found = true;
            }
        }
        if (found) {
            DukeList dukeList = new DukeList(arrayList);
            ui.matchingTasks();
            ui.printList(dukeList);
        } else {
            throw new DukeException("Item not found :(");
        }
    }
}
