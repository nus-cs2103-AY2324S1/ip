package process;

import exception.InvalidCommandException;
import exception.MissingArgumentException;
import parser.CommandParser;
import task.TaskList;

public class Delete implements SimpleProcess {
    private TaskList tasks = TaskList.init();
    @Override
    public String processInput(String input) {
        try {
            String number = CommandParser.getCommandArguments(input);
            return tasks.deleteTask(Integer.parseInt(number));
        } catch (MissingArgumentException e) {
            return e.toString();
        } catch (InvalidCommandException e){
            return e.toString();
        } catch (NumberFormatException e) {
            return " Strictly type 1 number only";
        } catch (IndexOutOfBoundsException e) {
            return " Index number does not exist in our list";
        }
    }
}
