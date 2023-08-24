public class InvalidTaskNumberException extends DukeException {
    public InvalidTaskNumberException(int number) {
        super("____________________________________________________________\n" +
                " ☹ OOPS!!! " + number + " is not a valid task number.\n" +
                "____________________________________________________________");
    }
}
