package exceptions;

public class InvalidDateTimeFormat extends ThorndikeException{
    public InvalidDateTimeFormat() {
        super("The given format is wrong!");
    }

}
