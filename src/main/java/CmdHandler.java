import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class CmdHandler {
    // used to handle type of tasks
    public static Task interpret(String cmd) {
        //Todo
        if (cmd.startsWith("todo")) {
            Pattern pattern = Pattern.compile("todo (.+)");
            Matcher matcher = pattern.matcher(cmd);

            if (matcher.find()) {
                String desc = matcher.group(1);
                return new ToDo(desc);
            }

        }
        //Deadline
        if (cmd.startsWith("deadline")) {
            Pattern pt = Pattern.compile("deadline (.+)/by (.+)");
            Matcher mt = pt.matcher(cmd);

            if (mt.find()) {
                String desc = mt.group(1);
                String due = mt.group(2);
                return new Deadline(desc, due);
            }
        }
        //Event
        if (cmd.startsWith("event")) {
            Pattern pt = Pattern.compile("event (.+) /from (.+) /to (.+)");
            Matcher mt = pt.matcher(cmd);

            if (mt.find()) {
                String desc = mt.group(1);
                String start = mt.group(2);
                String end = mt.group(3);
                return new Event(desc, start, end);
            }
        }
        return null; // to be replaced with exceptions
    }
}
