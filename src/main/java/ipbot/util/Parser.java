package ipbot.util;

import ipbot.model.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * A class to parse user input.
 */
public class Parser {
    /**
     * Processes the index argument given by the user as a String.
     *
     * @param indexArg The index argument given by the user as a String.
     * @param lstSize The size of the list to index.
     * @return The task index to use if the index argument given is valid. -1 otherwise.
     */
    public static int checkIndexArg(String indexArg, int lstSize){
        assert indexArg != null : "Index argument is null";
        if (!indexArg.matches("^\\d+$")) {
            return -1;
        }
        int index = Integer.parseInt(indexArg) - 1;
        if (0 > index || index >= lstSize){
            return -1;
        }
        return index;
    }

    /**
     * Parses the command given by the user and extracts the command as a String and the arguments as a Map.
     *
     * @param command The command given by the user.
     * @return A Pair of String and Map.
     * The String is the command String, while the Map contains the arguments given by the user.
     * @throws CommandArgumentException
     */
    public static Pair<String, Map<String, String>> parseCommand(String command) throws CommandArgumentException {
        assert command != null : "Command is null";
        int commandEndIndex = command.indexOf(" ");
        if (commandEndIndex == -1) {
            commandEndIndex = command.length();
        }
        String commandNameStr = command.substring(0, commandEndIndex).toLowerCase();
        String commandArgs = command.substring(commandEndIndex).strip();
        commandArgs = "/ " + commandArgs;
        String[] splitArgs = commandArgs.split("/");
        Map<String, String> args = new HashMap<>();
        boolean emptyArg = true;
        for (String currArg: splitArgs) {
            if (emptyArg) {
                emptyArg = false;
                continue;
            }
            int argEndIndex = currArg.indexOf(" ");
            if (argEndIndex == -1) {
                argEndIndex = currArg.length();
            }
            String argName = currArg.substring(0, argEndIndex).toLowerCase();
            String argArgs = currArg.substring(argEndIndex).strip();
            if (args.get(argName) != null) {
                throw new CommandArgumentException("Repeated argument: " + argName + "!");
            }
            args.put(argName, argArgs);
        }
        return new Pair<>(commandNameStr, args);
    }
}
