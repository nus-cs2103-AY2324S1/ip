package duke.parser;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * The Parser to parse input for the Chatbot.
 */
public class Parser {

    /**
     * The Message to display when Invalid Description is given.
     */
    private static final String INVALID_DESC_MESSAGE = "Hey! The task description cannot be empty!";

    /**
     * The Message to display when Invalid Deadline is given.
     */
    private static final String INVALID_DEADLINE_MESSAGE = "Hey, the Deadline given is Invalid! "
            + "Make sure that you follow this format:\n" + " 'taskDescription /by yyyy-mm-dd HH:mm'";

    /**
     * The Message to display when Invalid Event is given.
     */
    private static final String INVALID_EVENT_MESSAGE = "Hey, the Event given is Invalid!"
            + " Make sure that you follow this format:\n"
            + " 'eventDescription /from yyyy-mm-dd HH:mm /to yyyy-mm-dd HH:mm'";

    /**
     * The Message to display when Invalid Event is given.
     */
    private static final String INVALID_EDIT_MESSAGE = "The parameters for the Edit command are Invalid. "
            + "Edit only one field at a time!\n"
            + "Follow this format:\n"
            + " 'edit taskIndex /fieldToEdit editData'\n"
            + "Eg. 'edit 4 /desc newTaskTitle'";


    /**
     * The command parsed from the input.
     */
    private Command command;

    /**
     * The arguments parsed from the input.
     */
    private String arguments = "";

    /**
     * The Constructor of the Parser.
     *
     * @param rawData The data to be parsed.
     */
    public Parser(String rawData) {
        try {
            this.command = parseCommand(rawData);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Parses the input given to the Chatbot.
     *
     * @param rawData Input from the Chatbot.
     * @return The given Command from the input.
     * @throws DukeException if there is no input.
     */
    public Command parseCommand(String rawData) throws DukeException {
        String[] commandArray = rawData.split("\\s+", 2);
        if (commandArray.length == 0) {
            throw new DukeException("Hey, Type Something!");
        } else if (commandArray.length > 1) {
            this.arguments = commandArray[1];
        }
        return Command.getCommand(commandArray[0]);
    }

    /**
     * Returns the command of the parsed input.
     *
     * @return The command of the parsed input.
     */
    public Command getCommand() {
        return this.command;
    }

    /**
     * Returns the arguments of the parsed input.
     *
     * @return The arguments of the parsed input.
     */
    public String getArguments() {
        return this.arguments;
    }

    /**
     * Parses the ToDo Arguments from the input.
     *
     * @return The description of the ToDo task.
     * @throws DukeException if description is empty.
     */
    public String parseToDoArguments() throws DukeException {
        if (arguments.length() == 0) {
            throw new DukeException(INVALID_DESC_MESSAGE);
        }
        return arguments;
    }

    /**
     * Parses the find arguments.
     *
     * @return The arguments of the find command.
     * @throws DukeException if argument is empty.
     */
    public String parseFindQuery() throws DukeException {
        if (arguments.length() == 0) {
            throw new DukeException(INVALID_DESC_MESSAGE);
        }
        return arguments;
    }

    /**
     * Parses the Deadline arguments from the input.
     *
     * @return The String Array containing the description as the first element
     *     and time as the second element.
     * @throws DukeException if description is empty.
     */
    public String[] parseDeadlineArguments() throws DukeException {
        if (this.arguments.length() == 0) {
            throw new DukeException(INVALID_DESC_MESSAGE);
        }

        String[] arr = this.arguments.split(" /by ");

        if (arr.length != 2) {
            throw new DukeException(INVALID_DEADLINE_MESSAGE);
        }
        return arr;
    }

    /**
     * Parses the Event arguments from the input.
     *
     * @return The String array containing the description as the first element,
     *     start time as the second element and end time as the third element.
     * @throws DukeException if description is empty.
     */
    public String[] parseEventArguments() throws DukeException {
        if (this.arguments.length() == 0) {
            throw new DukeException(INVALID_DESC_MESSAGE);
        }
        String[] arr = this.arguments.split(" /from ");
        if (arr.length != 2) {
            throw new DukeException(INVALID_EVENT_MESSAGE);
        }
        String desc = arr[0];

        String[] startEnd = arr[1].split(" /to ");
        if (startEnd.length != 2) {
            throw new DukeException(INVALID_EVENT_MESSAGE);
        }
        String start = startEnd[0];
        String end = startEnd[1];

        return new String[]{desc, start, end};
    }

    /**
     * Parses the Edit arguments from the input.
     *
     * @return The String array containing the Index of the Task, the Edit Type and the Edit Data.
     * @throws DukeException If the arguments are invalid.
     */
    public String[] parseEditArguments() throws DukeException {

        String[] commandArray = arguments.split("\\s+", 3);
        if (commandArray.length != 3) {
            throw new DukeException(INVALID_EDIT_MESSAGE);
        }
        String index = commandArray[0];
        String editType = commandArray[1];
        String editData = commandArray[2];

        return new String[]{index, editType, editData};
    }

}
