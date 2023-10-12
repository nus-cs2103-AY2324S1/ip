package duke.commands;

import java.time.format.DateTimeFormatter;

import duke.exceptions.BackwardsTimeException;
import duke.exceptions.NoDescriptionException;
import duke.exceptions.UnknownCommandException;
import duke.exceptions.UnknownTimeException;
import duke.tasks.Task;
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
            task = Task.createToDo(fullCommand);
            break;
        case DEADLINE:
            task = Task.createDeadline(fullCommand, formatter);
            break;
        case EVENT:
            task = Task.createEvent(fullCommand, formatter);
            break;
        default:
            throw new UnknownCommandException(fullCommand);
        }

        tasks.add(task);
        assert tasks.retrieveForStorage() != null;
        storage.writeFile(tasks.retrieveForStorage());
        return ui.generateAddMessage(task, tasks.size());
    }




}
