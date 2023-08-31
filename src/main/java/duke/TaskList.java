package duke;

import exceptions.EmptyTaskException;
import exceptions.EmptyDateException;
import exceptions.OutOfRangeException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The TaskList class manages the list of tasks in the Duke application.
 * It provides methods to add, mark, unmark, delete, and list tasks.
 */
public class TaskList {
    private static ArrayList<Task> taskArray;

    /**
     * Constructs a TaskList instance with an empty task array.
     */
    public TaskList() {
        taskArray = new ArrayList<>();
    }

    /**
     * Constructs a TaskList instance with the specified task array.
     *
     * @param tasks The initial task array.
     */
    public TaskList(ArrayList<Task> tasks) {
        taskArray = tasks;
    }

    /**
     * Lists the tasks stored in the task array.
     *
     * @return A string containing the list of tasks.
     */
     public static String listTasks() {
        String inputArrayString = "";
        int num = 1;
        for (Task task : taskArray) {
            inputArrayString += num + ". " + task.statusAndTask() + "\n";
            num++;
        }
        return inputArrayString;
    }

    /**
     * Marks a task as done based on user input.
     *
     * @param userInput The user input specifying the task to be marked.
     * @throws EmptyTaskException If the user input is missing task details.
     * @throws OutOfRangeException If the task index is out of the array range.
     * @throws IOException If an I/O error occurs while updating the task file.
     */
    public static void markTask(String userInput) throws EmptyTaskException, OutOfRangeException, IOException {
        if (userInput.equals("mark")) {
            throw new EmptyTaskException("mark");
        }
        String[] parts = userInput.split("\\s+");
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex >= taskArray.size() || taskIndex < 0 || taskArray.get(taskIndex) == null) {
            throw new OutOfRangeException("Mark");
        }
        Task currentTask = taskArray.get(taskIndex);
        currentTask.markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(currentTask.statusAndTask());
    }

    /**
     * Unmarks a task as done based on user input.
     *
     * @param userInput The user input specifying the task to be unmarked.
     * @throws EmptyTaskException If the user input is missing task details.
     * @throws OutOfRangeException If the task index is out of the array range.
     */
    public static void unmarkTask(String userInput) throws EmptyTaskException, OutOfRangeException {
        if (userInput.equals("unmark")) {
            throw new EmptyTaskException("unmark");
        }
        String[] parts = userInput.split("\\s+");
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex >= taskArray.size() || taskIndex < 0 || taskArray.get(taskIndex) == null) {
            throw new OutOfRangeException("Unmark");
        }
        Task currentTask = taskArray.get(taskIndex);
        currentTask.unmarkDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(currentTask.statusAndTask());
    }

    /**
     * Adds a ToDo task based on user input.
     *
     * @param userInput The user input specifying the ToDo task.
     */
    public static void makeToDo(String userInput) {
        String taskName = userInput.substring("todo".length()).trim();
        taskArray.add(new ToDo(taskName));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskArray.get(taskArray.size() - 1).statusAndTask());
        System.out.println("Now you have " + taskArray.size() + " task(s) in the list.");
    }

    /**
     * Adds a Deadline task based on user input.
     *
     * @param userInput The user input specifying the Deadline task.
     * @throws EmptyDateException If the user input is missing the task deadline.
     */
    public static void makeDeadline(String userInput) throws EmptyDateException {
        String description = userInput.substring("deadline".length()).trim();
        String[] parts = description.split("/by");
        if (parts.length == 1) {
            throw new EmptyDateException("deadline");
        }
        String[] deadlineParts = {parts[0].trim(), parts[1].trim()};
        String taskName = deadlineParts[0];
        LocalDateTime by = Storage.saveAsDate(deadlineParts[1]);
        taskArray.add(new Deadline(taskName, by));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskArray.get(taskArray.size() - 1).statusAndTask());
        System.out.println("Now you have " + taskArray.size() + " task(s) in the list.");
    }

    /**
     * Adds an Event task based on user input.
     *
     * @param userInput The user input specifying the Event task.
     * @throws EmptyDateException If the user input is missing the event start or end date.
     */
    public static void makeEvent(String userInput) throws EmptyDateException {
        String description = userInput.substring("event".length()).trim();
        String[] partsA = description.split("/from");
        String taskName = partsA[0].trim();
        String[] partsB = partsA[1].split("/to");
        if (partsB.length == 1 || partsB[0].trim().isEmpty() || partsB[1].trim().isEmpty()) {
            throw new EmptyDateException("event");
        }
        String start = partsB[0].trim();
        String end = partsB[1].trim();
        String[] eventParts = {taskName, start, end};
        LocalDateTime startDateTime = Storage.saveAsDate(eventParts[1]);
        LocalDateTime endDateTime = Storage.saveAsDate(eventParts[2]);
        taskArray.add(new Event(taskName, startDateTime, endDateTime));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskArray.get(taskArray.size() - 1).statusAndTask());
        System.out.println("Now you have " + taskArray.size() + " task(s) in the list.");
    }

    /**
     * Deletes a task based on user input.
     *
     * @param userInput The user input specifying the task to be deleted.
     * @throws EmptyTaskException If the user input is missing task details.
     * @throws OutOfRangeException If the task index is out of the array range.
     */
    public static void deleteTask(String userInput) throws EmptyTaskException, OutOfRangeException {
        if (userInput.equals("delete")) {
            throw new EmptyTaskException("delete");
        }
        String[] parts = userInput.split("\\s+");
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex >= taskArray.size() || taskIndex < 0 || taskArray.get(taskIndex) == null) {
            throw new OutOfRangeException("Delete");
        }
        Task currentTask = taskArray.get(taskIndex);
        taskArray.remove(currentTask);
        System.out.println("Noted. I've removed this task:");
        System.out.println(currentTask.statusAndTask());
        System.out.println("Now you have " + taskArray.size() + " task(s) in the list.");
    }

    /**
     * Updates the task file with the current task array.
     *
     * @throws IOException If an I/O error occurs while updating the task file.
     */
    public static void updateTaskFile() throws IOException {
        try {
            Storage.generateTaskFileContent(taskArray);
        } catch (IOException e) {
            System.out.println("Something went wrong while updating the task file: " + e.getMessage());
        }
    }

    /**
     * Retrieves a task from the task array based on its index.
     *
     * @param i The index of the task to retrieve.
     * @return The retrieved task.
     */
    public Task getTask(int i) {
         return taskArray.get(i);
    }
}
