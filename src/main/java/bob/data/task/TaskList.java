package bob.data.task;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;

import bob.data.exception.DukeException;
import bob.parser.Parser;
import bob.storage.Storage;

/**
 * Represents a TaskList that contains the tasks and writes to a specified file.
 */
public class TaskList {
    /** The ArrayList for storing all the tasks. */
    private ArrayList<Task> tasks;
    /** The storage object for writing/reading the task to/from. */
    private Storage storage;

    /**
     * Constructs a new TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList based on the specified file.
     *
     * @param storage The storage class which handles the storage operations.
     */
    public TaskList(Storage storage) {
        assert storage != null : "storage should not be null";
        this.tasks = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * Reads the contents of the file and stores it into the ArrayList.
     */
    public void open() {
        this.tasks = new ArrayList<>();
        assert tasks != null : "tasks should not be null";
        try {
            this.storage.readFromFile(this.tasks);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred when trying to find the file.");
            e.printStackTrace();
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    /**
     * Adds a specified task to the ArrayList.
     *
     * @param task The task to be added to the ArrayList.
     */
    public void addTask(Task task, boolean isMuted) {
        assert task != null : "task should not be null";
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
     * @return A string describing the task that was added.
     * @throws DukeException If the EventTask is instantiated with invalid dates.
     */
    public String addTaskWithCommand(Parser.CommandType command, String input) throws DukeException, DateTimeException {
        assert command != null : "command should not be null";
        assert input != null : "input should not be null";
        Task taskToAdd;
        String taskDescription;
        String[] taskDescriptionArray;

        switch (command) {
        case TODO:
            String toDoTaskDescription = input.substring(5);
            taskToAdd = new ToDoTask(toDoTaskDescription);
            break;
        case DEADLINE:
            String deadlineTaskDescription = input.substring(9);
            taskDescriptionArray = deadlineTaskDescription.split(" /by ");
            taskToAdd = new DeadlineTask(taskDescriptionArray[0], taskDescriptionArray[1]);
            break;
        case EVENT:
            String eventTaskDescription = input.substring(6);
            taskDescriptionArray = eventTaskDescription.split(" /from ");
            String description = taskDescriptionArray[0];
            String[] dateArray = taskDescriptionArray[1].split(" /to ");
            taskToAdd = new EventTask(description, dateArray[0], dateArray[1]);
            break;
        default:
            throw new DukeException("No such command found.");
        }
        this.tasks.add(taskToAdd);
        return "Got it. I've added this task:\n"
                + taskToAdd + "\n"
                + "Now you have " + this.tasks.size() + " task(s) in the list.";
    }

    /**
     * Set the task at the index, based on the specified input string, of the ArrayList to be completed.
     * @param input The input string containing the index of the task.
     * @return A string representing the task that was set as completed.
     * @throws DukeException If the task number is out of range of the list.
     */
    public String setTaskComplete(String input) throws DukeException {
        assert input != null : "input should not be null";
        String[] inputSplit = input.split(" ");
        int taskNo = Integer.parseInt(inputSplit[1]);
        if (taskNo < 1 || taskNo > this.getSize()) {
            throw new DukeException("Task number out of range!");
        }
        Task task = this.tasks.get(taskNo - 1);
        task.setDone();
        return "OK, I've marked this task as done:\n" + task;
    }
    /**
     * Set the task at the index, based on the specified input string, of the ArrayList to be incomplete.
     *
     * @param input The input string containing the index of the task.
     */
    public String setTaskIncomplete(String input) throws DukeException {
        assert input != null : "input should not be null";
        String[] inputSplit = input.split(" ");
        int taskNo = Integer.parseInt(inputSplit[1]) - 1;
        if (taskNo < 1 || taskNo > this.getSize()) {
            throw new DukeException("Task number out of range!");
        }
        Task task = this.tasks.get(taskNo);
        task.setNotDone();
        return "OK, I've marked this task as not done yet:\n" + task;
    }
    /**
     * Removes the task at an index, based on the specified input, of the ArrayList.
     *
     * @param input The input string containing the index of the task to be removed.
     */
    public String deleteTask(String input) throws DukeException {
        int originalSize = tasks.size();
        assert input != null : "input should not be null";
        String[] inputSplit = input.split(" ");
        int taskNo = Integer.parseInt(inputSplit[1]);
        if (taskNo < 1 || taskNo > this.getSize()) {
            throw new DukeException("Task number out of range!");
        }
        Task task = this.tasks.get(taskNo - 1);
        this.tasks.remove(taskNo - 1);
        assert tasks.size() == originalSize - 1 : "task should not exist in the list anymore";
        return "Noted. I've removed this task:\n"
                + task + "\n"
                + "Now you have " + this.tasks.size() + " task(s) in the list.";
    }

    /**
     * Delete a task in the list given a specified index
     * @param index The index of the task to be deleted.
     * @throws DukeException If the task number is out of the range of the list.
     */
    public void deleteTaskAtIndex(int index) throws DukeException {
        if (index < 0 || index > this.getSize()) {
            throw new DukeException("Task number out of range!");
        }
        tasks.remove(index);
    }

    /**
     * Prints all the tasks contained in the ArrayList in an indexed manner.
     */
    @Override
    public String toString() {
        if (this.tasks.size() == 0) {
            return "No tasks in your list, add some!";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String indexedTask = i + 1 + "." + tasks.get(i) + "\n";
            stringBuilder.append(indexedTask);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    /**
     * Saves all tasks in the list to the file.
     * @return A string with the farewell message.
     */
    public String close() {
        try {
            assert this.storage.getFile().exists() : "file should exist so that it can be written to";
            FileWriter writer = new FileWriter(this.storage.getFile());
            for (Task task : tasks) {
                writer.write(task.toFileString());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving your tasks.");
            e.printStackTrace();
        }
        return "Bye!";
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
        assert input != null : "input should not be null";
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


    /**
     * Swaps two tasks in the taskList.
     * @param firstTaskIndex Index of the first task to be swapped.
     * @param secondTaskIndex Index of the second task to be swapped.
     */
    public void swap(int firstTaskIndex, int secondTaskIndex) throws DukeException {
        if (firstTaskIndex < 0 || firstTaskIndex > this.getSize()) {
            throw new DukeException("First task number out of range!");
        }

        if (secondTaskIndex < 0 || secondTaskIndex > this.getSize()) {
            throw new DukeException("Second task number out of range!");
        }
        Task temp = tasks.get(firstTaskIndex);
        tasks.set(firstTaskIndex, tasks.get(secondTaskIndex));
        tasks.set(secondTaskIndex, temp);
    }

    /**
     * Checks if this DeadlineTask is the same as a specified object.
     * @param obj The object to be compared with.
     * @return true if they are both the same instance or have the same contents.
     *         false if they have different contents.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof TaskList) {
            TaskList object = (TaskList) obj;

            if (this.getSize() != object.getSize()) {
                return false;
            }

            boolean sameContents = true;

            for (int i = 0; i < this.getSize(); i++) {
                if (this.tasks.get(i).equals(object.tasks.get(i))) {
                    sameContents = false;
                    break;
                }
            }

            return sameContents;
        }
        return false;
    }
}
