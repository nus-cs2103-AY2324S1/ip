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

    public static String[] parseCommand(String command) {
        if (command.isEmpty()) {
            return new String[0];
        }
        int commandEndIndex = 0;
        while (commandEndIndex < command.length() && command.charAt(commandEndIndex) != ' ') {
            commandEndIndex++;
        }
        String commandNameStr = command.substring(0, commandEndIndex).toLowerCase();
        String commandArgs = command.substring(commandEndIndex).strip();
        return new String[]{commandNameStr, commandArgs};
    }

    public static String[] parseDeadline(String commandArgs) throws CommandArgumentException {
        int byIndex = commandArgs.indexOf("/by");
        if (byIndex == -1) {
            throw new CommandArgumentException("Deadline missing a /by argument!");
        }
        String desc = commandArgs.substring(0, byIndex).strip();
        String by = commandArgs.substring(byIndex + "/by".length()).strip();
        if (desc.isEmpty()) {
            throw new CommandArgumentException("Task description cannot be empty!");
        }
        if (by.isEmpty()) {
            throw new CommandArgumentException("/by argument cannot be empty!");
        }
        return new String[]{desc, by};
    }

    public static String[] parseEvent(String commandArgs) throws CommandArgumentException {
        int fromIndex = commandArgs.indexOf("/from");
        int toIndex = commandArgs.indexOf("/to");
        if (fromIndex == -1) {
            throw new CommandArgumentException("Deadline missing a /from argument!");
        }
        if (toIndex == -1) {
            throw new CommandArgumentException("Deadline missing a /to argument!");
        }
        String desc, from, to;
        if (fromIndex < toIndex) {
            desc = commandArgs.substring(0, fromIndex).strip();
            from = commandArgs.substring(fromIndex + "/from".length(), toIndex).strip();
            to = commandArgs.substring(toIndex + "/to".length()).strip();
        } else {
            desc = commandArgs.substring(0, toIndex).strip();
            from = commandArgs.substring(fromIndex + "/from".length()).strip();
            to = commandArgs.substring(toIndex + "/to".length(), fromIndex).strip();
        }
        if (desc.isEmpty()) {
            throw new CommandArgumentException("Task description cannot be empty!");
        }
        if (from.isEmpty()) {
            throw new CommandArgumentException("/from argument cannot be empty!");
        }
        if (to.isEmpty()) {
            throw new CommandArgumentException("/to argument cannot be empty!");
        }
        return new String[]{desc, from, to};
    }
}
