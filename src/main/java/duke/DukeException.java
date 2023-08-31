package duke;

import java.lang.Exception;

/**
 * DukeException class that throws Exception if the input is not formatted correctly. Inherits Exception class.
 */
public class DukeException extends Exception{
    public DukeException(String msg) {
        super();
        String horizontalLine = "\t_______________________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println(msg);
        System.out.println("\t  Format the command correctly");
        System.out.println(horizontalLine);
    }
}
