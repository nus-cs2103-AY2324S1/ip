package taskmaster.parser;

import taskmaster.Taskmaster;
import taskmaster.tasks.*;
import taskmaster.storage.Storage;
import taskmaster.exceptions.DukeException;

public class Parser {

    public Parser () {}

    public void parse (String userInput, Storage storage) throws DukeException {
        if (userInput.equalsIgnoreCase("bye")) {
            Taskmaster.activated = false;
        } else if (userInput.equalsIgnoreCase("list")) {
            TaskList.printList();
        } else if (userInput.startsWith("todo")) {
            String description = userInput.substring(5).trim();
            TaskList.addTask(TaskList.TaskType.TODO, description, userInput, "unmarked");
            storage.saveTasksToFile();
        } else if (userInput.startsWith("event")) {
            String description = userInput.substring(5);
            TaskList.addTask(TaskList.TaskType.EVENT, description, userInput, "unmarked");
            storage.saveTasksToFile();
        } else if (userInput.startsWith("deadline")) {
            String description = userInput.substring(8);
            TaskList.addTask(TaskList.TaskType.DEADLINE, description, userInput, "unmarked");
            storage.saveTasksToFile();
        } else if (userInput.startsWith("mark")) {
            TaskList.toggleMark(TaskList.MarkStatus.MARK, userInput);
            storage.saveTasksToFile();
        } else if (userInput.startsWith("unmark")) {
            TaskList.toggleMark(TaskList.MarkStatus.UNMARK, userInput);
            storage.saveTasksToFile();
        } else if (userInput.startsWith("delete")) {
            String[] parts = userInput.split(" ");
            if (parts.length == 2) {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                TaskList.deleteTask(taskIndex);
            } else {
                System.out.println("Please specify the task number to delete.");
            }
            storage.saveTasksToFile();
        } else if (userInput.startsWith("due")) {
            String date = userInput.substring(4).trim();
            TaskList.printTasksByDate(date);
        } else {
            throw new DukeException("Please enter a valid command!");
        }
    }
}
