package shiba.commands;

import java.util.HashMap;

import shiba.exceptions.ShibaException;
import shiba.tasks.PersistentTaskList;
import shiba.ui.Replier;

/**
 * Represents a command to display help messages.
 */
public class HelpCommand extends ShibaCommand {
    /**
     * Represents the help information for a command.
     */
    public static class HelpInfo {
        public final String commandFormat;
        public final String commandDescription;

        /**
         * Creates a new HelpInfo object.
         *
         * @param commandFormat The format of the command.
         * @param commandDescription The description of the command and what it does.
         */
        public HelpInfo(String commandFormat, String commandDescription) {
            this.commandFormat = commandFormat;
            this.commandDescription = commandDescription;
        }
    }

    private static final HashMap<CommandType, HelpInfo> HELP_INFO_MAP = new HashMap<>();
    private static final String COMMAND_DOES_NOT_EXIST_MSG = "Woof! This command does not exist!";

    static {
        HELP_INFO_MAP.put(CommandType.BYE,
                new HelpInfo("bye", "Exits the program."));
        HELP_INFO_MAP.put(CommandType.LIST,
                new HelpInfo("list", "Lists all tasks."));
        HELP_INFO_MAP.put(CommandType.MARK,
                new HelpInfo("mark <task number>", "Marks the task as done."));
        HELP_INFO_MAP.put(CommandType.UNMARK,
                new HelpInfo("unmark <task number>", "Marks the task as not done."));
        HELP_INFO_MAP.put(CommandType.TODO,
                new HelpInfo("todo <description>", "Adds a todo task."));
        HELP_INFO_MAP.put(CommandType.DEADLINE,
                new HelpInfo("deadline <description> /by <date>\n"
                        + "Date should be in YYYY-MM-DD or YYYY-MM-DD HH:MM format", "Adds a deadline task."));
        HELP_INFO_MAP.put(CommandType.EVENT,
                new HelpInfo("event <description> /from <date> /to <date>\n"
                        + "Date should be in YYYY-MM-DD or YYYY-MM-DD HH:MM format", "Adds an event task."));
        HELP_INFO_MAP.put(CommandType.DELETE,
                new HelpInfo("delete <task number>", "Deletes the task."));
        HELP_INFO_MAP.put(CommandType.FIND,
                new HelpInfo("find <keyword>", "Finds tasks containing the keyword."));
        HELP_INFO_MAP.put(CommandType.PAT,
                new HelpInfo("pat", "Pats SHIBA-BOT."));
        HELP_INFO_MAP.put(CommandType.BELLYRUB,
                new HelpInfo("bellyrub", "Gives SHIBA-BOT a belly rub."));
        HELP_INFO_MAP.put(CommandType.HELP,
                new HelpInfo("help <optional command name>",
                        "Displays the help message for the command, "
                                + "or lists all commands if no command is specified."));
    }

    private final String fullCmd;

    /**
     * Creates a new HelpCommand object.
     *
     * @param tasks Current state of task list
     * @param cmd Full command string
     */
    public HelpCommand(PersistentTaskList tasks, String cmd) {
        super(tasks);
        fullCmd = cmd;
    }

    @Override
    public void execute() throws ShibaException {
        String[] cmdSplit = fullCmd.split(" ", 2);
        if (cmdSplit.length == 1) {
            printAllCommands();
        } else {
            printCommandHelp(cmdSplit[1].toLowerCase());
        }
    }

    /** Prints the help message when no command is specified - lists out all commands. */
    private void printAllCommands() {
        Replier.printWithNoIndents("Woof! Here are all the commands I can understand:\n");
        StringBuilder sb = new StringBuilder();
        for (CommandType command : CommandType.values()) {
            sb.append(command.name().toLowerCase()).append("\n");
        }
        Replier.printWithOneIndent(sb.toString());
        Replier.printWithNoIndents("For more information on a command, type \"help <command name>\".");
        Replier.reply();
    }

    /**
     * Prints the help message for a specific command.
     *
     * @param commandName The name of the command to print help for.
     */
    private void printCommandHelp(String commandName) {
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            Replier.printWithNoIndents(COMMAND_DOES_NOT_EXIST_MSG);
            Replier.reply();
            return;
        }

        HelpInfo helpInfo = HELP_INFO_MAP.get(commandType);
        if (helpInfo == null) {
            Replier.printWithNoIndents(COMMAND_DOES_NOT_EXIST_MSG);
            Replier.reply();
            return;
        }

        Replier.printWithNoIndents("Woof! Here's the help message for the " + commandName + " command:");
        Replier.printWithOneIndent(helpInfo.commandFormat);
        Replier.printWithOneIndent(helpInfo.commandDescription);
        Replier.reply();
    }
}
