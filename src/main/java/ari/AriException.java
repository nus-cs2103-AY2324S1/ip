package ari;

import java.lang.Exception;

/**
 * DukeException class that throws Exception if the input is not formatted correctly. Inherits Exception class.
 */
public class AriException extends Exception{
    public AriException(String msg) {
        super(msg);
    }
}
