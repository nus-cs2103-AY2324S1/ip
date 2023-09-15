package process;

import exception.InvalidCommandException;
import exception.InvalidInputException;
import exception.MissingArgumentException;
import parser.CommandParser;
import task.TaskManager;

/**
 * A class for the process of creating a find task
 */
public class Find implements SimpleProcess {
    private TaskManager tasks = TaskManager.init();

    @Override
    public String processInput(String input) {
        assert input.toLowerCase().startsWith("find") : "user input does not start with the correct word";

        try {
            String argument = CommandParser.getCommandArguments(input);

            String[] split = argument.split(" ");
            if (split.length > 1) {
                throw new InvalidInputException();
            }

            return tasks.findTask(argument);
        } catch (MissingArgumentException | InvalidCommandException | InvalidInputException e) {
            return e.toString();
        }
    }
}
