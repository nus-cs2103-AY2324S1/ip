package duke.parser;

import duke.command.AddDeadline;
import duke.command.AddEvent;
import duke.command.AddTodo;
import duke.command.Bye;
import duke.command.Command;
import duke.command.Delete;
import duke.command.Find;
import duke.command.List;
import duke.command.Remark;
import duke.command.Snooze;
import duke.command.Unknown;

/**
 * A utility class for parsing user input and creating corresponding command objects.
 * This class processes user input to determine the type of command to create.
 */
public class Parser {
    /**
     * Parses the user input and creates a corresponding command object.
     *
     * @param instr The user input string.
     * @return A command object based on the parsed input.
     */
    public static Command parse(String instr) {
        instr = instr.trim();
        String keyWord = instr.split(" ")[0];
        switch (keyWord) {
        case"":
            return new Unknown("You said nothing!\n");
        case "deadline":
            return new AddDeadline(instr.substring(8).trim());
        case "event":
            return new AddEvent(instr.substring(5).trim());
        case "todo":
            return new AddTodo(instr.substring(4).trim());
        case "mark":
            return new Remark(instr.substring(4).trim(), true);
        case "unmark":
            return new Remark(instr.substring(6).trim(), false);
        case "delete":
            return new Delete(instr.substring(6).trim());
        case "find":
            return new Find(instr.substring(4).trim());
        case "snooze":
            return new Snooze(instr.substring(6).trim());
        case "list":
            return new List(instr);
        case "bye":
            return new Bye(instr);
        default:
            return new Unknown("What are you talking about?\n");
        }
    }
}
