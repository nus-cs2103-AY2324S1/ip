package exceptions;

public class EventDateException extends HachiException {
    public EventDateException(String missing) {
        super(String.format("☹ OOPS!!! Missing %s. Please input using this format: " +
                        "\"event <description> /from <start date> /to <end date>\"", missing));
    }
}