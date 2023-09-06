package process;

import exception.InvalidCommandException;
import exception.MissingArgumentException;
import parser.CommandParser;
import task.TaskList;

public class Mark implements SimpleProcess {
    private TaskList tasks = TaskList.init();

    @Override
    public String processInput(String input) {
        try {
            String number = CommandParser.getCommandArguments(input);
            return tasks.markDone(Integer.parseInt(number));
        } catch (NumberFormatException e) {
            return "Strictly type 1 number only";
        } catch (IndexOutOfBoundsException e) {
            return "Index number does not exist in our list";
        } catch (MissingArgumentException | InvalidCommandException e) {
            return e.toString();
        }
    }
}
