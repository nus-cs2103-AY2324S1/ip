package seedu.duke.util;

import seedu.duke.Duke;

import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.MissingInputException;

import seedu.duke.tasks.Task;
import seedu.duke.tasks.ToDo;
import seedu.duke.tasks.Event;
import seedu.duke.tasks.Deadline;

import java.io.IOException;

public class Parser {

    public void parseUserInput(String userInput, TaskList taskList, Ui ui, Storage storage) {
        String[] individualWords = userInput.split(" ");
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
                default:
                    throw new InvalidCommandException();
            }

            try {
                storage.save(taskList.getAllTasks());
            } catch (IOException e) {
                ui.showError("Cannot save tasks.");
            }
        } catch (MissingInputException | InvalidCommandException | IndexOutOfBoundsException e) {
            ui.showError(e.getMessage());
        }
    }

    private void listTasks(TaskList taskList, Ui ui) {
        if (taskList.getSize() == 0) {
            ui.showMessage("You have 0 task.");
            ui.printLine();
        } else {
            ui.showMessage("Here are your tasks:");
            for (int i = 0; i < taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
                ui.showMessage((i + 1) + "." + task.toString());
            }
            ui.printLine();
        }
    }

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
            ui.showError("Invalid task number.");
        }
    }

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
            ui.showError("Invalid task number.");
        }
    }

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
            ui.printLine();
        } catch (NumberFormatException e) {
            ui.showError("Invalid task number.");
        }
    }

    private void addTodoTask(String userInput, TaskList taskList, Ui ui) throws MissingInputException {
        String[] individualWords = userInput.split(" ");
        if (individualWords.length <= 1) {
            throw new MissingInputException("The description of a todo cannot be empty!");
        }

        String description = userInput.substring(5).trim();
        ToDo task = new ToDo(description);
        taskList.addTask(task);
        ui.showMessage("I've added this task:\n  " + task + "\nYou have a total of " + taskList.getSize() + (taskList.getSize() == 1 ? " task." : " tasks."));
        ui.printLine();
    }

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
                ui.printLine();
            } else {
                ui.printLine();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingInputException("You are missing one or some of these inputs - description/ by.");
        }
    }

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
                ui.printLine();
            } else {
                ui.printLine();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingInputException("You are missing one or some of these inputs - description/ from/ to.");
        }
    }
}