package sana;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {

    // difference between the '/' index and the start index of due date
    private static final int BY_AND_DATE_ID_DIFFERENCE = 4;

    /**
     * Constructs an AddDeadlineCommand object.
     *
     * @param command   The command keyword.
     * @param arguments The arguments provided with the command.
     */
    public AddDeadlineCommand(String command, String arguments) {
        super(command, arguments);
    }

    /**
     * Executes the AddDeadlineCommand.
     *
     * @param tasks   The list of tasks.
     * @param storage The storage manager.
     * @return A response message after executing the command.
     * @throws SanaException If there's an error executing the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws SanaException {
        String arguments = getArguments();

        if (arguments.isBlank()) {
            throw new SanaException("OOPS!!! Incomplete task description.\nMake sure you follow the format "
                    + "'deadline [name of task] /by [deadline]'");
        }

        try {
            Task newDeadline = getDeadline(arguments);

            tasks.add(newDeadline);
            storage.save(newDeadline);

            return String.format("Got it. I've added this task:\n%s\nNow you have %d %s in the list\n",
                    newDeadline, tasks.size(), (tasks.size() == 1 ? "task" : "tasks"));
        } catch (DateTimeParseException e) {
            return "Invalid date format. Please use YYYY-MM-DD";
        }
    }

    /**
     * Retrieves a deadline task from the provided arguments.
     *
     * @param arguments The arguments provided with the command.
     * @return A deadline task.
     * @throws SanaException If there's an error retrieving the deadline task.
     */
    private static Task getDeadline(String arguments) throws SanaException {
        int descriptionEndIndex = arguments.indexOf('/');
        int dueDateStartIndex = descriptionEndIndex + BY_AND_DATE_ID_DIFFERENCE;

        boolean isDescriptionAbsent = descriptionEndIndex == -1 || arguments.length() < dueDateStartIndex;
        boolean isDeadlineEmpty = isDescriptionAbsent || arguments.substring(dueDateStartIndex).isBlank();
        if (isDeadlineEmpty) {
            throw new SanaException("OOPS!! The deadline cannot be empty.\nMake sure you follow the format "
                    + "'deadline [name of task] /by [deadline]'");
        }

        String description = arguments.substring(0, descriptionEndIndex - 1);
        LocalDate dueDate = LocalDate.parse(arguments.substring(dueDateStartIndex));

        return new Deadline(description, dueDate, false);
    }

    /**
     * Checks if this command is an exit command.
     *
     * @return Always returns false, as this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
