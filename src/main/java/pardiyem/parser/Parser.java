package pardiyem.parser;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    String SPACE = " ";
    public ArrayList<String> parseCommand(String in) {
        int x = in.indexOf(SPACE);
        String command;
        ArrayList<String> out = new ArrayList<String>(Arrays.asList("-1", "-1"));
        if (x == -1) {
            command = in;
        } else {
            command =  in.substring(0, x);
        }
        switch (command) {
            case "bye":
                out.set(0, "1");
                break;
            case "list":
                out.set(0, "2");
                break;
            case "mark":
                out.set(0, "3");
                out.set(1, in.substring(x+1));
                break;
            case "unmark":
                out.set(0, "4");
                out.set(1, in.substring(x+1));
                break;
            default:
                out.set(0, "0");
                break;
        }
        return out;
    }
}
