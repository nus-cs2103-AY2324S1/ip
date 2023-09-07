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
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


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
     */
    public String markTaskByBot(int taskIndex) throws DukeException {
        if (!taskList.isValidListIndex(taskIndex - 1)) {
            throw new DeleteException("Invalid Index of task!");
        }
        taskList.markTaskAsDone(taskIndex - 1);
        saveTasksToFile(this.taskList);
        return taskList.getTaskDetails(taskIndex - 1);
    }

    /**
     * Marks a task as not done and provides user feedback.
     *
     * @param taskIndex Index of the task to be marked as not done, starts from '1'
     */
    public String unmarkTaskByBot(int taskIndex) throws DukeException {
        if (!taskList.isValidListIndex(taskIndex - 1)) {
            throw new DeleteException("detail: Invalid Index of task!");
        }

        taskList.markTaskAsNotDone(taskIndex - 1);
        saveTasksToFile(this.taskList);
        return " OK, I've marked this task as not done yet:\n"
                + taskList.getTaskDetails(taskIndex - 1);
    }

    /**
     * Deletes a task from the task list based on the provided input.
     *
     * @param taskIndex The index of the task to be deleted.
     * @throws DeleteException If the input string is not numeric or if the task index is out of valid range.
     */
    public String deleteTaskByBot(int taskIndex) throws DukeException {
        if (taskIndex < 1 || taskIndex > taskList.getTaskCount()) {
            throw new DeleteException("Invalid Index of task!");
        } else {
            String return_str =  " Noted. I've removed this task:\n"
                    + this.taskList.getTaskDetails(taskIndex - 1)
                    + "\n Now you have " + (taskList.getTaskCount() - 1) + " tasks in the list.\n";
            taskList.deleteTask(taskIndex - 1);
            return return_str;
        }
    }

    /**
     * Adds a todo task to the task list and provides user feedback
     *
     * @param description The description of the todo task
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
     * @throws DeadlineException If the description or deadline date is empty
     */
    public String addDeadline(String description, LocalDate deadlineDate) throws DeadlineException {
        if (description.isEmpty() || deadlineDate == null) {
//            System.out.println("Error in addDeadline");
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
     */
    public void loadTasksFromFile() throws DukeException {
        for (Task taskData : storage.loadTasks()) {
            this.taskList.addTask(taskData);
        }

        if (!this.taskList.isEmpty()) {
            System.out.println(this.taskList);
        }
    }

    /**
     * Saves the tasks to the storage file
     */
    private void saveTasksToFile(TaskList taskList) throws DukeException {
        this.storage.saveTasks(taskList);
    }

    /**
     * Finds tasks containing the given keyword and displays them.
     *
     * @param keyword The keyword to search for.
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
     * @return True if the command is not "bye", false otherwise
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

    /**
     * Starts the chatbot application
     */
    public void start() throws DukeException {
//        Ui.showWelcomeMessage();
//        String userInput;
//        Command parsedCommand;
//
//        this.loadTasksFromFile();
//
//        boolean isContinuing = true;
//        while (isContinuing) {
//            try {
//                userInput = Ui.getUserInput();
//                parsedCommand = Parser.parse(userInput);
//                isContinuing = handleCommand(parsedCommand);
//                this.saveTasksToFile(this.taskList);
//            } catch (DukeException e) {
//                ui.showErrorMessage(e.getMessage());
//            }
//        }
//
//        Ui.showGoodByeMessage();
    }

    public static void main(String[] args) throws DukeException {
//        Duke duke = new Duke();
//        duke.start();
    }
}
