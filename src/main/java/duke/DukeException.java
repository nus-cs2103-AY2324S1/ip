package duke;

import java.lang.Exception;

/**
 * DukeException class that throws Exception if the input is not formatted correctly. Inherits Exception class.
 */
public class DukeException extends Exception{
    public DukeException(String msg) {
        super(msg);
    }
}
