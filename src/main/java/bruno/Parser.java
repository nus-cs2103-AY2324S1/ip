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
     * @param s User input string.
     * @return Flag value for termination.
     * @throws BrunoException Thrown if any error occurs in any of the tasks.
     */
    public boolean parse(String s) throws BrunoException {
        try {
            TaskType type = TaskType.valueOf(s.split(" ")[0].toUpperCase());
            switch (type) {
            case BYE:
                return false;
            case LIST:
                taskList.displayList();
                return true;
            case MARK:
                taskList.markTask(s);
                return true;
            case UNMARK:
                taskList.unmarkTask(s);
                return true;
            case DELETE:
                taskList.deleteTask(s);
                taskList.displayListSum();
                return true;
            case TODO:
                taskList.addToDo(s);
                taskList.displayListSum();
                return true;
            case DEADLINE:
                taskList.addDeadline(s);
                taskList.displayListSum();
                return true;
            case EVENT:
                taskList.addEvent(s);
                taskList.displayListSum();
                return true;
            case SCHEDULE:
                taskList.showSchedule(s);
                return true;
            default:
                throw new BrunoUnknownTaskException();
            }
        } catch (IllegalArgumentException e) {
            throw new BrunoUnknownTaskException();
        }
    }
}
