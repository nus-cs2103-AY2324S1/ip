package emiya;

import emiya.commands.ByeCommand;
import emiya.commands.Command;
import emiya.commands.DeadlineCommand;
import emiya.commands.DeleteCommand;
import emiya.commands.EventCommand;
import emiya.commands.FindCommand;
import emiya.commands.HelpCommand;
import emiya.commands.ListCommand;
import emiya.commands.MarkCommand;
import emiya.commands.TodoCommand;
import emiya.commands.UnknownCommand;
import emiya.commands.UnmarkCommand;

/**
 * An enum that contains all the keywords used to identify what command to run.
 */
public enum Keyword {
    TODO("todo", new TodoCommand()), DEADLINE("deadline", new DeadlineCommand()),
    EVENT("event", new EventCommand()), LIST("list", new ListCommand()),
    MARK("mark", new MarkCommand()), UNMARK("unmark", new UnmarkCommand()),
    DELETE("delete", new DeleteCommand()), FIND("find", new FindCommand()),
    BYE("bye", new ByeCommand()), HELP("help", new HelpCommand()),
    UNKNOWN("unknown", new UnknownCommand());

    private final String type;
    private final Command command;
    Keyword(String type, Command command) {
        this.type = type;
        this.command = command;
    }

    public Command getCommand() {
        return this.command;
    }

    /**
     * Returns the respective command of the input string.
     *
     * @param value The input String containing the command.
     * @return The respective command of the input string.
     */
    public static Keyword getCommandKeyword(String value) {
        for (Keyword kwd : Keyword.values()) {
            if (kwd.type.equals(value)) {
                return kwd;
            }
        }
        return UNKNOWN;
    }

}
