package duke;

import java.util.*;
import java.lang.Exception;

/**
 * A standard Exception class that throws an Exception with a custom message.
 */
public class DukeException extends Exception {

    /**
     * Creates a DukeException Throwable Object with the given message.
     *
     * @param message The message to be shown when the Exception is thrown.
     */
    DukeException(String message) {
        super(message);
    }

}
