import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Command to create a new event task.
 */
public class EventCommand extends Command {
    private final String details;

    /**
     * Instantiates an event command.
     * @param details Description of event task.
     */
    public EventCommand(String details) {
        super(false);
        this.details = details;
    }

    /**
     * Creates a new event task and save it to the task list.
     * @param tasks ArrayList of tasks.
     * @param ui User interaction handler.
     * @param storage File loading and saving handler.
     * @throws OscarException Incorrect format of event command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OscarException {
        String[] validatedDetails = validate(details);
        String description = validatedDetails[0];
        String start = validatedDetails[1];
        String end = validatedDetails[2];
        LocalDateTime startDateTime = LocalDateTime.parse(start, DTFORMAT);
        LocalDateTime endDateTime = LocalDateTime.parse(end, DTFORMAT);
        Task newEvent = new Event(description, startDateTime, endDateTime);
        tasks.add(newEvent);
        storage.save(tasks);
        System.out.println("Oscar has added:\n" + newEvent + "\n");
        tasks.listCount();
    }

    /**
     * Validates details of event task.
     * Format: event [task] /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm
     * @param details Information about the details, as well as start and end
     *                date/time of task.
     * @throws OscarException Incorrect format of event command.
     */
    public String[] validate(String details) throws OscarException {
        if (!details.contains(" /from ") || !details.contains(" /to ")) {
            throw new OscarException("Sorry! " +
                    "The event task is not formatted correctly.\n" +
                    "Please use the format: 'event [task] /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm'.\n");
        }
        String[] split = details.split(" /from | /to ");
        String description = split[0];
        if (description.isEmpty()) {
            throw new OscarException("Sorry! " +
                    "The description of an event task cannot be empty.\n");
        }
        String start = split[1];
        if (start.isEmpty()) {
            throw new OscarException("Sorry! " +
                    "The start date and time of an event task cannot be empty.\n");
        }
        String end = split[2];
        if (end.isEmpty()) {
            throw new OscarException("Sorry! " +
                    "The end date and time of an event task cannot be empty.\n");
        }
        if (!start.contains(" ") || !end.contains(" ")) {
            throw new OscarException("Sorry! Please enter a valid date and time in this format: '2019-10-15 1800'.\n");
        }
        try {
            LocalDateTime startDateTime = LocalDateTime.parse(start, DTFORMAT);
            LocalDateTime endDateTime = LocalDateTime.parse(end, DTFORMAT);
            if (endDateTime.isBefore(startDateTime)) {
                throw new OscarException("Sorry! End date and time must be after start date and time.\n");
            }

        } catch (DateTimeParseException e) {
            throw new OscarException("Sorry! Please enter a valid date and time in the format 'yyyy-MM-dd HHmm'.\n");
        }
        return new String[]{description, start, end};
    }

}
