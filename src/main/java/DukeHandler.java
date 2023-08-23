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

    public DukeEnum map(String command) throws DukeException {
        for(DukeEnum e: DukeEnum.values()) {
            if (command.equals(e.getText())) {
                return e;
            }  
        }
        throw new InvalidCommandException();
    }

    public String handle(String input) throws DukeException{
        String[] parsedInput = inputParser(input);
        String command = parsedInput[0];
        String rest = parsedInput[1];
        
        try {
            DukeEnum commandtype = map(command);
            switch (commandtype) {
                case LIST:
                    return tasklist.toString();
                case MARK:
                    return tasklist.markAsDone(rest);
                case UNMARK:
                    return tasklist.unmarkAsDone(rest);
                case TODO:
                    return tasklist.addTodo(rest);
                case DEADLINE:
                    return tasklist.addDeadline(rest);
                case EVENT:
                    return tasklist.addEvent(rest);
                case DELETE:
                    return tasklist.delete(rest);
                default:
                    throw new InvalidCommandException();
            }
        } catch (DukeException e) {
            throw e;
        } 
    }
}
