package duke;

import duke.task.*;

/**
 * Parser helps to parse user input and call the relevant commands
 * related to modifying the task list.
 */
public class Parser {
    private TaskList tasks;
    private Ui ui;

    private Storage storage;

    /**
     * Constructs a Parser object.
     *
     * @param tasks A TaskList class containing tasks.
     */
    public Parser(TaskList tasks) {

        this.tasks = tasks;
        this.ui = new Ui();
        this.storage = new Storage();
    }

    /**
     * Parses the user input and calls relevant functions that modify
     * the TaskList based on input.
     *
     * @param command User's input in a String Format.
     * @throws DukeException If there's any error parsing the command
     */
    public String parse(String command) throws DukeException {
        String[] spacedCommand = command.split(" ");
        String mainCommand = spacedCommand[0];
        String output = null;
        try {
            switch (mainCommand) {
            case "list":
                output = this.tasks.listTask();
                break;
            case "find":
                output = this.tasks.findTask(command.substring(5));
                break;
            case "mark":
                output = parseMarkCommand(spacedCommand, mainCommand);
                break;
            case "unmark":
                output = parseUnmarkCommand(spacedCommand, mainCommand);
                break;
            case "delete":
                output = parseDeleteCommand(spacedCommand, mainCommand);
                break;
            case "todo":
                output = parseTodoCommand(command, spacedCommand, mainCommand);
                break;
            case "deadline":
                output = parseDeadlineCommand(command, spacedCommand, mainCommand);
                break;
            case "event":
                output = parseEventCommand(command, spacedCommand, mainCommand);
                break;
            case "":
                output = this.ui.emptyCommandMessage();
                break;
            case "bye":
                output = this.ui.byeMessage();
                break;
            default:
                output = this.ui.noCommandMessage();
                break;
            }
        } catch (DukeException e) {
            return "OOPS!" + e.toString().split("DukeException:")[1];
        }
        return output;
    }

    /**
     * Parses a command that starts with "mark".
     * @param spacedCommand The input command split by a spaces.
     * @param mainCommand The first word of the input command.
     * @return A String with the Output of the mark command.
     * @throws DukeException If there's any error parsing the command
     */
    public String parseMarkCommand(String[] spacedCommand, String mainCommand) throws DukeException{
        if (spacedCommand.length == 1) {
            throw new DukeException("Please specify the task to mark.");
        }
        String output = this.tasks.modifyTask(mainCommand, Integer.parseInt(spacedCommand[1]));
        this.storage.writeTasks(this.tasks.getTasks());
        return output;
    }

    /**
     * Parses a command that starts with "unmark".
     * @param spacedCommand The input command split by a spaces.
     * @param mainCommand The first word of the input command.
     * @return A String with the Output of the command.
     * @throws DukeException If there's any error parsing the command
     */
    public String parseUnmarkCommand(String[] spacedCommand, String mainCommand) throws DukeException{
        if (spacedCommand.length == 1) {
            throw new DukeException("Please specify the task to unmark.");
        }
        return this.tasks.modifyTask(mainCommand, Integer.parseInt(spacedCommand[1]));
    }

    /**
     * Parses a command that starts with "delete".
     * @param spacedCommand The input command split by a spaces.
     * @param mainCommand The first word of the input command.
     * @return A String with the Output of the command.
     * @throws DukeException If there's any error parsing the command
     */
    public String parseDeleteCommand(String[] spacedCommand, String mainCommand) throws DukeException{
        if (spacedCommand.length == 1) {
            throw new DukeException("Please specify the task to delete.");
        }
        String output = this.tasks.deleteTask(Integer.parseInt(spacedCommand[1]));
        this.storage.writeTasks(this.tasks.getTasks());
        return output;
    }


    /**
     * Parses a command that starts with "todo".
     * @param command The user input from parse function in String.
     * @param spacedCommand The input command split by a spaces.
     * @param mainCommand The first word of the input command.
     * @return A String with the Output of the command.
     * @throws DukeException If there's any error parsing the command
     */
    public String parseTodoCommand(String command, String[] spacedCommand, String mainCommand) throws DukeException{
        if (spacedCommand.length == 1) {
            throw new DukeException("☹ Description of todo cannot be empty. ☹");
        }
        Task task = new Todo(command.substring(5));
        String output = this.tasks.addTask(task);
        this.storage.writeTasks(this.tasks.getTasks());
        return output;
    }

    /**
     * Parses a command that starts with "deadline".
     * @param command The user input from parse function in String.
     * @param spacedCommand The input command split by a spaces.
     * @param mainCommand The first word of the input command.
     * @return A String with the Output of the command.
     * @throws DukeException If there's any error parsing the command
     */
    public String parseDeadlineCommand(String command, String[] spacedCommand, String mainCommand) throws DukeException{
        if (spacedCommand.length == 1) {
            throw new DukeException("☹ Description of deadline cannot be empty. ☹");
        }
        int spacer = command.indexOf("/");
        if (spacer == -1) {
            throw new DukeException("Please remember to add your deadline! Write /by, " +
                    "followed by a date in YYYY-MM-DD format.");
        }
        Task task = new Deadline(command.substring(9, spacer), command.substring(spacer + 4));
        String output = this.tasks.addTask(task);
        this.storage.writeTasks(this.tasks.getTasks());
        return output;
    }


    /**
     * Parses a command that starts with "event".
     * @param command The user input from parse function in String.
     * @param spacedCommand The input command split by a spaces.
     * @param mainCommand The first word of the input command.
     * @return A String with the Output of the command.
     * @throws DukeException If there's any error parsing the command
     */
    public String parseEventCommand(String command, String[] spacedCommand, String mainCommand) throws DukeException{
        if (spacedCommand.length == 1) {
            throw new DukeException("☹ Description of event cannot be empty. ☹");
        }
        int startSpacer = command.indexOf("/");
        int endSpacer = command.lastIndexOf("/");
        if (startSpacer == -1 || endSpacer == -1 || startSpacer == endSpacer) {
            throw new DukeException("Please remember to add your start and end dates! In YYYY-MM-DD format.");
        }
        Task task = new Event(command.substring(6, startSpacer),
                command.substring(startSpacer + 6, endSpacer - 1), command.substring(endSpacer + 4));
        String output = this.tasks.addTask(task);
        this.storage.writeTasks(this.tasks.getTasks());
        return output;
    }
}
