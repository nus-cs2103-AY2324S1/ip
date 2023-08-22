/**
 * Event encapsulates task that starts at a specific date / time and
 * ends at a specific date/time
 */
public class Event extends Task {
    private String taskName;
    private String startTime;
    private String endTime;

    /**
     * Constructor for creating a task
     *
     * @param taskName name of task.
     */
    public Event(String taskName, String startTime, String endTime) {
        super(taskName);
        this.taskName = taskName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Prints out a message that a Event has been added
     */
    @Override
    public void taskAdded(int noOfTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this.toString());
        System.out.println("Now you have " + noOfTask + " tasks in the list.");
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
            }
        }
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] "
                + this.taskName + " (from: "
                + this.startTime + " to: " + this.endTime + ")";
    }
}
