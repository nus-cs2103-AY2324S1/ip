package jeeves;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import jeeves.exception.DeletedIdException;
import jeeves.parser.Parser;
import jeeves.storage.Storage;
import jeeves.task.Deadline;
import jeeves.task.Event;
import jeeves.task.Task;
import jeeves.task.TaskList;
import jeeves.task.Todo;
import jeeves.ui.Ui;

/**
 * Contains the main method and primary logic for Jeeves.
 */
public class Jeeves {

    private static final String RELATIVE_PATH_DATA_DIRECTORY = "data";
    private static final String RELATIVE_PATH_DATA_FILE = "data/JeevesData.txt";

    private static final Storage storage = new Storage(RELATIVE_PATH_DATA_DIRECTORY, RELATIVE_PATH_DATA_FILE);
    private static final TaskList tasks = new TaskList(storage.readTasklistFromFile());
    private static final Parser parser = new Parser();
    private static final Ui ui = new Ui();

    /**
     * Main process.
     *
     * @param args Optional command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Ui.class);
    }

    /**
     * Contains the primary logic for Jeeves.
     * Constantly loops and waits for a new line of user input.
     * Processes the input depending on what was entered.
     */
    public static String processInput(String inputLine) {
        // Reads the user input and parses the relevant tokens for use
        ArrayList<String> tokens = parser.parseUserInput(inputLine);
        String currentCommand = tokens.get(0);
        // Performs a different action depending on the input received
        // Unless a specific pre-defined command is received, the program will
        // print a default error message.
        if (currentCommand.equals("list")) {
            // Displays a different message if no task is being tracked
            StringBuilder sb = new StringBuilder();
            if (Task.getTaskCount() == 0) {
                return "I am not currently tracking anything for you Master\n";
            } else {
                sb.append("This is what I am tracking for you Master\n");
            }

            // Displays the current list of tasks tracked and their status
            for (int i = 1; i <= Task.getTaskCount(); i++) {
                if (tasks.getTask(i) != null) {
                    sb.append(tasks.getTask(i).toString());
                    sb.append("\n");
                }
            }
            sb.append("\n");
            return sb.toString();
        } else if (currentCommand.equals("mark")) {
            // Get the id and try to process the marking
            int id = Integer.parseInt(tokens.get(1));

            try {
                if (tasks.getTask(id) == null) {
                    // If the id to be marked belongs to a deleted task (null), throws the DeletedIdException
                    throw new DeletedIdException();
                }

                tasks.markTask(id);
                return "Understood, I have marked the following task as done:\n"
                                + "    "
                                + tasks.getTask(id).toString()
                                + "\n";
            } catch (DeletedIdException e) {
                return e.getMessage();
            }
        } else if (currentCommand.equals("unmark")) {
            int id = Integer.parseInt(tokens.get(1));

            try {
                if (tasks.getTask(id) == null) {
                    // If the id to be marked belongs to a deleted task (null), throws the DeletedIdException
                    throw new DeletedIdException();
                }

                tasks.unmarkTask(id);
                return "Understood, I have marked the following task as not done:\n"
                                + "    "
                                + tasks.getTask(id).toString()
                                + "\n";
            } catch (DeletedIdException e) {
                return e.getMessage();
            }
        } else if (currentCommand.equals("delete")) {
            int id = Integer.parseInt(tokens.get(1));

            try {
                if (tasks.getTask(id) == null) {
                    // If the id to be marked belongs to a deleted task (null), throws the DeletedIdException
                    throw new DeletedIdException();
                }

                tasks.setTask(id, null);
                return "Understood, I have deleted the following task:\n"
                                + "    " 
                                + tasks.getTask(id).toString()
                                + "\n";
            } catch (DeletedIdException e) {
                return e.getMessage();
            }
        } else if (currentCommand.equals("todo")) {
            // Adds the To.do normally to the task list
            Todo newTodo = new Todo(tokens.get(1));
            tasks.addTaskAtIndex(Task.getTaskCount(), newTodo);
            return "Task added:\n"
                            + "    "
                            + newTodo
                            + "\n";
        } else if (currentCommand.equals("deadline")) {
            // Checks if the Date input is in the accepted format
            LocalDate deadline = LocalDate.parse(tokens.get(2));

            // Adds the 'Deadline' Task to the task list
            Deadline newDeadline = new Deadline(tokens.get(1), deadline);
            tasks.addTaskAtIndex(Task.getTaskCount(), newDeadline);
            return "Deadline added:\n"
                            + "    "
                            + newDeadline + "\n";
        } else if (currentCommand.equals("event")) {
            // Adds the 'Event' Task to the task list
            Event newEvent = new Event(tokens.get(1), tokens.get(2), tokens.get(3));
            tasks.addTaskAtIndex(Task.getTaskCount(), newEvent);
            return "Event added:\n"
                            + "    " 
                            + newEvent
                            + "\n";
        } else if (currentCommand.equals("find")) {
            tasks.searchFor(tokens.get(1));
        } else if (currentCommand.equals("bye")) {
            // Before the actual termination of the program, writes the current task list to the external file.
            storage.writeTasklistToFile(tasks.getTaskListDataAsString());

            // Displays the farewell message and terminates the application
            return "I bid you farewell, Master\n";
        }
        
        // By default, informs the user that the command is not recognized.
        return "Apologies Master, I am unable to understand that command.\n"
                        + "I will improve myself to better serve you in the future.\n";
    }

}
