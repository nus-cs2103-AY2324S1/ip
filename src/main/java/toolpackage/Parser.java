package toolpackage;

import dukepackage.DukeException;

import taskpackage.Deadlines;
import taskpackage.Events;
import taskpackage.ToDos;

/**
 * Represents the parser tool for the
 * Duke bot. A Parser object is used
 * by the Duke bot to understand
 * and execute user commands.
 */
public class Parser {

    /**
     * Parses and executes command given by user.
     *
     * @param userInput String given by user.
     * @param tasks Task list of bot to be modified.
     * @param ui UI of bot.
     * @return String Response by Duke bot.
     * @throws DukeException if there are any issues in the command execution.
     */
    public static String parse(String userInput, TaskList tasks, Ui ui) throws DukeException {
        String[] parsedCommand = userInput.split(" ", 2);
        String command = parsedCommand[0];

        String task = "";
        if (parsedCommand.length > 1) {
            task = parsedCommand[1];
        }

        if (command.equals("bye")) {
            return "bye";
        } else if (command.equals("list")) {
            return tasks.printList(ui);
        } else if (command.equals("mark")) {
            return tasks.toggleDone(task, "mark", ui);
        } else if (command.equals("unmark")) {
            return tasks.toggleDone(task, "unmark", ui);
        } else if (command.equals("delete")) {
            return tasks.removeItem(task, ui);
        } else if (command.equals("find")) {
            return tasks.findTasks(task, ui);
        } else if (command.equals("todo")) {
            return tasks.addItem(new ToDos(task, "0"), ui);
        } else if (command.equals("deadline")) {
            parsedCommand = task.split("/");
            assert parsedCommand.length >= 1 : "Incomplete deadline details.";
            if (parsedCommand.length == 1) {
                throw new DukeException(" OOPS!!! There are missing deadline details.");
            } else {
                return tasks.addItem(new Deadlines(parsedCommand[0], parsedCommand[1], "0"), ui);
            }
        } else if (command.equals("event")) {
            parsedCommand = task.split("/");
            assert parsedCommand.length >= 1 : "Incomplete event details.";
            if (parsedCommand.length <= 2) {
                throw new DukeException(" OOPS!!! There are missing event details.");
            } else {
                return tasks.addItem(new Events(parsedCommand[0], parsedCommand[1], parsedCommand[2], "0"), ui);
            }
        } else {
            throw new DukeException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
