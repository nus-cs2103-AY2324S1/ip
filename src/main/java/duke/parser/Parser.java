package duke.parser;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.OngoingCommand;
import duke.command.QueueCommand;
import duke.command.RemoveCommand;
import duke.command.task.DeadlineCommand;
import duke.command.task.EventCommand;
import duke.command.task.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.exception.CommandFormatException;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.parser.element.argument.Argument;
import java.util.AbstractMap.SimpleEntry;
import java.util.function.Function;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * Object to interpret user input.
 */
public class Parser {

    private static final Map<String, Function<Map<String, Object>, Command>> COMMANDS = Map.ofEntries(
            new SimpleEntry<>("list", x -> new ListCommand(x)),
            new SimpleEntry<>("find", x -> new FindCommand(x)),
            new SimpleEntry<>("queue", x -> new QueueCommand(x)),
            new SimpleEntry<>("ongoing", x -> new OngoingCommand(x)),
            new SimpleEntry<>("mark", x -> new MarkCommand(x)),
            new SimpleEntry<>("unmark", x -> new UnmarkCommand(x)),
            new SimpleEntry<>("todo", x -> new ToDoCommand(x)),
            new SimpleEntry<>("deadline", x -> new DeadlineCommand(x)),
            new SimpleEntry<>("event", x -> new EventCommand(x)),
            new SimpleEntry<>("remove", x -> new RemoveCommand(x)),
            new SimpleEntry<>("bye", x -> new ByeCommand(x)));

    /**
     * Converts input to Command.
     * 
     * @param input User input.
     * @return Command based on the input.
     * @throws DukeException When the input is not formatted correctly.
     */
    public static Command parse(String input) throws DukeException {
        String[] commandParts = input.split(" ", 2);
        String commandName = commandParts[0];
        String commandArgs = commandParts.length > 1 ? commandParts[1] : "";
        Map<String, Object> args = new HashMap<>();
        if (!COMMANDS.containsKey(commandName)) {
            throw new InvalidCommandException(commandName);
        }
        Command cmd = COMMANDS.get(commandName).apply(args);
        Matcher groups = cmd.getPattern().matcher(commandArgs);
        if (!groups.matches()) {
            throw new CommandFormatException(commandName, cmd.getStructure());
        }
        List<Argument> fields = cmd.getArguments();
        for (int i = 0; i < fields.size(); i++) {
            Argument arg = fields.get(i);
            if (arg != null) {
                args.put(arg.toString(), arg.formatInput(groups.group(i)));
            }
        }
        return cmd;
    }

}
