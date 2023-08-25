package jeeves.main;

import jeeves.task.Task;
import jeeves.task.Todo;
import jeeves.task.Deadline;
import jeeves.task.Event;

import java.util.Scanner;

/**
 * Contains the main method and primary logic for Jeeves.
 */
public class Jeeves {
    /**
     * The array used to track tasks.
     * Due to how the taskCount variable is used as the id and
     * array access position, index 0 will always be unused.
     * taskList is effectively 1-indexed
     */
    private static final Task[] taskList = new Task[100];

    /**
     * Main process.
     * Greets the user and waits for user input.
     * Then, responds appropriately.
     *
     * @param args Optional command line arguments.
     */
    public static void main(String[] args) {
        System.out.println("Greetings, Master. Jeeves at your service");
        System.out.println("How may I serve you today?\n");
        Scanner sc = new Scanner(System.in);

        // Waits for user input and process it accordingly
        while (true) {
            // Reads the user input
            String currentCommand = sc.nextLine();
            // Performs a different action depending on the input received
            // Unless a specific pre-defined command is received, the program will
            // print a default error message.
            if (currentCommand.equals("list")) {
                System.out.println("This is what I am tracking for you Master");
                // Displays the current list of tasks tracked and their status
                for (int i = 1; i <= Task.getTaskCount(); i++) {
                    System.out.println(taskList[i].toString());
                }
                // Prints an empty line for output clarity
                System.out.print("\n");
            }  else if (currentCommand.startsWith("mark ")) {
                // Gets the task ID that the user wish to mark
                int id = Integer.parseInt(currentCommand.substring(5));
                // Update the task's status and notifies the user
                taskList[id].setStatus(true);
                System.out.println("Understood, I have marked the following task as done:");
                System.out.println("    " + taskList[id].toString() + "\n");
            } else if (currentCommand.startsWith("unmark ")) {
                // Gets the task ID that the user wish to unmark
                int id = Integer.parseInt(currentCommand.substring(7));
                // Update the task's status and notifies the user
                taskList[id].setStatus(false);
                System.out.println("Understood, I have marked the following task as not done:");
                System.out.println("    " + taskList[id].toString() + "\n");
            } else if (currentCommand.startsWith("todo ")) {
                // Adds the 'To do' Task to the task list
                String currTask = currentCommand.substring(5);
                Todo newTodo = new Todo(currTask);
                taskList[Task.getTaskCount()] = newTodo;
                System.out.println("Task added:\n" +
                        "    " + newTodo + "\n");
            } else if (currentCommand.startsWith("deadline ")) {
                // Extracts the necessary information from the String command
                int byDateIndex = currentCommand.indexOf("/by ");
                String currTask = currentCommand.substring(9, byDateIndex - 1);
                String byDate = currentCommand.substring(byDateIndex + 4);

                // Adds the 'Deadline' Task to the task list
                Deadline newDeadline = new Deadline(currTask, byDate);
                taskList[Task.getTaskCount()] = newDeadline;
                System.out.println("Deadline added:\n" +
                        "    " + newDeadline + "\n");
            } else if (currentCommand.startsWith("event ")) {
                // Extracts the necessary information from the String command
                int fromDateIndex = currentCommand.indexOf("/from ");
                int toDateIndex = currentCommand.indexOf("/to ");
                String currTask = currentCommand.substring(6, fromDateIndex - 1);
                String fromDate = currentCommand.substring(fromDateIndex + 6, toDateIndex - 1);
                String toDate = currentCommand.substring(toDateIndex + 4);

                // Adds the 'Event' Task to the task list
                Event newEvent = new Event(currTask, fromDate, toDate);
                taskList[Task.getTaskCount()] = newEvent;
                System.out.println("Event added:\n" +
                        "    " + newEvent + "\n");
            } else if (currentCommand.equals("bye")) {
                // Displays the farewell message and terminates the application
                System.out.println("I bid you farewell, Master");
                System.exit(0);
            } else {
                // By default, informs the user that the command is not recognized.
                System.out.println("Apologies Master, I am unable to understand that command.\n"
                        + "I will improve myself to better serve you in the future.\n");
            }
        }
    }

}
