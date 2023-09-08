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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws IOException {
        return parser.readInput(input);
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public void executeTask(StringBuilder res, String details) {
        if (tasks.checkDuplicates(details)) {
            res.append(ui.getDuplicateTasksMessage(details));
        } else {
            tasks.add(new Task(details));
            res.append(ui.getTaskAddedMessage(details));
        }
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public void executeTodo(StringBuilder res, String details) {
        if (tasks.checkDuplicates(details)) {
            res.append(ui.getDuplicateTasksMessage(details));
        } else {
            tasks.add(new ToDo(details));
            res.append(ui.getTodoAddedMessage(details));
        }
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
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

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
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

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
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

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public void executeMark(StringBuilder res, int taskNumber) {
        if (markAsComplete(taskNumber)) {
            res.append(ui.getMarkMessage(Parser.Command.MARK, taskNumber));
        } else {
            res.append(ui.getMarkRedundantMessage(Parser.Command.MARK, taskNumber));
        }
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public void executeUnmark(StringBuilder res, int taskNumber) {
        if (markAsIncomplete(taskNumber)) {
            res.append(ui.getMarkMessage(Parser.Command.UNMARK, taskNumber));
        } else {
            res.append(ui.getMarkRedundantMessage(Parser.Command.UNMARK, taskNumber));
        }
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public void executeDelete(StringBuilder res, int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        tasks.remove(task);
        res.append(ui.getTaskDeletedMessage(tasks, taskNumber));
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
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
     * Checks if the input {@code LocalDateTime} is at or after the current system time.
     *
     * @param dateTime {@code LocalDateTime} of the {@code Task}.
     * @return {@code true} if the {@code LocalDateTime} is at or after the current system time;
     * {@code false} otherwise.
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

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
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
        case SEARCH -> executeSearch(res, message);
        case COMMANDS -> res.append(ui.getCommands());
        default -> {
        }
        }
        res.append(ui.getEndOfOperationMessage());
        parser.resetCommandInExecution();
        return res.toString();
    }

    public static void main(String[] args) throws IOException {
        new Duke();
    }
}
