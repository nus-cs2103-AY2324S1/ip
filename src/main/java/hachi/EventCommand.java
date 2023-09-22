package hachi;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import hachi.exceptions.DateFormatWrongException;
import hachi.exceptions.EmptyTaskException;
import hachi.exceptions.EventDateException;
import hachi.exceptions.HachiException;

/**
 * Represents the "event" command.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    private TaskList taskList;

    private Storage storage;

    /**
     * Constructor method for the EventCommand class.
     * @param arguments The arguments passed in by the user.
     * @param taskList The task list passed in by the user.
     * @param storage The storage used by the app.
     */
    public EventCommand(String[] arguments, TaskList taskList, Storage storage) {
        super(arguments);
        this.taskList = taskList;
        this.storage = storage;
    }

    @Override
    public String execute() throws HachiException {
        checkArgumentLength(getArguments().length);
        String[] arguments = getArguments();
        int fromIndex = Parser.getWordIndex("/from", arguments);
        int toIndex = Parser.getWordIndex("/to", arguments);

        // catching event date exceptions
        if (fromIndex == -1 && toIndex == -1) {
            throw new EventDateException("/from and /to");
        } else if (toIndex == -1) {
            throw new EventDateException("/to");
        } else if (fromIndex == -1) {
            throw new EventDateException("/from");
        }

        String eventTask = String.join(" ",
                Arrays.copyOfRange(arguments, 0, fromIndex));
        String eventStartDate = String.join(" ",
                Arrays.copyOfRange(arguments, fromIndex + 1, toIndex));
        String eventEndDate = String.join(" ",
                Arrays.copyOfRange(arguments, toIndex + 1, arguments.length));

        // catching more specific exceptions regarding the input content
        if (eventTask.equals("")) {
            throw new EmptyTaskException("event");
        } else if (eventStartDate.equals("") && eventEndDate.equals("")) {
            throw new EventDateException("start date and end date");
        } else if (eventEndDate.equals("")) {
            throw new EventDateException("end date");
        } else if (eventStartDate.equals("")) {
            throw new EventDateException("start date");
        }

        try {
            Event ev = new Event(eventTask, LocalDate.parse(eventStartDate),
                    LocalDate.parse(eventEndDate));
            taskList.add(ev);
            storage.updateTaskFile(taskList);
            return "Got it. I've added this task:\n   " + ev
                    + String.format("\nNow you have %d tasks in the list.", taskList.size());
        } catch (DateTimeParseException e) {
            throw new DateFormatWrongException(eventStartDate + ", " + eventEndDate);
        }
    }

    @Override
    protected void checkArgumentLength(int argumentLength) throws HachiException {
        if (argumentLength < 1) {
            throw new EmptyTaskException(EventCommand.COMMAND_WORD);
        }
    }
}
