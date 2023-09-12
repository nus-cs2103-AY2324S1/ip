package com.nyanbot.duketasklist;

import com.nyanbot.dukeexceptions.DukeException;
import com.nyanbot.dukeparsers.DukeParser;
import com.nyanbot.dukestorage.DukeStorageDatabase;
import com.nyanbot.duketasks.Deadline;
import com.nyanbot.duketasks.Event;
import com.nyanbot.duketasks.Task;
import com.nyanbot.duketasks.Todo;
import com.nyanbot.dukeuiclasses.DukeErrorUi;
import com.nyanbot.dukeuiclasses.DukeUi;

import java.io.IOException;
import java.util.ArrayList;

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
            // get the length of the task
            int taskDescriptionLength = task.length();
            // handle the 3 different types of class
            if (task.startsWith("todo")) {
                // handle errors, capture the error message in an InvalidClass instance
                createdTask = this.errorUI.handleTodoErrors(task, taskDescriptionLength);
                if (createdTask == null) {
                    description = task.substring(5);
                    createdTask = new Todo(description);
                }
            } else if (task.startsWith("deadline")) {
                // cache the start index of the "/by" substring
                int indexOfBy = task.indexOf("/by");
                createdTask = this.errorUI.handleDeadlineErrors(task, taskDescriptionLength, indexOfBy);
                if (createdTask == null) {
                    // get the task description
                    description = task.substring(9, indexOfBy - 1);
                    // get the deadline
                    String by = task.substring(indexOfBy + 4);
                    createdTask = new Deadline(description, by);
                }
            } else {
                int fromStart = task.lastIndexOf("/from");
                int toStart = task.lastIndexOf("/to");
                createdTask = this.errorUI.handleEverythingElseError(task, fromStart, toStart, taskDescriptionLength);
                if (createdTask == null) {
                    // get the task description
                    description = task.substring(6, fromStart - 1);
                    // get the string that holds the start and the end
                    String period = task.substring(fromStart);
                    createdTask = new Event(description, period);
                }
            }
            if (createdTask.isValid()) {
                this.tasks.add(createdTask);
                this.databaseController.saveTaskList();
            }
            return createdTask;
        } catch (DukeException | IOException ignored) {}
        return null;
    }

    /**
     * Marks a task as done.
     *
     * @author Tan Kerway
     * @param currentTask the task to be marked as done
     *
     */
    public void handleMarkTask(Task currentTask){
        currentTask.markDone();
    }

    /**
     * Marks a task as done.
     *
     * @author Tan Kerway
     * @param currentTask the task to be marked as done
     */
    public void handleUnmarkTask(Task currentTask){
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
        } catch (IOException ignored) {}
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
        // gc: no items to delete
        if (this.tasks.isEmpty()) {
            return this.errorUI.handleEmptyTasksList();
        }
        // get the string containing the index
        Integer numberString = this.parser.parseString(input.substring(7));
        // gc: string not parsable
        if (numberString == null) {
            // handle the error
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
        // gc: no items to delete
        if (this.tasks.isEmpty()) {
            return this.errorUI.handleEmptyTasksList();
        }
        // get the string containing the index
        Integer numberString = this.parser.parseString(input.substring(5));
        // gc: string not parsable
        if (numberString == null) {
            // handle the error
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
        // case where the input is empty
        if (this.tasks.isEmpty()) {
            return this.errorUI.handleEmptyTasksList();
        }
        // get the substring that is the search query
        String query = input.substring(4).trim();
        // get the list of tasks that contain the query String
        ArrayList<Task> matchingTasks = getMatchingTasks(query);
        // print the matching tasks
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
        for (Task currentTask : this.tasks) {
            if (currentTask.getDescription().contains(query)) {
                matchingTasks.add(currentTask);
            }
        }
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
        // gc: task list is empty
        if (matchingTasks.isEmpty()) {
            return "No nyatching tasks found!";
        } else {
            StringBuilder response = new StringBuilder();
            response.append("Here are the nyatching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                response.append((i + 1)).append(".").append(matchingTasks.get(i)).append("\n");
            }
            return response.toString();
        }
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
        this.databaseController.saveTaskList();
        // return the deleted task
        return deletedTask;
    }
}
