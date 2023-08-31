import ip.utils.Pair;

import java.util.Scanner;

public class Parser {
    /**
     * Helper function. Splits the console input string into the invoking keyword
     * and its description after the invoking keyword. <br>
     * If no description exists after the keyword, an empty string is returned in the
     * second half of the Pair structure.
     *
     * @param input The unmodified console string that the user inputs.
     * @return A Pair&lt;Command, String&gt; object containing the enum and description.
     */
    public static Pair<CommandType, String> parse(String input) {
        Scanner scanner = new Scanner(input);
        // if the input is empty, return the unknown keyword with an empty description.
        if (!scanner.hasNext()) {
            scanner.close();
            return new Pair<>(CommandType.UNKNOWN, "");
        }

        String keyword = scanner.next();
        CommandType first = CommandType.UNKNOWN;

        for (CommandType command: CommandType.values()) {
            if (keyword.equals(command.getKeyword())) {
                first = command;
                break;
            }
        }

        if (first.equals(CommandType.UNKNOWN) || !scanner.hasNextLine()) {
            scanner.close();
            return new Pair<>(first, "");
        }

        String second = scanner.nextLine().trim();
        scanner.close();
        return new Pair<>(first, second);
    }
}
