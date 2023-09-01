package duke.main;
import duke.task.*;
import duke.exception.*;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.commands.*;


import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The main class that initiates the chatbot application.
 */
public class Duke {

    private final TaskList taskList;
    private final String FILE_PATH = "./src/main/data/duke.txt";
    private final Storage storage = new Storage(FILE_PATH);


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
    public void markTaskByBot(int taskIndex) throws DeleteException {
        if (!taskList.isValidListIndex(taskIndex - 1)) {
            throw new DeleteException("Invalid Index of task!");
        }
        taskList.markTaskAsDone(taskIndex - 1);
        saveTasksToFile(this.taskList);
        Ui.showMessage(taskList.getTaskDetails(taskIndex - 1));
    }

    /**
     * Marks a task as not done and provides user feedback.
     *
     * @param taskIndex Index of the task to be marked as not done, starts from '1'
     */
    public void unmarkTaskByBot(int taskIndex) throws DeleteException {
        if (!taskList.isValidListIndex(taskIndex - 1)) {
            throw new DeleteException("detail: Invalid Index of task!");
        }

        taskList.markTaskAsNotDone(taskIndex - 1);
        saveTasksToFile(this.taskList);
        Ui.showMessage(" OK, I've marked this task as not done yet:\n" +
                taskList.getTaskDetails(taskIndex - 1));
    }


    /**
     * Deletes a task from the task list based on the provided input.
     *
     * @param taskIndex The index of the task to be deleted.
     * @throws DeleteException If the input string is not numeric or if the task index is out of valid range.
     */
    public void deleteTaskByBot(int taskIndex) throws DeleteException {
        if (taskIndex < 1 || taskIndex > taskList.getTaskCount()) {
            throw new DeleteException("Invalid Index of task!");
        } else {
            Ui.showMessage(" Noted. I've removed this task:\n" +
                    this.taskList.getTaskDetails(taskIndex - 1) +
                    "\n Now you have " + (taskList.getTaskCount() - 1) + " tasks in the list.\n");
            taskList.deleteTask(taskIndex - 1);
        }
    }


    /**
     * Adds a todo task to the task list and provides user feedback
     *
     * @param description The description of the todo task
     * @throws TodoException If the description is empty
     */
    public void addTodo(String description) throws TodoException {
        Task newTask;
        if (description.isEmpty()) {
            throw new TodoException();
        } else {
            newTask = new Todo(description);
            taskList.addTask(newTask);
            Ui.showMessage(
                    " Got it. I've added this task:\n" +
                            newTask +
                            "\n Now you have " + taskList.getTaskCount() + " tasks in the list.\n");
        }
    }

    /**
     * Adds a deadline task to the task list and provides user feedback
     *
     * @param description The description of the deadline task
     * @param deadlineDate The deadline date of the deadline task
     * @throws DeadlineException If the description or deadline date is empty
     */
    public void addDeadline(String description, LocalDate deadlineDate) throws DeadlineException {
        if (description.isEmpty() || deadlineDate == null) {
            System.out.println("Error in addDeadline");
            throw new DeadlineException();
        } else {
            Task newTask = new Deadline(description, deadlineDate);
            taskList.addTask(newTask);
            Ui.showMessage(
                    " Got it. I've added this task:\n" +
                            newTask +
                            "\n Now you have " + taskList.getTaskCount() + " tasks in the list.\n");
        }
    }

    /**
     * Adds an event task to the task list and provides user feedback
     *
     * @param description The description of the event task
     * @param eventFromDate The start date of the event task
     * @param eventToDate The end date of the event task
     * @throws EventException If the description, start date or end date is empty
     */
    public void addEvent(String description, LocalDate eventFromDate, LocalDate eventToDate) throws EventException {
        if (description.isEmpty() || eventFromDate == null || eventToDate == null) {
            throw new EventException();
        } else {
            Task newTask = new Event(description, eventFromDate, eventToDate);
            taskList.addTask(newTask);
            Ui.showMessage(
                    " Got it. I've added this task:\n" +
                            newTask +
                            "\n Now you have " + taskList.getTaskCount() + " tasks in the list.\n");
        }
    }

    /**
     * Loads the tasks from the storage file
     */
    private void loadTasksFromFile() {
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
    private void saveTasksToFile(TaskList taskList) {
        this.storage.saveTasks(taskList);
    }

    /**
     * Finds tasks containing the given keyword and displays them.
     *
     * @param keyword The keyword to search for.
     */
    public void findTasksByKeyword(String keyword) {
        ArrayList<Task> matchingTasks = taskList.findTasksByKeyword(keyword);
        if (matchingTasks.isEmpty()) {
            Ui.showMessage("No matching tasks found.");
        } else {
            StringBuilder matchingTasksString = new StringBuilder();
            matchingTasksString.append("Here are the matching tasks in your list:\n");
            int count = 1;
            for (Task task : matchingTasks) {
                matchingTasksString.append(count).append(".").append(task).append("\n");
                count++;
            }
            Ui.showMessage(matchingTasksString.toString());
        }
    }

    /**
     * Handles the command based on the command type
     *
     * @param command The command to be handled
     * @return True if the command is not "bye", false otherwise
     * @throws DukeException If the command type is not recognized
     */
    public boolean handleCommand(Command command) throws DukeException {
        String commandType = command.getCommandType();
        String description = command.getDescription();
        int taskIndex = command.getTaskIndex();
        LocalDate deadlineDate = command.getDeadlineDate();
        LocalDate eventFromDate = command.getEventFromDate();
        LocalDate eventToDate = command.getEventToDate();

        switch (commandType) {
        case "mark":
            this.markTaskByBot(taskIndex);
            break;
        case "unmark":
            this.unmarkTaskByBot(taskIndex);
            break;
        case "bye":
            return false;
        case "list":
            Ui.showMessage(this.taskList.toString());
            break;
        case "find":
            this.findTasksByKeyword(description);
            break;
        case "todo":
            this.addTodo(description);
            break;
        case "deadline":
            this.addDeadline(description, deadlineDate);
            break;
        case "event":
            this.addEvent(description, eventFromDate, eventToDate);
            break;
        case "delete":
            this.deleteTaskByBot(taskIndex);
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }

    /**
     * Starts the chatbot application
     */
    public void start() {
        Ui.showWelcomeMessage();
        String userInput;
        Command parsedCommand;

        this.loadTasksFromFile();

        boolean isContinuing = true;
        while (isContinuing) {
            try {
                userInput = Ui.getUserInput();
                parsedCommand = Parser.parse(userInput);
                isContinuing = handleCommand(parsedCommand);
                this.saveTasksToFile(this.taskList);
            } catch (DukeException e) {
               Ui.showErrorMessage(e.getMessage());
            }
        }

        Ui.showGoodByeMessage();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
