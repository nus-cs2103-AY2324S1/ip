package jeeves.main;

import jeeves.exception.MissingIdException;
import jeeves.exception.NotIntegerIdException;
import jeeves.exception.OutOfBoundIdException;
import jeeves.exception.MissingDescriptionException;
import jeeves.exception.MissingByException;
import jeeves.exception.MissingFromException;
import jeeves.exception.MissingToException;
import jeeves.exception.DeletedIdException;

import java.io.IOException;
import java.io.BufferedWriter;

import jeeves.task.Task;
import jeeves.task.Todo;
import jeeves.task.Deadline;
import jeeves.task.Event;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Contains the main method and primary logic for Jeeves.
 */
public class Jeeves {

    private static final String RELATIVEPATH_DATA_DIRECTORY = "data";
    private static final String RELATIVEPATH_DATA_FILE = "data/JeevesData.txt";
    private static final int FINDCOMMAND_TODO_OFFSET = 5;
    private static final int FINDCOMMAND_DEADLINE_OFFSET = 9;
    private static final int FINDCOMMAND_EVENT_OFFSET = 6;
    private static final int FINDCOMMAND_MARK_OFFSET = 5;
    private static final int FINDCOMMAND_UNMARK_OFFSET = 7;
    private static final int FINDCOMMAND_DELETE_OFFSET = 7;
    private static final int FINDFIELD_TO_OFFSET = 4;
    private static final int FINDFIELD_FROM_OFFSET = 6;
    private static final int FINDFIELD_BY_OFFSET = 4;
    /**
     * The arraylist used to track tasks.
     * Due to how the taskCount variable is used as the id and
     * array access position, index 0 will always be unused.
     * taskList is effectively 1-indexed
     */
    private static ArrayList<Task> taskList = new ArrayList<>();

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
        
        Path dirPath = Paths.get(RELATIVEPATH_DATA_DIRECTORY);
        // If the directory does not exist, create it for the user
        if (Files.notExists(dirPath)) {
            try {
                Files.createDirectories(dirPath);
            } catch (IOException e) {
                // Do nothing if an error is encountered since the directory existence is already checked
            }
        }
        Path dataPath = Paths.get(RELATIVEPATH_DATA_FILE);
        // If the file does not exist, create it for the user
        if (Files.notExists(dataPath)) {
            try {
                Files.createFile(dataPath);
            } catch (IOException e) {
                // Do nothing if an error is encountered since the file existence is already checked
            }
        }
        // Initialization step for task list, adds an empty object so the arraylist is 1-indexed
        taskList.add(null);

        // Waits for user input and process it accordingly
        while (true) {
            // Reads the user input
            String currentCommand = sc.nextLine();
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
                    if (taskList.get(i) != null) {
                        System.out.println(taskList.get(i).toString());
                    }
                }
                // Prints an empty line for output clarity
                System.out.print("\n");
            }  else if (currentCommand.startsWith("mark ")) {
                // Gets the task ID that the user wish to mark
                String idString = currentCommand.substring(FINDCOMMAND_MARK_OFFSET);
                // If the task ID is invalid or not found, throw an error
                // Else, update the task's status and notifies the user
                try {
                    if (idString.isEmpty()) {
                        // id field is empty
                        throw new MissingIdException("I cannot do that as you have not provided me with a Task ID\n");
                    } else if (isNotNumber(idString)) {
                        // id field is not an integer
                        throw new NotIntegerIdException("I cannot do that as that is not a valid Task ID "
                                + "(ID provided is not an integer)\n");
                    } else if (Integer.parseInt(idString) > Task.getTaskCount()) {
                        // id does not exist
                        throw new OutOfBoundIdException("I cannot do that as that is not a valid Task ID "
                                + "(ID provided does not exist)\n");
                    } else {
                        // Updates the task status
                        int id = Integer.parseInt(idString);
                        if (taskList.get(id) == null) {
                            // If the id to be marked belongs to a deleted task (null), throws the DeletedIdException
                            throw new DeletedIdException("I cannot do that as that is not a valid Task ID "
                                    + "(ID provided belongs to a deleted task)\n");
                        }
                        taskList.get(id).setStatus(true);
                        System.out.println("Understood, I have marked the following task as done:");
                        System.out.println("    " + taskList.get(id).toString() + "\n");
                    }
                } catch (MissingIdException | NotIntegerIdException | OutOfBoundIdException | DeletedIdException e) {
                    System.out.println(e.getMessage());
                }
            } else if (currentCommand.startsWith("unmark ")) {
                // Gets the task ID that the user wish to unmark
                String idString = currentCommand.substring(FINDCOMMAND_UNMARK_OFFSET);
                // If the task ID is invalid or not found, throw an error
                // Else, update the task's status and notifies the user
                try {
                    if (idString.isEmpty()) {
                        // id field is empty
                        throw new MissingIdException("I cannot do that as you have not provided me with a Task ID\n");
                    } else if (isNotNumber(idString)) {
                        // id field is not an integer
                        throw new NotIntegerIdException("I cannot do that as that is not a valid Task ID "
                                + "(ID provided is not an integer)\n");
                    } else if (Integer.parseInt(idString) > Task.getTaskCount()) {
                        // id does not exist
                        throw new OutOfBoundIdException("I cannot do that as that is not a valid Task ID "
                                + "(ID provided does not exist)\n");
                    } else {
                        // Updates the task status
                        int id = Integer.parseInt(idString);
                        if (taskList.get(id) == null) {
                            // If the id to be marked belongs to a deleted task (null), throws the DeletedIdException
                            throw new DeletedIdException("I cannot do that as that is not a valid Task ID "
                                    + "(ID provided belongs to a deleted task)\n");
                        }
                        taskList.get(id).setStatus(false);
                        System.out.println("Understood, I have marked the following task as not done:");
                        System.out.println("    " + taskList.get(id).toString() + "\n");
                    }
                } catch (MissingIdException | NotIntegerIdException | OutOfBoundIdException | DeletedIdException e) {
                    System.out.println(e.getMessage());
                }
            } else if (currentCommand.startsWith("todo ")) {
                // Checks if the user provided a description
                // If so, adds the 'To do' Task to the task list normally
                // Else throws the custom MissingDescriptionException error
                try {
                    String currTask = currentCommand.substring(FINDCOMMAND_TODO_OFFSET);
                    if (currTask.isEmpty()) {
                        throw new MissingDescriptionException("The description of a todo cannot be empty\n");
                    } 
                    // Adds the Task normally to the task list if no errors are detected
                    Todo newTodo = new Todo(currTask);
                    taskList.add(Task.getTaskCount(), newTodo);
                    System.out.println("Task added:\n" +
                            "    " + newTodo + "\n");
                    
                } catch (MissingDescriptionException e) {
                    System.out.println(e.getMessage());
                }
            } else if (currentCommand.startsWith("deadline ")) {
                // Checks if the user provided a proper description and "by" date/time.
                // If so, adds the 'deadline' Task to the task list normally
                // Else throws the custom MissingDescriptionException error
                try {
                    int byDateIndex = currentCommand.indexOf("/by ");
                    if (byDateIndex == -1 || currentCommand.length() == byDateIndex + FINDFIELD_BY_OFFSET) {
                        // If the "/by " block is missing, throws the MissingByException
                        throw new MissingByException("I cannot do that as the deadline has not been provided.\n" 
                                + "Please add ' /by <Time/Date>' after the task description\n");
                    } 
                    if ((byDateIndex - 1) <= FINDCOMMAND_DEADLINE_OFFSET) {
                        throw new MissingDescriptionException("The description of a deadline cannot be empty\n");
                    }
                    String currTask = currentCommand.substring(FINDCOMMAND_DEADLINE_OFFSET, byDateIndex - 1);
                    String byDate = currentCommand.substring(byDateIndex + FINDFIELD_BY_OFFSET);
                    // Adds the 'Deadline' Task to the task list
                    Deadline newDeadline = new Deadline(currTask, byDate);
                    taskList.add(Task.getTaskCount(), newDeadline);
                    System.out.println("Deadline added:\n" +
                            "    " + newDeadline + "\n");
                        
                } catch (MissingDescriptionException | MissingByException e) {
                    System.out.println(e.getMessage());
                }
            } else if (currentCommand.startsWith("event ")) {
                // Checks if the user provided a description
                // If so, adds the 'dead' Task to the task list normally
                // Else throws the custom MissingDescriptionException error
                try {
                    int fromDateIndex = currentCommand.indexOf("/from ");
                    if (fromDateIndex == -1) {
                        // If the "/from " block is missing, throws the MissingFromException
                        throw new MissingFromException("I cannot do that as the start time has not been provided.\n"
                                + "Please add ' /from <Time/Date>' after the task description\n");
                    }
                    
                    int toDateIndex = currentCommand.indexOf("/to ");
                    if (toDateIndex == -1) {
                        // If the "/to " block is missing, throws the MissingByException
                        throw new MissingToException("I cannot do that as the end time has not been provided.\n"
                                + "Please add ' /to <Time/Date>' after the task end date (after /from block)\n");
                    }
                    
                    if ((fromDateIndex - 1) <= FINDCOMMAND_DEADLINE_OFFSET) {
                        throw new MissingDescriptionException("The description of a event cannot be empty\n");
                    }
                    
                    String currTask = currentCommand.substring(FINDCOMMAND_EVENT_OFFSET, fromDateIndex - 1);

                    if ((toDateIndex - 1) <= (fromDateIndex + FINDFIELD_FROM_OFFSET)) {
                        // If the /from block is present but no data has been given to the field, throw 
                        // the MissingFromException
                        throw new MissingFromException("I cannot do that as the start time has not been provided.\n"
                                + "Please add ' /from <Time/Date>' after the task description\n");
                    }
                    String fromDate = currentCommand.substring(fromDateIndex + FINDFIELD_FROM_OFFSET, toDateIndex - 1);

                    if (currentCommand.length() == (toDateIndex + FINDFIELD_TO_OFFSET)) {
                        // If the /to block is present but no data has been given to the field, throw 
                        // the MissingToException
                        throw new MissingToException("I cannot do that as the end time has not been provided.\n"
                                + "Please add ' /to <Time/Date>' after the task end date (after /from block)\n");
                    }
                    String toDate = currentCommand.substring(toDateIndex + FINDFIELD_TO_OFFSET);

                    // Adds the 'Event' Task to the task list
                    Event newEvent = new Event(currTask, fromDate, toDate);
                    taskList.add(Task.getTaskCount(), newEvent);
                    System.out.println("Event added:\n" +
                            "    " + newEvent + "\n");
                } catch (MissingDescriptionException | MissingFromException | MissingToException e) {
                    System.out.println(e.getMessage());
                }
            } else if (currentCommand.startsWith("delete ")) {
                // Gets the task ID that the user wish to delete
                String idString = currentCommand.substring(FINDCOMMAND_DELETE_OFFSET);
                // If the task ID is invalid or not found, throw an error
                // Else, update the task's status and notifies the user
                try {
                    if (idString.isEmpty()) {
                        // id field is empty
                        throw new MissingIdException("I cannot do that as you have not provided me with a Task ID\n");
                    } else if (isNotNumber(idString)) {
                        // id field is not an integer
                        throw new NotIntegerIdException("I cannot do that as that is not a valid Task ID "
                                + "(ID provided is not an integer)\n");
                    } else if (Integer.parseInt(idString) > Task.getTaskCount()) {
                        // id does not exist
                        throw new OutOfBoundIdException("I cannot do that as that is not a valid Task ID "
                                + "(ID provided does not exist)\n");
                    } else {
                        // Updates the task status
                        int id = Integer.parseInt(idString);
                        if (taskList.get(id) == null) {
                            // If the id to be marked belongs to a deleted task (null), throws the DeletedIdException
                            throw new DeletedIdException("I cannot do that as that is not a valid Task ID "
                                    + "(ID provided belongs to a deleted task)\n");
                        }
                        System.out.println("Understood, I have deleted the following task:");
                        System.out.println("    " + taskList.get(id).toString() + "\n");
                        taskList.set(id, null);
                    }
                } catch (MissingIdException | NotIntegerIdException | OutOfBoundIdException | DeletedIdException e) {
                    System.out.println(e.getMessage());
                }
            } else if (currentCommand.equals("bye")) {
                // Before the actual termination of the program, writes the current task list to the external file.
                // Starts by creating the text to write to the output file
                StringBuilder sb = new StringBuilder();
                for (Task currTask : taskList) {
                    // If the task is already deleted from the list, (represented as null object)
                    // don't write it to the file
                    if (currTask != null) {
                        // Determines what type of Task is being handled currently for printing purposes
                        if (currTask instanceof Todo) {
                            sb.append("T |");
                        } else if (currTask instanceof Deadline) {
                            sb.append("D |");
                        } else {
                            sb.append("E |");
                        }
                        
                        // Writes the status of the task
                        if (currTask.isDone()) {
                            sb.append(" 1 | ");
                        } else {
                            sb.append(" 0 | ");
                        }
                        
                        // Writes the description and other tracked data.
                        if (currTask instanceof Todo) {
                            sb.append(currTask.getDesc())
                                    .append("\n");
                        } else if (currTask instanceof Deadline) {
                            sb.append(currTask.getDesc())
                                    .append(" | ")
                                    .append(((Deadline) currTask).getDeadline())
                                    .append("\n");
                        } else {
                            sb.append(currTask.getDesc())
                                    .append(" | ")
                                    .append(((Event) currTask).getStartTime())
                                    .append(" | ")
                                    .append(((Event) currTask).getEndTime())
                                    .append("\n");
                        }
                    }
                }
                // Writes the text to the output file
                try {
                    BufferedWriter bw = Files.newBufferedWriter(dataPath);
                    bw.write(sb.toString());
                    bw.flush();
                    bw.close();
                } catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
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

    /**
     * Checks if a string is only made up of integer numbers.
     * Uses regex to determine if a string contains only integers
     *
     * @param input The string to be checked
     * @return Whether the string is fully comprised of integers
     */
    private static boolean isNotNumber(String input) {
        return !input.matches("[0-9]+");
    }

}
