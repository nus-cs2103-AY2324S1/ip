import TaskPackages.TaskList;
import Utility.DukeException;
import Utility.InvalidCommandException;

public class DukeHandler {
    
    TaskList tasklist;

    public DukeHandler() {
        tasklist = new TaskList();
    }

    private static String[] inputParser(String input) {
        int index = input.indexOf(' ');
        if (index > -1) {
            return input.split(" ", 2);
        } else {
            String[] tempString = {input, ""};
            return tempString;
        }
    }

    public boolean checkExit(String input) {
        if (input.equals("bye")) {
            return true;
        } else {
            return false;
        }
    }

    public String handle(String input) throws DukeException{
        String[] parsedInput = inputParser(input);
        String command = parsedInput[0];
        String rest = parsedInput[1];
        String output = "";
        
        try {
            if (command.equals("list")) {
                output = tasklist.toString();
            } else if (command.equals("mark")) {
                output = tasklist.markAsDone(rest);
            } else if (command.equals("unmark")) {
                output = tasklist.unmarkAsDone(rest);
            } else if (command.equals("todo")) {
                output = tasklist.addTodo(rest);
            } else if (command.equals("deadline")) {
                output = tasklist.addDeadline(rest);
            } else if (command.equals("event")) {
                output = tasklist.addEvent(rest);
            } else if (command.equals("delete")) {
                output = tasklist.delete(rest);
            } else {
                throw new InvalidCommandException();
            }
            return output;
        } catch (DukeException e) {
            throw e;
        }
    }
}
