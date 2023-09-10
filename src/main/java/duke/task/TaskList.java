package duke.task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import duke.exception.IllegalTaskIndexException;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.ui.Ui;


/**
 * Represents a list of tasks.
 */
public class TaskList {

    /**
     * Represents the type of task.
     */
    public enum TaskType {

        TODO {
            @Override
            public Task createTask(String details) {
                return new ToDo(details);
            }
        },
        DEADLINE {
            @Override
            public Task createTask(String details) throws InvalidArgumentException {
                String[] split = details.split(" /by ");
                if (split.length != 2) {
                    throw new InvalidArgumentException("☹ OOPS!!! The deadline format is incorrect. "
                            + "It should be: deadline <name> /by <date> <time>");
                }
                String taskName = split[0];
                String dateTime = split[1];
                return new Deadline(taskName, parseDateTime(dateTime));
            }
        },
        EVENT {
            @Override
            public Task createTask(String details) throws InvalidArgumentException {
                String[] firstSplit = details.split(" /from ");
                String[] secondSplit = firstSplit[firstSplit.length - 1].split(" /to ");
                if (firstSplit.length != 2 || secondSplit.length != 2) {
                    throw new InvalidArgumentException("☹ OOPS!!! The event format is incorrect. "
                            + "It should be: event <name> /from <date> <time> /to <date> <time>");
                }
                String taskName = firstSplit[0];
                String startDateTime = secondSplit[0];
                String endDateTime = secondSplit[1];
                return new Event(taskName, parseDateTime(startDateTime), parseDateTime(endDateTime));
            }
        };

        private static final DateTimeFormatter[] DATE_TIME_FORMATS = {
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"),
        };

        /**
         * Creates a deadline duke.task.
         * @param details The details of the deadline duke.task.
         * @return The deadline duke.task.
         * @throws InvalidArgumentException If the deadline task's format is invalid.
         */
        public abstract Task createTask(String details) throws InvalidArgumentException;
        private static LocalDateTime parseDateTime(String dateTime) throws InvalidArgumentException {
            for (DateTimeFormatter format: DATE_TIME_FORMATS) {
                try {
                    return LocalDateTime.parse(dateTime, format);
                } catch (DateTimeParseException e) {
                    // Do nothing
                }
            }
            throw new InvalidArgumentException("☹ OOPS!!! Your dateTime format is not supported!");
        }
    }

    private List<Task> tasks;
    private final Storage storage;

    /**
     * Constructor for TaskList.
     */
    public TaskList(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.load();
        } catch (IOException e) {
            Ui.getErrorLoadingFromFileMessage();
            this.tasks = new ArrayList<>();
        }
    }

    /**
     * Adds a task to the list of tasks.
     * @param command The command that the user inputted.
     * @return The add task message.
     * @throws InvalidArgumentException If the task's format is invalid.
     */
    public String addTask(String command) throws InvalidArgumentException {
        StringBuilder output = new StringBuilder();
        String[] splitCommand = command.split("\\s", 2);
        if (splitCommand.length < 2) {
            throw new InvalidArgumentException("☹ OOPS!!! The description cannot be empty.");
        }
        String type = splitCommand[0];
        String taskDetails = splitCommand[1];
        if (taskDetails.trim().isEmpty()) {
            throw new InvalidArgumentException("☹ OOPS!!! The description cannot be empty.");
        }
        TaskType taskType;
        try {
            taskType = TaskType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidArgumentException("Bro your task type is unknown: " + type);
        }
        tasks.add(taskType.createTask(taskDetails));
        try {
            storage.save(tasks);
        } catch (IOException e) {
            output.append(Ui.getErrorSavingToFileMessage());
        }
        output.append(Ui.getAddTaskMessage(tasks));
        return output.toString();
    }

    /**
     * Lists all the tasks.
     * @return The list tasks message.
     */
    public String listTasks() {
        return Ui.getListTasksMessage(tasks);
    }

    /**
     * Marks a duke.task as done.
     * @param index The index of the task to be marked as done.
     * @return The mark as done message.
     * @throws IllegalTaskIndexException If the index is invalid.
     */
    public String markAsDone(int index) throws IllegalTaskIndexException {
        StringBuilder output = new StringBuilder();
        if (index > tasks.size() || index < 1) {
            throw new IllegalTaskIndexException();
        }
        output.append(Ui.getMarkAsDoneMessage(tasks, index));
        assert tasks.get(index - 1).isDone : "Task should be marked as done";
        try {
            storage.save(tasks);
        } catch (IOException e) {
            output.append(Ui.getErrorSavingToFileMessage());
        }
        return output.toString();

    }

    /**
     * Marks a duke.task as undone.
     * @param index The index of the task to be marked as undone.
     * @return The mark as undone message.
     * @throws IllegalTaskIndexException If the index is invalid.
     */
    public String markAsUndone(int index) throws IllegalTaskIndexException {
        StringBuilder output = new StringBuilder();
        if (index > tasks.size() || index < 1) {
            throw new IllegalTaskIndexException();
        }
        output.append(Ui.getMarkAsUndoneMessage(tasks, index));
        assert !tasks.get(index - 1).isDone : "Task should be marked as done";
        try {
            storage.save(tasks);
        } catch (IOException e) {
            output.append(Ui.getErrorSavingToFileMessage());
        }
        return output.toString();
    }

    /**
     * Delete a task from the list of tasks.
     * @param index The index of the task to be deleted.
     * @return The delete message.
     * @throws IllegalTaskIndexException If the index is invalid.
     */
    public String deleteTask(int index) throws IllegalTaskIndexException {
        StringBuilder output = new StringBuilder();
        if (index > tasks.size() || index < 1) {
            throw new IllegalTaskIndexException();
        }
        // Calls delete message from duke.ui.Ui class
        output.append(Ui.getDeleteTaskMessage(tasks, index));
        tasks.remove(index - 1);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            output.append(Ui.getErrorSavingToFileMessage());
        }
        return output.toString();
    }

    /**
     * Delete all tasks from the list of tasks.
     */
    public void deleteAllTasks() {
        tasks.clear();
        try {
            storage.save(tasks);
        } catch (IOException e) {
            Ui.getErrorSavingToFileMessage();
        }
    }

    /**
     * Find tasks with the given keyword.
     * @param keyword The keyword to search for.
     */
    public String findTasks(String keyword) {
        StringBuilder output = new StringBuilder();
        output.append(Ui.getDottedLine());
        if (tasks.isEmpty()) {
            output.append("There are no tasks in your list.\n");
        } else {
            output.append("Here are the matching tasks in your list:\n");
            int matchingTasks = 0;
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getDescription().contains(keyword)) {
                    output.append((matchingTasks + 1) + "." + tasks.get(i) + "\n");
                    matchingTasks++;
                }
            }
            if (matchingTasks == 0) {
                output.append("Sorry there are no matching tasks!\n");
            }
        }
        output.append(Ui.getDottedLine());
        return output.toString();
    }
}
