
package duke;


public class Parser {


    public static CommandType parseCommand(String s) throws DukeException {
        String res = s.split(" ",2)[0];
        try {
            return CommandType.valueOf(res.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("Oops, idk what you saying");
        }
    }

    public static String doWhat(String s) throws DukeException {
        try {
            return s.split(" ",2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("dey empty command la");
        }
    }
}
