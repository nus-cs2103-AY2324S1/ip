package bareum;

import bareum.commands.Command;
import bareum.commands.AddDeadlineCommand;
import bareum.commands.AddEventCommand;
import bareum.commands.AddTodoCommand;
import bareum.commands.ByeCommand;
import bareum.commands.DeleteCommand;
import bareum.commands.FindCommand;
import bareum.commands.IncorrectCommand;
import bareum.commands.ListCommand;
import bareum.commands.MarkCommand;
import bareum.commands.TagCommand;
import bareum.commands.UnmarkCommand;

/**
 * This class implements the parser which deals with interpreting the user input.
 */
public class Parser {
    public Parser() {

    }

    /**
     * Parse the user input and create the corresponding command to execute.
     * @param input Details of task user inputs
     * @return An executable command with the details input by the user.
     * @throws BareumException If input is incorrect.
     */
    static Command parse(String input) throws BareumException {
        String[] commandInputs = input.split(" ", 2);
        Command cmd = null;
        if (commandInputs[0].equals("bye")) {
            cmd = parseByeCommand();
        } else if (commandInputs[0].equals("list")) {
            cmd = parseListCommand();
        } else if (commandInputs[0].equals("mark")) {
            cmd = parseMarkCommand(commandInputs);
        } else if (commandInputs[0].equals("unmark")) {
            cmd = parseUnmarkCommand(commandInputs);
        } else if (commandInputs[0].equals("delete")) {
            cmd = parseDeleteCommand(commandInputs);
        } else if (commandInputs[0].equals("find")) {
            cmd = parseFindCommand(commandInputs);
        } else if (commandInputs[0].equals("tag")) {
            cmd = parseTagCommand(commandInputs);
        } else if (commandInputs[0].equals("todo")) {
            cmd = parseTodoCommand(commandInputs);
        } else if (commandInputs[0].equals("deadline")){
            cmd = parseDeadlineCommand(commandInputs);
        } else if (commandInputs[0].equals("event")) {
            cmd = parseEventCommand(commandInputs);
        } else {
            cmd = parseIncorrectCommand();
        }

        return cmd;
    }

    /**
     * Parses the contents of the bye command input by the user.
     * @return A ByeCommand for execution to exit the program.
     */
    public static Command parseByeCommand() {
        // method is abstracted for SLAP
        return new ByeCommand();
    }

    /**
     * Parses the contents of the list command input by the user.
     * @return A ListCommand for execution to list all tasks.
     */
    public static Command parseListCommand() {
        // method is abstracted for SLAP
        return new ListCommand();
    }

    /**
     * Parses the contents of the mark command input by the user and validates the inputs.
     * @param commandInputs Index of the task the user wants to mark as done.
     * @return A MarkCommand for execution to mark the specified task as done.
     * @throws BareumException If index is not added or is not a number.
     */
    public static Command parseMarkCommand(String[] commandInputs) throws BareumException {
        if (commandInputs.length == 1) {
            throw new BareumException("Oops! Please give the index of the task you would like to mark.\n"
                    + "\nCorrect format: mark <index>");
        }
        int inputIndex;
        try {
            inputIndex = Integer.parseInt(commandInputs[1]);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            throw new BareumException("Oops! Please give an integer as the index of the task"
                    + "you would like to delete.\n\nCorrect format: delete <index>");
        }
        int index = inputIndex - 1;
        return new MarkCommand(index);
    }

    /**
     * Parses the contents of the unmark command input by the user and validates the inputs.
     * @param commandInputs Index of the task the user wants to mark as not done.
     * @return An UnmarkCommand for execution to mark the specified task as not done.
     * @throws BareumException If index is not added or is not a number.
     */
    public static Command parseUnmarkCommand(String[] commandInputs) throws BareumException {
        if (commandInputs.length == 1) {
            throw new BareumException("Oops! Please give the index of the task you would like to unmark.\n"
                    + "\nCorrect format: unmark <index>");
        }
        int inputIndex;
        try {
            inputIndex = Integer.parseInt(commandInputs[1]);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            throw new BareumException("Oops! Please give an integer as the index of the task"
                    + "you would like to delete.\n\nCorrect format: delete <index>");
        }
        int index = inputIndex - 1;
        return new UnmarkCommand(index);
    }

    /**
     * Parses the contents of the delete command input by the user and validates the inputs.
     * @param commandInputs Index of the task the user wants to delete.
     * @return A DeleteCommand for execution to delete the specified task.
     * @throws BareumException If index is not added or is not a number.
     */
    public static Command parseDeleteCommand(String[] commandInputs) throws BareumException {
        if (commandInputs.length == 1) {
            throw new BareumException("Oops! Please give the index of the task you would like to delete.\n"
                    + "\nCorrect format: delete <index>");
        }

        int inputIndex;
        try {
            inputIndex = Integer.parseInt(commandInputs[1]);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            throw new BareumException("Oops! Please give an integer as the index of the task"
                    + "you would like to delete.\n\nCorrect format: delete <index>");
        }
        int index = inputIndex - 1;
        return new DeleteCommand(index);
    }

    /**
     * Parses the contents of the find command input by the user and validates the inputs.
     * @param commandInputs Keyword the user wants to find.
     * @return A FindCommand for execution to find the tasks with the specified keyword.
     * @throws BareumException If keyword is not added.
     */
    public static Command parseFindCommand(String[] commandInputs) throws BareumException {
        if (commandInputs.length == 1) {
            throw new BareumException("Oops! Please give the keyword you would like to search for.\n"
                    + "\nCorrect format: find <keyword>");
        }

        return new FindCommand(commandInputs[1]);
    }

    /**
     * Parses the contents of the tag command input by the user and validates the inputs.
     * @param commandInputs Tag the user wants to tag the specified task with.
     * @return A TagCommand for execution to tag the specified task with the input tag.
     * @throws BareumException If tag name is not added.
     */
    public static Command parseTagCommand(String[] commandInputs) throws BareumException {
        if (commandInputs.length == 1) {
            throw new BareumException("Oops! The details of your tag are missing :(\n"
                    + "\nCorrect format: tag <index> /tag <keyword>");
        }

        String allDetails = commandInputs[1];
        int indexOfKeywordTag = allDetails.indexOf("/tag");
        if (indexOfKeywordTag == -1) {
            throw new BareumException("Oops! Please include the tag you would like to add :(\n"
                    + "\nCorrect format: tag <index> /tag <keyword>");
        } else if (indexOfKeywordTag == 0) {
            throw new BareumException("Oops! Please include the index of the task you would like to tag :(\n"
                    + "\nCorrect format: tag <index> /tag <keyword>");
        }

        String[] indexTag = allDetails.split(" /tag");
        int inputIndex;
        try {
            inputIndex = Integer.parseInt(indexTag[0]);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            throw new BareumException("Oops! Please give an integer as the index of the task"
                    + " you would like to tag.\n\nCorrect format: tag <index> /tag <keyword>");
        }
        int index = inputIndex - 1;

        if (indexTag.length == 1) {
            throw new BareumException("Oops! Please include the tag you would like to add :(\n"
                    + "\nCorrect format: tag <index> /tag <keyword>");
        }

        String tag = indexTag[1].substring(1);
        return new TagCommand(index, tag);
    }

    /**
     * Parses the contents of the todo command input by the user and validates the inputs.
     * @param commandInputs Details of the todo command.
     * @return An AddTodoCommand for execution to add the new todo with the specified details.
     * @throws BareumException If description is not added.
     */
    public static Command parseTodoCommand(String[] commandInputs) throws BareumException {
        if (commandInputs.length == 1) {
            throw new BareumException("Oops! Please include the description of your todo :(\n"
                    + "\nCorrect format: todo <description>");
        }
        return new AddTodoCommand(commandInputs[1]);
    }

    /**
     * Parses the contents of the deadline command input by the user and validates the inputs.
     * @param commandInputs Details of the deadline command.
     * @return An AddDeadlineCommand for execution to add the new deadline with the specified details.
     * @throws BareumException If description or due date is not added, or if wrong format is used.
     */
    public static Command parseDeadlineCommand(String[] commandInputs) throws BareumException {
        if (commandInputs.length == 1) {
            throw new BareumException("Oops! The details of your deadline are missing :(\n"
                    + "\nCorrect format: deadline <description> /by <due date in YYYY-MM-DD>");
        }

        String allDetails = commandInputs[1];
        int indexOfKeywordBy = allDetails.indexOf("/by");
        if (indexOfKeywordBy == -1) {
            throw new BareumException("Oops! Please include the due date of your deadline :(\n"
                    + "\nCorrect format: deadline <description> /by <due date in YYYY-MM-DD>");
        } else if (indexOfKeywordBy == 0) {
            throw new BareumException("Oops! Please include the description of your deadline :(\n"
                    + "\nCorrect format: deadline <description> /by <due date in YYYY-MM-DD>");
        }

        String[] descriptionDueTime = allDetails.split("/by ");
        String description = descriptionDueTime[0];
        if (descriptionDueTime.length == 1) {
            throw new BareumException("Oops! Please include the due date of your deadline :(\n"
                    + "\nCorrect format: deadline <description> /by <due date in YYYY-MM-DD>");
        }

        String dueDate = descriptionDueTime[1];
        return new AddDeadlineCommand(description, dueDate);
    }

    /**
     * Parses the contents of the event command input by the user and validates the inputs.
     * @param commandInputs Details of the event command.
     * @return An AddEventCommand for execution to add the new event with the specified details.
     * @throws BareumException If description, start or end date is not added, or if wrong format is used.
     */
    public static Command parseEventCommand(String[] commandInputs) throws BareumException {
        if (commandInputs.length == 1) {
            throw new BareumException("Oops! The details of your event are missing :(\n"
                    + "\nCorrect format: event <description> /from <start time> /to <end time>");
        }

        String allDetails = commandInputs[1];
        int indexOfKeywordFrom = allDetails.indexOf("/from");
        if (indexOfKeywordFrom == -1) {
            throw new BareumException("Oops! Please include the start time of your event :(\n"
                    + "\nCorrect format: event <description> /from <start time> /to <end time>");
        } else if (indexOfKeywordFrom == 0) {
            throw new BareumException("Oops! Please include the description of your event :(\n"
                    + "\nCorrect format: event <description> /from <start time> /to <end time>");
        }

        String[] descriptionStartEndTime = allDetails.split("/from");
        String description = descriptionStartEndTime[0];
        int indexOfKeywordTo = descriptionStartEndTime[1].indexOf("/to");
        if (indexOfKeywordTo == -1) {
            throw new BareumException("Oops! Please include the end time of your event :(\n"
                    + "\nCorrect format: event <description> /from <start time> /to <end time>");
        } else if (indexOfKeywordTo == 0) {
            throw new BareumException("Oops! Please include the start time of your event :(\n"
                    + "\nCorrect format: event <description> /from <start time> /to <end time>");
        }

        String[] startEndTime = descriptionStartEndTime[1].split("/to");
        String startDateTime = startEndTime[0];
        if (startEndTime.length == 1) {
            throw new BareumException("Oops! Please include the end time of your event :(\n"
                    + "\nCorrect format: event <description> /from <start time> /to <end time>");
        }
        String endDateTime = startEndTime[1];

        return new AddEventCommand(description, startDateTime, endDateTime);
    }

    /**
     * Parses the contents of the incorrect command input by the user.
     * @return An IncorrectCommand for execution to tell user input was invalid.
     */
    public static Command parseIncorrectCommand() {
        return new IncorrectCommand();
    }
}
