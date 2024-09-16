package duke.Parser;

import duke.DukeException.DukeException;
import duke.Storage.Storage;
import duke.Task.*;
import duke.TaskList.TaskList;
import duke.Ui.Ui;

/**
 * Splits input into command that the chatbot can understand.
 */
public class Parser {

    /**
     * Checks whether the string give is an integer.
     * @param str String that will be checked.
     * @return Boolean that represent whether the string is a integer.
     */
    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Gets the command and translate it to the work that will be done by the chatbot.
     * @param input String entered by the user.
     * @param ui The Ui that allow the chatbot to print the message.
     * @param storage Storage where the tasks will be stored.
     * @param tasks Temporary place where the tasks is stored before written in the storage.
     * @throws DukeException Exception to handle unknown command.
     */
    public static String parse(String input, Ui ui, Storage storage, TaskList tasks) throws DukeException {
        String command = input.split(" ")[0].toUpperCase();
        try {
            if (command.equals("BYE")) {
                return parseBye(input, storage, ui, tasks);
            } else if (command.equals("LIST")) {
                return ui.listing(tasks);
            } else if (command.equals("TODO")) {
                return parseTodo(input, ui, tasks);
            } else if (command.equals("DEADLINE")) {
                return parseDeadline(input, ui, tasks);
            } else if (command.equals("EVENT")) {
                return parseEvent(input, ui, tasks);
            } else if (command.equals("PERIOD")) {
                return parsePeriod(input, ui, tasks);
            } else if (command.equals("MARK")) {
                return parseMark(input, ui, tasks);
            } else if (command.equals("UNMARK")) {
                return parseUnmark(input, ui, tasks);
            } else if (command.equals("DELETE")) {
                return parseDelete(input, ui, tasks);
            } else if (command.equals("FIND")) {
                return ui.find(tasks, input.substring(5));
            } else {
                throw new DukeException("OOPS! I don't understand this command.");
            }
        } catch (DukeException e) {
            return ui.error(e);
        }
    }

    private static String parseTodo(String input, Ui ui, TaskList tasks) throws DukeException{
        Task targetTask;
        if (ToDos.isTodo(input)) {
            targetTask= new ToDos(input.substring(5));
            tasks.addTask(targetTask);
            return ui.addTask(targetTask, tasks.getNumberOfTask());
        } else {
            throw new DukeException("This todo is invalid.");
        }
    }

    private static String parseDeadline(String input, Ui ui, TaskList tasks) throws DukeException{
        Task targetTask;
        if (Deadlines.isDeadline(input)) {
            targetTask = new Deadlines(
                    input.substring(9, input.indexOf("/by ")),
                    input.substring(input.indexOf("/by ") + 4)
            );
            tasks.addTask(targetTask);
            return ui.addTask(targetTask, tasks.getNumberOfTask());
        } else {
            throw new DukeException("This deadline is invalid.");
        }
    }

    private static String parseEvent(String input, Ui ui, TaskList tasks) throws DukeException{
        Task targetTask;
        if (Events.isEvent(input)) {
            targetTask = new Events(
                    input.substring(6, input.indexOf("/from ")),
                    input.substring(input.indexOf("/from ") + 6, input.indexOf("/to ") - 1),
                    input.substring(input.indexOf("/to ") + 4)
            );
            tasks.addTask((targetTask));
            return ui.addTask(targetTask, tasks.getNumberOfTask());
        } else {
            throw new DukeException("This event is invalid.");
        }
    }

    private static String parsePeriod(String input, Ui ui, TaskList tasks) throws DukeException{
        Task targetTask;
        if (Periods.isPeriod(input)) {
            targetTask = new Periods(
                    input.substring(7, input.indexOf("/between ")),
                    input.substring(input.indexOf("/between ") + 8, input.indexOf("/and ") - 1),
                    input.substring(input.indexOf("/and ") + 5)
            );
            tasks.addTask((targetTask));
            return ui.addTask(targetTask, tasks.getNumberOfTask());
        } else {
            throw new DukeException("This period is invalid.");
        }
    }

    private static String parseMark(String input, Ui ui, TaskList tasks) throws DukeException{
        if (input.split(" ").length == 2 && isInteger(input.split(" ")[1])) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index < 0) {
                throw new DukeException("There is no task number " + (index + 1));
            } else if (index <= tasks.getNumberOfTask() - 1) {
                assert index > -1;
                tasks.getTask(index).changeMarkStatus(true);
                return ui.markAsDone(tasks.getTask(index));
            } else {
                throw new DukeException("You don't have that many tasks.");
            }
        } else {
            throw new DukeException("You must specified the index of the task to mark.");
        }
    }

    private static String parseUnmark(String input, Ui ui, TaskList tasks) throws DukeException{
        if (input.split(" ").length == 2 && isInteger(input.split(" ")[1])) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index < 0) {
                throw new DukeException("There is no task number " + (index + 1));
            } else if (index <= tasks.getNumberOfTask() - 1) {
                assert index > -1;
                tasks.getTask(index).changeMarkStatus(false);
                return ui.markAsNotDone(tasks.getTask(index));
            } else {
                throw new DukeException("You don't have that many tasks.");
            }
        } else {
            throw new DukeException("You must specified the index of the task to unmark.");
        }
    }

    private static String parseDelete(String input, Ui ui, TaskList tasks) throws DukeException{
        Task targetTask;
        if (input.split(" ").length == 2 && isInteger(input.split(" ")[1])) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index < 0) {
                throw new DukeException("There is no task number " + (index + 1));
            } else if (index <= tasks.getNumberOfTask() - 1) {
                assert index > -1;
                targetTask = tasks.getTask(index);
                tasks.deleteTask(index);
                return ui.deleteTask(targetTask, tasks.getNumberOfTask());
            } else {
                throw new DukeException("You don't have that many tasks.");
            }
        } else {
            throw new DukeException("You must specified the index of the task to delete.");
        }
    }

    private static String parseBye(String input, Storage storage, Ui ui, TaskList tasks) throws DukeException {
        if (input.length() > 3) {
            throw new DukeException("I don't understand this command. Please use 'bye' only");
        }
        return ui.bye(storage, tasks);
    }
}
