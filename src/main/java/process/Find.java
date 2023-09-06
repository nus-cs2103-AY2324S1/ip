package process;

import exception.InvalidCommandException;
import exception.InvalidInputException;
import exception.MissingArgumentException;
import parser.CommandParser;
import task.TaskList;

/**
 * A class for the process of creating a find task
 */
public class Find implements SimpleProcess {
    private TaskList tasks = TaskList.init();

    @Override
    public String processInput(String input) {
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
