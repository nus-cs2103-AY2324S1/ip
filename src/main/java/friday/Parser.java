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
     * Processes user inputs and interacts with the TaskList to execute user commands.
     * Continues to run until the user inputs the "bye" command.
     */
    public void echo(Scanner input, TaskList taskList, Ui ui, Storage storage) {
        while (input.hasNextLine()) {
            try {
                String userInput = input.nextLine();
                if (userInput.equals("bye")) {
                    ui.bye();
                    break;
                } else if (userInput.equals("list")) {
                    ui.listTasks(taskList);
                } else if (userInput.contains("unmark")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    ui.unmark();
                    taskList.unmark(taskNumber - 1);
                    saveTasks(taskList.toString(), storage);
                } else if (userInput.contains("mark")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    ui.mark();
                    taskList.mark(taskNumber - 1);
                    saveTasks(taskList.toString(), storage);
                } else if (userInput.contains("delete")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    taskList.delete(taskNumber - 1);
                    saveTasks(taskList.toString(), storage);
                } else if (userInput.contains("todo")) {
                    String[] todoInput = userInput.split(" ", 2);
                    if (todoInput.length < 2 || todoInput[1].trim().isEmpty()) {
                        ui.showTodoError();
                    }
                    Todo todo = new Todo(todoInput[1]);
                    ui.addTask(todo);
                    taskList.add(todo);
                    ui.taskListMessage(taskList);
                    saveTasks(taskList.toString(), storage);

                } else if (userInput.contains("deadline")) {
                    String[] commandAndDetails = userInput.split(" ", 2);
                    if (commandAndDetails.length < 2 || !userInput.contains("/by")) {
                        ui.showDeadlineFormatError();
                    }
                    String[] taskAndDate = commandAndDetails[1].split(" /by ", 2);
                    if (taskAndDate.length < 2) {
                        ui.showDeadlineMissingError();
                    }
                    String taskDescription = taskAndDate[0];
                    String deadlineDate = taskAndDate[1];
                    Deadline deadline = new Deadline(taskDescription, deadlineDate);
                    ui.addTask(deadline);
                    taskList.add(deadline);
                    ui.taskListMessage(taskList);
                    saveTasks(taskList.toString(), storage);
                } else if (userInput.contains("event")) {
                    String[] commandAndDetails = userInput.split(" ", 2);
                    if (commandAndDetails.length < 2 || !userInput.contains("/from") || !userInput.contains("/to")) {
                        ui.showEventFormatError();
                    }
                    String[] taskAndTimes = commandAndDetails[1].split(" /from | /to ", 3);
                    if (taskAndTimes.length < 3) {
                        ui.showEventMissingError();
                    }
                    String taskDescription = taskAndTimes[0];
                    String startTime = taskAndTimes[1];
                    String endTime = taskAndTimes[2];
                    Event event = new Event(taskDescription, startTime, endTime);
                    ui.addTask(event);
                    taskList.add(event);
                    ui.taskListMessage(taskList);
                    saveTasks(taskList.toString(), storage);
                } else {
                    ui.showBadInputError();
                }
            } catch (FridayException e) {
                ui.showLoadingError(e);
                taskList = new TaskList();
            }
        }
    }
}