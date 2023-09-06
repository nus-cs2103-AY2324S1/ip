package ruiz;

import ruiz.command.Command;
import ruiz.exception.BotException;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    /**
     * Parses the input of the user and returns a Command
     *
     * @param input This is the input of the user.
     * @return The Command used in the input of the user.
     */
    public Command getCommand(String input) {
        String[] words = input.split(" ");
        if (words.length == 0) {
            return Command.UNKNOWN;
        }
        String keyword = words[0].toLowerCase(); // Get the first word (command) in lowercase
        switch (keyword) {
        case "bye":
            return Command.BYE;
        case "list":
            return Command.LIST;
        case "mark":
            return Command.MARK;
        case "unmark":
            return Command.UNMARK;
        case "delete":
            return Command.DELETE;
        case "deadline":
            return Command.DEADLINE;
        case "todo":
            return Command.TODO;
        case "event":
            return Command.EVENT;
        case "find":
            return Command.FIND;
        default:
            return Command.UNKNOWN;
        }
    }

    /**
     * Parses and splits the input by the user.
     *
     * @param input
     * @return
     */
    public String[] splitInputFromFile(String input) {
        return input.split(" \\| ");
    }

    /**
     * Returns the description of the Todo input by the user.
     *
     * @param input input of the user
     * @return description of the ToDo input by the user.
     * @throws BotException if the ToDo is in an invalid format.
     */
    public String getTodoDescription(String input) throws BotException {
        if (input.split(" ", 2).length <= 1) {
            throw new BotException("OOPS!!! The description of a todo cannot be empty.");
        }
        return input.split(" ", 2)[1];
    }

    /**
     * Returns the description of the Deadline input by the user.
     *
     * @param input input of the user
     * @return description of the deadline input by the user.
     * @throws BotException if the deadline is in an invalid format.
     */
    public String getDeadlineDescription(String input) throws BotException {
        if (input.split("/by", 2).length <= 1) {
            throw new BotException("OOPS!!! The description the deadline is incomplete,"
                    + " it should be in the format 'deadline *** /by ***'");
        }
        return input.split(" ", 2)[1].split(" /by ")[0];
    }

    /**
     * Returns the time the deadline must be completed by input by the user.
     *
     * @param input input of the user
     * @return time the deadline must be completed by input by the user.
     * @throws BotException if the deadline is in an invalid format.
     */
    public String getBy(String input) throws BotException {
        if (input.split("/by", 2).length <= 1) {
            throw new BotException("OOPS!!! The description the deadline is incomplete,"
                    + " it should be in the 'format deadline *** /by ***'"
                    + "");
        }
        return input.split(" ", 2)[1].split(" /by ")[1];
    }

    /**
     * Returns the description of the Event input by the user.
     *
     * @param input input of the user
     * @return description of the Event input by the user.
     * @throws BotException if the Event is in an invalid format.
     */
    public String getEventDescription(String input) throws BotException {
        if (input.split("/").length <= 2) {
            throw new BotException("OOPS!!! The description the event is incomplete."
                    + "it should be in the format 'event *** /from *** /to ***'");
        }
        return input.split(" ", 2)[1].split(" /from")[0];
    }

    /**
     * Returns the time the event begins by input by the user.
     *
     * @param input input of the user
     * @return time the event begins by input by the user.
     * @throws BotException if the event is in an invalid format.
     */
    public String getFrom(String input) throws BotException {
        if (input.split("/").length <= 2) {
            throw new BotException("OOPS!!! The description the event is incomplete."
                    + "it should be in the format 'event *** /from *** /to ***'");
        }
        return input.split(" ", 2)[1].split(" /from ")[1].split(" /")[0];
    }

    /**
     * Returns the time the event finishes by input by the user.
     *
     * @param input input of the user
     * @return time the event finishes by input by the user.
     * @throws BotException if the event is in an invalid format.
     */
    public String getTo(String input) throws BotException {
        if (input.split("/").length <= 2) {
            throw new BotException("OOPS!!! The description the event is incomplete.");
        }
        return input.split(" ", 2)[1].split(" /from ")[1].split("/to ")[1];
    }

    /**
     * Returns the keyword used to search from the input to find.
     *
     * @param input input of the user.
     * @return the keyword of the input.
     * @throws BotException if the input to find is in an invalid format.
     */
    public String getKeyword(String input) throws BotException {
        if (input.split(" ").length != 2) {
            throw new BotException("OOPS!!! PLease use the format of 'find *keyword*'");
        }
        return input.split(" ", 2)[1];
    }
}

