public class InvalidDateTimeException extends Exception {
    public InvalidDateTimeException() {
        super("☹ OOPS!!! I'm sorry, but the input of date and time is invalid. Please follow the format of yyyy-MM-dd or yyyy-MM-dd HHmm :-(");
    }
}
