package ruiz.utils;

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
        assert input != null : "input should not be null";
        try {
            String inputWithoutLocation = input.split(" /at ")[0];
            String todoDescription = inputWithoutLocation.split(" ", 2)[1];
            assert todoDescription != null : "todoDescription should not be null";
            return todoDescription;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BotException("OOPS!!! The description of a todo cannot be empty. " +
                    "It should be in the format 'todo *** /at ***'");
        }
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
        assert input != null : "input should not be null";
        try {
            String inputWithoutLocation = input.split(" /at ")[0];
            String deadlineDescription = inputWithoutLocation.split(" ", 2)[1].split(" /by ")[0];
            assert deadlineDescription != null : "deadlineDescription should not be null";
            return deadlineDescription;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BotException("OOPS!!! The description the deadline is incomplete,"
                    + " it should be in the format 'deadline *** /by ***'");
        }
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
        assert input != null : "input should not be null";
        String inputWithoutLocation = input.split(" /at ")[0];
        try {
            String by = inputWithoutLocation.split(" ", 2)[1].split(" /by ")[1];
            assert by != null : "by should not be null";
            return by;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BotException("OOPS!!! The description the deadline is incomplete,"
                    + " it should be in the 'format deadline *** /by ***'");
        }
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
        assert input != null : "input should not be null";
        String inputWithoutLocation = input.split(" /at ")[0];
        try {
            String eventDescription = inputWithoutLocation.split(" ", 2)[1].split(" /from")[0];
            assert eventDescription != null : "eventDescription should not be null";
            return eventDescription;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BotException("OOPS!!! The description the event is incomplete."
                    + "it should be in the format 'event *** /from *** /to ***'");
        }
    }

    /**
     * Returns the time the event begins by input by the user.
     *
     * @param input input of the user
     * @return time the event begins by input by the user.
     * @throws BotException if the event is in an invalid format.
     */
    public String getEventBeginning(String input) throws BotException {
        if (input.split("/").length <= 2) {
            throw new BotException("OOPS!!! The description the event is incomplete."
                    + "it should be in the format 'event *** /from *** /to ***'");
        }
        assert input != null : "input should not be null";
        String inputWithoutLocation = input.split(" /at ")[0];
        try {
            String beginning = inputWithoutLocation.split(" ", 2)[1].split(" /from ")[1].split(" /")[0];
            assert beginning != null : "beginning should not be null";
            return beginning;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BotException("OOPS!!! The description the event is incomplete."
                    + "it should be in the format 'event *** /from *** /to ***'");
        }
    }

    /**
     * Returns the time the event finishes by input by the user.
     *
     * @param input input of the user
     * @return time the event finishes by input by the user.
     * @throws BotException if the event is in an invalid format.
     */
    public String getEventEnd(String input) throws BotException {
        if (input.split("/").length <= 2) {
            throw new BotException("OOPS!!! The description the event is incomplete.");
        }
        assert input != null : "input should not be null";
        String inputWithoutLocation = input.split(" /at ")[0];
        try {
            String to = inputWithoutLocation.split(" ", 2)[1].split(" /from ")[1].split("/to ")[1];
            assert to != null : "to should not be null";
            return to;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BotException("OOPS!!! The description of the event is incomplete.");
        }
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
        assert input != null : "input should not be null";
        String keyword = input.split(" ", 2)[1];
        assert keyword != null : "keyword should not be null";
        return keyword;
    }

    /**
     * Returns the task number of the task to be marked.
     *
     * @param input input of the user.
     * @return the task number of the task to be marked.
     * @throws BotException if the input to mark is in an invalid format.
     */
    public int getTaskNumber(String input) throws BotException {
        if (input.split(" ").length < 2) {
            throw new BotException("OOPS!!! The index of a task cannot be empty.");
        }
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new BotException("OOPS!!! The index of a task has to be an integer.");
        }
        assert input != null : "input should not be null";
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        assert taskIndex >= 0 : "taskIndex should be greater than 0";
        return taskIndex;
    }

    /**
     * Returns the task number of the task to be deleted.
     * @param input input of the user.
     * @return the task number of the task to be deleted.
     */
    public String getLocation(String input) throws BotException {
        try {
            return input.split(" /at ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BotException("You did not input your location. It should be in the format '*** /at ***'");
        }
    }
}

