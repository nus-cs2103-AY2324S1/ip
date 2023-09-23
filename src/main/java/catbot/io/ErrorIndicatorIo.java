package catbot.io;

import catbot.internal.Bounds;
import catbot.internal.NamedParameterMap;

/**
 * An object that supports interacting with the user to communicate error states.
 */
public interface ErrorIndicatorIo {

    /**
     * Tells the user that the command they provided is not supported.
     *
     * @param attemptedCommand command that was attempted.
     */
    void indicateInvalidCommand(String attemptedCommand);

    /**
     * Tells the user that the string they provided is not an integer.
     * Usually intended to also inform the user that an integer was expected instead.
     *
     * @param attemptedInteger string that could not be parsed as an integer.
     */
    void indicateInvalidInteger(String attemptedInteger);

    /**
     * Tells the user that the integer they provided could not be used as an index.
     * Possibly also informs the user of the range of possible indices.
     *
     * @param attemptedIndex integer that was provided, that does not fall within expected bounds.
     * @param bounds         {@link Bounds Bounds} object signifying the minimum and maximum accepted indices.
     */
    void indicateInvalidIndex(int attemptedIndex, Bounds bounds);

    /**
     * Enum that identifies reason for the invalidity of arguments.
     * Used as a default option when parameter-specific information is not required.
     *
     * @see ErrorIndicatorIo#indicateInvalidIndex example of invalid argument with parameter-specific information
     */
    enum InvalidArgumentState {
        PARAMETER_EMPTY, PARAMETER_MISSING, NOT_A_DATE
    }

    /**
     * Tells the user that the argument they provided is invalid.
     * Handles all {@link InvalidArgumentState InvalidArgumentStates}.
     *
     * @param invalidState {@link InvalidArgumentState InvalidArgumentState} describing the reason for invalidity.
     * @param namedParameterMap map with all invalid parameters as keys, and their respective arguments as values.
     */
    void indicateArgumentInvalid(InvalidArgumentState invalidState, NamedParameterMap namedParameterMap);

}
