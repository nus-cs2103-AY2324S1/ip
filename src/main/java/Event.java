/**
 * Event encapsulates task that starts at a specific date / time and
 * ends at a specific date/time
 */
public class Event extends Task {
    private String taskName;
    private String startTime;
    private String endTime;

    private DateTime start;
    private DateTime end;

    /**
     * Constructor for creating a Event
     *
     * @param taskName name of task.
     */
    public Event(String taskName, DateTime start, DateTime end) {
        super(taskName);
        this.taskName = taskName;
        this.startTime = start.getDateTime();
        this.endTime = end.getDateTime();
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor for creating an Event based on whether its done or not
     * @param taskName  name of task.
     * @param isDone    whether the task is done or not
     * @param startTime the start time of the event
     * @param endTime   the end time of the event
     */
    public Event(String taskName, boolean isDone, DateTime start, DateTime end) {
        super(taskName);
        if (isDone) {
            super.quietlyCompleteTask();
        }
        this.taskName = taskName;
        this.startTime = start.getDateTime();
        this.endTime = end.getDateTime();
        this.start = start;
        this.end = end;
    }

    /**
     * taskValidator does nothing if there are no wrong inputs but throws a WrongInputException
     * if inputs are invalid
     * @param input the user's string input
     * @throws WrongInputTask which informs the user of the error and actions to take
     */
    public static void taskValidator(String input) throws WrongInputTask {
        String[] segmentedViaFrom = input.split(" /from ");
        if (segmentedViaFrom.length < 2) {
            throw new WrongInputTask("/from command is required",
                    "Use the /from command in 'event <event_name> /from <start> /to <end>'");
        }
        try {
            String taskNameEvent = segmentedViaFrom[0].substring(Duke.EVENTOFFSET);
        } catch (StringIndexOutOfBoundsException e) {
            throw new WrongInputTask("Task name cannot be blank", "Enter a non-blank name");
        }
        String taskNameEvent = segmentedViaFrom[0].substring(Duke.EVENTOFFSET);
        String[] segmentedViaTo = segmentedViaFrom[1].split(" /to ");
        if (segmentedViaTo.length < 2) {
            throw new WrongInputTask("/to command is required",
                    "Use the /to command in 'event <event_name> /from <start> /to <end>'");
        } else {
            String start = segmentedViaTo[0];
            String end = segmentedViaTo[1];
            if (taskNameEvent.trim().isEmpty()) {
                throw new WrongInputTask("Task name cannot be blank", "Enter a non-blank name");
            } else if (start.trim().isEmpty()) {
                throw new WrongInputTask("/from <content>, content cannot be blank",
                        "Enter non-blank text after /from ");

            } else if (end.trim().isEmpty()) {
                throw new WrongInputTask("/to <content>, content cannot be blank", "Enter text after /to ");
            } else if (!DateTimeParser.isValidDateTime(start)) {
                throw new WrongInputTask("Invalid date and time format for /from <datetime>",
                        DateTimeParser.getValidDateTimeFormat());
            } else if (!DateTimeParser.isValidDateTime(end)) {
                throw new WrongInputTask("Invalid date and time format for /to <datetime>",
                        DateTimeParser.getValidDateTimeFormat());
            }
        }
    }


    /**
     * Converts an Event task into a string that can be saved
     * @return  a string that can be saved
     */
    @Override
    public String convertToSaveFormat() {
        return "E" + Storage.FILESEPERATORCHARACTER + this.isDone() + Storage.FILESEPERATORCHARACTER + this.taskName
                + Storage.FILESEPERATORCHARACTER + this.end.toString() + Storage.FILESEPERATORCHARACTER
                + this.start.toString();
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] "
                + this.taskName + " (from: "
                + this.start.toString() + " to: " + this.end.toString() + ")";
    }
}
