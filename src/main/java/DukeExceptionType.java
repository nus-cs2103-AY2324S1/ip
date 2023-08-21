public enum DukeExceptionType {
    DEADLINE_NO_DESCRIPTION(
"The description of a deadline cannot be empty.\n" +
        "Correct usage: deadline <description> /by <date>"
    ),
    EVENT_NO_DESCRIPTION(
"The description of an event cannot be empty.\n" +
        "Correct usage: event <description> /from <start> /to <end>"
    ),
    TODO_NO_DESCRIPTION(
"The description of a todo cannot be empty.\n" +
        "Correct usage: todo <description>"
    ),
    INVALID_DEADLINE_FORMAT(
"Invalid deadline format!\n" +
        "Correct usage: deadline <description> /by <date>"
    ),
    INVALID_EVENT_FORMAT(
"Invalid event format!\n" +
        "Correct usage: event <description> /from <start> /to <end>"
    ),
    INVALID_RANGE("The task number you've entered is out of the valid range."),
    TASK_ALREADY_MARKED("The task number you've entered is already marked as complete."),
    TASK_ALREADY_UNMARKED("The task number you've entered is already unmarked."),
    UNKNOWN_COMMAND("I'm sorry, I don't know what that means :-(");

    private final String message;

    DukeExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
