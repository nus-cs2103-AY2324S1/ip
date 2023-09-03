package seedu.duke.util;

import seedu.duke.Duke;

import seedu.duke.exception.InvalidCommandException;
import seedu.duke.exception.MissingInputException;

import seedu.duke.task.Task;
import seedu.duke.task.ToDo;
import seedu.duke.task.Event;
import seedu.duke.task.Deadline;

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

    /**
     * Parses the user's input and executes the corresponding commands.
     *
     * @param userInput The user's input.
     * @param taskList The list of tasks.
     * @param ui The Ui for displaying messages.
     * @param storage The Storage for saving and loading tasks.
     */
    public void parseUserInput(String userInput, TaskList taskList, Ui ui, Storage storage) {

        try {
            String[] individualWords = userInput.split(" ");

            if (individualWords.length > 0) { // To handle whitespace input
                String firstWord = individualWords[0];
                String lowerCapsFirstWord = firstWord.toLowerCase();

                try {
                    switch (lowerCapsFirstWord) {
                        case "bye":
                            ui.showGoodbye();
                            Duke.isDone = true;
                            break;
                        case "list":
                            listTasks(taskList, ui);
                            break;
                        case "mark":
                            markTask(userInput, taskList, ui);
                            break;
                        case "unmark":
                            unmarkTask(userInput, taskList, ui);
                            break;
                        case "delete":
                            deleteTask(userInput, taskList, ui);
                            break;
                        case "todo":
                            addTodoTask(userInput, taskList, ui);
                            break;
                        case "deadline":
                            addDeadlineTask(userInput, taskList, ui);
                            break;
                        case "event":
                            addEventTask(userInput, taskList, ui);
                            break;
                        case "find":
                            findByKeyword(userInput, taskList, ui);
                            break;
                        default:
                            throw new InvalidCommandException();
                    }

                    try {
                        storage.save(taskList.getAllTasks());
                    } catch (IOException e) {
                        ui.showMessage("Cannot save tasks.");
                    }
                } catch (MissingInputException | InvalidCommandException | IndexOutOfBoundsException e) {
                    ui.showMessage(e.getMessage());
                }
            } else {
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e) {
            ui.showMessage(e.getMessage());
        }
    }

    /**
     * Displays the list of tasks saved.
     *
     * @param taskList The list of tasks.
     * @param ui The Ui for displaying messages.
     */
    private void listTasks(TaskList taskList, Ui ui) {
        if (taskList.getSize() == 0) {
            ui.showMessage("You have 0 task.");
        } else {
            System.out.println("Here are your tasks:"); // Not using ui.printLine() to prevent lines between the tasks
            for (int i = 0; i < taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
                System.out.println((i + 1) + "." + task.toString());
            }
            ui.printLine();
        }
    }

    /**
     * Marks a task as done.
     *
     * @param userInput The user's input.
     * @param taskList The list of tasks.
     * @param ui The Ui for displaying messages.
     * @throws MissingInputException If the user input is missing required information.
     */
    private void markTask(String userInput, TaskList taskList, Ui ui) throws MissingInputException {
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
            task.updateTaskStatus(true, "Task " + (taskNumber + 1) + " is already done!", "Great job! Task " + (taskNumber + 1) + " is done!");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.showMessage("Invalid task number.");
        }
    }

    /**
     * Marks a task as undone.
     *
     * @param userInput The user's input.
     * @param taskList The list of tasks.
     * @param ui The Ui for displaying messages.
     * @throws MissingInputException If the user input is missing required information.
     */
    private void unmarkTask(String userInput, TaskList taskList, Ui ui) throws MissingInputException {
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
            task.updateTaskStatus(false, "Task " + (taskNumber + 1) + " is still incomplete.", "Okay, I've updated Task " + (taskNumber + 1) + " to be incomplete.");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.showMessage("Invalid task number.");
        }
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param userInput The user's input.
     * @param taskList The list of tasks.
     * @param ui The Ui for displaying messages.
     * @throws MissingInputException If the user input is missing required information.
     */
    private void deleteTask(String userInput, TaskList taskList, Ui ui) throws MissingInputException {
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
            ui.showMessage("This task has been removed:\n  " + deletedTask + "\nYou have a total of " + taskList.getSize() + (taskList.getSize() == 1 ? " task.\n" : " tasks."));
        } catch (NumberFormatException e) {
            ui.showMessage("Invalid task number.");
        }
    }

    /**
     * Adds a ToDo task to the list of tasks.
     *
     * @param userInput The user's input.
     * @param taskList The list of tasks.
     * @param ui The Ui for displaying messages.
     * @throws MissingInputException If the user input is missing required information.
     */
    private void addTodoTask(String userInput, TaskList taskList, Ui ui) throws MissingInputException {
        String[] individualWords = userInput.split(" ");
        if (individualWords.length <= 1) {
            throw new MissingInputException("The description of a todo cannot be empty!");
        }

        String description = userInput.substring(5).trim();
        ToDo task = new ToDo(description);
        taskList.addTask(task);
        ui.showMessage("I've added this task:\n  " + task + "\nYou have a total of " + taskList.getSize() + (taskList.getSize() == 1 ? " task." : " tasks."));
    }

    /**
     * Adds a Deadline task to the list of tasks.
     *
     * @param userInput The user's input.
     * @param taskList The list of tasks.
     * @param ui The Ui for displaying messages.
     * @throws MissingInputException If the user input is missing required information.
     */
    private void addDeadlineTask(String userInput, TaskList taskList, Ui ui) throws MissingInputException {
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
                ui.showMessage("I've added this task:\n  " + task + "\nYou have a total of " + taskList.getSize() + (taskList.getSize() == 1 ? " task." : " tasks."));
            } else {
                ui.printLine();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingInputException("You are missing one or some of these inputs - description/ by.");
        }
    }

    /**
     * Adds an Event task to the list of tasks.
     *
     * @param userInput The user's input.
     * @param taskList The list of tasks.
     * @param ui The Ui for displaying messages.
     * @throws MissingInputException If the user input is missing required information.
     */
    private void addEventTask(String userInput, TaskList taskList, Ui ui) throws MissingInputException {
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
                taskList.addTask(task);
                ui.showMessage("I've added this task:\n  " + task + "\nYou have a total of " + taskList.getSize() + (taskList.getSize() == 1 ? " task." : " tasks."));
            } else {
                ui.printLine();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingInputException("You are missing one or some of these inputs - description/ from/ to.");
        }
    }

    private void findByKeyword (String userInput, TaskList taskList, Ui ui) throws MissingInputException {
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
                ui.showMessage("No matching tasks found.");
            } else {
                System.out.println("Here are the matching tasks in your list:");
                for (int i = 0; i < matchingTasks.size(); i++) {
                    Task task = matchingTasks.get(i);
                    System.out.println((i + 1) + "." + task.toString());
                }
                ui.printLine();
                matchingTasks.clear();
            }
        }
    }
}