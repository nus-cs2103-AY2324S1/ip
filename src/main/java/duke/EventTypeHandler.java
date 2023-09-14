package duke;

public class EventTypeHandler {
    private TaskList tasks;
    private String extractedSubstring;
    private String isDone;
    public EventTypeHandler(TaskList tasks, String extractedSubstring, String isDone) {
        this.tasks = tasks;
        this.extractedSubstring = extractedSubstring;
        this.isDone = isDone;
    }

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
