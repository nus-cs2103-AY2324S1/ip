package duke;

import java.io.IOException;

public class Parser {

    public static boolean userCommand(String input, TaskList taskList,
                                      Ui ui, Storage storage) throws DukeException {
        if (input.equals("bye")) {
            parseByeCommand(taskList, ui, storage);
            return true;
        } else if (input.equals("list")) {
            parseListCommand(taskList, ui);
        } else if (input.startsWith("todo")) {
            parseToDoCommand(input, taskList, ui);
        } else if (input.startsWith("deadline")) {
            parseDeadlineCommand(input, taskList, ui);
        } else if (input.startsWith("event")) {
            parseEventCommand(input, taskList, ui);
        } else if (input.startsWith("mark")) {
            parseMarkCommand(input, taskList, ui);
        } else if (input.startsWith("unmark")) {
            parseUnmarkCommand(input, taskList, ui);
        } else if (input.startsWith("delete")) {
            parseDeleteCommand(input, taskList, ui);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return false;
    }

    public static void parseByeCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.displayByeMessage();
        try {
            storage.appendTasksToFile(taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void parseListCommand(TaskList taskList, Ui ui) {
        ui.displayListMessage(taskList);
    }

    public static void parseToDoCommand(String input, TaskList taskList,
                                 Ui ui) throws DukeException {
        if (input.length() <= 5) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String description = input.substring(5).trim();
        taskList.addTask(new ToDo(description, 'T'));

        ui.displayToDoMessage(taskList);
    }

    public static void parseDeadlineCommand(String input, TaskList taskList, Ui ui) {
        int byIndex = input.indexOf("/by");
        String description = input.substring(9, byIndex).trim();
        String by = input.substring(byIndex + 3).trim();
        taskList.addTask(new Deadline(description, by, 'D'));
        ui.displayDeadlineMessage(taskList);
    }

    public static void parseEventCommand(String input, TaskList taskList, Ui ui) {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        String description = input.substring(6, fromIndex).trim();
        String from = input.substring(fromIndex + 5, toIndex).trim();
        String to = input.substring(toIndex + 3).trim();
        taskList.addTask(new Event(description, from, to, 'E'));
        ui.displayEventMessage(taskList);
    }

    public static void parseMarkCommand(String input, TaskList taskList, Ui ui) {
        int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
        if (taskIndex >= 0 && taskIndex < taskList.listSize()) {
            taskList.getTask(taskIndex).markAsDone();
            ui.displayMarkMessage(taskList, taskIndex);
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public static void parseUnmarkCommand(String input, TaskList taskList,
                                   Ui ui) {
        int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
        if (taskIndex >= 0 && taskIndex < taskList.listSize()) {
            taskList.getTask(taskIndex).markAsNotDone();
            ui.displayUnmarkMessage(taskList, taskIndex);
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public static void parseDeleteCommand(String input, TaskList taskList,
                                   Ui ui) {
        int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
        if (taskIndex >= 0 && taskIndex < taskList.listSize()) {
            Task removedTask = taskList.deleteTask(taskIndex);
            ui.displayDeleteMessage(taskList, removedTask);
        } else {
            System.out.println("Invalid task index.");
        }
    }

}

