import java.util.Map;
import java.util.HashMap;

public abstract class CommandParser {
    public static Map<String, String> parse(String input) {
        String command = input.split(" ")[0];
        String[] arguments = input.split(" /");
        Map<String, String> res = new HashMap<>();
        for (String arg : arguments) {
            String option = getFirstWord(arg);
            String details = removeFirstWord(arg);

            if (option.equals(command)) {
                res.put("command", command);
                res.put("description", details);
            } else {
                res.put(option, details);
            }

        }
        return res;
    }

    private static String removeFirstWord(String s) {
        int indexOfSpace = s.indexOf(' ');
        if (indexOfSpace == -1) {
            return "";
        }
        return s.substring(indexOfSpace + 1);
    }

    private static String getFirstWord(String s) {
        int indexOfSpace = s.indexOf(' ');
        if (indexOfSpace == -1) {
            return s;
        }
        return s.substring(0, indexOfSpace);
    }

    public static void main(String[] args) {
        System.out.println(parse("print 1").entrySet());
    }
}