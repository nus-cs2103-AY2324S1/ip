package process;

import exception.InvalidCommandException;
import exception.MissingArgumentException;
import parser.CommandParser;
import task.TaskList;

/**
 * A class for the process of creating a delete task
 */
public class Delete implements SimpleProcess {
    private TaskList tasks = TaskList.init();
    @Override
    public String processInput(String input) {
        assert input.toLowerCase().startsWith("delete") : "user input does not start with the correct word";
        try {
            String number = CommandParser.getCommandArguments(input);
            return tasks.deleteTask(Integer.parseInt(number));
        } catch (MissingArgumentException e) {
            return e.toString();
        } catch (InvalidCommandException e) {
            return e.toString();
        } catch (NumberFormatException e) {
            return "Strictly type 1 number only";
        } catch (IndexOutOfBoundsException e) {
            return "Index number does not exist in our list";
        }
    }
}
