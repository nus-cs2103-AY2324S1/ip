package duke.command;

import java.io.IOException;

import duke.DukeList;
import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.task.ToDo;

/**
 * The AddToDoCommand class represents a command to add a new ToDo task to the list.
 */
public class AddToDoCommand extends Command {
    /**
     * Constructs a new AddToDoCommand object with the specified command.
     *
     * @param command The input command string.
     */
    public AddToDoCommand(String command) {
        super(command);
    }

    /**
     * Executes the add ToDo command, adding a new ToDo task to the list.
     *
     * @param tasks   The DukeList containing the list of tasks.
     * @param ui      The Ui object for user interaction.
     * @param storage The Storage object for file storage.
     * @throws DukeException If there is an error during execution.
     */
    @Override
    public void execute(DukeList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputs = this.command.split(" ", 2);
        ToDo todo;
        try {
            todo = new ToDo(inputs[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid Todo format");
        }
        tasks.add(todo);
        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            throw new DukeException("Error writing to file");
        }
        ui.addToList(todo, tasks.getSize());
    }
}
