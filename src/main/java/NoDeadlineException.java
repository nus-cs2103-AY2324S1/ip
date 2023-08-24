public class NoDeadlineException extends DukeException {
    public NoDeadlineException() {
        super("____________________________________________________________\n" +
                " ☹ OOPS!!! There is no deadline given.\n" +
                "____________________________________________________________");
    }
}
