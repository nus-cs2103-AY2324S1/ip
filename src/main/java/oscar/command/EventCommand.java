package oscar.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import oscar.essential.Storage;
import oscar.essential.TaskList;
import oscar.exception.OscarException;
import oscar.task.Event;
import oscar.task.Task;

/**
 * Command to create a new event task.
 */
public class EventCommand extends Command {
    private final String details;

    /**
     * Instantiates an event command.
     *
     * @param details Description of event task.
     */
    public EventCommand(String details) {
        this.details = details;
    }

    /**
     * Creates a new event task and save it to the task list.
     *
     * @param tasks   ArrayList of tasks.
     * @param storage File loading and saving handler.
     * @return String output of event task.
     * @throws OscarException Incorrect format of event command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws OscarException {
        assert tasks != null;
        assert storage != null;


        String[] validatedDetails = validateString();
        validateDate(validatedDetails);
        String description = validatedDetails[0];
        String start = validatedDetails[1];
        String end = validatedDetails[2];
        LocalDateTime startDateTime = LocalDateTime.parse(start, DATE_TIME_FORMAT);
        LocalDateTime endDateTime = LocalDateTime.parse(end, DATE_TIME_FORMAT);

        Task newEvent = new Event(description, startDateTime, endDateTime);
        tasks.add(newEvent);
        storage.save(tasks);
        return "Oscar has added:\n" + newEvent + "\n" + tasks.listCount();
    }

    /**
     * Validates date and time of event task.
     * Format: event [task] /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm.
     *
     * @throws OscarException Invalid date and time entered.
     */
    public void validateDate(String[] validStringArray) throws OscarException {
        try {
            String start = validStringArray[1];
            String end = validStringArray[2];
            LocalDateTime startDateTime = LocalDateTime.parse(start, DATE_TIME_FORMAT);
            LocalDateTime endDateTime = LocalDateTime.parse(end, DATE_TIME_FORMAT);
            if (endDateTime.isBefore(startDateTime)) {
                throw new OscarException("Sorry! End date and time must be after start date and time.\n");
            }
        } catch (DateTimeParseException e) {
            throw new OscarException("Sorry! Please enter a valid date and time in the format 'yyyy-MM-dd HHmm'.\n");
        }
    }

    /**
     * Validates details of event task.
     * Format: event [task] /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm.
     *
     * @return String array containing description, start and end date and time of event task.
     * @throws OscarException Incorrect format of event command.
     */
    public String[] validateString() throws OscarException {
        if (!details.contains(" /from ") || !details.contains(" /to ")) {
            throw new OscarException("Sorry! The event task is not formatted correctly.\n"
                    + "Please use the format: 'event [task] /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm'.\n");
        }
        String[] splitDetails = details.split(" /from | /to ");
        if (splitDetails.length != 3) {
            throw new OscarException("Sorry! The event task is not formatted correctly.\n"
                    + "Please use the format: 'event [task] /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm'.\n");
        }
        String description = splitDetails[0];
        if (description.isEmpty()) {
            throw new OscarException("Sorry! The description of an event task cannot be empty.\n");
        }
        String start = splitDetails[1];
        if (start.isEmpty()) {
            throw new OscarException("Sorry! The start date and time of an event task cannot be empty.\n");
        }
        String end = splitDetails[2];
        if (!start.contains(" ") || !end.contains(" ")) {
            throw new OscarException("Sorry! Please enter a valid date and time in this format: '2019-10-15 1800'.\n");
        }
        return new String[]{description, start, end};
    }
}
