package duke;

import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.PostponeCommand;
import commands.RescheduleCommand;
import commands.ToDoCommand;
import commands.UnmarkCommand;

/**
 * Parser class that makes sense of user command and executes appropriate actions.
 */
public class Parser {
    /**
     * Parses the command input by the user for execution
     * @param command input by the user
     * @return an appropriate Command object depending on the command
     * @throws DukeException if command is invalid
     */
    public static Command parse(String command) throws DukeException{
        if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("bye")) {
            return new ByeCommand();
        }

        String commandIdentifier = command.substring(0, command.indexOf(" "));
        String taskInfo = command.substring(command.indexOf(" ") + 1);
        String[] taskInfoInArr = taskInfo.split(" [/]");

        switch (commandIdentifier) {
        case ("todo") :
            return new ToDoCommand(taskInfo);
        case ("event") :
            return new EventCommand(taskInfoInArr);
        case ("deadline") :
            return new DeadlineCommand(taskInfoInArr);
        case ("mark") :
            return new MarkCommand(taskInfo);
        case ("unmark") :
            return new UnmarkCommand(taskInfo);
        case ("delete") :
            return new DeleteCommand(taskInfo);
        case ("find") :
            return new FindCommand(taskInfo);
        case ("postpone") :
            return new PostponeCommand(taskInfoInArr);
        case ("reschedule") :
            return new RescheduleCommand(taskInfoInArr);
        }

        throw new DukeException("Invalid command");
    }
}
