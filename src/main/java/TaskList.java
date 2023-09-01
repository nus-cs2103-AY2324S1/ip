import Exceptions.DukeException;
import Exceptions.InvalidTaskException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks in the Duke App. A <code>TaskList</code> object contains
 * the list of tasks and task operations.
 */
public class TaskList {
    List<Task> taskList;
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Saves all the tasks in TaskList to the output file.
     *
     * @param filepath the path to the file to save tasks in.
     */
    public void saveTask(String filepath) {
        System.out.println("Saving tasks...");
        try (PrintWriter writer = new PrintWriter(filepath)) {
            for (Task task: taskList) {
                writer.println(task.toFileString());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving tasks to file" + e.getMessage());
        }
        System.out.println("Tasks.Task saved successfully");
    }

    /**
     * Adds a task to TaskList.
     * Displays the number of tasks in the list.
     *
     * @param userInput The String which the user has typed.
     * @throws DukeException If the command is not a valid addTask command.
     */
    public void addTask(String userInput) throws DukeException {
        String inputCommand = Parser.getCommand(userInput);
        String taskDesc = Parser.getTaskDesc(userInput);
        Task task;
        switch (inputCommand) {
            case "todo":
                task = new Todo(taskDesc);
                break;
            case "deadline":
                task = new Deadline(taskDesc);
                break;
            case "event":
                task = new Event(taskDesc);
                break;
            default:
                throw new DukeException("Error, see 'help' for a list of commands");
        }
        // Add task to task list
        taskList.add(task);
        System.out.println("Got it. I've added this task:\n\t" + task);
        System.out.printf("Now you have %d tasks in the list%n", taskList.size());
    }

    /**
     * Removes a task from the TaskList.
     * Displays the number of tasks in the list.
     *
     * @param userInput The String which the user has typed.
     * @throws InvalidTaskException If the task id entered does not correspond to any task
     */
    public void deleteTask(String userInput) throws DukeException {
        int taskId = Parser.getTaskId(userInput);
        if (taskId < 0 || taskId > taskList.size() - 1) throw new InvalidTaskException();
        Task deletedTask = taskList.remove(taskId);
        System.out.println("Noted. I've removed this task:\n\t" + deletedTask);
        System.out.printf("Now you have %d tasks in the list%n", taskList.size());
    }

    /**
     * Unmarks a task in the TaskList as done.
     *
     * @param userInput The String which the user has typed.
     * @throws InvalidTaskException If the task id entered does not correspond to any task
     */
    public void unmarkTaskAsDone(String userInput) throws DukeException {
        int taskId = Parser.getTaskId(userInput);
        if (taskId < 0 || taskId > taskList.size() - 1) throw new InvalidTaskException();
        Task selectedTask = taskList.get(taskId);
        selectedTask.unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:\n"
                + selectedTask);
    }

    /**
     * Marks a task in the TaskList as done.
     *
     * @param userInput The String which the user has typed.
     * @throws InvalidTaskException If the task id entered does not correspond to any task
     */
    public void markTaskAsDone(String userInput) throws DukeException {
        int taskId = Parser.getTaskId(userInput);
        if (taskId < 0 || taskId > taskList.size() - 1) throw new InvalidTaskException();
        // Mark the selected task as done
        Task selectedTask = taskList.get(taskId);
        selectedTask.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n"
                + selectedTask);
    }

    /**
     * Lists all the tasks in the TaskList.
     */
    public void listTasks() {
        if (taskList.size() == 0) {
            System.out.println("Chewy detected no task for you!");
        } else {
            System.out.println("Here are the tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String taskLine = String.format("%d.%s",
                        i + 1,
                        task.toString());
                System.out.println(taskLine);
            }
        }
    }
}
