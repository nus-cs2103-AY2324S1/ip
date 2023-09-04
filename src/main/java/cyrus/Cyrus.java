package cyrus;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cyrus.commands.Command;
import cyrus.commands.CommandError;
import cyrus.parser.ParseInfo;
import cyrus.parser.Parser;
import cyrus.storage.FileStorage;
import cyrus.storage.IStorage;
import cyrus.tasks.TaskList;


/**
 * Entry point of Cyrus interface.
 */
public class Cyrus {
    private final IStorage storage = new FileStorage("data/data.json");
    private final TaskList taskList = new TaskList(storage);
    private final Parser parser = new Parser();

    /**
     * Parses input into {@code ParseInfo}.
     *
     * @param input user input to parse.
     * @return parsed user input.
     */
    public ParseInfo parseInput(String input) {
        return parser.parse(input);
    }

    /**
     * Executes a given command given the {@code ParseInfo}.
     *
     * @param parseInfo parsed information of the command.
     * @return {@code String} output of command if successfully executed.
     * @throws CommandError if there is an error with the input.
     */
    public String dispatchAndExecute(ParseInfo parseInfo) throws CommandError {
        Command command = parser.dispatchCommand(taskList, parseInfo);
        String[] lines = command.execute();
        List<String> text =
                Arrays.stream(lines)
                        .flatMap((line) -> Stream.of(line.split("\n")))
                        .collect(Collectors.toList());
        return String.join("\n", text);
    }
}
