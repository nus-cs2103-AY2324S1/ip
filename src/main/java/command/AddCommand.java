package command;

import duke.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
    public static final DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);

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
     * @param tasks List of tasks.
     * @param ui UI of the application.
     * @param storage Object to handle data storage.
     * @throws DukeException If error encountered when saving data.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws StorageException {
        switch (super.params.get(0)) {
            case AddCommand.COMMAND_ADD_TODO:
                ToDo newTodo = new ToDo(params.get(1));
                Task task = tasks.add(newTodo);
                ui.printTaskAddedMessage(task, tasks.getTaskCount());
                tasks.saveState(storage);
                break;
            case AddCommand.COMMAND_ADD_DEADLINE:
                Deadline newDeadline = new Deadline(params.get(1),
                        LocalDateTime.parse(params.get(2), dateTimeInputFormatter));
                task = tasks.add(newDeadline);
                ui.printTaskAddedMessage(task, tasks.getTaskCount());
                tasks.saveState(storage);
                break;
            case AddCommand.COMMAND_ADD_EVENT:
                Event newEvent = new Event(params.get(1),
                        LocalDateTime.parse(params.get(2), dateTimeInputFormatter),
                        LocalDateTime.parse(params.get(3), dateTimeInputFormatter));
                task = tasks.add(newEvent);
                ui.printTaskAddedMessage(task, tasks.getTaskCount());
                tasks.saveState(storage);
                break;
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
