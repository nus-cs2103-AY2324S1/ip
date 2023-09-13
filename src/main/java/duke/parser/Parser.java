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
     * @return the response
     */
    public static String parse(String message, Ui ui, TaskList tasks, Storage storage) {
        try {
            String messageType = message.split(" ")[0];
            switch (messageType.toLowerCase()) {
            case "list":
                return Command.list(message, ui, tasks, storage);
            case "mark":
                return Command.mark(message, ui, tasks, storage);
            case "unmark":
                return Command.unmark(message, ui, tasks, storage);
            case "todo":
                return Command.addToDo(message, ui, tasks, storage);
            case "deadline":
                return Command.addDeadline(message, ui, tasks, storage);
            case "event":
                return Command.addEvent(message, ui, tasks, storage);
            case "delete":
                return Command.delete(message, ui, tasks, storage);
            case "find":
                return Command.find(message, ui, tasks, storage);
            case "bye":
                return Command.bye(message, ui, tasks, storage);
            default:
                throw new InvalidCommandException();
            }
        } catch (InvalidToDoException e) {
            return ui.printException(e);
        } catch (InvalidEventException e) {
            return ui.printException(e);
        } catch (InvalidDeadlineException e) {
            return ui.printException(e);
        } catch (InvalidCommandException e) {
            return ui.printException(e);
        }
    }
}
