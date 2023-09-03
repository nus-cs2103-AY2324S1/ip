package jeeves;

import jeeves.exception.DeletedIdException;

import jeeves.task.Task;
import jeeves.task.Todo;
import jeeves.task.Deadline;
import jeeves.task.Event;
import jeeves.task.TaskList;
import jeeves.storage.Storage;
import jeeves.parser.Parser;
import jeeves.ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * Contains the main method and primary logic for Jeeves.
 */
public class Jeeves {

    private static final String RELATIVEPATH_DATA_DIRECTORY = "data";
    private static final String RELATIVEPATH_DATA_FILE = "data/JeevesData.txt";
    private static final String DELETED_ID_EXCEPTION_MESSAGE = "I cannot do that as that is not a valid Task ID "
            + "(ID provided belongs to a deleted task)\n";
    
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;
    private final Ui ui;
    
    public Jeeves() {
        storage = new Storage(RELATIVEPATH_DATA_DIRECTORY, RELATIVEPATH_DATA_FILE);
        tasks = new TaskList(storage.readTasklistFromFile());
        parser = new Parser();
        ui = new Ui();
    }

    /**
     * Main process.
     *
     * @param args Optional command line arguments.
     */
    public static void main(String[] args) {
        new Jeeves().run();
    }
    
    public void run() {
        ui.printGreeting();
        
        Scanner sc = new Scanner(System.in);
        
        // Waits for user input and process it accordingly
        while (true) {
            // Reads the user input and parses the relevant tokens for use
            ArrayList<String> tokens = parser.parseUserInput(sc.nextLine());
            String currentCommand = tokens.get(0);
            // Performs a different action depending on the input received
            // Unless a specific pre-defined command is received, the program will
            // print a default error message.
            if (currentCommand.equals("list")) {
                // Displays a different message if no task is being tracked
                if (Task.getTaskCount() == 0) {
                    System.out.println("I am not currently tracking anything for you Master");
                } else {
                    System.out.println("This is what I am tracking for you Master");
                }

                // Displays the current list of tasks tracked and their status
                for (int i = 1; i <= Task.getTaskCount(); i++) {
                    if (tasks.getTask(i) != null) {
                        tasks.printTask(i);
                    }
                }
                // Prints an empty line for output clarity
                System.out.print("\n");
            } else if (currentCommand.equals("mark")) {
                // Get the id and try to process the marking
                int id = Integer.parseInt(tokens.get(1));
                
                try {
                    if (tasks.getTask(id) == null) {
                        // If the id to be marked belongs to a deleted task (null), throws the DeletedIdException
                        throw new DeletedIdException(DELETED_ID_EXCEPTION_MESSAGE);
                    }
                    
                    tasks.markTask(id);
                    System.out.println("Understood, I have marked the following task as done:");
                    System.out.println("    ");
                    tasks.printTask(id);
                    System.out.println("\n");
                } catch (DeletedIdException e) {
                    System.out.println(e.getMessage());
                }
            } else if (currentCommand.equals("unmark")) {
                int id = Integer.parseInt(tokens.get(1));
                
                try {
                    if (tasks.getTask(id) == null) {
                        // If the id to be marked belongs to a deleted task (null), throws the DeletedIdException
                        throw new DeletedIdException(DELETED_ID_EXCEPTION_MESSAGE);
                    }
                    
                    tasks.unmarkTask(id);
                    System.out.println("Understood, I have marked the following task as not done:");
                    System.out.println("    " + tasks.getTask(id).toString() + "\n");
                } catch (DeletedIdException e) {
                    System.out.println(e.getMessage());
                }
            } else if (currentCommand.equals("delete")) {
                int id = Integer.parseInt(tokens.get(1));
                
                try {
                    if (tasks.getTask(id) == null) {
                        // If the id to be marked belongs to a deleted task (null), throws the DeletedIdException
                        throw new DeletedIdException(DELETED_ID_EXCEPTION_MESSAGE);
                    }
                    
                    System.out.println("Understood, I have deleted the following task:");
                    System.out.println("    " + tasks.getTask(id).toString() + "\n");
                    tasks.setTask(id, null);
                } catch (DeletedIdException e) {
                    System.out.println(e.getMessage());
                }
            } else if (currentCommand.equals("todo")) {
                // Adds the To.do normally to the task list
                Todo newTodo = new Todo(tokens.get(1));
                tasks.addTaskAtIndex(Task.getTaskCount(), newTodo);
                System.out.println("Task added:\n" +
                        "    " + newTodo + "\n");

            } else if (currentCommand.equals("deadline")) {
                // Checks if the Date input is in the accepted format
                LocalDate deadline = LocalDate.parse(tokens.get(2));
                
                // Adds the 'Deadline' Task to the task list
                Deadline newDeadline = new Deadline(tokens.get(1), deadline);
                tasks.addTaskAtIndex(Task.getTaskCount(), newDeadline);
                System.out.println("Deadline added:\n" +
                        "    " + newDeadline + "\n");
            } else if (currentCommand.equals("event")) {
                // Adds the 'Event' Task to the task list
                Event newEvent = new Event(tokens.get(1), tokens.get(2), tokens.get(3));
                tasks.addTaskAtIndex(Task.getTaskCount(), newEvent);
                System.out.println("Event added:\n" +
                        "    " + newEvent + "\n");
            } else if (currentCommand.equals("bye")) {
                // Before the actual termination of the program, writes the current task list to the external file.
                storage.writeTasklistToFile(tasks.getTaskListDataAsString());

                // Displays the farewell message and terminates the application
                ui.printFarewell();
                System.exit(0);
            } else {
                // By default, informs the user that the command is not recognized.
                ui.printInvalidCommand();
            }
        }
    }
}
