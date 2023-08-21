import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkPattern {
    public MarkPattern () {}

    public int mark(String args) {
        Pattern pattern = Pattern.compile("mark\\s*(\\d+)");
        Matcher matcher = pattern.matcher(args);

        if (matcher.find()) {
            String num = matcher.group(1);
            return Integer.parseInt(num)-1;
        } else {
            return -1;
        }
    }

    public int unmark(String args) {
        Pattern pattern = Pattern.compile("unmark\\s*(\\d+)");
        Matcher matcher = pattern.matcher(args);

        if (matcher.find()) {
            String num = matcher.group(1);
            return Integer.parseInt(num)-1;
        } else {
            return -1;
        }
    }
}
