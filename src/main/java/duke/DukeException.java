package duke;

import java.lang.Exception;
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
