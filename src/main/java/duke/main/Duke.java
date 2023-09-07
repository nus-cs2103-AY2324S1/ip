package duke.main;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.commands.Command;
import duke.exception.DeadlineException;
import duke.exception.DeleteException;
import duke.exception.DukeException;
import duke.exception.EventException;
import duke.exception.TodoException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * The main class that initiates the chatbot application.
 */
public class Duke {

    private final TaskList taskList;
    private final String filePath = "./src/main/resources/duke.txt";
    private final Storage storage = new Storage(filePath);



    /**
     * Initializes the Chatbot with an empty task list.
     */
    public Duke() {
        this.taskList = new TaskList();
    }


    /**
     * Marks a task as done and provides user feedback
     *
     * @param taskIndex Index of the task to be marked as done, starts from '1'
     * @return The feedback string.
     * @throws DeleteException If the input string is not numeric or if the task index is out of valid range.
     */
    public String markTaskByBot(int taskIndex) throws DukeException {
        if (!taskList.isValidListIndex(taskIndex - 1)) {
            throw new DeleteException("Invalid Index of task!");
        }
        taskList.markTaskAsDone(taskIndex - 1);
        saveTasksToFile();
        return " OK, I've marked this task as done:\n"
                + taskList.getTaskDetails(taskIndex - 1);
    }

    /**
     * Marks a task as not done and provides user feedback.
     *
     * @param taskIndex Index of the task to be marked as not done, starts from '1'
     * @return The feedback string.
     * @throws DeleteException If the input string is not numeric or if the task index is out of valid range.
     */
    public String unmarkTaskByBot(int taskIndex) throws DukeException {
        if (!taskList.isValidListIndex(taskIndex - 1)) {
            throw new DeleteException("detail: Invalid Index of task!");
        }

        taskList.markTaskAsNotDone(taskIndex - 1);
        saveTasksToFile();
        return " OK, I've marked this task as not done yet:\n"
                + taskList.getTaskDetails(taskIndex - 1);
    }

    /**
     * Deletes a task from the task list based on the provided input.
     *
     * @param taskIndex The index of the task to be deleted.
     * @return The feedback string.
     * @throws DeleteException If the input string is not numeric or if the task index is out of valid range.
     */
    public String deleteTaskByBot(int taskIndex) throws DukeException {
        if (taskIndex < 1 || taskIndex > taskList.getTaskCount()) {
            throw new DeleteException("Invalid Index of task!");
        } else {
            String returnStr = " Noted. I've removed this task:\n"
                    + this.taskList.getTaskDetails(taskIndex - 1)
                    + "\n Now you have " + (taskList.getTaskCount() - 1) + " tasks in the list.\n";
            taskList.deleteTask(taskIndex - 1);
            return returnStr;
        }
    }

    /**
     * Adds a todo task to the task list and provides user feedback
     *
     * @param description The description of the todo task
     * @return The feedback string.
     * @throws TodoException If the description is empty
     */
    public String addTodo(String description) throws TodoException {
        Task newTask;
        if (description.isEmpty()) {
            throw new TodoException();
        } else {
            newTask = new Todo(description);
            taskList.addTask(newTask);
            return " Got it. I've added this task:\n"
                    + newTask
                    + "\n Now you have " + taskList.getTaskCount() + " tasks in the list.\n";
        }
    }

    /**
     * Adds a deadline task to the task list and provides user feedback
     *
     * @param description  The description of the deadline task
     * @param deadlineDate The deadline date of the deadline task
     * @return The feedback string.
     * @throws DeadlineException If the description or deadline date is empty
     */
    public String addDeadline(String description, LocalDate deadlineDate) throws DeadlineException {
        if (description.isEmpty() || deadlineDate == null) {
            throw new DeadlineException();
        } else {
            Task newTask = new Deadline(description, deadlineDate);
            taskList.addTask(newTask);
            return " Got it. I've added this task:\n"
                    + newTask
                    + "\n Now you have " + taskList.getTaskCount() + " tasks in the list.\n";
        }
    }

    /**
     * Adds an event task to the task list and provides user feedback
     *
     * @param description   The description of the event task
     * @param eventFromDate The start date of the event task
     * @param eventToDate   The end date of the event task
     * @return The feedback string.
     * @throws EventException If the description, start date or end date is empty
     */
    public String addEvent(String description, LocalDate eventFromDate, LocalDate eventToDate) throws EventException {
        if (description.isEmpty() || eventFromDate == null || eventToDate == null) {
            throw new EventException();
        } else {
            Task newTask = new Event(description, eventFromDate, eventToDate);
            taskList.addTask(newTask);
            return " Got it. I've added this task:\n"
                    + newTask
                    + "\n Now you have " + taskList.getTaskCount() + " tasks in the list.\n";
        }
    }

    /**
     * Loads the tasks from the storage file
     *
     * @return The feedback string.
     * @throws DukeException If the file path is invalid
     */
    public String loadTasksFromFile() throws DukeException {
        for (Task taskData : storage.loadTasks()) {
            this.taskList.addTask(taskData);
        }

        if (!this.taskList.isEmpty()) {
            return this.taskList.toString();
        }
        return "No tasks found in file.";
    }

    /**
     * Saves the tasks to the storage file
     *
     * @throws DukeException If the file path is invalid
     */
    public void saveTasksToFile() throws DukeException {
        this.storage.saveTasks(this.taskList);
    }

    /**
     * Finds tasks containing the given keyword and displays them.
     *
     * @param keyword The keyword to search for.
     * @return The feedback string.
     */
    public String findTasksByKeyword(String keyword) {
        ArrayList<Task> matchingTasks = taskList.findTasksByKeyword(keyword);
        StringBuilder matchingTasksString = new StringBuilder();
        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            matchingTasksString.append("Here are the matching tasks in your list:\n");
            int count = 1;
            for (Task task : matchingTasks) {
                matchingTasksString.append(count).append(".").append(task).append("\n");
                count++;
            }
        }
        return matchingTasksString.toString();
    }

    /**
     * Handles the command based on the command type
     *
     * @param command The command to be handled
     * @return The feedback string.
     * @throws DukeException If the command type is not recognized
     */
    public String handleCommand(Command command) throws DukeException {
        String commandType = command.getCommandType();
        String description = command.getDescription();
        int taskIndex = command.getTaskIndex();
        LocalDate deadlineDate = command.getDeadlineDate();
        LocalDate eventFromDate = command.getEventFromDate();
        LocalDate eventToDate = command.getEventToDate();

        switch (commandType) {
        case "mark":
            return markTaskByBot(taskIndex);
        case "unmark":
            return unmarkTaskByBot(taskIndex);
        case "bye":
            return "Bye. Hope to see you again soon!";
        case "list":
            return this.taskList.toString();
        case "find":
            return this.findTasksByKeyword(description);
        case "todo":
            return this.addTodo(description);
        case "deadline":
            return this.addDeadline(description, deadlineDate);
        case "event":
            return this.addEvent(description, eventFromDate, eventToDate);
        case "delete":
            return this.deleteTaskByBot(taskIndex);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
