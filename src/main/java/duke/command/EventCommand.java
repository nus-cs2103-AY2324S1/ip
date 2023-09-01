package duke.command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidArgumentException;
import duke.exception.InvalidDateException;
import duke.task.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * The EventCommand class represents a command to add a Event task to the task list in the Duke application.
 * It parses the user input and handles exceptions related to invalid input or date formatting.
 */
public class EventCommand extends Command {

    /**
     * Constructs a new EventCommand object with the specified full command string.
     *
     * @param fullCommand The full command string as entered by the user.
     */
    public EventCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the Event command, adding a new Event task to the task list.
     *
     * @param tasks   The task list to which the Event task will be added.
     * @param ui      The user interface for displaying messages to the user.
     * @param storage The storage object for reading from or writing to a data file.
     * @throws InvalidArgumentException If the command is missing required arguments.
     * @throws InvalidDateException      If the date provided is in an invalid format.
     */
    @Override
    public void execute(TaskList tasks , Ui ui, Storage storage) throws InvalidArgumentException, InvalidDateException {
        String[] words = this.fullCommand.split(" ", 2);
        if(words.length < 2) {
            throw new InvalidArgumentException("event");
        } else {
            String[] splitCommand = words[1].split("/", 2);
            if(splitCommand.length < 2) {
                throw new InvalidArgumentException("event");
            }
            String c = splitCommand[0];
            String[] splitDeadline = splitCommand[1].split("/", 2);
            if(splitDeadline.length < 2) {
                throw new InvalidArgumentException("event");
            } else {
                if(splitDeadline[0].split(" ", 2).length < 2
                        || splitDeadline[1].split(" ", 2).length < 2) {
                    throw new InvalidArgumentException("event");
                } else {
                    try {
                        LocalDateTime from = LocalDateTime.parse(splitDeadline[0].split(" ", 2)[1].strip(), Storage.dateTimeInputFormatter);
                        LocalDateTime to = LocalDateTime.parse(splitDeadline[1].split(" ", 2)[1].strip(), Storage.dateTimeInputFormatter);
                        Event t = new Event(c, from, to);
                        tasks.addTask(t);
                    } catch(DateTimeParseException e) {
                        throw new InvalidDateException();
                    }
                }
            }
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
