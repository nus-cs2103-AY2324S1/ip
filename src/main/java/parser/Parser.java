package parser;

import command.Command;
import exception.InvalidCommandException;
import exception.InvalidDeadlineException;
import exception.InvalidEventException;
import exception.InvalidToDoException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;
import ui.Ui;

public class Parser {
    /**
     * Parses the message given
     * 
     * @param message the message given
     * @param ui the ui of the program
     * @param tasks the list of tasks
     * @param storage the storage program
     */
    public static void parse(String message, Ui ui, TaskList tasks, Storage storage) {
        try{
            String messageType = message.split(" ")[0];
            if(message.equals("list")) {
                ui.printTasks(tasks);
            } else if(messageType.equals("mark")) {
                Command.mark(message, ui, tasks, storage);
            } else if(messageType.equals("unmark")) {
                Command.unmark(message, ui, tasks, storage);
            } else if(messageType.equals("todo")) {
                Command.addToDo(message, ui, tasks, storage);
            } else if(messageType.equals("deadline")) {
                Command.addDeadline(message, ui, tasks, storage);
            } else if(messageType.equals("event")) {
                Command.addEvent(message, ui, tasks, storage);
            } else if(messageType.equals("delete")) {
                Command.delete(message, ui, tasks, storage);
            } else {
                throw new InvalidCommandException();
            }
        } catch(InvalidToDoException e) {
            ui.printException(e);
        } catch(InvalidEventException e) {
            ui.printException(e);
        } catch(InvalidDeadlineException e) {
            ui.printException(e);
        } catch(InvalidCommandException e) {
            ui.printException(e);
        }
    }

    /**
     * Merthod to check whether a string is integer
     * 
     * @param str the string that wanted to be checked
     * @return whether the string is integer
     */
    private static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
