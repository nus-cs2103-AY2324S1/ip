package duke;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    public static boolean userCommand(String input, ArrayList<Task> taskList,
                                      Ui ui, Storage storage) throws DukeException {
        if (input.equals("bye")) {
            // Farewell message
            ui.displayByeMessage();
            try {
                storage.appendTasksToFile(taskList);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
            return true;
        } else if (input.equals("list")) {
            ui.displayListMessage(taskList);
        } else if (input.startsWith("todo")) {
            if (input.length() <= 5) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            String description = input.substring(5).trim();
            taskList.add(new ToDo(description, 'T'));

            ui.displayToDoMessage(taskList);

        } else if (input.startsWith("deadline")) {
            int byIndex = input.indexOf("/by");
            String description = input.substring(9, byIndex).trim();
            String by = input.substring(byIndex + 3).trim();
            taskList.add(new Deadline(description, by, 'D'));
            ui.displayDeadlineMessage(taskList);
        } else if (input.startsWith("event")) {
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            String description = input.substring(6, fromIndex).trim();
            String from = input.substring(fromIndex + 5, toIndex).trim();
            String to = input.substring(toIndex + 3).trim();
            taskList.add(new Event(description, from, to, 'E'));
            ui.displayEventMessage(taskList);
        } else if (input.startsWith("mark")) {
            int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                taskList.get(taskIndex).markAsDone();
                ui.displayMarkMessage(taskList, taskIndex);
            } else {
                System.out.println("Invalid task index.");
            }
        } else if (input.startsWith("unmark")) {
            int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                taskList.get(taskIndex).markAsNotDone();
                ui.displayUnmarkMessage(taskList, taskIndex);
            } else {
                System.out.println("Invalid task index.");
            }
        } else if (input.startsWith("delete")) {
            int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                Task removedTask = taskList.remove(taskIndex);
                ui.displayDeleteMessage(taskList, removedTask);
            } else {
                System.out.println("Invalid task index.");
            }
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return false;

    }
}

