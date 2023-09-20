package com.nyanbot.duketasklist;

import java.io.IOException;
import java.util.ArrayList;

import com.nyanbot.dukeexceptions.DukeException;
import com.nyanbot.dukeparsers.DukeParser;
import com.nyanbot.dukestorage.DukeStorageDatabase;
import com.nyanbot.duketasks.Deadline;
import com.nyanbot.duketasks.Event;
import com.nyanbot.duketasks.Task;
import com.nyanbot.duketasks.Todo;
import com.nyanbot.dukeuiclasses.DukeErrorUi;
import com.nyanbot.dukeuiclasses.DukeUi;

/**
 * Encapsulates a class which supports the manipulation of the task list.
 *
 * @author Tan Kerway
 */
public class DukeTaskList {
    private ArrayList<Task> tasks;
    private final DukeErrorUi errorUI;
    private DukeStorageDatabase databaseController;
    private DukeParser parser;
    private final DukeUi ui;

    /**
     * Constructs an instance of the DukeTaskList. Initialises the
     * required DukeLauncher.Duke utility classes.
     *
     * @author Tan Kerway
     */
    public DukeTaskList() {
        this.errorUI = new DukeErrorUi();
        this.tasks = new ArrayList<>();
        this.ui = new DukeUi();
    }

    /**
     * Sets the database object.
     *
     * @author Tan Kerway
     * @param databaseController the DukeStorageDatabase object
     */
    public void setDatabaseController(DukeStorageDatabase databaseController) throws IOException {
        this.databaseController = databaseController;
        this.setTasks(this.databaseController.loadDatabase());
    }

    /**
     * Sets the parser object.
     *
     * @author Tan Kerway
     * @param parser the parser object
     */
    public void setParser(DukeParser parser) {
        this.parser = parser;
    }

    /**
     * Loads the list of tasks from the database(if any).
     *
     * @author Tan Kerway
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the current list of tasks.
     *
     * @author Tan Kerway
     * @return the list of tasks that the chatbot has
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets the current task given the list of tokens.
     *
     * @author Tan Kerway
     * @param tokens the input on the current line of the database
     * @return the task if the operation completes normally. can be Todo, Deadline, or Event
     * @throws IOException when the command is not recognised
     */
    public Task getTask(String[] tokens) throws IOException {
        int n = tokens.length;
        Task currentTask;
        StringBuilder description = new StringBuilder();
        switch (tokens[0]) {
        case "t":
            currentTask = processTodo(tokens, description);
            break;
        case "d": {
            StringBuilder sb = new StringBuilder();
            currentTask = processDeadline(tokens, description, sb);
            if (((Deadline) currentTask).getBy() == null) {
                return null;
            }
            break;
        }
        case "e": {
            currentTask = processEvent(tokens, description);
            Event t = (Event) currentTask;
            if (t.getFrom() == null || t.getTo() == null || !t.isValid()) {
                return null;
            }
            break;
        }
        default:
            throw new IOException("Bad file type!");
        }
        assert currentTask != null;
        return currentTask;
    }

    /**
     * Processes a Todo type task.
     * @author Tan Kerway
     * @param tokens the string, broken up by spaces
     * @param description the StringBuilder to append the result to
     * @return a Todo task
     */
    private Todo processTodo(String[] tokens, StringBuilder description) {
        int n = tokens.length;
        for (int i = 2; i < n; i++) {
            description.append(tokens[i]);
            if (i != n - 1) {
                description.append(" ");
            }
        }
        return new Todo(description.toString(), Boolean.parseBoolean(tokens[1]));
    }

    /**
     * Processes a Deadline type task.
     * @author Tan Kerway
     * @param tokens the string array, but broken up
     * @param description the StringBuilder to build the description on
     * @param sb the StringBuilder to build the StringBuilder class
     * @return a Deadline class type
     */
    private Deadline processDeadline(String[] tokens, StringBuilder description, StringBuilder sb) {
        int n = tokens.length;
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
        for (; i < n; i++) {
            sb.append(tokens[i]);
            if (i != n - 1) {
                sb.append(" ");
            }
        }
        return new Deadline(description.toString().trim(), Boolean.parseBoolean(tokens[1]), sb.toString());
    }

    /**
     * Processes an Event type class.
     * @author Tan Kerway
     * @param tokens the array of tokens to process, broken up by space
     * @param description the StringBuilder that will process the description that the user typed in
     * @return the Event instance
     */
    private Event processEvent(String[] tokens, StringBuilder description) {
        int n = tokens.length;
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
        return new Event(description.toString().trim(),
            Boolean.parseBoolean(tokens[1]), sb1.toString().trim(), sb2.toString().trim());
    }

    /**
     * Adds a task to the local task list. Handles the three different types
     * class.
     *
     * @author Tan Kerway
     * @param task the task to be added to the current list of tasks
     * @return the task object created that was just added
     */
    public Task addTask(String task) {
        String description;
        Task createdTask;
        try {
            int taskDescriptionLength = task.length();
            // handle each command type and handle their respective errors
            if (task.startsWith("todo")) {
                createdTask = this.errorUI.handleTodoErrors(task, taskDescriptionLength);
                if (createdTask == null) {
                    description = task.substring(5);
                    createdTask = new Todo(description);
                }
            } else if (task.startsWith("deadline")) {
                int indexOfBy = task.indexOf("/by");
                createdTask = this.errorUI.handleDeadlineErrors(task, taskDescriptionLength, indexOfBy);
                if (createdTask == null) {
                    description = task.substring(9, indexOfBy - 1);
                    String by = task.substring(indexOfBy + 4);
                    createdTask = new Deadline(description, by);
                }
            } else {
                int fromStart = task.lastIndexOf("/from");
                int toStart = task.lastIndexOf("/to");
                createdTask = this.errorUI.handleEverythingElseError(task, fromStart, toStart, taskDescriptionLength);
                if (createdTask == null) {
                    description = task.substring(6, fromStart - 1);
                    String period = task.substring(fromStart);
                    createdTask = new Event(description, period);
                }
            }
            if (createdTask.isValid()) {
                this.tasks.add(createdTask);
                this.databaseController.saveTaskList();
            }
            return createdTask;
        } catch (DukeException | IOException ignored) {
            String s = "";
        }
        return null;
    }

    /**
     * Marks a task as done.
     *
     * @author Tan Kerway
     * @param currentTask the task to be marked as done
     *
     */
    public void handleMarkTask(Task currentTask) {
        assert currentTask != null;
        currentTask.markDone();
    }

    /**
     * Marks a task as done.
     *
     * @author Tan Kerway
     * @param currentTask the task to be marked as done
     */
    public void handleUnmarkTask(Task currentTask) {
        assert currentTask != null;
        currentTask.markUndone();
    }

    /**
     * Handles the delete command.
     *
     * @author Tan Kerway
     * @param input the input string that the user entered
     * @return the String that represents the user's response
     */
    public String handleDelete(String input) {
        try {
            // gc: no items to delete
            if (this.tasks.isEmpty()) {
                return this.errorUI.handleEmptyTasksList();
            }
            // get the string containing the index
            Integer numberString = this.parser.parseString(input.substring(6));
            // gc: string not parsable
            if (numberString == null) {
                // handle the error
                return this.errorUI.handleInvalidIndex();
            }
            // delete the task and announce that the task has been deleted
            return this.ui.echoTaskDeleted(deleteTask(numberString), this.tasks.size());
        } catch (IOException ignored) {
            String s = "";
        }
        return "";
    }

    /**
     * Handles the unmark command.
     *
     * @author Tan Kerway
     * @param input the input string that the user entered
     * @return the response String to a task unmarked
     */
    // todo: make this return string
    public String handleUnmark(String input) {
        assert this.errorUI != null;
        // gc: we cannot unmark a task that definitely does not exist in an empty list
        if (this.tasks.isEmpty()) {
            return this.errorUI.handleEmptyTasksList();
        }
        Integer numberString = this.parser.parseString(input.substring(7));
        // null is returned by the parseString method if the input is not parsable
        if (numberString == null) {
            return this.errorUI.handleInvalidIndex();
        }
        Task currentTask = this.tasks.get(Integer.parseInt(input.substring(7, 8)) - 1);
        this.handleUnmarkTask(currentTask);
        return this.ui.echoTaskUnmarked(currentTask);
    }

    /**
     * Handles the mark command.
     *
     * @author Tan Kerway
     * @param input the input string that the user entered
     * @return the response String of the chatbot
     */
    public String handleMark(String input) {
        assert this.errorUI != null;
        // gc: we cannot mark a task that definitely does not exist in an empty list
        if (this.tasks.isEmpty()) {
            return this.errorUI.handleEmptyTasksList();
        }
        Integer numberString = this.parser.parseString(input.substring(5));
        // null is returned by the parseString method if the input is not parsable
        if (numberString == null) {
            return this.errorUI.handleInvalidIndex();
        }
        Task currentTask = this.tasks.get(numberString - 1);
        this.handleMarkTask(currentTask);
        return this.ui.echoTaskMarked(currentTask);
    }

    /**
     * Finds and prints the list of tasks that the user has
     * searched for. Only prints tasks that contain the search
     * String.
     *
     * @author Tan Kerway
     * @param input the input String the user has typed in,
     *              including the "find" command
     * @return the String that represents the chatbot response
     */
    public String handleFind(String input) {
        assert this.errorUI != null;
        // case where the input is empty
        if (this.tasks.isEmpty()) {
            return this.errorUI.handleEmptyTasksList();
        }
        String query = input.substring(4).trim();
        ArrayList<Task> matchingTasks = getMatchingTasks(query);
        return printMatchingTasks(matchingTasks);
    }

    /**
     * Returns the list of tasks that match the given query.
     *
     * @author Tan Kerway
     * @param query the input string
     * @return the list of tasks that contain the query
     */
    private ArrayList<Task> getMatchingTasks(String query) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        assert this.tasks != null;
        this.tasks
                .stream()
                .filter((currentTask) -> currentTask.getDescription().contains(query))
                .forEach(matchingTasks::add);
        return matchingTasks;
    }

    /**
     * Prints the list of matching tasks to the console.
     *
     * @author Tan Kerway
     * @param matchingTasks the list of tasks that match the query
     * @return a String telling the user that there are no matching tasks
     *         if there are 0 matching tasks, else the list of tasks if there
     *         are a non-zero number of matches
     */
    private String printMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return "No nyatching tasks found!";
        }
        StringBuilder response = new StringBuilder();
        response.append("Here are the nyatching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            response.append((i + 1)).append(".").append(matchingTasks.get(i)).append("\n");
        }
        return response.toString();
    }

    /**
     * Deletes the task at the given index.
     *
     * @author Tan Kerway
     * @param index the index of the task to delete
     * @return the deleted task instance
     */
    private Task deleteTask(int index) throws IOException {
        assert index >= 0;
        assert index <= this.tasks.size();
        Task deletedTask = this.tasks.remove(index - 1);
        this.databaseController.saveTaskList();
        return deletedTask;
    }
}
