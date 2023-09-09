package duke.util;

import duke.Duke;

import duke.exception.InvalidCommandException;
import duke.exception.MissingInputException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The Parser class is responsible for parsing user input and executing corresponding commands.
 * It handles various user commands such as adding tasks, listing tasks, marking tasks as done, un-marking tasks and
 * deleting tasks.
 *
 * @author Win Sheng
 * @since 3 September 2023
 */
public class Parser {
    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Parses the user's input and executes the corresponding commands.
     *
     * @param userInput The user's input.
     */
    public String parseUserInput(String userInput) {

        try {
            String[] individualWords = userInput.split(" ");

            if (individualWords.length == 0) {
                throw new InvalidCommandException();
            }

            String firstWord = individualWords[0];
            String lowerCapsFirstWord = firstWord.toLowerCase();

            switch (lowerCapsFirstWord) {
                case "bye":
                    Duke.isDone = true;
                    return ui.goodbyeMessage();
                case "list":
                    return listTasks();
                case "mark":
                    return markTask(userInput);
                case "unmark":
                    return unmarkTask(userInput);
                case "delete":
                    return deleteTask(userInput);
                case "todo":
                    return addTodoTask(userInput);
                case "deadline":
                    return addDeadlineTask(userInput);
                case "event":
                    return addEventTask(userInput);
                case "find":
                    return findByKeyword(userInput);
                default:
                    throw new InvalidCommandException();
            }
        } catch (MissingInputException | InvalidCommandException | IndexOutOfBoundsException e) {
            return e.getMessage();
        } finally {
            try {
                storage.save(taskList.getAllTasks());
            } catch (IOException e) {
                return("Cannot save tasks.");
            }
        }
    }

    /**
     * Displays the list of tasks saved.
     */
    private String listTasks() {
        if (taskList.getSize() == 0) {
            return("You have 0 task.");
        } else {
            StringBuilder message = new StringBuilder();
            message.append("Here are your tasks:\n");
            for (int i = 0; i < taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
                message.append((i + 1) + "." + task.toString() + "\n");
            }
            return message.toString();
        }
    }

    /**
     * Marks a task as done.
     *
     * @param userInput The user's input.
     * @throws MissingInputException If the user input is missing required information.
     */
    private String markTask(String userInput) throws MissingInputException {
        String[] individualWords = userInput.split(" ");
        if (individualWords.length <= 1) {
            throw new MissingInputException("Task to mark cannot be empty!");
        }

        if (taskList.isEmpty()) {
            throw new IndexOutOfBoundsException("There are no tasks to mark.");
        }

        try {
            int taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
            Task task = taskList.getTask(taskNumber);
            return task.updateTaskStatus(true, "Task " + (taskNumber + 1) + " is already done!", "Great job! Task " + (taskNumber + 1) + " is done!\n");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return("Invalid task number.");
        }
    }

    /**
     * Marks a task as undone.
     *
     * @param userInput The user's input.
     * @throws MissingInputException If the user input is missing required information.
     */
    private String unmarkTask(String userInput) throws MissingInputException {
        String[] individualWords = userInput.split(" ");
        if (individualWords.length <= 1) {
            throw new MissingInputException("Task to unmark cannot be empty!");
        }

        if (taskList.isEmpty()) {
            throw new IndexOutOfBoundsException("There are no tasks to unmark.");
        }

        try {
            int taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
            Task task = taskList.getTask(taskNumber);
            return task.updateTaskStatus(false, "Task " + (taskNumber + 1) + " is still incomplete.", "Okay, I've updated Task " + (taskNumber + 1) + " to be incomplete.\n");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return("Invalid task number.");
        }
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param userInput The user's input.
     * @throws MissingInputException If the user input is missing required information.
     */
    private String deleteTask(String userInput) throws MissingInputException {
        String[] individualWords = userInput.split(" ");
        if (individualWords.length <= 1) {
            throw new MissingInputException("Task to be deleted cannot be empty!");
        }

        if (taskList.isEmpty()) {
            throw new IndexOutOfBoundsException("There are no tasks to be deleted.");
        }

        try {
            int taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
            Task deletedTask = taskList.getTask(taskNumber);
            taskList.deleteTask(taskNumber);
            return "This task has been removed:\n  " +
                    deletedTask +
                    "\nYou have a total of " +
                    taskList.getSize() +
                    (taskList.getSize() == 1 ? " task.\n" : " tasks.\n");
        } catch (NumberFormatException e) {
            return("Invalid task number.");
        }
    }

    /**
     * Adds a ToDo task to the list of tasks.
     *
     * @param userInput The user's input.
     * @throws MissingInputException If the user input is missing required information.
     */
    private String addTodoTask(String userInput) throws MissingInputException {
        String[] individualWords = userInput.split(" ");
        if (individualWords.length <= 1) {
            throw new MissingInputException("The description of a todo cannot be empty!");
        }

        String description = userInput.substring(5).trim();
        ToDo task = new ToDo(description);
        taskList.addTask(task);
        return "I've added this task:\n  " +
                task +
                "\nYou have a total of " +
                taskList.getSize() +
                (taskList.getSize() == 1 ? " task." : " tasks.");
    }

    /**
     * Adds a Deadline task to the list of tasks.
     *
     * @param userInput The user's input.
     * @throws MissingInputException If the user input is missing required information.
     */
    private String addDeadlineTask(String userInput) throws MissingInputException {
        String[] individualWords = userInput.split(" ");
        if (individualWords.length <= 1) {
            throw new MissingInputException("You are missing one or some of these inputs - description/ by.");
        }

        try {
            String fullStr = userInput.substring(9);
            String[] parts = fullStr.split("/by");
            String description = parts[0].trim();
            String by = parts[1].trim();
            Deadline task = new Deadline(description, by);

            if (task.dateTime != null) {
                taskList.addTask(task);
                return "I've added this task:\n  " +
                        task +
                        "\nYou have a total of " +
                        taskList.getSize() +
                        (taskList.getSize() == 1 ? " task." : " tasks.");
            } else {
                return "Please use the following formats:\n" +
                        "deadline task /by yyyy-mm-dd hhmm\n" +
                        "deadline task /by dd/mm/yyyy hhmm\n" +
                        "deadline task /by yyyy-mm-dd\n" +
                        "deadline task /by dd/mm/yyyy";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingInputException("You are missing one or some of these inputs - description/ by.");
        }
    }

    /**
     * Adds an Event task to the list of tasks.
     *
     * @param userInput The user's input.
     * @throws MissingInputException If the user input is missing required information.
     */
    private String addEventTask(String userInput) throws MissingInputException {
        String[] individualWords = userInput.split(" ");
        if (individualWords.length <= 1) {
            throw new MissingInputException("You are missing one or some of these inputs - description/ from/ to.");
        }

        try {
            String fullStr = userInput.substring(6);
            String[] partialStr = fullStr.split("/from");
            String description = partialStr[0].trim();
            String[] toFrom = partialStr[1].split("/to");
            String from = toFrom[0].trim();
            String to = toFrom[1].trim();
            Event task = new Event(description, from, to);

            if (task.fromDateTime != null && task.toDateTime != null) {
                //If user sets to date to be before from date
                if (task.fromDateTime.isAfter(task.toDateTime)) {
                    return("To date cannot be before from date.");
                }
                taskList.addTask(task);
                return "I've added this task:\n  " + task + "\nYou have a total of " + taskList.getSize() + (taskList.getSize() == 1 ? " task.\n" : " tasks.\n");
            } else {
                return "Please use the following formats:\n" +
                        "event task /from yyyy-mm-dd hhmm /to yyyy-mm-dd hhmm\n" +
                        "event task /from dd/mm/yyyy hhmm /to dd/mm/yyyy hhmm\n" +
                        "event task /from yyyy-mm-dd /to yyyy-mm-dd\n" +
                        "event task /from dd/mm/yyyy /to dd/mm/yyyy\n";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingInputException("You are missing one or some of these inputs - description/ from/ to.");
        }
    }

    /**
     * Finds all tasks matching a specified keyword and displays the list of matching tasks.
     *
     * @param userInput The user's input.
     * @throws MissingInputException If the user input is missing required information.
     */
    private String findByKeyword (String userInput) throws MissingInputException {
        String[] individualWords = userInput.split(" ");
        if (individualWords.length <= 1) {
            throw new MissingInputException("Keyword cannot be empty.");
        } else {
            String keyword = individualWords[1];
            ArrayList<Task> matchingTasks = new ArrayList<>();
            ArrayList<Task> tasks = taskList.getAllTasks();

            for (Task task: tasks) {
                if (task.containsKeyword(keyword)) {
                    matchingTasks.add(task);
                }
            }

            if (matchingTasks.isEmpty()) {
                return("No matching tasks found.");
            } else {
                StringBuilder message = new StringBuilder();
                message.append("Here are the matching tasks in your list:\n");
                for (int i = 0; i < matchingTasks.size(); i++) {
                    Task task = matchingTasks.get(i);
                    message.append((i + 1) + "." + task.toString() + "\n");
                }
                matchingTasks.clear();
                return message.toString();
            }
        }
    }
}