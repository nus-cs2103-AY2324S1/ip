package command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.Deadline;
import duke.DukeException;
import duke.Event;
import duke.Storage;
import duke.StorageException;
import duke.Task;
import duke.TaskList;
import duke.ToDo;
import duke.UI;

/**
 * A command to add either a Todo, Deadline or Event.
 */
public class AddCommand extends Command {
    /**
     * Command to add a todo.
     */
    public static final String COMMAND_ADD_TODO = "todo";
    /**
     * Command to add a deadline.
     */
    public static final String COMMAND_ADD_DEADLINE = "deadline";
    /**
     * Command to add an event.
     */
    public static final String COMMAND_ADD_EVENT = "event";

    /**
     * Format of input.
     */
    private static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HHmm";
    /**
     * Formatter object to format user datetime inputs.
     */
    public static final DateTimeFormatter DATE_TIME_INPUT_FORMATTER =
            DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);

    /**
     * Constructor for the Add Command class.
     *
     * @param params Parsed user input.
     */
    public AddCommand(ArrayList<String> params) throws InvalidCommandException {
        super(params);
        if (params.get(0).equals(COMMAND_ADD_TODO)) {
            if (params.size() != 2) {
                throw new InvalidCommandException("Add todo command format is wrong");
            }
        }
        if (params.get(0).equals(COMMAND_ADD_DEADLINE)) {
            if (params.size() != 3) {
                throw new InvalidCommandException("Add deadline command format is wrong");
            }
        }
        if (params.get(0).equals(COMMAND_ADD_EVENT)) {
            if (params.size() != 4) {
                throw new InvalidCommandException("Add event command format is wrong");
            }
        }
    }

    /**
     * Executes the command.
     *
     * @param tasks   List of tasks.
     * @param ui      UI of the application.
     * @param storage Object to handle data storage.
     * @return Message to be shown to the user.
     * @throws DukeException If error encountered when saving data.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws StorageException {
        switch (super.params.get(0)) {
        case AddCommand.COMMAND_ADD_TODO:
            ToDo newTodo = new ToDo(params.get(1));
            Task task = tasks.add(newTodo);
            tasks.saveState(storage);
            return ui.getTaskAddedMessage(task, tasks.getTaskCount());
        case AddCommand.COMMAND_ADD_DEADLINE:
            Deadline newDeadline = new Deadline(params.get(1),
                    LocalDateTime.parse(params.get(2), DATE_TIME_INPUT_FORMATTER));
            task = tasks.add(newDeadline);
            tasks.saveState(storage);
            return ui.getTaskAddedMessage(task, tasks.getTaskCount());
        default:
            Event newEvent = new Event(params.get(1),
                    LocalDateTime.parse(params.get(2), DATE_TIME_INPUT_FORMATTER),
                    LocalDateTime.parse(params.get(3), DATE_TIME_INPUT_FORMATTER));
            task = tasks.add(newEvent);
            tasks.saveState(storage);
            return ui.getTaskAddedMessage(task, tasks.getTaskCount());
        }
    }

    /**
     * Returns a boolean representing whether the command requires the application to exit.
     *
     * @return Boolean representing whether the command exits the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
