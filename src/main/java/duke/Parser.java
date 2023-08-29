
package duke;


public class Parser {
    public static CommandType parseCommand(String s) {
        String res = s.split(" ",2)[0];
        try {
            return CommandType.valueOf(res.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String doWhat(String s) {
        try {
            return s.split(" ",2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
