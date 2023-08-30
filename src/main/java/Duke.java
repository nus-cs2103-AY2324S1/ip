import DukeExceptions.*;
import DukeTasks.Deadline;
import DukeTasks.Event;
import DukeTasks.Task;
import DukeTasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    // hardcoded file paths
    private static final String DIR_PATH = System.getProperty("user.dir") + "/data";
    private static final String FILE_PATH = System.getProperty("user.dir") + "/data/duke.txt";

    /**
     * Constructs an instance of a chatbot class.
     *
     * @author Tan Kerway
     * @throws IOException if the database is unable to be loaded
     */
    public Duke() throws IOException {
        this.tasks = loadDatabase();
    }

    /**
     * Loads the database from the file path(if any).
     *
     * @author Tan Kerway
     * @return the list of tasks in the database
     */
    private ArrayList<Task> loadDatabase() throws IOException {
        // get the directory
        File dir = new File(DIR_PATH);
        // if the dir does not exist, we create one
        if (!dir.exists()) {
            dir.mkdir();
        }
        // get the database
        File database = new File(FILE_PATH);
        // task list loaded from the tasks database
        ArrayList<Task> taskList = new ArrayList<>();
        // create the file if it does not exist
        // and if it does not exist, the method will return true
        if(!database.exists()) {
            database.createNewFile();
            // since the database is empty, just return an empty arraylist
            return taskList;
        }
        // else, we need to load the tasks onto the Duke instance
        // use a scanner to read each line
        Scanner reader = new Scanner(database);
        while (reader.hasNextLine()) {
            // get the input
            String[] tokens = reader.nextLine().split(" ");
            // get the input length
            Task currentTask = getTask(tokens);
            if (currentTask == null || currentTask.isValid()) {
                // add the current task to the list of tasks
                taskList.add(currentTask);
            }
        }
        // close the scanner
        reader.close();
        // return the final tasklist loaded from the database
        return taskList;
    }

    /**
     * Gets the current task given the list of tokens.
     *
     * @author Tan Kerway
     * @param tokens the input on the current line of the database
     * @return the task if the operation completes normally. can be Todo, Deadline, or Event
     * @throws IOException when the command is not recognised
     */
    private Task getTask(String[] tokens) throws IOException {
        int n = tokens.length;
        // get the task
        Task currentTask;
        StringBuilder description = new StringBuilder();
        // case where todo
        switch (tokens[0]) {
        case "t":
            for (int i = 2; i < n; i++) {
                description.append(tokens[i]);
                if (i != n - 1) {
                    description.append(" ");
                }
            }
            currentTask = new Todo(description.toString(), Boolean.parseBoolean(tokens[1]));
            break;
        case "d": {
            int i;
            for (i = 2; i < n; i++) {
                if (tokens[i].equals("by")) {
                    i++;
                    break;
                }
                description.append(tokens[i]);
                if (i != n - 2) {
                    description.append(" ");
                }
            }
            StringBuilder sb = new StringBuilder();
            for (; i < n; i++) {
                sb.append(tokens[i]);
                if (i != n - 1) {
                    sb.append(" ");
                }
            }
            currentTask = new Deadline(description.toString().trim(), Boolean.parseBoolean(tokens[1]), sb.toString());
            // case where the deadline is not valid
            if (((Deadline) currentTask).getBy() == null) {
                return null;
            }
            break;
        }
        case "e": {
            int i;
            for (i = 2; i < n - 2; i++) {
                if (tokens[i].equals("from")) {
                    i++;
                    break;
                }
                description.append(tokens[i]);
                if (i != n - 1) {
                    description.append(" ");
                }
            }
            StringBuilder sb1 = new StringBuilder();
            while (!tokens[i].equals("to")) {
                sb1.append(tokens[i++]);
                sb1.append(" ");
            }
            i++;
            StringBuilder sb2 = new StringBuilder();
            for (; i < n; i++) {
                sb2.append(tokens[i]);
                sb2.append(" ");
            }
            currentTask = new Event(description.toString().trim(), Boolean.parseBoolean(tokens[1]), sb1.toString().trim(), sb2.toString().trim());
            Event t = (Event) currentTask;
            // case where the deadline is not valid
            if (t.getFrom() == null || t.getTo() == null || !t.isValid()) {
                return null;
            }
            break;
        }
        default:
            throw new IOException("Bad file type!");
        }
        return currentTask;
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
        // list all the tasks
        listAllTasks();
    }

    /**
     * Prints the current list of tasks.
     *
     * @author Tan Kerway
     */
    void listAllTasks() {
        if (tasks.isEmpty()) { return; }
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
        Task createdTask;
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
            if (createdTask.isValid()) {
                this.tasks.add(createdTask);
                saveTaskList();
                return createdTask;
            }
        } catch (DukeException | IOException ignored) {}
        return null;
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
        if (taskDescriptionLength == 8 || (indexOfBy != -1 &&taskString.substring(8, taskString.lastIndexOf("/by") + 1).trim().isEmpty())) {
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
    private static void handleNoDate(String details) throws DukeException {
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
        System.out.println("Got it. I've added this task:\n    " + task);
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
        if (createdTask != null) {
            echoTaskAdded(createdTask);
        }
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
        } catch (DukeException | IOException ignored) {}
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
    private Task deleteTask(int index) throws IOException {
        // remove the task
        Task deletedTask = this.tasks.remove(index - 1);
        // update the list
        saveTaskList();
        // return the deleted task
        return deletedTask;
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
        boolean isNotDone;
        while (sc.hasNextLine()) {
            input = sc.nextLine(); // get the input from the user
            isNotDone = processUserCommand(input); // process the input
            if (!isNotDone) { break; }
        }
        sc.close();
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
     * Saves the latest list of user's tasks to the database
     *
     * @author Tan Kerway
     */
    private void saveTaskList() throws IOException {
        // get the file
        File database = new File(FILE_PATH);
        // delete the file
        database.delete();
        // create new database
        database.createNewFile();
        try (FileWriter writer = new FileWriter(database)) {
            // loop over the tasklist and add the tasks
            for (Task task : this.tasks) {
                // stringBuilder class to parse the task into a string for database storage
                StringBuilder taskString = new StringBuilder();
                if (task instanceof Todo) {
                    taskString.append("t ");
                    taskString.append(task.isDone());
                    taskString.append(" ");
                    taskString.append(task.getDescription());
                } else if (task instanceof Deadline) { // case where deadline
                    taskString.append("d ");
                    taskString.append(task.isDone());
                    taskString.append(" ");
                    taskString.append(task.getDescription());
                    Deadline temp = (Deadline) task;
                    taskString.append(" by ");
                    taskString.append(temp.getBy());
                } else if (task instanceof Event) { // case where event
                    taskString.append("e ");
                    taskString.append(task.isDone());
                    taskString.append(" ");
                    taskString.append(task.getDescription());
                    Event temp = (Event) task;
                    taskString.append(" from ");
                    taskString.append(temp.getFrom());
                    taskString.append(" to ");
                    taskString.append(temp.getTo());
                } else {
                    throw new IOException("Bad task type!");
                }
                // add the parsed string to the database
                writer.write(taskString.toString());
                // shift to next line
                writer.write(System.getProperty("line.separator"));
            }
            writer.flush();
        }
    }

    /**
     * Function that starts a chat with the user.
     *
     * @author Kerway
     */
    private void initiateChat() throws IOException {
        greet();           // warmly welcome the user
        handleUserInput(); // take in the user input
        sayGoodBye();      // say goodbye to the user
        saveTaskList();    // save the user's task to the database
    }

    /**
     * This main function, when run, will cause the Chat Bot
     * to greet the user, and then exit.
     *
     * @author Tan Kerway
     * @param args pointer to some array of command-line arguments
     */
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        try {
            Duke dukeInstance = new Duke();
            dukeInstance.initiateChat();
        } catch (IOException e) {
            System.out.println("There was an issue accessing my nyanory :c");
        }
    }
}
