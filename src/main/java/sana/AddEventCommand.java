package sana;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add an event task.
 */
public class AddEventCommand extends Command {

    // difference between the indices of '/' and the start of from date id
    private static final int FROM_AND_DATE_ID_DIFFERENCE = 6;

    // difference between the indices of '/' of to and the start of the id of to date
    private static final int TO_AND_DATE_ID_DIFFERENCE = 4;

    /**
     * Constructs an AddEventCommand object.
     *
     * @param command   The command keyword.
     * @param arguments The arguments provided with the command.
     */
    public AddEventCommand(String command, String arguments) {
        super(command, arguments);
    }

    /**
     * Executes the AddEventCommand.
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
            throw new SanaException("OOPS!!! Incomplete command. Make sure you follow the format "
                    + "'event [name of task] /from [from] /to [to]'");
        }

        try {
            Task newEvent = getEvent(arguments);
            tasks.add(newEvent);
            storage.save(newEvent);

            return String.format("Got it. I've added this task:\n%s\nNow you have %d %s in the list\n",
                    newEvent,
                    tasks.size(),
                    tasks.size() <= 1 ? "task" : "tasks");
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return "Invalid date format! Please use YYYY-MM-DD";
        }
    }

    /**
     * Retrieves an event task from the provided arguments.
     *
     * @param arguments The arguments provided with the command.
     * @return An event task.
     * @throws SanaException If there's an error retrieving the event task.
     */
    private static Task getEvent(String arguments) throws SanaException {
        int descriptionEndIndex = arguments.indexOf('/');
        int fromDateStartIndex = descriptionEndIndex + FROM_AND_DATE_ID_DIFFERENCE;
        int fromDateEndIndex = arguments.indexOf('/', descriptionEndIndex + 1);
        int toDateStartIndex = fromDateEndIndex + TO_AND_DATE_ID_DIFFERENCE;

        boolean isFromDateFormatIncorrect = descriptionEndIndex == -1 || arguments.length() < fromDateStartIndex;
        boolean isFromDateAbsent = isFromDateFormatIncorrect || arguments.substring(fromDateStartIndex).isBlank();

        if (isFromDateAbsent) {
            throw new SanaException("OOPS!! The 'from' field cannot be empty.\nMake sure you follow the format "
                    + "'event [name of task] /from [from] /to [to]'");
        }

        boolean isToDateFormatIncorrect = fromDateEndIndex == -1 || arguments.length() < toDateStartIndex;
        boolean isToDateAbsent = isToDateFormatIncorrect || arguments.substring(toDateStartIndex).isBlank();

        if (isToDateAbsent) {
            throw new SanaException("OOPS!! The 'to' field cannot be empty.\nMake sure you follow the format "
                    + "'event [name of task] /from [from] /to [to]'");
        }

        String description = arguments.substring(0, descriptionEndIndex - 1);
        LocalDate fromDate = LocalDate.parse(arguments.substring(fromDateStartIndex, fromDateEndIndex - 1));
        LocalDate toDate = LocalDate.parse(arguments.substring(toDateStartIndex));

        Task newEvent = new Event(description, fromDate, toDate, false);
        return newEvent;
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
