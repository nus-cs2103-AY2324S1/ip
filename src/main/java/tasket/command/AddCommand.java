package tasket.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.storage.Storage;
import tasket.task.Deadline;
import tasket.task.Event;
import tasket.task.Task;
import tasket.task.ToDo;
import tasket.ui.Ui;

/**
 * The class for add command.
 */
public class AddCommand extends Command {

    private String header;

    /**
     * The constructor for add command.
     *
     * @param header The command itself.
     * @param command The arguments of the command.
     */
    public AddCommand(String header, String command) {
        super(command);
        this.header = header;
    }

    /**
     * Crates and adds a task to the task list.
     *
     * @param taskList The task list instance of duke.
     * @param ui The ui instance of duke.
     * @param storage The storage instance of duke.
     * @return The description of the added task.
     * @throws TasketException If error occurred while creating task.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TasketException {
        Task task = null;
        switch (this.header) {
        case "todo":
            task = createToDoTask(commandDescription.replaceFirst("todo", "").trim());
            break;

        case "deadline":
            task = createDeadlineTask(commandDescription.replaceFirst("deadline", "").trim());
            break;

        case "event":
            task = createEventTask(commandDescription.replaceFirst("event", "").trim());
            break;

        default:
            throw new TasketException("I'm sorry, but I don't know what it means :(");
        }

        taskList.add(task);
        storage.append(task.toSaveString());

        return ui.showAddedTask(task.toString(), taskList.size());
    }

    /**
     * Creates a todo task.
     *
     * @param prompt The description of the task.
     * @return A todo task instance.
     * @throws TasketException If the description is empty.
     */
    private Task createToDoTask(String prompt) throws TasketException {
        if (prompt.isEmpty()) {
            throw new TasketException("The description of todo cannot be empty");
        }

        return new ToDo(prompt.trim());
    }

    /**
     * Creates a deadline task.
     *
     * @param prompt The arguments of the task.
     * @return A deadline task instance.
     * @throws TasketException If the description or deadline is empty.
     */
    private Task createDeadlineTask(String prompt) throws TasketException {
        if (prompt.isEmpty()) {
            throw new TasketException("The description of deadline cannot be empty");
        }

        String[] arguments = prompt.replaceAll("/by", "|").split(" \\| ");
        if (arguments.length < 2) {
            throw new TasketException("The deadline cannot be empty");
        }

        return new Deadline(arguments[0].trim(), convertToDate(arguments[1].trim()));
    }

    /**
     * Creates an event instance
     *
     * @param prompt The arguments of the task.
     * @return An event task instance.
     * @throws TasketException If description, start or end date is empty.
     */
    private Task createEventTask(String prompt) throws TasketException {
        if (prompt.isEmpty()) {
            throw new TasketException("The description of event cannot be empty");
        }

        String[] arguments = prompt.replaceAll("/from", "|").replaceAll("/to", "|")
                .split(" \\| ");
        if (arguments.length < 2) {
            throw new TasketException("The start time cannot be empty");
        } else if (arguments.length < 3) {
            throw new TasketException("The end time cannot be empty");
        }

        return new Event(arguments[0].trim(), convertToDate(arguments[1].trim()), convertToDate(arguments[2].trim()));
    }

    /**
     * Convert the deadline and event dates.
     * If the format fits, convert it, if not, re
     *
     * @param deadline The argument to be converted.
     * @return The converted date format or original form if unable to parse.
     */
    private String convertToDate(String deadline) {
        try {
            LocalDate date = LocalDate.parse(deadline);
            return date.format(DateTimeFormatter.ofPattern("yyyy MMM d"));
        } catch (DateTimeParseException exception) {
            return deadline;
        }
    }
}
