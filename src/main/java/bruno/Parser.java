package bruno;

import bruno.exceptions.BrunoException;
import bruno.exceptions.BrunoUnknownTaskException;
import bruno.task.TaskType;

/**
 * The Parser class is responsible for parsing the user input and calling methods to perform the
 * appropriate tasks.
 */
public class Parser {

    private TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses the user input and calls methods to perform the appropriate task.
     *
     * @param input User input string.
     * @return Flag value for termination.
     * @throws BrunoException Thrown if any error occurs in any of the tasks.
     */
    public boolean parseInput(String input) throws BrunoException {
        try {
            TaskType type = TaskType.valueOf(input.split(" ")[0].toUpperCase());
            switch (type) {
            case BYE:
                return false;
            case LIST:
                taskList.displayList();
                return true;
            case MARK:
                taskList.markTask(input);
                return true;
            case UNMARK:
                taskList.unmarkTask(input);
                return true;
            case DELETE:
                taskList.deleteTask(input);
                taskList.displayListSum();
                return true;
            case TODO:
                taskList.addToDo(input);
                taskList.displayListSum();
                return true;
            case DEADLINE:
                taskList.addDeadline(input);
                taskList.displayListSum();
                return true;
            case EVENT:
                taskList.addEvent(input);
                taskList.displayListSum();
                return true;
            case SCHEDULE:
                taskList.showSchedule(input);
                return true;
            default:
                throw new BrunoUnknownTaskException();
            }
        } catch (IllegalArgumentException e) {
            throw new BrunoUnknownTaskException();
        }
    }
}
