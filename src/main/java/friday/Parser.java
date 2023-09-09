package friday;

import java.io.IOException;
import java.util.Scanner;

public class Parser {

    /**
     * Saves tasks to tasks.txt.
     * If an error occurs during save operation, an error message is printed.
     */
    public void saveTasks(String taskList, Storage storage) {
        try {
            storage.saveFile(taskList);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Processes user input and interacts with the TaskList to execute user commands.
     * @param userInput The command input by the user.
     * @param taskList The list of tasks.
     * @param storage The storage object to save tasks.
     */
    public String processUserCommand(String userInput, TaskList taskList, Storage storage) {
        try {
            if (userInput.equals("bye")) {
                return "Bye. Hope to see you again soon!";
            } else if (userInput.equals("list")) {
                return "Here are the tasks in your list:\n" + taskList.toString();
            } else if (userInput.contains("unmark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                taskList.unmark(taskNumber - 1);
                saveTasks(taskList.toString(), storage);
                return "Nice! I've marked this task as not done yet:";
            } else if (userInput.contains("mark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                taskList.mark(taskNumber - 1);
                saveTasks(taskList.toString(), storage);
                return "Nice! I've marked this task as done:";
            } else if (userInput.contains("delete")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                taskList.delete(taskNumber - 1);
                saveTasks(taskList.toString(), storage);
                return "Task deleted successfully!";
            } else if (userInput.contains("find")) {
                String[] findInput = userInput.split(" ", 2);
                if (findInput.length < 2 || findInput[1].trim().isEmpty()) {
                    return "Oops! Please add a keyword to search for!";
                }
                TaskList result = taskList.findTasks(findInput[1]);
                return "Here are the matching tasks in your list:\n" + result.toString();
            } else if (userInput.contains("todo")) {
                String[] todoInput = userInput.split(" ", 2);
                if (todoInput.length < 2 || todoInput[1].trim().isEmpty()) {
                    return "OOPS!!! The description of a todo cannot be empty.";
                }
                Todo todo = new Todo(todoInput[1]);
                taskList.add(todo);
                saveTasks(taskList.toString(), storage);
                return "added: " + todo.toString();
            } else if (userInput.contains("deadline")) {
                String[] commandAndDetails = userInput.split(" ", 2);
                if (commandAndDetails.length < 2 || !userInput.contains("/by")) {
                    return "Incorrect format for 'deadline'. Here is a sample:\ndeadline return book /by Sunday";
                }
                String[] taskAndDate = commandAndDetails[1].split(" /by ", 2);
                if (taskAndDate.length < 2) {
                    return "Please provide both a task description and a deadline date.";
                }
                String taskDescription = taskAndDate[0];
                String deadlineDate = taskAndDate[1];
                Deadline deadline = new Deadline(taskDescription, deadlineDate);
                taskList.add(deadline);
                saveTasks(taskList.toString(), storage);
                return "added: " + deadline.toString();
            } else if (userInput.contains("event")) {
                String[] commandAndDetails = userInput.split(" ", 2);
                if (commandAndDetails.length < 2 || !userInput.contains("/from") || !userInput.contains("/to")) {
                    return "Incorrect format for 'event'. Expected format: event TASK_DESCRIPTION /from START_TIME /to END_TIME";
                }
                String[] taskAndTimes = commandAndDetails[1].split(" /from | /to ", 3);
                if (taskAndTimes.length < 3) {
                    return "Please provide a task description, start time, and end time.";
                }
                String taskDescription = taskAndTimes[0];
                String startTime = taskAndTimes[1];
                String endTime = taskAndTimes[2];
                Event event = new Event(taskDescription, startTime, endTime);
                taskList.add(event);
                saveTasks(taskList.toString(), storage);
                return "added: " + event.toString();
            } else {
                return "OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }
    }
}