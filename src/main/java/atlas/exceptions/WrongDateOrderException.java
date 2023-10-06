package atlas.exceptions;

import java.time.LocalDate;

import atlas.components.Parser;

/**
 * Exception for dates in the wrong order (i.e. if Date 1 is supposed to come before Date 2, but
 * Date 1 has a later date than Date 2)
 */
public class WrongDateOrderException extends RuntimeException {
    private final LocalDate earlierDate;
    private final LocalDate laterDate;

    /**
     * Constructs a WrongDateOrderException (firstDate is supposed to be before secondDate, but the value
     * of firstDate is larger than the value of secondDate)
     * @param firstDate Date that is supposed to be earlier, but has a larger value
     * @param secondDate Date that is supposed to be later, but has a smaller value
     */
    public WrongDateOrderException(LocalDate firstDate, LocalDate secondDate) {
        earlierDate = secondDate;
        laterDate = firstDate;
    }

    @Override
    public String getMessage() {
        return "I hope you realise that " + laterDate.format(Parser.DATE_FORMATTER) + " does not come before "
                + earlierDate.format(Parser.DATE_FORMATTER);
    }
}
