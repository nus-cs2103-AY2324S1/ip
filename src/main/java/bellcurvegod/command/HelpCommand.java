package bellcurvegod.command;

/**
 * Encapsulates the HelpCommand.
 */
public class HelpCommand {
    /**
     * Returns help message corresponding to the input.
     *
     * @param input user input.
     * @return help message.
     */
    public static String run(String input) {
        String intro = "The following are the commands you can use:\n";
        String commandInfo = "Commands related to creating new tasks:\n"
            + "\"todo\"\t\"deadline\"\t\"event\"\t\n"
            + "Commands related to existing tasks:\n"
            + "\"list\"\t\"find\"\t\"mark\"\t\"unmark\"\t\"delete\"\t\n"
            + "Misc:\n"
            + "\"bye\"\n";
        String helpMessage = intro + commandInfo
            + "Type 'help <command>' and enter to see how you can use these commands.\n";

        String[] words = input.split(" ");
        if (words.length == 2) {
            String cmd = words[1];
            switch (cmd) {
            case "bye":
                return ExitCommand.getHelpMessage();
            case "list":
                return ListTasksCommand.getHelpMessage();
            case "find":
                return FindCommand.getHelpMessage();
            case "mark":
                return MarkCommand.getHelpMessage();
            case "unmark":
                return UnmarkCommand.getHelpMessage();
            case "delete":
                return DeleteTaskCommand.getHelpMessage();
            default:
                return AddTaskCommand.getHelpMessage(cmd);
            }
        }

        return helpMessage;
    }
}
