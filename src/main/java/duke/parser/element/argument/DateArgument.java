package duke.parser.element.argument;

import duke.exception.InvalidDateException;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class DateArgument extends Argument {

    public DateArgument(String text) {
        super(text);
    }

    @Override
    public LocalDate formatInput(String input) throws InvalidDateException {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    @Override
    public String formatOutput(Object val) {
        return val.toString();
    }
    
}