package duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * Represents a command to check tasks scheduled on a specific date and time.
 */
public class CheckCommand extends Command {

    private final DateTimeFormatter formatter;

    /**
     * Constructs a CheckCommand with the specified command and date-time formatter.
     *
     * @param fullCommand The full command string, including the "check" keyword and date-time.
     * @param formatter   The date-time formatter to parse date and time inputs.
     */
    public CheckCommand(String fullCommand, DateTimeFormatter formatter) {
        super(fullCommand);
        this.formatter = formatter;
    }

    /**
     * Executes the check command, displaying tasks scheduled on the specified date and time.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for saving and loading tasks.
     * @return A message indicating the tasks scheduled on the specified date and time.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String content = fullCommand.replaceAll("^\\s*check\\s*", "");
        LocalDateTime dateTime = LocalDateTime.parse(content, formatter);
        return ui.printScheduledTasks(tasks, dateTime);
    }
}
