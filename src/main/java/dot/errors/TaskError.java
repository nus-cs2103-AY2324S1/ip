package dot.errors;

/**
 * This contains the main logic of error handling.
 * Each enum value has an associated method and
 * error message. The method `printErrorMessage`
 * takes in an Exception as an argument, and
 * prints the exception message as a sub-error
 * message. As such we are able to combine Dot's
 * own error messages, and Java's exception messages,
 * in the case of a built-in runtime error.
 */
public enum TaskError {

    ERR_USING_MARK("..o.o..beep..Invalid use of mark, use: \"mark <task number>\""),
    ERR_USING_UNMARK("..o.o..beep..Invalid use of unmark, use: \"unmark <task number>\""),
    ERR_USING_TODO("..o.o..beep..Invalid use of todo, use: \"todo <description>\""),
    ERR_USING_DEADLINE("..o.o..beep..Invalid use of deadline, use: \"deadline <description> "
            + "/by <deadline_desc>\""),
    ERR_USING_EVENT("..o.o..beep..Invalid use of event, use: \"event <description>"
            + " /from <start_desc> /to <end_desc>\""),
    ERR_DELETING_TASK("..o.o..beep..Invalid use of delete, use: \"delete <task number>\""),
    ERR_READING_COMMAND("..o.o..beep..Command not found..beep.."),
    ERR_GETTING_FILE("..o.o..beep..Error getting file"),
    ERR_READING_FILE("..o.o..beep..Error reading file"),
    ERR_WRITING_FILE("..o.o..beep..Error writing to file"),
    ERR_USING_WHATSGOINGON("..o.o..beep..Error using command 'whatsgoingon', "
            + "use: \"whatsgoingon <dd/MM/yyyy>\""),
    ERR_USING_FIND("..o.o..beep..Error using command 'find', "
            + "use: \"find <query>\""),
    ERR_READING_FXML("..o.o..beep..There seems to be an error loading your messages."
            + " Restart the app is this persists, or file a bug report."),
    ERR_TASKLIST_FULL("..o.o..beep..Your task list has reached its limit. "
            + "Remove some tasks to continue."),
    ERR_INVALID_POSITION("..o.o..beep..Invalid position!"),
    ERR_NO_UNDO_FOUND("..o.o..beep..Nothing to undo."),
    ERR_ALREADY_MARKED("..o.o..beep..Task already marked."),
    ERR_ALREADY_UNMARKED("..o.o..beep..Task already unmarked.");


    private final String errorMessage;

    TaskError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Attaches the main errorMessage to the exception's sub-message,
     * and displays it to the user.
     *
     * @param e This is the DotException (or any other exception),
     *          which is passed along with the TaskError enum, to be
     *          handled.
     * @return Full error message.
     */
    public String getFullErrorMessage(Exception e) {
        if (e instanceof NumberFormatException) {
            return this.errorMessage + "\nConnecting the dots: An index number was not specified.";
        } else {
            return this.errorMessage + String.format("\nConnecting the dots: %s", e);
        }
    }

}
