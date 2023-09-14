package bob.data.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import bob.data.exception.DukeException;
import bob.parser.Parser;

/**
 * Represents a TaskList that contains the tasks and writes to a specified file.
 */
public class TaskList {
    /** The ArrayList for storing all the tasks. */
    private ArrayList<Task> tasks;
    /** The file for writing/reading the task to/from. */
    private File file;

    /**
     * Constructs a new TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a new TaskList based on the specified file.
     *
     * @param file The file to write to or read from.
     */
    public TaskList(File file) {
        this.tasks = new ArrayList<Task>();
        this.file = file;
    }

    /**
     * Reads the contents of the file and stores it into the ArrayList.
     */
    public void open() {
        this.tasks = new ArrayList<Task>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String storedTask = scanner.nextLine();
                String[] taskArray = storedTask.split(",");
                Task task;
                if (taskArray[0].startsWith("Todo")) {
                    task = new ToDoTask(taskArray[2]);
                } else if (taskArray[0].startsWith("Deadline")) {
                    task = new DeadlineTask(taskArray[2], taskArray[3]);
                } else {
                    task = new EventTask(taskArray[2], taskArray[3], taskArray[4]);
                }
                if ((taskArray[1]).equals("1")) {
                    task.setDone();
                }
                this.tasks.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred when trying to find the file.");
            e.getMessage();
            e.printStackTrace();
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    /**
     * Calls a specific method with the specified input based on the specified command.
     *
     * @param command The command to be run.
     * @param input The input string for the command.
     * @throws DukeException If an EventTask is instantiated with invalid dates.
     */
    public String executeCommand(Parser.Command command, String input) throws DukeException {
        switch (command) {
        case MARK:
            return this.setTaskComplete(input);
        case UNMARK:
            return this.setTaskIncomplete(input);
        case DELETE:
            return this.deleteTask(input);
        case TODO:
        case DEADLINE:
        case EVENT:
            return this.addTask(command, input);
        default:
            return "Unknown command";
        }
    }

    /**
     * Adds a specified task to the ArrayList.
     *
     * @param task The task to be added to the ArrayList.
     */
    public void addTask(Task task, boolean isMuted) {
        this.tasks.add(task);
        if (!isMuted) {
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.println("Now you have " + this.tasks.size() + " task(s) in the list.");
        }
    }

    /**
     * Adds a task to the ArrayList based on the specified command and input.
     *
     * @param command The command that determines which subclass of Task will be instantiated.
     * @param input The input needed for the instantiation of the Task.
     * @throws DukeException If the EventTask is instantiated with invalid dates.
     */
    public String addTask(Parser.Command command, String input) throws DukeException {
        Task taskToAdd;
        String taskDescription;
        String[] taskDescriptionArray;

        switch (command) {
        case TODO:
            taskDescription = input.substring(5);
            taskToAdd = new ToDoTask(taskDescription);
            break;
        case DEADLINE:
            taskDescription = input.substring(9);
            taskDescriptionArray = input.split(" /by ");
            taskToAdd = new DeadlineTask(taskDescriptionArray[0], taskDescriptionArray[1]);
            break;
        case EVENT:
            taskDescription = input.substring(6);
            taskDescriptionArray = input.split(" /from ");
            String description = taskDescriptionArray[0];
            String[] dateArray = taskDescriptionArray[1].split(" /to ");
            taskToAdd = new EventTask(description, dateArray[0], dateArray[1]);
            break;
        default:
            throw new DukeException("No such command found.");
        }
        this.tasks.add(taskToAdd);
        String output = "Got it. I've added this task:\n"
                + taskToAdd + "\n"
                + "Now you have " + this.tasks.size() + " task(s) in the list.";
        return output;
    }

    /**
     * Set the task at the specified index of the ArrayList to be completed.
     *
     * @param i Index of the task to set as complete.
     */
    public void setTaskComplete(int i) {
        Task task = this.tasks.get(i);
        task.setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Set the task at the index, based on the specified input string, of the ArrayList to be completed.
     *
     * @param input The input string containing the index of the task.
     */
    public String setTaskComplete(String input) {
        String[] inputSplit = input.split(" ");
        int taskNo = Integer.parseInt(inputSplit[1]) - 1;
        Task task = this.tasks.get(taskNo);
        task.setDone();
        String output = "OK, I've marked this task as done:\n" + task;
        return output;
    }

    /**
     * Set the task at the specified index of the ArrayList to be incomplete.
     *
     * @param i Index of the task to set as incomplete.
     */
    public void setTaskIncomplete(int i) {
        Task task = this.tasks.get(i);
        task.setNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Set the task at the index, based on the specified input string, of the ArrayList to be incomplete.
     *
     * @param input The input string containing the index of the task.
     */
    public String setTaskIncomplete(String input) {
        String[] inputSplit = input.split(" ");
        int taskNo = Integer.parseInt(inputSplit[1]) - 1;
        Task task = this.tasks.get(taskNo);
        task.setNotDone();
        String output = "OK, I've marked this task as not done yet:\n" + task;
        return output;
    }

    /**
     * Returns the task at the specified index of the ArrayList.
     *
     * @param i The index of the ArrayList to retrieve the task from.
     * @return A Task that is stored at the specified index of the ArrayList.
     */
    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    /**
     * Removes the task at the specified index of the ArrayList.
     *
     * @param i The index of the ArrayList containing the task to be removed.
     */
    public void deleteTask(int i) {
        Task task = this.tasks.get(i);
        this.tasks.remove(i);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.tasks.size() + " task(s) in the list.");
    }

    /**
     * Removes the task at an index, based on the specified input, of the ArrayList.
     *
     * @param input The input string containing the index of the task to be removed.
     */
    public String deleteTask(String input) {
        String[] inputSplit = input.split(" ");
        int taskNo = Integer.parseInt(inputSplit[1]) - 1;
        Task task = this.tasks.get(taskNo);
        this.tasks.remove(taskNo);
        String output = "Noted. I've removed this task:\n"
                + task + "\n"
                + "Now you have " + this.tasks.size() + " task(s) in the list.";
        return output;
    }

    /**
     * Prints all the tasks contained in the ArrayList in an indexed manner.
     */
    @Override
    public String toString() {
        if (this.tasks.size() == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append(i + 1 + "." + tasks.get(i) + "\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    /**
     * Writes all the tasks in the ArrayList to the file.
     */
    public void close() {
        try {
            FileWriter writer = new FileWriter(this.file);
            for (Task task : tasks) {
                writer.write(task.toFileString());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving your tasks.");
            e.printStackTrace();
        }
    }

    /**
     * Returns the size of the ArrayList.
     * @return An integer representing the size of the ArrayList.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns the String representation of all the tasks that are similar to the specified input in an indexed manner.
     * @param input The String to be compared with other tasks.
     * @return The String representation of matching tasks.
     */
    public String find(String input) {
        String toFind = input.substring(5);
        TaskList tasksFound = new TaskList();
        for (Task task : this.tasks) {
            if (task.toString().contains(toFind)) {
                tasksFound.addTask(task, true);
            }
        }
        if (tasksFound.getSize() == 0) {
            return "No tasks found matching that description.";
        }
        return tasksFound.toString();
    }
}
