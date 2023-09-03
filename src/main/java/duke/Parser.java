package duke;

import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * Handles user input
 */
public class Parser {

    /**
     * Processes the user input and take appropriate action on the list of tasks.
     *
     * @param scannerIn User input.
     * @param tasks     List of tasks.
     * @return true if it is bye command else false.
     * @throws Exception If the command fails to execute.
     */
    public static boolean processCommand(String scannerIn, TaskList tasks) throws Exception {
        String[] userIn = scannerIn.split(" ");
        String command = userIn[0];
        Ui.printHorizontalLine();
        switch (command) {
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            return false;
        case "list":
            tasks.listTasks();
            break;
        case "mark": {
            tasks.markTask(Integer.parseInt(userIn[1]) - 1);
            break;
        }
        case "unmark": {
            tasks.unmarkTask(Integer.parseInt(userIn[1]) - 1);
            break;
        }
        case "todo":
        case "deadline":
        case "event":
            Task newTask = null;
            switch (command) {
            case "deadline":
                newTask = Deadline.initializeFromInput(scannerIn);
                break;
            case "todo":
                newTask = Todo.initializeFromInput(scannerIn);
                break;
            case "event":
                newTask = Event.initializeFromInput(scannerIn);
                break;
            default:
                throw new UnknownCommandException();
            }
            tasks.addTask(newTask);
            break;
        case "delete":
            tasks.deleteTask(Integer.parseInt(userIn[1]) - 1);
            break;
        case "find":
            tasks.findTask(userIn[1]);
            break;
        default:
            throw new UnknownCommandException();
        }
        return true;
    }
}
