package duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exceptions.BackwardsTimeException;
import duke.exceptions.NoDescriptionException;
import duke.exceptions.UnknownCommandException;
import duke.exceptions.UnknownTimeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tools.Parser;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * Represents a command to add tasks to the task list.
 */
public class AddCommand extends Command {

    private final DateTimeFormatter formatter;

    /**
     * Constructs an AddCommand with the specified command and date-time formatter.
     *
     * @param fullCommand The full command string.
     * @param formatter   The date-time formatter to parse date and time inputs.
     */
    public AddCommand(String fullCommand, DateTimeFormatter formatter) {
        super(fullCommand);
        this.formatter = formatter;
    }

    /**
     * Executes the add command, adding the appropriate task to the task list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler to manage data persistence.
     * @throws NoDescriptionException   If the task description is missing.
     * @throws UnknownTimeException     If the task time is not recognized.
     * @throws BackwardsTimeException   If an event's end time is before its start time.
     * @throws UnknownCommandException  If the command is not recognized.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoDescriptionException,
            UnknownTimeException, BackwardsTimeException, UnknownCommandException {
        Parser.Operations operation = Parser.Operations.valueOf(
                fullCommand.replaceAll("^\\W*\\b(\\w+).*", "$1").toUpperCase());
        Task task;

        switch (operation) {
        case TODO:
            String desc = fullCommand.replaceAll("^\\s*todo\\s*", "");
            if (desc.equals("")) {
                throw new NoDescriptionException("todo");
            }

            task = new ToDo(desc);
            tasks.add(task);
            ui.showAddMessage(task, tasks.size());
            break;
        case DEADLINE:
            String deadlineTime = fullCommand.replaceAll("^\\s*deadline\\s*", "");

            String[] strings = deadlineTime.split(" /by ");

            if (deadlineTime.equals("")) {
                throw new NoDescriptionException("deadline");
            }
            if (strings.length == 1) {
                throw new UnknownTimeException(strings[0]);
            }

            task = new Deadline(strings[0],
                    LocalDateTime.parse(strings[1], formatter));
            tasks.add(task);
            ui.showAddMessage(task, tasks.size());
            break;
        case EVENT:
            String content = fullCommand.replaceAll("^\\s*event\\s*", "");
            if (content.equals("")) {
                throw new NoDescriptionException("event");
            }

            String[] descTime = content.split(" /from ");
            String[] times = descTime[1].split(" /to ");

            if (times.length == 1) {
                throw new UnknownTimeException(descTime[0]);
            }

            LocalDateTime start = LocalDateTime.parse(times[0], formatter);
            LocalDateTime end = LocalDateTime.parse(times[1], formatter);

            if (start.isAfter(end)) {
                throw new BackwardsTimeException();
            }

            task = new Event(descTime[0], start, end);
            tasks.add(task);
            ui.showAddMessage(task, tasks.size());
            break;
        default:
            throw new UnknownCommandException(fullCommand);
        }
    }
}
