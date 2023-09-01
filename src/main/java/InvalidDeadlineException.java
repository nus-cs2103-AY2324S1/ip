public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException() {
        super("Oh no! It seems like you have indicated an invalid deadline :/ \n"
                + "Please follow this format:\n"
                + "deadline <task name> /by <yyyy-mm-dd> <HHmm>"
        );
    }
}
