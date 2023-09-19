package duke;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidEventException;
import duke.exception.InvalidNumberException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * This class deals with making sense of the user command.
 */
public class Parser {
    private Ui ui;

    public Parser() {
        ui = new Ui();
    }

    /**
     * Processes the user commands and decides the correct output.
     *
     * @param command The user command.
     * @param taskList The user's list of tasks.
     * @param storage Storage of the user's task list.
     * @return String output depending on the input command.
     * @throws Exception If an error occurs during command processing.
     */
    public String processCommand(String command, TaskList taskList, Storage storage) throws Exception {
        switch (command) {
        case "start":
            storage.createFile();
            return ui.startMessage();
        case "list":
            return ui.showList(taskList);
        case "bye":
            storage.save(taskList.getAllTasks());
            return ui.endMessage();
        case "help":
            return ui.helpMessage();
        case "status":
            return ui.showCompletedList(taskList.getCompletedTaskList(),
                    taskList.getPercentageCompleted());
        default:
            if (command.startsWith("mark ")) {
                return printMark(command, taskList);
            } else if (command.startsWith("unmark ")) {
                return printUnmark(command, taskList);
            } else if (command.startsWith("todo ")) {
                return addToDo(command, taskList);
            } else if (command.startsWith("deadline ")) {
                return addDeadline(command, taskList);
            } else if (command.startsWith("event ")) {
                return addEvent(command, taskList);
            } else if (command.startsWith("delete ")) {
                return deleteTask(command, taskList);
            } else if (command.startsWith("find ")) {
                return ui.showMatchingList(findMatchingTaskList(command, taskList));
            } else {
                checkInvalidDescription(command);
                throw new InvalidCommandException();
            }
        }
    }

    /**
     * Checks the description of a command and throws an exception if it is invalid.
     *
     * @param command The user command.
     * @throws Exception If the description is invalid.
     */
    private void checkInvalidDescription(String command) throws Exception {
        if (command.startsWith("todo")) {
            throw new InvalidDescriptionException("todo");
        } else if (command.startsWith("deadline")) {
            throw new InvalidDescriptionException("deadline");
        } else if (command.startsWith("event")) {
            throw new InvalidDescriptionException("event");
        } else if (command.startsWith("find")) {
            throw new InvalidDescriptionException("find");
        }
    }

    /**
     * Marks a task as done and displays a message to the user.
     *
     * @param command The user command to mark a task as done.
     * @param taskList The list of tasks to update.
     * @return String output of a task being marked.
     * @throws Exception If an invalid number is given.
     */
    public String printMark(String command, TaskList taskList) throws Exception {
        assert taskList != null : "taskList should not be null";
        // Space behind is needed!, number index = 5
        // Convert substring to int, -1 for index
        int taskPos = Integer.parseInt(command.substring(5)) - 1;

        if (taskPos >= 0 && taskPos < taskList.getSize()) {
            taskList.getTask(taskPos).markAsDone();
            return ui.showTaskMarked(taskList.getTask(taskPos));
        } else {
            throw new InvalidNumberException();
        }
    }

    /**
     * Unmarks a task as done and displays a message to the user.
     *
     * @param command The user command to mark a task as done.
     * @param taskList The list of tasks to update.
     * @return String output of a task being unmarked.
     * @throws InvalidNumberException If an invalid number is given.
     * @throws Exception If an error occurs during execution.
     */
    public String printUnmark(String command, TaskList taskList) throws Exception {
        assert taskList != null : "taskList should not be null";
        // Number index = 7
        // Convert substring to int, -1 for index
        int taskPos = Integer.parseInt(command.substring(7)) - 1;

        if (taskPos >= 0 && taskPos < taskList.getSize()) {
            taskList.getTask(taskPos).unmark();
            return ui.showTaskUnmarked(taskList.getTask(taskPos));
        } else {
            throw new InvalidNumberException();
        }
    }

    /**
     * Adds a new ToDo task to the task list and displays a message to the user.
     *
     * @param command The user command.
     * @param taskList The list of tasks to which the task will be added.
     * @return String output of a ToDo task being added.
     * @throws InvalidDescriptionException If the description for the ToDo task is empty.
     * @throws Exception If an error occurs during execution.
     */
    public String addToDo(String command, TaskList taskList) throws Exception {
        assert taskList != null : "taskList should not be null";
        // Description starting index = 5
        String description = command.substring(5);

        if (description.isEmpty()) {
            throw new InvalidDescriptionException("todo");
        }

        Task newTask = new ToDo(description);
        taskList.addTask(newTask);

        return ui.showTaskAdded(newTask, taskList.getSize());
    }

    /**
     * Adds a new Deadline task to the task list and displays a message to the user.
     *
     * @param command The user command.
     * @param taskList The list of tasks to which the task will be added.
     * @return String output of a Deadline task being added.
     * @throws InvalidDescriptionException If the description for the Deadline task is empty.
     * @throws InvalidDeadlineException If there is an invalid deadline task given. (without by)
     * @throws Exception If an error occurs during execution.
     */
    public String addDeadline(String command, TaskList taskList) throws Exception {
        assert taskList != null : "taskList should not be null";
        // indexOf: searches for the substring and returns the index of the first character
        if (command.contains(" /by ")) {
            // Description starting index = 9
            String description = command.substring(9, command.indexOf(" /by "));
            // From " " to the specified date is 5
            String by = command.substring(command.indexOf(" /by ") + 5);

            if (description.isEmpty()) {
                throw new InvalidDescriptionException("deadline");
            }

            if (by.isEmpty()) {
                throw new InvalidDeadlineException();
            }

            Task newTask = new Deadline(description, by);
            taskList.addTask(newTask);

            return ui.showTaskAdded(newTask, taskList.getSize());

        } else {
            throw new InvalidDeadlineException();
        }
    }

    /**
     * Adds a new Event task to the task list and displays a message to the user.
     *
     * @param command The user command.
     * @param taskList The list of tasks to which the task will be added.
     * @return String output of an Event task being added.
     * @throws InvalidDescriptionException If the description for the Deadline task is empty.
     * @throws InvalidEventException If there is an invalid event task given. (without from/to)
     * @throws Exception If an error occurs during execution.
     */
    public String addEvent(String command, TaskList taskList) throws Exception {
        assert taskList != null : "taskList should not be null";
        if (command.contains(" /from ") && command.contains(" /to ")) {
            // Description starting index = 6
            String description = command.substring(6, command.indexOf(" /from "));
            // From " " to 'from' date is 7
            String from = command.substring(command.indexOf(" /from ") + 7,
                    command.indexOf(" /to "));
            // From " " to 'to' date is 5
            String to = command.substring(command.indexOf(" /to ") + 5);

            if (description.isEmpty()) {
                throw new InvalidDescriptionException("event");
            }

            if (from.isEmpty() || to.isEmpty()) {
                throw new InvalidEventException();
            }

            Task newTask = new Event(description, from, to);
            taskList.addTask(newTask);

            return ui.showTaskAdded(newTask, taskList.getSize());

        } else {
            throw new InvalidEventException();
        }
    }

    /**
     * Deletes a task from the task list and displays a message to the user.
     *
     * @param command The user command.
     * @param taskList The list of tasks to which the task will be deleted from.
     * @return String output of a task being deleted.
     * @throws InvalidNumberException If there is an invalid number given.
     * @throws Exception If an error occurs during execution.
     */
    public String deleteTask(String command, TaskList taskList) throws Exception {
        assert taskList != null : "taskList should not be null";
        // Number index = 7
        // Convert substring to int, -1 for index
        int taskPos = Integer.parseInt(command.substring(7)) - 1;
        if (taskPos >= 0 && taskPos < taskList.getSize()) {
            Task deleted = taskList.getTask(taskPos);
            taskList.removeTask(taskPos);

            return ui.showTaskDeleted(deleted, taskList.getSize());

        } else {
            throw new InvalidNumberException();
        }
    }

    /**
     * Returns the list of tasks that matches the keyword indicated.
     *
     * @param command The user command.
     * @param taskList The list of tasks to search from.
     * @return A TaskList of matching tasks.
     * @throws InvalidDescriptionException If the keyword is empty.
     * @throws Exception If an error occurs during execution.
     */
    public TaskList findMatchingTaskList(String command, TaskList taskList) throws Exception {
        assert taskList != null : "taskList should not be null";
        TaskList matchingTaskList = new TaskList();

        // Keyword starting index = 5
        String keyword = command.substring(5);

        if (keyword.isEmpty()) {
            throw new InvalidDescriptionException("find");
        }

        for (int i = 0; i < taskList.getSize(); i++) {
            Task taskUnderCheck = taskList.getTask(i);
            if (taskUnderCheck.getDescription().contains(keyword)) {
                matchingTaskList.addTask(taskUnderCheck);
            }
        }

        return matchingTaskList;
    }

}

