package tasket.command;

import static tasket.commons.Messages.MESSAGE_EMPTY_DEADLINE;
import static tasket.commons.Messages.MESSAGE_EMPTY_DESC;
import static tasket.commons.Messages.MESSAGE_EMPTY_END;
import static tasket.commons.Messages.MESSAGE_EMPTY_START;
import static tasket.commons.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.storage.StorageInterface;
import tasket.task.Deadline;
import tasket.task.Event;
import tasket.task.Task;
import tasket.task.ToDo;
import tasket.ui.Ui;

/**
 * The class for add command.
 */
public class AddCommand extends Command {

    private final String header;

    /**
     * Constructs an add command.
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
    public String execute(TaskList taskList, Ui ui, StorageInterface storage) throws TasketException {
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
            throw new TasketException(MESSAGE_UNKNOWN_COMMAND);
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
        // To separate task elements and tags.
        String[] taskElements = prompt.split("#", 2);
        boolean hasTag = taskElements.length == 2;

        if (prompt.isEmpty() || taskElements[0].isEmpty()) {
            throw new TasketException(String.format(MESSAGE_EMPTY_DESC, "todo"));
        }

        if (hasTag) {
            String[] tags = trimTags(taskElements[1]);

            return new ToDo(taskElements[0].trim(), tags);
        }

        return new ToDo(taskElements[0].trim());
    }

    /**
     * Creates a deadline task.
     *
     * @param prompt The arguments of the task.
     * @return A deadline task instance.
     * @throws TasketException If the description or deadline is empty.
     */
    private Task createDeadlineTask(String prompt) throws TasketException {
        // To separate task elements and tags.
        String[] taskElements = prompt.split("#", 2);
        boolean hasTag = taskElements.length == 2;

        if (prompt.isEmpty() || taskElements[0].isEmpty()) {
            throw new TasketException(String.format(MESSAGE_EMPTY_DESC, "deadline"));
        }

        String[] arguments = taskElements[0].replaceAll("/by", "|").split(" \\| ");
        if (arguments.length < 2) {
            throw new TasketException(MESSAGE_EMPTY_DEADLINE);
        }

        if (hasTag) {
            String[] tags = trimTags(taskElements[1]);

            return new Deadline(arguments[0].trim(), convertToDate(arguments[1].trim()), tags);
        }

        return new Deadline(arguments[0].trim(), convertToDate(arguments[1].trim()));
    }

    /**
     * Creates an event task.
     *
     * @param prompt The arguments of the task.
     * @return An event task instance.
     * @throws TasketException If description, start or end date is empty.
     */
    private Task createEventTask(String prompt) throws TasketException {
        // To separate task elements and tags.
        String[] taskElements = prompt.split("#", 2);
        boolean hasTag = taskElements.length == 2;

        if (prompt.isEmpty() || taskElements[0].isEmpty()) {
            throw new TasketException(String.format(MESSAGE_EMPTY_DESC, "event"));
        }

        String[] arguments = taskElements[0].replaceAll("/from", "|")
                .replaceAll("/to", "|")
                .split(" \\| ");

        if (arguments.length < 2) {
            throw new TasketException(MESSAGE_EMPTY_START);
        } else if (arguments.length < 3) {
            throw new TasketException(MESSAGE_EMPTY_END);
        }

        if (hasTag) {
            String[] tags = trimTags(taskElements[1]);

            return new Event(arguments[0].trim(), convertToDate(arguments[1].trim()),
                    convertToDate(arguments[2].trim()), tags);
        }

        return new Event(arguments[0].trim(), convertToDate(arguments[1].trim()),
                convertToDate(arguments[2].trim()));
    }

    /**
     * Converts the deadline and event dates.
     * If the format fits, convert it, if not, return the original text.
     *
     * @param deadline The argument to be converted.
     * @return The converted date format or original text if unable to parse.
     */
    private String convertToDate(String deadline) {
        try {
            LocalDate date = LocalDate.parse(deadline);
            return date.format(DateTimeFormatter.ofPattern("yyyy MMM d"));
        } catch (DateTimeParseException exception) {
            return deadline;
        }
    }

    /**
     * Removes whitespaces on both sides of tags.
     *
     * @param prompt The prompt of tags.
     * @return An array of tags.
     */
    private String[] trimTags(String prompt) {
        return Arrays.stream(prompt.split("#"))
                .map(String::trim)
                .toArray(String[]::new);
    }
}
