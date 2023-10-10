package duke.utils;

import duke.utils.Command.Type;

/**
 * The InvalidArgumentException class represents an exception that is thrown when
 * an invalid argument is provided in a user command in the Duke application.
 * It extends the DukeException class and includes a custom error message.
 */
public class InvalidArgumentException extends DukeException {
    /**
     * Constructs a new InvalidArgumentException with a custom error message.
     *
     * @param arg  The name of the argument.
     * @param type The expected type of the argument.
     */
    protected InvalidArgumentException(String arg, Type type) {
        super(String.format(
            "I'm sorry, but you have keyed in an invalid argument for the argType /%s. Try again with /%s [%s]",
            arg,
            arg,
            type
          ));
    }
}
