package rat.tasks;

import static rat.io.RatPrinter.printWithLines;

import java.time.LocalDateTime;
import java.util.ArrayList;

import rat.storage.RatStorage;

/**
 * This class encapsulates the taskList of Rat, which is a list of tasks.
 * Includes methods to add, modify, and delete tasks.
 * Handles writing to storage.
 *
 * @author Keagan
 */
public class RatTaskManager {

    /**
     * The list of tasks managed by RatTaskManager, represented by an ArrayList.
     */
    private final ArrayList<Task> taskList;

    /**
     * The RatStorage object used to read and write the task list to local storage.
     */
    private RatStorage storage;

    /**
     * Constructor for a RatTaskManager object.
     * Checks if stored file is empty and reads data from file if it is not.
     * Updates taskList with data from file.
     * @param storage The RatStorage object used to read and write the task list to local storage.
     */
    public RatTaskManager(RatStorage storage) {
        this.taskList = new ArrayList<>();
        this.storage = storage;
        if (!storage.isFileEmpty()) {
            getDataFromFile();
        }
    }

    /**
     * Returns a list of tasks matching a given keyword.
     * @param keyword The keyword to search for.
     * @return A list of tasks matching a given keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.toString().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    /**
     * Prints a list of tasks matching a given keyword.
     * @param keyword The keyword to search for.
     * @return A String representation of the list of tasks matching a given keyword.
     */
    public String printFoundTasks(String keyword) {
        ArrayList<Task> foundTasks = this.findTasks(keyword);
        if (foundTasks.isEmpty()) {
            String output = "No tasks found matching the keyword " + "\"" + keyword + "\"" + ".";
            printWithLines(output);
            return output;
        }
        StringBuilder str = new StringBuilder();
        str.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < foundTasks.size(); i++) {
            str.append((i + 1)).append(". ").append(foundTasks.get(i).toString()).append("\n");
        }
        printWithLines(str.toString());
        return str.toString();
    }

    /**
     * Reads the data from the local storage file and adds it to the taskList.
     */
    protected void getDataFromFile() {
        int taskSymbol = 0;
        int taskStatus = 1;
        int taskName = 2;
        int firstTime = 3;
        int secondTime = 4;
        String[] data = storage.readFile().split("\n");
        for (String s : data) {
            String[] taskData = s.split(", ");
            switch (taskData[taskSymbol]) {
            case "T":
                this.taskList.add(new ToDo(taskData[taskName]));
                break;
            case "D":
                this.taskList.add(new Deadline(LocalDateTime.parse(taskData[firstTime]), taskData[taskName]));
                break;
            case "E":
                this.taskList.add(new Event(LocalDateTime.parse(taskData[firstTime]),
                        LocalDateTime.parse(taskData[secondTime]), taskData[taskName]));
                break;
            default:
                break;
            }
            try {
                if (taskData[taskStatus].equals("1")) {
                    this.markItemDone(this.taskList.size());
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                // Do nothing
            }
        }

    }

    /**
     * Returns the String representation of RatTaskManager.
     * The String representation is a numbered list of tasks.
     * @return String representation of RatTaskManager.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            str.append((i + 1)).append(". ").append(this.taskList.get(i).toString()).append("\n");
        }
        return str.toString();
    }

    /**
     * Adds a ToDo task to the taskList, given its name.
     * Instantiates a new ToDo object and adds it to the taskList.
     * @param item Name of the ToDo task.
     * @return A confirmation that the ToDo task has been added.
     */
    public String addToDo(String item) {
        ToDo newToDo = new ToDo(item);
        this.taskList.add(newToDo);
        String response = "Got it. I've added this to do:\n"
                + newToDo
                + "\nNow you have " + this.taskList.size() + " tasks in the list.";
        printWithLines(response);
        return response;
    }


    /**
     * Adds a Deadline task to the taskList, given its deadline and name.
     * Instantiates a new Deadline object and adds it to the taskList.
     * @param deadline Deadline of the Deadline task.
     * @param name Name of the Deadline task.
     * @return A confirmation that the Deadline task has been added.
     */
    public String addDeadline(String deadline, String name) {
        Deadline newDeadline = new Deadline(deadline, name);
        this.taskList.add(newDeadline);
        String response = "Got it. I've added this Deadline:\n"
                + newDeadline
                + "\nNow you have " + this.taskList.size() + " tasks in the list.";
        printWithLines(response);
        return response;
    }

    /**
     * Adds an Event task to the taskList, given its start time, end time, and name.
     * Instantiates a new Event object and adds it to the taskList.
     * @param startTime Start time of the Event task.
     * @param endTime   End time of the Event task.
     * @param name      Name of the Event task.
     * @return A confirmation that the Event task has been added.
     */
    public String addEvent(String startTime, String endTime, String name) {
        Event newEvent = new Event(startTime, endTime, name);
        this.taskList.add(newEvent);
        String response = "Got it. I've added this Event:\n"
                + newEvent
                + "\nNow you have " + this.taskList.size() + " tasks in the list.";
        printWithLines(response);
        return response;
    }

    /**
     * Marks a task as done, given its 1-indexed index in the taskList.
     * @param index Index of the task in the taskList.
     * @return A confirmation that the task has been marked as done.
     */
    public String markItemDone(int index) {
        if (index > this.taskList.size() || index < 1) {
            throw new IndexOutOfBoundsException("Task not found");
        } else if (this.taskList.get(index - 1) == null) {
            throw new IndexOutOfBoundsException("Task not found");
        }
        Task item = this.taskList.get(index - 1);
        assert (item != null);
        item.markDone();
        String response = "Nice! I've marked this task as done: " + taskList.get(index - 1).toString();
        printWithLines(response);
        return response;
    }

    /**
     * Marks a task as not done, given its 1-indexed index in the taskList.
     * @param index Index of the task in the taskList.
     * @return A confirmation that the task has been marked as not done.
     */
    public String unmarkItemDone(int index) {
        if (index > this.taskList.size() || index < 1) {
            throw new IndexOutOfBoundsException("Task not found");
        } else if (this.taskList.get(index - 1) == null) {
            throw new IndexOutOfBoundsException("Task not found");
        }
        Task item = this.taskList.get(index - 1);
        assert(item != null);
        item.unmarkDone();
        String response = "Ok, I've marked this task as not done yet: " + taskList.get(index - 1).toString();
        printWithLines(response);
        return response;
    }

    /**
     * Deletes a task from taskList, given its 1-indexed index in the taskList.
     * @param index Index of the task in the taskList.
     * @return A confirmation that the task has been deleted.
     */
    public String deleteItem(int index) {
        if (index > this.taskList.size() || index < 1) {
            throw new IndexOutOfBoundsException("Task not found");
        } else if (this.taskList.get(index - 1) == null) {
            throw new IndexOutOfBoundsException("Task not found");
        }
        Task item = this.taskList.get(index - 1);
        assert(item != null);
        this.taskList.remove(index - 1);
        String response = "Noted. I've removed this task:\n"
                + item.toString()
                + "\nNow you have " + this.taskList.size() + " tasks in the list.";
        printWithLines(response);
        return response;
    }

    /**
     * Deletes all tasks from taskList.
     * @return A confirmation that all tasks have been deleted.
     */
    public String deleteAll() {
        this.taskList.clear();
        String response = "Noted. I've removed all tasks.";
        printWithLines(response);
        return response;
    }

    /**
     * Displays all tasks in taskList.
     * Formats the taskList's String representation with a message and count.
     * @return A String representation of the taskList.
     */
    public String listItems() {
        if (this.taskList.isEmpty()) {
            printWithLines("You have no tasks in the list.");
            return "You have no tasks in the list.";
        } else {
            String list = "Here are the tasks in your list:\n"
                    + this + "\n"
                    + "You have " + this.taskList.size() + " tasks in the list.";
            printWithLines(list);
            return list;
        }
    }

    /**
     * Method that updates local storage after each run of Rat.
     * Checks if the taskList is already in the file, and if not, adds it to the file.
     */
    public String save() {
        String existingItems = this.storage.readFile();
        if (this.taskList.isEmpty()) {
            String[] items = existingItems.split("\n");
            StringBuilder sb = new StringBuilder();
            for (String s : items) {
                if (s.startsWith("N")) {
                    sb.append(s + "\n");
                }
            }
            this.storage.overwriteFile(sb.toString());
        }
        for (Task task : this.taskList) {
            if (!existingItems.contains(task.formatForFile())) {
                this.storage.addToFile(task.formatForFile() + ("\n"));
            }
        }
        return "Tasks saved to file. ";
    }
}
