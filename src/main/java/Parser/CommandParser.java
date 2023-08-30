package Parser;

import Commands.Command;

public class CommandParser {
    private String input;
    private String start;
    public CommandParser(String input) {
        this.input = input;
        String[] words = input.split(" ");
        String firstWord = words[0];
    }

    private static boolean isMarkCommand(String input) {
        return input.startsWith("mark ");
    }

    private static boolean isUnmarkCommand(String input) {
        return input.startsWith("unmark ");
    }

    private static boolean isDeleteCommand(String input) {
        return input.startsWith("delete ");
    }


}
