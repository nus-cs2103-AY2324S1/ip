package bob.exception;

public class InvalidPriorityException extends BobException {
    public String message = "Please input valid priority.\n"
            + "For example:\n"
            + "todo p/high read\n"
            + "deadline p/mid read /by 2022/01/01\n"
            + "event p/low read /from 2022/01/01 /to 2022/02/02";

}
