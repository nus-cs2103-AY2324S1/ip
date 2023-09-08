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
     */
    public String parse(String command) throws DukeException{
        String[] spacedCommand = command.split(" ");
        String mainCommand = spacedCommand[0];
        Task task;
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
                if (spacedCommand.length == 1) {
                    throw new DukeException("Please specify the task to mark.");
                }
                output = this.tasks.modifyTask(mainCommand, Integer.parseInt(spacedCommand[1]));
                this.storage.writeTasks(this.tasks.getTasks());
                break;
            case "unmark":
                if (spacedCommand.length == 1) {
                    throw new DukeException("Please specify the task to unmark.");
                }
                output = this.tasks.modifyTask(mainCommand, Integer.parseInt(spacedCommand[1]));
                break;
            case "delete":
                if (spacedCommand.length == 1) {
                    throw new DukeException("Please specify the task to delete.");
                }
                output = this.tasks.deleteTask(Integer.parseInt(spacedCommand[1]));
                this.storage.writeTasks(this.tasks.getTasks());
                break;
            case "todo":
                if (spacedCommand.length == 1) {
                    throw new DukeException("☹ Description of todo cannot be empty. ☹");
                }
                task = new Todo(command.substring(5));
                output = this.tasks.addTask(task);
                this.storage.writeTasks(this.tasks.getTasks());
                break;
            case "deadline":
                if (spacedCommand.length == 1) {
                    throw new DukeException("☹ Description of deadline cannot be empty. ☹");
                }
                int spacer = command.indexOf("/");
                if (spacer == -1) {
                    throw new DukeException("Please remember to add your deadline! Write /by, " +
                            "followed by a date in YYYY-MM-DD format.");
                }
                task = new Deadline(command.substring(9, spacer), command.substring(spacer + 4));
                output = this.tasks.addTask(task);
                this.storage.writeTasks(this.tasks.getTasks());
                break;
            case "event":
                if (spacedCommand.length == 1) {
                    throw new DukeException("☹ Description of event cannot be empty. ☹");
                }
                int startSpacer = command.indexOf("/");
                int endSpacer = command.lastIndexOf("/");
                if (startSpacer == -1 || endSpacer == -1) {
                    throw new DukeException("Please remember to add your start and end dates! In YYYY-MM-DD format.");
                }
                task = new Event(command.substring(6, startSpacer),
                        command.substring(startSpacer + 6, endSpacer - 1), command.substring(endSpacer + 4));
                output = this.tasks.addTask(task);
                this.storage.writeTasks(this.tasks.getTasks());
                break;
            case "/help":
                output = this.ui.helpMessage();
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
}
