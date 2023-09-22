package atlas.exceptions;

import java.time.LocalDateTime;

import atlas.components.Parser;

/**
 * Exception for datetimes in the wrong order (i.e. if DateTime 1 is supposed to come before DateTime 2, but
 * DateTime 1 has a later date/time than DateTime 2)
 */
public class WrongDateTimeOrderException extends RuntimeException {
    private final LocalDateTime earlierDateTime;
    private final LocalDateTime laterDateTime;

    /**
     * Constructs a WrongDateTimeOrderException (firstDateTime is supposed to be before secondDateTime, but the value
     * of firstDateTime is larger than the value of secondDateTime)
     * @param firstDateTime Datetime that is supposed to be earlier, but has a larger value
     * @param secondDateTime Datetime that is supposed to be later, but has a smaller value
     */
    public WrongDateTimeOrderException(LocalDateTime firstDateTime, LocalDateTime secondDateTime) {
        earlierDateTime = secondDateTime;
        laterDateTime = firstDateTime;
    }

    @Override
    public String getMessage() {
        return "I hope you realise that " + laterDateTime.format(Parser.DATETIME_FORMATTER) + " does not come before "
                + earlierDateTime.format(Parser.DATETIME_FORMATTER);
    }
}
