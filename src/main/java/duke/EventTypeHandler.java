package duke;

/**
 * Class to determine EventTypeHandler
 */
public class EventTypeHandler {
    private TaskList tasks;
    private String extractedSubstring;
    private String isDone;

    /**
     * Constructor for eventHandler
     * @param tasks TaskList
     * @param extractedSubstring Substring to determine which type of event
     * @param isDone whether the event is marked as done or not
     */
    public EventTypeHandler(TaskList tasks, String extractedSubstring, String isDone) {
        this.tasks = tasks;
        this.extractedSubstring = extractedSubstring;
        this.isDone = isDone;
    }

    /**
     * To handle the different type of tasks (T,D,E)
     * @param eventType
     * @throws EmptyDescriptionException
     */
    public void handleEventType(String eventType) throws EmptyDescriptionException {
        switch (eventType) {
        case "T": {
            tasks.toDoHandler(extractedSubstring, isDone.equals("X"), true);
            break;
        }
        case "D": {
            //System.out.println(extractedSubstring);
            String description = Parser.convertDeadlineFormat(extractedSubstring);
            tasks.deadlineHandler(description, isDone.equals("X"), true);
            break;
        }
        case "E": {
            String description = Parser.convertEventFormat(extractedSubstring);
            tasks.eventHandler(description, isDone.equals("X"), true);
            break;
        }
        default: {
            break;
        }
        }
    }
}
