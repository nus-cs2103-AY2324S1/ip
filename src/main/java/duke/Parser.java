package duke;

import commands.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Parser class that makes sense of user command and executes appropriate actions.
 */
public class Parser {

    public static Command parse(String command) throws DukeException{
        if (command.equals("list")) {
            return new ListCommand();
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
