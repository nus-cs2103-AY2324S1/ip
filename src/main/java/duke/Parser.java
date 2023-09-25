package duke;

import java.io.IOException;

public class Parser {

    public static String userCommand(String input, TaskList taskList,
                                      Ui ui, Storage storage) throws DukeException {
        if (input.equals("bye")) {
            return parseByeCommand(taskList, ui, storage);
        } else if (input.equals("list")) {
            return parseListCommand(taskList, ui);
        } else if (input.startsWith("todo")) {
            return parseToDoCommand(input, taskList, ui);
        } else if (input.startsWith("deadline")) {
            return parseDeadlineCommand(input, taskList, ui);
        } else if (input.startsWith("event")) {
            return parseEventCommand(input, taskList, ui);
        } else if (input.startsWith("mark")) {
            return parseMarkCommand(input, taskList, ui);
        } else if (input.startsWith("unmark")) {
            return parseUnmarkCommand(input, taskList, ui);
        } else if (input.startsWith("delete")) {
            return parseDeleteCommand(input, taskList, ui);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static String parseByeCommand(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.appendTasksToFile(taskList);
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
        return ui.displayByeMessage();
    }

    private static String parseListCommand(TaskList taskList, Ui ui) {

        return ui.displayListMessage(taskList);
    }

    public static String parseToDoCommand(String input, TaskList taskList,
                                 Ui ui) throws DukeException {
        if (input.length() <= 5) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String description = input.substring(5).trim();
        taskList.addTask(new ToDo(description, 'T'));

        return ui.displayToDoMessage(taskList);
    }

    private static String parseDeadlineCommand(String input, TaskList taskList, Ui ui) {
        int byIndex = input.indexOf("/by");
        String description = input.substring(9, byIndex).trim();
        String by = input.substring(byIndex + 3).trim();
        taskList.addTask(new Deadline(description, by, 'D'));
        return ui.displayDeadlineMessage(taskList);
    }

    private static String parseEventCommand(String input, TaskList taskList, Ui ui) {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        String description = input.substring(6, fromIndex).trim();
        String from = input.substring(fromIndex + 5, toIndex).trim();
        String to = input.substring(toIndex + 3).trim();
        taskList.addTask(new Event(description, from, to, 'E'));
        return ui.displayEventMessage(taskList);
    }

    private static String parseMarkCommand(String input, TaskList taskList, Ui ui) {
        int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
        if (taskIndex >= 0 && taskIndex < taskList.listSize()) {
            taskList.getTask(taskIndex).markAsDone();
            return ui.displayMarkMessage(taskList, taskIndex);
        } else {
            return "Invalid task index.";
        }
    }

    private static String parseUnmarkCommand(String input, TaskList taskList,
                                   Ui ui) {
        int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
        if (taskIndex >= 0 && taskIndex < taskList.listSize()) {
            taskList.getTask(taskIndex).markAsNotDone();
            return ui.displayUnmarkMessage(taskList, taskIndex);
        } else {
            return "Invalid task index.";
        }
    }

    private static String parseDeleteCommand(String input, TaskList taskList,
                                   Ui ui) {
        int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
        if (taskIndex >= 0 && taskIndex < taskList.listSize()) {
            Task removedTask = taskList.deleteTask(taskIndex);
            return ui.displayDeleteMessage(taskList, removedTask);
        } else {
            return "Invalid task index.";
        }
    }

}

