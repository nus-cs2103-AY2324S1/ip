package duke.parser;

import duke.command.Command;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.exception.InvalidToDoException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The Parser class to parse the command
 */
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
            if (message.equals("list")) {
                ui.printTasks(tasks);
            } else if (messageType.equals("mark")) {
                Command.mark(message, ui, tasks, storage);
            } else if (messageType.equals("unmark")) {
                Command.unmark(message, ui, tasks, storage);
            } else if (messageType.equals("todo")) {
                Command.addToDo(message, ui, tasks, storage);
            } else if (messageType.equals("deadline")) {
                Command.addDeadline(message, ui, tasks, storage);
            } else if (messageType.equals("event")) {
                Command.addEvent(message, ui, tasks, storage);
            } else if (messageType.equals("delete")) {
                Command.delete(message, ui, tasks, storage);
            } else {
                throw new InvalidCommandException();
            }
        } catch (InvalidToDoException e) {
            ui.printException(e);
        } catch (InvalidEventException e) {
            ui.printException(e);
        } catch (InvalidDeadlineException e) {
            ui.printException(e);
        } catch (InvalidCommandException e) {
            ui.printException(e);
        }
    }
}
