package duke;

import duke.task.*;

/**
 * Parser helps to parse user input and call the relevant commands
 * related to modifying the task list.
 */
public class Parser {
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Parser object.
     *
     * @param tasks A TaskList class containing tasks.
     */
    public Parser(TaskList tasks) {

        this.tasks = tasks;
        this.ui = new Ui();
    }

    /**
     * Parses the user input and calls relevant functions that modify
     * the TaskList based on input.
     *
     * @param command User's input in a String Format.
     */
    public void parse(String command) {
        String[] spacedCommand = command.split(" ");
        String mainCommand = spacedCommand[0];
        Task task;
        try {
            switch (mainCommand) {
            case "list":
                this.tasks.listTask();
                break;
            case "find":
                this.tasks.findTask(command.substring(5));
                break;
            case "mark":
                if (spacedCommand.length == 1) {
                    throw new DukeException("Please specify the task to mark.");
                }
                this.tasks.modifyTask(mainCommand, Integer.parseInt(spacedCommand[1]));
                break;
            case "unmark":
                if (spacedCommand.length == 1) {
                    throw new DukeException("Please specify the task to unmark.");
                }
                this.tasks.modifyTask(mainCommand, Integer.parseInt(spacedCommand[1]));
                break;
            case "delete":
                if (spacedCommand.length == 1) {
                    throw new DukeException("Please specify the task to delete.");
                }
                this.tasks.deleteTask(Integer.parseInt(spacedCommand[1]));
                break;
            case "todo":
                if (spacedCommand.length == 1) {
                    throw new DukeException("☹ Description of todo cannot be empty. ☹");
                }
                task = new Todo(command.substring(5));
                this.tasks.addTask(task);
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
                this.tasks.addTask(task);
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
                this.tasks.addTask(task);
                break;
            case "/help":
                this.ui.helpMessage();
                break;
            case "":
                this.ui.emptyCommandMessage();
                break;
            default:
                this.ui.noCommandMessage();
                break;
            }
        } catch (DukeException e) {
            System.out.println("OOPS!" + e.toString().split("DukeException:")[1]);
        }
    }
}
