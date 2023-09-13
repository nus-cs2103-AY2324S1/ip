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
     * @return A message indicating the result of adding the task.
     * @throws NoDescriptionException   If the task description is missing.
     * @throws UnknownTimeException     If the task time is not recognized.
     * @throws BackwardsTimeException   If an event's end time is before its start time.
     * @throws UnknownCommandException  If the command is not recognized.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NoDescriptionException,
            UnknownTimeException, BackwardsTimeException, UnknownCommandException {

        // Remove all words except the command
        String op = fullCommand.replaceAll("^\\W*\\b(\\w+).*", "$1").toUpperCase();

        Parser.Operations operation = Parser.Operations.valueOf(op);

        Task task;

        switch (operation) {
        case TODO:
            //Removes the command type from the entire command
            String desc = fullCommand.replaceAll("^\\s*todo\\s*", "");
            if (desc.equals("")) {
                throw new NoDescriptionException("todo");
            }

            task = new ToDo(desc);
            break;
        case DEADLINE:
            //Removes the command type from the entire command
            String deadlineTime = fullCommand.replaceAll("^\\s*deadline\\s*", "");

            // Separates into the description and the deadline
            String[] strings = deadlineTime.split(" /by ");

            if (deadlineTime.equals("")) {
                throw new NoDescriptionException("deadline");
            }
            if (strings.length == 1) {
                throw new UnknownTimeException(strings[0]);
            }

            task = new Deadline(strings[0],
                    LocalDateTime.parse(strings[1], formatter));
            break;
        case EVENT:
            //Removes the command type from the entire command
            String content = fullCommand.replaceAll("^\\s*event\\s*", "");
            if (content.equals("")) {
                throw new NoDescriptionException("event");
            }

            // Separates the command into the description and the times
            String[] descTime = content.split(" /from ");

            // Separates into the start time and end time
            String[] times = descTime[1].split(" /to ");

            if (times.length == 1) {
                throw new UnknownTimeException(descTime[0]);
            }

            assert times.length == 2 : "Incorrect timing format";

            LocalDateTime start = LocalDateTime.parse(times[0], formatter);
            LocalDateTime end = LocalDateTime.parse(times[1], formatter);

            if (start.isAfter(end)) {
                throw new BackwardsTimeException();
            }

            task = new Event(descTime[0], start, end);
            break;
        default:
            throw new UnknownCommandException(fullCommand);
        }

        tasks.add(task);
        assert tasks.retrieveForStorage() != null;
        storage.writeFile(tasks.retrieveForStorage());
        ui.showAddMessage(task, tasks.size());
        return "Added " + task;
    }
}
