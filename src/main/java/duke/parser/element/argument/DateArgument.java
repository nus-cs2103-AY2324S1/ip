package duke.parser.element.argument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidDateException;

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
