package duke.parser.element.argument;

import duke.exception.InvalidDateException;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

/**
 * Input must be a date.
 */
public class DateArgument extends Argument {

    /**
     * Constructor for DateArgument.
     * 
     * @param text The name of the input field.
     */
    public DateArgument(String text) {
        super(text);
    }

    /**
     * @inheritdoc
     */
    @Override
    public LocalDate formatInput(String input) throws InvalidDateException {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    /**
     * @inheritdoc
     */
    @Override
    public String formatOutput(Object val) {
        return val.toString();
    }
    
}
