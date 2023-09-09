package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The {@code Duke} class. Main class that drives other functions.
 */
public class Duke {

    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Initiates a new {@code Duke} object.
     *
     * @throws IOException When the {@code saveTasksToDisk()} method in the {@code Storage} class
     *                     fails to function properly.
     */
    public Duke() throws IOException {
        this.tasks = new TaskList();
        this.ui = new Ui();
        Storage storage = new Storage(tasks);
        this.parser = new Parser(this, tasks, ui, storage);
        storage.launchOnStart();
        storage.readTasksFromDisk("./data/tasks.txt");
    }

    /**
     * Passes the user input to the {@code Parser} that returns the required response.
     */
    public String getResponse(String input) throws IOException {
        return parser.readInput(input);
    }

    /**
     * Helper function to execute the {@code TASK} command.
     *
     * @param res     StringBuilder to be returned in {@code executeCommand}.
     * @param details Details of task.
     */
    public void executeTask(StringBuilder res, String details) {
        if (tasks.checkDuplicates(details)) {
            res.append(ui.getDuplicateTasksMessage(details));
        } else {
            tasks.add(new Task(details));
            res.append(ui.getTaskAddedMessage(details));
        }
    }

    /**
     * Helper function to execute the {@code TODO} command.
     *
     * @param res     StringBuilder to be returned in {@code executeCommand}.
     * @param details Details of TODO.
     */
    public void executeTodo(StringBuilder res, String details) {
        if (tasks.checkDuplicates(details)) {
            res.append(ui.getDuplicateTasksMessage(details));
        } else {
            tasks.add(new ToDo(details));
            res.append(ui.getTodoAddedMessage(details));
        }
    }

    /**
     * Helper function to execute the {@code DEADLINE} command.
     *
     * @param res     StringBuilder to be returned in {@code executeCommand}.
     * @param details Details of deadline.
     */
    public void executeDeadline(StringBuilder res, String details, LocalDateTime dueDateTime) {
        if (tasks.checkDuplicates(details)) {
            res.append(ui.getDuplicateTasksMessage(details));
        } else if (checkStartDateTime(dueDateTime)) {
            res.append(ui.getInvalidStartMessage(Parser.Command.DEADLINE));
        } else {
            tasks.add(new Deadline(details, dueDateTime));
            res.append(ui.getDeadlineAddedMessage(details));
        }
    }

    /**
     * Helper function to execute the {@code EVENT} command.
     *
     * @param res     StringBuilder to be returned in {@code executeCommand}.
     * @param details Details of event.
     */
    public void executeEvent(StringBuilder res, String details, LocalDateTime startDateTime,
                             LocalDateTime endDateTime) {
        if (tasks.checkDuplicates(details)) {
            res.append(ui.getDuplicateTasksMessage(details));
        } else if (checkStartDateTime(endDateTime)) {
            res.append(ui.getInvalidStartMessage(Parser.Command.EVENT));
        } else if (!checkTimeInterval(startDateTime, endDateTime)) {
            res.append(ui.getInvalidIntervalMessage(Parser.Command.EVENT));
        } else {
            tasks.add(new Event(details, startDateTime, endDateTime));
            res.append(ui.getEventAddedMessage(details));
        }
    }

    /**
     * Helper function to execute the {@code LIST} command.
     *
     * @param res StringBuilder to be returned in {@code executeCommand}.
     */
    public void executeList(StringBuilder res) {
        int numOfTasks = tasks.getNumOfTasks();
        int numOfCompletedTasks = tasks.getNumOfCompletedTasks();
        res.append(ui.getListSummary(numOfTasks, numOfCompletedTasks));
        res.append(ui.getTasksInList(tasks));
        if (numOfCompletedTasks == numOfTasks) {
            res.append(ui.getAllCompleteMessage());
        } else {
            res.append(ui.getNotAllCompleteMessage());
        }
    }

    /**
     * Helper function to execute the {@code MARK} command.
     *
     * @param res        StringBuilder to be returned in {@code executeCommand}.
     * @param taskNumber Index of task to be marked.
     */
    public void executeMark(StringBuilder res, int taskNumber) {
        if (markAsComplete(taskNumber)) {
            res.append(ui.getMarkMessage(Parser.Command.MARK, taskNumber));
        } else {
            res.append(ui.getMarkRedundantMessage(Parser.Command.MARK, taskNumber));
        }
    }

    /**
     * Helper function to execute the {@code UNMARK} command.
     *
     * @param res        StringBuilder to be returned in {@code executeCommand}.
     * @param taskNumber Index of task to be unmarked.
     */
    public void executeUnmark(StringBuilder res, int taskNumber) {
        if (markAsIncomplete(taskNumber)) {
            res.append(ui.getMarkMessage(Parser.Command.UNMARK, taskNumber));
        } else {
            res.append(ui.getMarkRedundantMessage(Parser.Command.UNMARK, taskNumber));
        }
    }

    /**
     * Helper function to execute the {@code DELETE} command.
     *
     * @param res        StringBuilder to be returned in {@code executeCommand}.
     * @param taskNumber Index of task to be deleted.
     */
    public void executeDelete(StringBuilder res, int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        tasks.remove(task);
        res.append(ui.getTaskDeletedMessage(tasks, taskNumber));
    }

    /**
     * Helper function to execute the {@code SEARCH} command.
     *
     * @param res     StringBuilder to be returned in {@code executeCommand}.
     * @param keyword Keyword to be searched.
     */
    public void executeSearch(StringBuilder res, String keyword) {
        ArrayList<Task> matchingTasks = getMatchingTasks(keyword);
        if (matchingTasks.isEmpty()) {
            res.append(ui.getEmptySearchResultsMessage(keyword));
        } else {
            res.append(ui.getSearchResultsMessage(matchingTasks));
        }
    }

    /**
     * Marks a selected task as complete, with the task number input by the user.
     */
    public boolean markAsComplete(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        if (!task.isCompleted) {
            task.setCompleted();
            tasks.incrementCompletedTasks();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Marks a selected task as incomplete, with the task number input by the user.
     */
    public boolean markAsIncomplete(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        if (task.isCompleted) {
            task.setIncomplete();
            tasks.decrementCompletedTasks();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets all tasks in the {@code TaskList} containing a keyword input by the user.
     *
     * @return {@code ArrayList} of tasks matching the keyword; {@code null} if the user input is blank.
     */
    public ArrayList<Task> getMatchingTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.getNumOfTasks(); i++) {
            Task t = tasks.get(i);
            String details = t.getDetails();
            if (details.contains(keyword)) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }

    /**
     * Checks if the input {@code LocalDateTime} is at or after the current time.
     *
     * @param dateTime {@code LocalDateTime} of the {@code Task}.
     * @return {@code true} if the {@code LocalDateTime} is at or after the current time; {@code false} otherwise.
     */
    public boolean checkStartDateTime(LocalDateTime dateTime) {
        return dateTime.isAfter(LocalDateTime.now());
    }

    /**
     * Checks if the end time of a {@code Task} is at or after the start time.
     *
     * @param startTime Start time of the {@code Task}.
     * @param endTime   End time of the {@code Task}.
     * @return {@code true} if the end time is at or after the start time; {@code false} otherwise.
     */
    public boolean checkTimeInterval(LocalDateTime startTime, LocalDateTime endTime) {
        return endTime.isAfter(startTime);
    }

    /**
     * Driver function to execute commands once all operations are complete.
     *
     * @param command        Command to be executed.
     * @param message        Message that is passed from the {@code Parser}.
     * @param localDateTimes Variable number of {@code LocalDateTime}s to account for the parameters
     *                       of commands.
     * @return Output of Duke as a String.
     */
    public String executeCommand(Parser.Command command, String message,
                                 LocalDateTime... localDateTimes) {
        StringBuilder res = new StringBuilder();
        switch (command) {
        case TASK -> executeTask(res, message);
        case TODO -> executeTodo(res, message);
        case DEADLINE -> executeDeadline(res, message, localDateTimes[0]);
        case EVENT -> executeEvent(res, message, localDateTimes[0], localDateTimes[1]);
        case LIST -> executeList(res);
        case MARK -> {
            int taskNumber = Integer.parseInt(message);
            executeMark(res, taskNumber);
        }
        case UNMARK -> {
            int taskNumber = Integer.parseInt(message);
            executeUnmark(res, taskNumber);
        }
        case DELETE -> {
            int taskNumber = Integer.parseInt(message);
            executeDelete(res, taskNumber);
        }
        case FIND, SEARCH -> executeSearch(res, message);
        case COMMANDS -> res.append(ui.getCommands());
        default -> {
        }
        }
        parser.resetCommandInExecution();
        return res.toString();
    }

    public static void main(String[] args) throws IOException {
        new Duke();
    }
}
