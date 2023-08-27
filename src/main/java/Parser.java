import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static ArrayList<String> parseDatabaseEntry(String entry) throws DukeDatabaseException {
        ArrayList<String> elements = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\[([A-Z])\\]\\[(.)\\] (.+?)(?: \\(by: (.+?)\\)| \\(from: (.+?) to: (.+?)\\))?");
        Matcher matcher = pattern.matcher(entry);

        if (matcher.matches()) {
            elements.add(matcher.group(1));
            elements.add(matcher.group(2));
            elements.add(matcher.group(3));
            if (matcher.group(4) != null) {
                elements.add(matcher.group(4));
            } else if (matcher.group(5) != null && matcher.group(6) != null) {
                elements.add(matcher.group(5));
                elements.add(matcher.group(6));
            }
        } else {
            throw new DukeDatabaseException();
        }

        return elements;
    }

    public static Command parseCommand(String input) throws DukeNoSuchCommandException {
        String[] inputArr = input.split(" ");
        try {
            return Command.valueOf(inputArr[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeNoSuchCommandException();
        }
    }

    public static ArrayList<String> parseUserInput(String input) {
        ArrayList<String> result = new ArrayList<>();

        String byePattern = "bye";
        String listPattern = "list";
        String helpPattern = "help";
        String todoPattern = "todo (.+)";
        String deadlinePattern = "deadline (.+) /by (.+)";
        String eventPattern = "event (.+) /from (.+) /to (.+)";
        String markPattern = "mark (\\d+)";
        String unmarkPattern = "unmark (\\d+)";
        String deletePattern = "delete (\\d+)";

        if (input.matches(byePattern)) {
            result.add("bye");
        } else if (input.matches(listPattern)) {
            result.add("list");
        } else if (input.matches(helpPattern)) {
            result.add("help");
        } else {
            Matcher matcher;

            matcher = Pattern.compile(todoPattern).matcher(input);
            if (matcher.matches()) {
                result.add("todo");
                result.add(matcher.group(1));
            }

            matcher = Pattern.compile(deadlinePattern).matcher(input);
            if (matcher.matches()) {
                result.add("deadline");
                result.add(matcher.group(1));
                result.add(matcher.group(2));
            }

            matcher = Pattern.compile(eventPattern).matcher(input);
            if (matcher.matches()) {
                result.add("event");
                result.add(matcher.group(1));
                result.add(matcher.group(2));
                result.add(matcher.group(3));
            }

            matcher = Pattern.compile(markPattern).matcher(input);
            if (matcher.matches()) {
                result.add("mark");
                result.add(matcher.group(1));
            }

            matcher = Pattern.compile(unmarkPattern).matcher(input);
            if (matcher.matches()) {
                result.add("unmark");
                result.add(matcher.group(1));
            }

            matcher = Pattern.compile(deletePattern).matcher(input);
            if (matcher.matches()) {
                result.add("delete");
                result.add(matcher.group(1));
            }
        }
        return result;
    }
}