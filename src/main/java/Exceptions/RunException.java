package Exceptions;

public class RunException extends Exception{
    public RunException(String input) {
        super("____________________________________________________________\n" +
                input +
                "____________________________________________________________\n");
    }
}
