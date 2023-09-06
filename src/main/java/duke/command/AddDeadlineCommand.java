package duke.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.DateParserService;
import duke.DukeList;
import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.task.Deadline;

/**
 * The AddDeadlineCommand class represents a command to add a new Deadline task to the list.
 */
public class AddDeadlineCommand extends Command {
    /**
     * Constructs a new AddDeadlineCommand object with the specified command.
     *
     * @param command The input command string.
     */
    public AddDeadlineCommand(String command) {
        super(command);
    }

    /**
     * Executes the add Deadline command, adding a new Deadline task to the list.
     *
     * @param tasks   The DukeList containing the list of tasks.
     * @param ui      The Ui object for user interaction.
     * @param storage The Storage object for file storage.
     * @throws DukeException If there is an error during execution.
     */
    @Override
    public void execute(DukeList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputs = this.command.split(" ", 2);
        Deadline deadline;
        try {
            String[] by = inputs[1].split("/by", 2);
            LocalDate b = DateParserService.parseDate(by[1]);
            deadline = new Deadline(by[0], b);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            if (e instanceof ArrayIndexOutOfBoundsException) {
                throw new DukeException("Invalid Deadline format");
            }
            throw new DukeException("Invalid date format");
        }
        tasks.add(deadline);
        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            throw new DukeException("Error writing to file");
        }
        ui.addToList(deadline, tasks.getSize());
    }
}
