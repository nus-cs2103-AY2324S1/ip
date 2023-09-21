package duke;


/**
 * Handles parsing of user input and performing associated actions.
 */
public class Parser {
    private DtFormat dtf;
    private Ui ui;
    private TaskList tl;
    private CommandHandler ch;
    /**
     * Constructor for Parser class.
     *
     * @param dtf DtFormat object used to handle datetime-related strings
     * @param ui Ui object used to handle displaying output to user.
     * @param tl TaskList object to track user-created tasks.
     */
    public Parser(DtFormat dtf, Ui ui, TaskList tl, CommandHandler ch) {
        this.dtf = dtf;
        this.ui = ui;
        this.tl = tl;
        this.ch = ch;
    }



    /**
     * Parses user input and performs associated actions
     *
     * @param userInput One line of the user input
     */

    public String parse(String userInput) throws DukeException {
        String[] splitStr = userInput.split("\\s+");

        if (userInput.equals("bye")) {
            return ch.handleByeCommand();
        } else if (userInput.equals("list")) {
            return ch.handleListCommand();
        } else if (splitStr[0].equals("find")) {
            return ch.handleFindCommand(splitStr);
        } else if (splitStr[0].equals("mark")) {
            return ch.handleMarkCommand(splitStr);
        } else if (splitStr[0].equals("unmark")) {
            return ch.handleUnmarkCommand(splitStr);
        } else if (splitStr[0].equals("remove")) {
            return ch.handleRemoveCommand(splitStr);
        } else if (splitStr[0].equals("todo")) {
            return ch.handleTodoCommand(splitStr);
        } else if (splitStr[0].equals("deadline")) {
            return ch.handleDeadlineCommand(splitStr);
        } else if (splitStr[0].equals("event")) {
            return ch.handleEventCommand(splitStr);
        } else if (splitStr[0].equals("priority")) {
            return ch.handlePriorityCommand(splitStr);
        } else {
            throw new DukeException("Sorry, I don't understand that command");
        }
    }
}
