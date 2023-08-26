package pardiyem.parser;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    String SPACE = " ";
    public ArrayList<String> parseCommand(String in) {
        int x = in.indexOf(SPACE);
        String command;
        ArrayList<String> out = new ArrayList<String>(Arrays.asList("", ""));
        if (x == -1) {
            command = in;
        } else {
            command =  in.substring(0, x);
        }
        switch (command) {
            case "bye":
                out.set(0, "1");
                out.set(1, in.substring(3).trim());
                break;
            case "list":
                out.set(0, "2");
                out.set(1, in.substring(4).trim());
                break;
            case "mark":
                out.set(0, "3");
                out.set(1, in.substring(4).trim());
                break;
            case "unmark":
                out.set(0, "4");
                out.set(1, in.substring(6).trim());
                break;
            case "todo":
                out.set(0, "5");
                out.set(1, in.substring(4).trim());
                break;
            case "deadline":
                out.set(0, "6");
                out.set(1, in.substring(8).trim());
                break;
            case "event":
                out.set(0, "7");
                out.set(1, in.substring(5).trim());
                break;
            default:
                out.set(0, "-1");
        }
        return out;
    }
}
