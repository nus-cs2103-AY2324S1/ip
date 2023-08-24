import DukeExceptions.*;
import DukeTasks.Deadline;
import DukeTasks.Event;
import DukeTasks.Task;
import DukeTasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class encapsulates the skeleton of the nyanbot.
 *
 * @author Tan Kerway
 */
public class Duke {
    // arraylist to store the user's tasks, as a DukeTasks.Task object
    private final ArrayList<Task> tasks;

    /**
     * Constructs an instance of a chatbot class.
     *
     * @author Tan Kerway
     */
    public Duke() {
        this.tasks = new ArrayList<>();
    }

    /**
     * When called, the chatbot will greet the user.
     *
     * @author Tan Kerway
     */
    private void greet() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Hello! I'm nyancatbot!\nWhat can I do for nyan?");
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Prints the current list of tasks.
     *
     * @author Tan Kerway
     */
    void listAllTasks() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Here are the tasks in your list :3");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Adds a task to the local task list. Handles the three different types
     * class.
     *
     * @author Tan Kerway
     * @param task the task to be added to the current list of tasks
     * @return the task object created that was just added
     */
    private Task addTask(String task) {
        String description;
        Task createdTask = null;
        try {
            // get the length of the task
            int taskDescriptionLength = task.length();
            // handle the 3 different types of class
            if (task.startsWith("todo")) {
                // handle errors
                handleTodoErrors(task, taskDescriptionLength);
                description = task.substring(5);
                createdTask = new Todo(description);
            } else if (task.startsWith("deadline")) {
                // cache the start index of the "/by" substring
                int indexOfBy = task.indexOf("/by");
                handleDeadlineErrors(task, taskDescriptionLength, indexOfBy);
                // get the task description
                description = task.substring(9, indexOfBy - 1);
                // get the deadline
                String by = task.substring(indexOfBy + 4);
                createdTask = new Deadline(description, by);
            } else {
                int fromStart = task.lastIndexOf("/from");
                int toStart = task.lastIndexOf("/to");
                handleEverythingElseError(task, fromStart, toStart, taskDescriptionLength);
                // get the task description
                description = task.substring(6, fromStart - 1);
                // get the string that holds the start and the end
                String period = task.substring(fromStart);
                createdTask = new Event(description, period);
            }
            this.tasks.add(createdTask);
            return createdTask;
        } catch (DukeException ignored) {}
        return createdTask;
    }

    /**
     * Returns errors associated with the event command and invalid commands.
     *
     * @author Tan Kerway
     * @param taskString the description of the task
     * @param fromStart the index of the /from string
     * @param toStart the index of the /to string
     * @param taskDescriptionLength the length of the taskString
     */
    private void handleEverythingElseError(String taskString, int fromStart, int toStart, int taskDescriptionLength) throws DukeException {
        // handle errors
        if (!taskString.startsWith("event")) {
            handleInvalidCommand();
        }
        if (taskDescriptionLength == 5) {
            handleEmptyCommand("event");
        }
        if (fromStart == -1 && toStart == -1) {
            handleNoDate("from and to");
        }
        if (fromStart == -1 || fromStart + 5 == toStart) {
            handleNoDate("from");
        }
        if (toStart == -1 || toStart + 3 == taskDescriptionLength) {
            handleNoDate("to");
        }
        if (taskString.substring(fromStart + 5, toStart).trim().equals("")) {
            handleNoDate("from");
        }
    }

    /**
     * Returns errors associated with the deadline command.
     *
     * @author Tan Kerway
     * @param taskString the task description
     * @param taskDescriptionLength the length of the task
     * @param indexOfBy the index of the /by string
     */
    private void handleDeadlineErrors(String taskString, int taskDescriptionLength, int indexOfBy) throws DukeException {
        // handle errors
        if (taskDescriptionLength == 8) {
            handleEmptyCommand("deadline");
        }
        if (!taskString.contains("/by") || indexOfBy + 3 == taskString.length() || taskString.substring(indexOfBy + 3).trim().equals("")) {
            handleNoDate("by");
        }
    }

    /**
     * Returns errors associated with the todo command.
     *
     * @author Tan Kerway
     * @param taskString the input that the user typed in
     * @param taskDescriptionLength the length of the task that the user typed in
     */
    private void handleTodoErrors(String taskString, int taskDescriptionLength) throws DukeException{
        if (taskDescriptionLength == 4 || taskString.substring(4).trim().equals("")) {
            handleEmptyCommand("todo");
        }
    }

    /**
     * Prints error message telling the user that the command cannot be empty
     *
     * @author Tan Kerway
     * @param message the input command that the user typed in
     */
    private void handleEmptyCommand(String message) throws DukeException {
        DukeException res = new DukeEmptyInputException(message);
        System.out.println("------------------------------------------------------------------------");
        System.out.println(res.getMessage());
        System.out.println("------------------------------------------------------------------------");
        throw res;
    }

    /**
     * Prints error message telling the user that the command is invalid.
     *
     * @author Tan Kerway
     */
    private void handleInvalidCommand() throws DukeException {
        DukeException res = new DukeInvalidCommandException();
        System.out.println("------------------------------------------------------------------------");
        System.out.println(res.getMessage());
        System.out.println("------------------------------------------------------------------------");
        throw res;
    }

    /**
     * Prints error message telling the user that the command is invalid.
     * Applicable for the deadline and event command.
     *
     * @author Tan Kerway
     * @param details the String containing the missing info that the user did
     *                not type in
     */
    private void handleNoDate(String details) throws DukeException {
        DukeException res = new DukeInvalidTimeException(details);
        System.out.println("------------------------------------------------------------------------");
        System.out.println(res.getMessage());
        System.out.println("------------------------------------------------------------------------");
        throw res;
    }

    /**
     * Echos the task that was added to the task list to
     * the console.
     *
     * @author Tan Kerway
     * @param task the task to be echoed to the console
     */
    void echoTaskAdded(Task task) {
        if (task == null) { return; }
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Got it. I've added this task:\n    " + task.toString());
        System.out.println("Nyan you have " + tasks.size() + " tasks in the list.");
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Marks a task as done.
     *
     * @author Tan Kerway
     * @param currentTask the task to be marked as done
     */
    private void handleMarkTask(Task currentTask){
        currentTask.markDone();
    }

    /**
     * Echos that the task has been marked.
     *
     * @author Tan Kerway
     * @param currentTask the marked task to be echoed
     */
    private void echoTaskMarked(Task currentTask) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Nice! I've marked this task as nyan:");
        System.out.println("    " + currentTask);
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Marks a task as done.
     *
     * @author Tan Kerway
     * @param currentTask the task to be marked as done
     */
    private void handleUnmarkTask(Task currentTask){
        currentTask.markUndone();
    }

    /**
     * Echos that the task has been marked.
     *
     * @author Tan Kerway
     * @param currentTask the marked task to be echoed
     */
    private void echoTaskUnmarked(Task currentTask) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("OK, I've marked this task as not nyan yet:");
        System.out.println("    " + currentTask);
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Processes user commands. Breaks down the input and
     * Checks which command the user types in.
     *
     * @author Tan Kerway
     * @param input the input to be processed
     * @return true if the command is not "bye", false otherwise
     */
    private boolean processUserCommand(String input) {
        // case where the chatbot has been first initialised
        if (input == null) { return true; }
        // case where the input is "list" => enumerate all tasks
        if (input.equals("list")) {
            listAllTasks();
            return true;
        }
        // case where the input is "bye" => terminate early
        if (input.equals("bye")) { return false; }
        // case where the input is the mark command => mark the task as done
        if (input.startsWith("mark")) {
            handleMark(input);
            return true;
        }
        // case where the input is unmark
        if (input.startsWith("unmark")) {
            handleUnmark(input);
            return true;
        }
        // case where the input is deleted
        if (input.startsWith("delete")) {
            handleDelete(input);
            return true;
        }
        Task createdTask = addTask(input);
        echoTaskAdded(createdTask);
        return true;
    }

    /**
     * Handles the delete command.
     *
     * @author Tan Kerway
     * @param input the input string that the user entered
     */
    private void handleDelete(String input) {
        try {
            // gc: no items to delete
            if (this.tasks.isEmpty()) {
                handleEmptyTasksList();
                return;
            }
            // get the string containing the index
            Integer numberString = parseString(input.substring(6));
            // gc: string not parsable
            if (numberString == null) {
                // handle the error
                handleInvalidIndex();
                return;
            }
            // delete the task and announce that the task has been deleted
            echoTaskDeleted(deleteTask(numberString));
        } catch (DukeException ignored) {}
    }

    /**
     * Handles the unmark command.
     *
     * @author Tan Kerway
     * @param input the input string that the user entered
     */
    private void handleUnmark(String input) {
        try {
            // gc: no items to delete
            if (this.tasks.isEmpty()) {
                handleEmptyTasksList();
                return;
            }
            // get the string containing the index
            Integer numberString = parseString(input.substring(7));
            // gc: string not parsable
            if (numberString == null) {
                // handle the error
                handleInvalidIndex();
                return;
            }
            Task currentTask = this.tasks.get(Integer.parseInt(input.substring(7, 8)) - 1);
            handleUnmarkTask(currentTask);
            echoTaskUnmarked(currentTask);
        } catch (DukeException ignored) {}
    }

    /**
     * Handles the mark command.
     *
     * @author Tan Kerway
     * @param input the input string that the user entered
     */
    private void handleMark(String input) {
        try {
            // gc: no items to delete
            if (this.tasks.isEmpty()) {
                handleEmptyTasksList();
                return;
            }
            // get the string containing the index
            Integer numberString = parseString(input.substring(5));
            // gc: string not parsable
            if (numberString == null) {
                // handle the error
                handleInvalidIndex();
                return;
            }
            Task currentTask = this.tasks.get(numberString - 1);
            handleMarkTask(currentTask);
            echoTaskMarked(currentTask);
        } catch (DukeException ignored) {}
    }

    /**
     * Handles the case where the tasks list is empty but the user enters a delete command.
     *
     * @author Tan Kerway
     */
    private void handleEmptyTasksList() throws DukeException {
        DukeException e = new DukeEmptyTaskListException();
        System.out.println("------------------------------------------------------------------------");
        System.out.println(e.getMessage());
        System.out.println("------------------------------------------------------------------------");
        throw e;
    }

    /**
     * Echos that the task has been deleted.
     *
     * @author Tan Kerway
     * @param removedTask the index of the deleted task
     */
    private void echoTaskDeleted(Task removedTask) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + removedTask);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Deletes the task at the given index.
     *
     * @author Tan Kerway
     * @param index the index of the task to delete
     * @return the deleted task instance
     */
    private Task deleteTask(int index) {
        // remove the task
        return this.tasks.remove(index - 1);
    }

    /**
     * parses the String. if there is error, this method will return null to
     * indicate unsuccessful parsing.
     *
     * @author Tan Kerway
     * @param numberString the number to parse
     * @return an integer if parsing was successful, null otherwise
     */
    private Integer parseString(String numberString) {
        int res = 0;
        // trim and trailing spaces
        numberString = numberString.trim();
        for (int i = 0; i < numberString.length(); i++) {
            // get the current char
            char currentChar = numberString.charAt(i);
            // gc: not a number
            if (!Character.isDigit(currentChar)) {
                return null;
            }
            // else, add to the res
            res = res * 10 + (currentChar - '0');
        }
        return res - 1 < 0 || res - 1 >= tasks.size() ? null : res;
    }

    /**
     * Method that deals with invalid indexes.
     *
     * @author Tan Kerway
     */
    private void handleInvalidIndex() throws DukeException {
        DukeException e = new DukeInvalidIndexException();
        System.out.println("------------------------------------------------------------------------");
        System.out.println(e.getMessage());
        System.out.println("------------------------------------------------------------------------");
        throw e;
    }

    /**
     * When called, awaits user input. If the input is "list", the tasks
     * that the user has added to the list so far is printed to the console.
     * If the input is "bye" the program will terminate. For other inputs,
     * the input will be added and saved as a task.
     *
     * @author Tan Kerway
     */
    private void handleUserInput() {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean isNotDone = true;
        while (sc.hasNextLine() && isNotDone) {
            input = sc.nextLine(); // get the input from the user
            isNotDone = processUserCommand(input); // process the input
        }
    }

    /**
     * When called, will bid the user farewell.
     *
     * @author Tan Kerway
     */
    private void sayGoodBye() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Bye. Hope to see you a-nyan soon!");
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Function that starts a chat with the user.
     *
     * @author Kerway
     */
    private void initiateChat() {
        greet();
        handleUserInput();
        sayGoodBye();
    }

    /**
     * This main function, when run, will cause the Chat Bot
     * to greet the user, and then exit.
     *
     * @author Tan Kerway
     * @param args pointer to some array of command-line arguments
     */
    public static void main(String[] args) {
        Duke dukeInstance = new Duke();
        dukeInstance.initiateChat();
    }
}
