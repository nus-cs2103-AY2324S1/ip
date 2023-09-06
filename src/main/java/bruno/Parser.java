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
    public String parseInput(String input) throws BrunoException {
        try {
            TaskType type = TaskType.valueOf(input.split(" ")[0].toUpperCase());
            switch (type) {
            case BYE:
                return "bye";
            case LIST:
                return taskList.displayList();
            case MARK:
                return taskList.markTask(input);
            case UNMARK:
                return taskList.unmarkTask(input);
            case DELETE:
                return taskList.deleteTask(input) + "\n" + taskList.displayListSum();
            case TODO:
                return taskList.addToDo(input) + "\n" + taskList.displayListSum();
            case DEADLINE:
                return taskList.addDeadline(input) + "\n" + taskList.displayListSum();
            case EVENT:
                return taskList.addEvent(input) + "\n" + taskList.displayListSum();
            case SCHEDULE:
                return taskList.showSchedule(input);
            case FIND:
                return taskList.findTasks(input);
            default:
                throw new BrunoUnknownTaskException();
            }
        } catch (IllegalArgumentException e) {
            throw new BrunoUnknownTaskException();
        }
    }
}
