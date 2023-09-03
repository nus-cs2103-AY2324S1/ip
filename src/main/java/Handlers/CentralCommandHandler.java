package Handlers;

import Exceptions.DukeInvalidFormatException;
import Models.Deadline;
import Models.Event;
import Models.TaskArray;
import Models.ToDo;

import java.util.HashMap;
import java.util.Objects;

import static Printers.BasicOutputPrinter.printBasicOutput;
import static Printers.ErrorOutputPrinter.printErrorOutput;
import static java.lang.System.exit;

/**
 * Handles and parses all user input, and assigns each command to a valid handler.
 */
public class CentralCommandHandler {
    private static final HashMap<String, Command> COMMAND_MAP = new HashMap<>();
    private TaskArray tasks;

    private CentralCommandHandler(TaskArray tasks) {
        this.tasks = tasks;
        COMMAND_MAP.put("todo", new TodoHandler(this.tasks));
        COMMAND_MAP.put("deadline", new DeadlineHandler(this.tasks));
        COMMAND_MAP.put("event", new EventHandler(this.tasks));
        COMMAND_MAP.put("bye", new ExitHandler(this.tasks));
        COMMAND_MAP.put("exit", new ExitHandler(this.tasks));
        COMMAND_MAP.put("list", new ListTasksHandler(this.tasks));
        COMMAND_MAP.put("mark", new MarkCommandHandler(this.tasks));
        COMMAND_MAP.put("unmark", new UnmarkCommandHandler(this.tasks));
        COMMAND_MAP.put("delete", new DeleteHandler(this.tasks));
    }

    public static CentralCommandHandler initializeCommandHandler(TaskArray tasks) {
        return new CentralCommandHandler(tasks);
    }

    public void parseInput(String input) {
        String[] splitInput = input.split(" ", 2);

        // The command inputted.
        String command = splitInput[0].strip();

        if (!COMMAND_MAP.containsKey(command)) {
            InvalidInputHandler invalidHandler = new InvalidInputHandler(this.tasks);
            invalidHandler.parseCommandContent("");

        } else {
            Command commandHandler= COMMAND_MAP.get(command);

            if (splitInput.length < 2) {
                commandHandler.parseCommandContent("");
            } else {
                commandHandler.parseCommandContent(splitInput[1].strip());
            }
        }
    }
}
