package duke.Parser;

import duke.DukeException.DukeException;
import duke.Storage.Storage;
import duke.Task.Deadlines;
import duke.Task.Events;
import duke.Task.Task;
import duke.Task.ToDos;
import duke.TaskList.TaskList;
import duke.Ui.Ui;

/**
 * Split input into command that the chatbot can understand.
 */
public class Parser {

    /**
     * Check whether the string give is an integer.
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
     * Get the command and translate it to the work that will be done by the chatbot.
     * @param input String entered by the user.
     * @param ui The Ui that allow the chatbot to print the message.
     * @param storage Storage where the tasks will be stored.
     * @param tasks Temporary place where the tasks is stored before written in the storage.
     * @throws DukeException Exception to handle unknown command.
     */
    public static void parse(String input, Ui ui, Storage storage, TaskList tasks) throws DukeException {
        String command = input.split(" ")[0].toUpperCase();
        Task targetTask;
        if (command.equals("BYE")) {
            ui.bye();
            storage.editStorage(tasks);
        } else if (command.equals("LIST")) {
            ui.listing(tasks);
        } else if (command.equals("TODO")) {
            if (ToDos.isTodo(input)) {
                targetTask = new ToDos(input.substring(5));
                ui.addTask(targetTask, tasks.getNumberOfTask() + 1);
                tasks.addTask(targetTask);
            } else {
                throw new DukeException("This todo is invalid");
            }
        } else if (command.equals("DEADLINE")) {
            if (Deadlines.isDeadline(input)) {
                targetTask = new Deadlines(
                        input.substring(9, input.indexOf("/by ")),
                        input.substring(input.indexOf("/by ") + 4)
                );
                ui.addTask(targetTask, tasks.getNumberOfTask() + 1);
                tasks.addTask(targetTask);
            } else  {
                throw  new DukeException("This deadline is invalid");
            }
        } else if (command.equals("EVENT")) {
            if (Events.isEvent(input)) {
                targetTask = new Events(
                        input.substring(6, input.indexOf("/from ")),
                        input.substring(input.indexOf("/from ") + 6, input.indexOf("/to ") - 1),
                        input.substring(input.indexOf("/to ") + 4)
                );
                ui.addTask(targetTask, tasks.getNumberOfTask() + 1);
                tasks.addTask((targetTask));
            } else {
                throw new DukeException("This event is invalid");
            }
        } else if (command.equals("MARK")){
            if (input.split(" ").length == 2 && isInteger(input.split(" ")[1])) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index <= tasks.getNumberOfTask() - 1) {
                    tasks.getTask(index).changeMarkStatus(true);
                    ui.markAsDone(tasks.getTask(index));
                } else {
                    throw new DukeException("You don't have that many tasks");
                }
            }
        } else if (command.equals("UNMARK")) {
            if (input.split(" ").length == 2 && isInteger(input.split(" ")[1])) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index <= tasks.getNumberOfTask() - 1) {
                    tasks.getTask(index).changeMarkStatus(false);
                    ui.markAsNotDone(tasks.getTask(index));
                } else {
                    throw new DukeException("You don't have that many tasks");
                }
            }
        } else if (command.equals("DELETE")) {
            if (input.split(" ").length == 2 && isInteger(input.split(" ")[1])) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index <= tasks.getNumberOfTask() - 1) {
                    ui.deleteTask(tasks.getTask(index), tasks.getNumberOfTask() - 1);
                    tasks.deleteTask(index);
                } else {
                    throw new DukeException("You don't have that many tasks");
                }
            }
        } else if (command.equals("FIND")) {
            ui.find(tasks, input.substring(5));
        } else {
            throw new DukeException("OOPS! I don't understand this command");
        }
    }
}
