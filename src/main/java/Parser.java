import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static int checkIndexArg(String indexArg, int lstSize){
        if (!indexArg.matches("^\\d+$")) {
            return -1;
        }
        int index = Integer.parseInt(indexArg) - 1;
        if (0 > index || index >= lstSize){
            return -1;
        }
        return index;
    }

    public static Pair<String, Map<String, String>> parseCommand(String command) {
        int commandEndIndex = command.indexOf(" ");
        if (commandEndIndex == -1) {
            commandEndIndex = command.length();
        }
        String commandNameStr = command.substring(0, commandEndIndex).toLowerCase();
        String commandArgs = command.substring(commandEndIndex).strip();
        commandArgs = "/ " + commandArgs;
        String[] splitArgs = commandArgs.split("/");
        Map<String, String> args = new HashMap<>();
        for (String currArg: splitArgs) {
            int argEndIndex = currArg.indexOf(" ");
            if (argEndIndex == -1) {
                argEndIndex = currArg.length();
            }
            String argName = currArg.substring(0, argEndIndex).toLowerCase();
            String argArgs = currArg.substring(argEndIndex).strip();
            args.put(argName, argArgs);
        }
        return new Pair<>(commandNameStr, args);
    }
}
