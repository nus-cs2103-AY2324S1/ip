package chewy;

import exception.DukeException;
import exception.InvalidTaskException;
import task.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks in the Chewy App. A <code>TaskList</code> object contains
 * the list of tasks and task operations.
 */
public class TaskList {
    List<Task> taskList;
    public static final List<String> validTasks = List.of("todo", "deadline", "event", "doafter");
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
     * @return A message that confirms the task as saved.
     */
    public String saveTask(String filepath) {
        assert filepath != null : "data filepath should be specified";
        System.out.println("Saving tasks...");
        try (PrintWriter writer = new PrintWriter(filepath)) {
            for (Task task: taskList) {
                writer.println(task.toFileString());
            }
        } catch (FileNotFoundException e) {
            return "Error saving tasks to file" + e.getMessage();
        }
        return "[Task saved successfully]";
    }

    /**
     * Adds a task to TaskList.
     * Displays the number of tasks in the list.
     *
     * @param userInput The String which the user has typed.
     * @return A message that confirms the task as added.
     * @throws DukeException If the command is not a valid addTask command.
     */
    public String addTask(String userInput) throws DukeException {
        assert !userInput.isBlank() : "user input should not be empty";
        String inputCommand = Parser.getCommand(userInput);
        String taskDesc = Parser.getTaskDesc(userInput);
        assert !inputCommand.isBlank();
        assert !taskDesc.isBlank();
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
        case "doafter":
            task = new DoAfter(taskDesc);
            break;
        default:
            throw new DukeException("Error, see 'help' for a list of commands");
        }
        taskList.add(task);
        return "Got it. I've added this task:\n\t" + task + "\n"
                + String.format("Now you have %d tasks in the list%n", taskList.size());
    }

    /**
     * Removes a task from the TaskList.
     * Displays the number of tasks in the list.
     *
     * @param userInput The String which the user has typed.
     * @return A message that confirms the task as deleted.
     * @throws InvalidTaskException If the task id entered does not correspond to any task
     */
    public String deleteTask(String userInput) throws DukeException {
        assert !userInput.isBlank() : "user input should not be empty";
        int taskId = Parser.getTaskId(userInput);
        if (taskId < 0 || taskId > taskList.size() - 1) throw new InvalidTaskException();
        assert taskId >= 0 && taskId <= taskList.size() - 1 : "task Id should be between 0 and total tasks - 1";
        Task deletedTask = taskList.remove(taskId);
        assert !taskList.contains(deletedTask) : "taskList should not contain the deleted task anymore";
        return "Noted. I've removed this task:\n\t" + deletedTask + "\n"
                + String.format("Now you have %d tasks in the list%n", taskList.size());
    }

    /**
     * Unmarks a task in the TaskList as done.
     *
     * @param userInput The String which the user has typed.
     * @return A message that confirms the task as unmarked.
     * @throws InvalidTaskException If the task id entered does not correspond to any task
     */
    public String unmarkTaskAsDone(String userInput) throws DukeException {
        assert !userInput.isBlank() : "user input should not be empty";
        int taskId = Parser.getTaskId(userInput);
        if (taskId < 0 || taskId > taskList.size() - 1) throw new InvalidTaskException();
        assert taskId >= 0 && taskId <= taskList.size() - 1 : "task Id should be between 0 and total tasks - 1";
        Task selectedTask = taskList.get(taskId);
        selectedTask.unmarkAsDone();
        assert selectedTask.getStatusIcon().equals("[ ]") : "task should be undone";
        return "OK, I've marked this task as not done yet:\n" + selectedTask + "\n";
    }

    /**
     * Marks a task in the TaskList as done.
     *
     * @param userInput The String which the user has typed.
     * @return A message that confirms the task as marked.
     * @throws InvalidTaskException If the task id entered does not correspond to any task
     */
    public String markTaskAsDone(String userInput) throws DukeException {
        assert !userInput.isBlank() : "user input should not be empty";
        int taskId = Parser.getTaskId(userInput);
        if (taskId < 0 || taskId > taskList.size() - 1) throw new InvalidTaskException();
        assert taskId >= 0 && taskId <= taskList.size() - 1 : "task Id should be between 0 and total tasks - 1";
        Task selectedTask = taskList.get(taskId);
        selectedTask.markAsDone();
        assert selectedTask.getStatusIcon().equals("[X]") : "task should be done";
        return "Nice! I've marked this task as done:\n" + selectedTask + "\n";
    }

    /**
     * Lists all the tasks in the TaskList.
     * @return The list of tasks.
     */
    public String listTasks() {
        if (taskList.size() == 0) {
            return "Chewy detected no task for you!";
        } else {
            StringBuilder taskListAsString = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String taskLine = String.format("%d.%s",
                        i + 1,
                        task.toString());
                taskListAsString.append(taskLine).append("\n");
            }
            assert !taskListAsString.toString().isEmpty() : "tasks should not be empty";
            return "Here are the tasks in your list:\n" + taskListAsString;
        }
    }

    /**
     * Displays all the tasks that matches the keyword.
     *
     * @param userInput the input String that the user has entered.
     * @return The list of tasks.
     * @throws DukeException If an invalid keyword or no keyword is entered.
     */
    public String findTasks(String userInput) throws DukeException {
        String keyword = Parser.getTaskDesc(userInput);
        int count = 0;
        StringBuilder taskListAsString = new StringBuilder();
        for (Task task: taskList) {
            if (!task.containsKeyword(keyword)) continue;
            if (task.containsKeyword(keyword) && count == 0) {
                taskListAsString.append("Here are the matching tasks in your list:\n");
            }
            count++;
            taskListAsString.append(count).append(".").append(task).append("\n");
            assert count > 0;
        }
        if (count == 0) {
            return "No matching tasks found!";
        }
        assert !taskListAsString.toString().isBlank() : "list of tasks should not be empty";
        return taskListAsString.toString();
    }
}
