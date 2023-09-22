package duke.ronaldo;

import java.io.IOException;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.extensions.SearchInTasks;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.ui.Ui;
import duke.util.DateAndTimeHandler;
import duke.util.TaskList;

/**
 * Main to class to handle duke operations
 */
public class RonaldoSaysDo {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;
    /**
     * Constructor
     */
    public RonaldoSaysDo() {
        storage = new Storage();
        parser = new Parser();
        ui = new Ui();
        tasks = new TaskList();
        try {
            tasks = new TaskList(storage.handleReadAllTasksFromFile());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Returns the response equivalent of the input
     * Else, exception is thrown
     * @param input user input
     * @return response equivalent of the input
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            return ui.exit();
        }
        try {
            String[] commandType = parser.handleUserInput(input);
            String str = handleCommand(commandType[0], commandType[1]);
            ArrayList<Task>currentTasks = tasks.getTasks();
            storage.handleChangesInFile(currentTasks);
            return str;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    /**
     * Returns the specific command from the given user input
     * @param command command of user
     * @param input inout of user
     * @return String representing the process performed
     * @throws DukeException
     * @throws IOException
     */
    public String handleCommand(String command, String input) throws DukeException {
        if (input.equals("")) {
            throw new DukeException("SUI, Invalid empty command!");
        }
        switch (command) {
        case "mark":
            return markTask(input);
        case "unmark":
            return unmarkTask(input);
        case "list":
            return tasks.getAllToDo();
        case "todo":
            return handleTodoTask(input);
        case "deadline":
            return handleDeadlineTask(input);
        case "event":
            return handleEventTask(input);
        case "delete":
            return deleteTask(input);
        case "find":
            return SearchInTasks.handleFindTask(input, tasks.getTasks());
        default:
            throw new DukeException("SUI, Invalid Command!");
        }
    }
    /**
     * Retrieves a formatted string indicating the success of marking a task.
     *
     * @param input  The input by the user.
     * @return A message confirming the action's success.
     * @throws DukeException If there's an issue with the task tasks or input.
     */
    public String markTask(String input) throws DukeException {
        String[] parts = input.split(" ");
        String res = "";

        //No index to mark
        if (parts.length == 1) {
            throw new DukeException("SUI, Specify index to mark task!\n");
        }
        if (parts.length > 2) {
            throw new DukeException("SUI, Enter mark command properly!\n");
        }
        //No task to mark
        if (tasks.getSize() == 0) {
            throw new DukeException("SUI, No tasks! Add more tasks to mark!\n");
        }
        if (parts.length == 2) {
            String sec = parts[1];
            //index is not valid integer
            try {
                int index = Integer.parseInt(sec);
                //index entered is more than totalTodos or negative index
                checkIfIndexIsValid(index);
                res = tasks.getTaskAtIndex(index - 1).setMarked();
                assert !res.isEmpty() : "Failed to mark task.";
            } catch (NumberFormatException e) {
                throw new DukeException("SUI, Enter a valid positive integer after your markcommand!\n");
            }
        }
        return res;
    }
    /**
     * Retrieves a formatted string indicating the success of unmarking a task.
     *
     * @param input  The input by the user.
     * @return A message confirming the action's success.
     * @throws DukeException If there's an issue with the task tasks or input.
     */
    public String unmarkTask(String input) throws DukeException {
        String[] parts = input.split(" ");
        String res = "";

        //No index to mark
        if (parts.length == 1) {
            throw new DukeException("SUI, Specify index to unmark task!\n");
        }
        if (parts.length > 2) {
            throw new DukeException("SUI, Enter unmark command properly!\n");
        }
        //No task to mark
        if (tasks.getSize() == 0) {
            throw new DukeException("SUI, No tasks! Add more tasks to unmark!\n");
        }
        if (parts.length == 2) {
            String sec = parts[1];
            //index is not valid integer
            try {
                int index = Integer.parseInt(sec);
                //index entered is more than totalTodos or negative index
                checkIfIndexIsValid(index);
                res = tasks.getTaskAtIndex(index - 1).setUnmarked();
                assert !res.isEmpty() : "Failed to unmark task.";
            } catch (NumberFormatException e) {
                throw new DukeException("SUI, Enter a valid positive integer after your unmark command!\n");
            }
        }
        return res;
    }
    /**
     * Returns if index is valid or not
     * @param index index to check
     * @throws DukeException if it is indeed invalid
     */
    public void checkIfIndexIsValid(int index) throws DukeException {
        if (index > tasks.getSize() || index <= 0) {
            throw new DukeException("SUI, Enter command with positive index lesser than "
                    + (tasks.getSize() + 1) + "\n");
        }
    }
    /**
     * Deletes a task from the task tasks based on the provided index.
     *
     * @param input The input of the task to be deleted.
     * @return A message indicating the success of the deletion.
     * @throws DukeException If there's an issue with the task tasks or input.
     */
    public String deleteTask(String input) throws DukeException {
        String[] parts = input.split(" ");
        String res = "";

        //No index to delete
        if (input.equals("delete") && parts.length == 1) {
            throw new DukeException("SUI, Specify index to delete task!\n");
        }
        //No task to delete
        if (tasks.getSize() == 0) {
            throw new DukeException("SUI, No tasks to delete! Add more tasks to delete!\n");
        }
        if ((parts[0].equals("delete")) && parts.length == 2) {
            String sec = parts[1];
            //index is not valid integer
            try {
                int index = Integer.parseInt(sec);
                String removedTask = tasks.getTaskAtIndex(index - 1).toString();
                tasks.removeTask(index - 1);
                res = "SUI, Noted. I've removed this task: \n " + "  " + removedTask + "\n" + tasks.getTaskLeft();
                assert !res.isEmpty() : "Failed to delete task.";
            } catch (NumberFormatException e) {
                throw new DukeException("SUI, Enter a valid positive integer after your mark/unmark command!\n");
            }
        }
        return res;
    }
    /**
     * Adds a todo task to the task tasks based on the provided input.
     *
     * @param input The user input containing the task description.
     * @return A message indicating the success of adding the duke.tasks.ToDo task.
     * @throws DukeException If there's an issue with the input or task description.
     */
    public String handleTodoTask(String input) throws DukeException {
        String task = "";
        String[] parts = input.split(" ");

        for (int i = 1; i < parts.length; i++) {
            task += parts[i] + " ";
        }
        if (task.equals("")) {
            throw new DukeException("SUI, No description specified la dei!! How to do work when no work is said?! "
                    + "Enter again!\n");
        }

        tasks.addTask(new ToDo(task, TaskType.TODO));

        String str = tasks.getTaskAtIndex(tasks.getSize() - 1).toString();
        assert !str.isEmpty() : "Failed to add todo task.";
        String res = "SUI, Got it. I've added this task :\n" + str + "\n";
        res += tasks.getTaskLeft();

        return res;
    }
    /**
     * Adds a deadline task to the task tasks based on the provided input.
     *
     * @param input The user input containing the task description and deadline.
     * @return A message indicating the success of adding the duke.tasks.Deadline task.
     * @throws DukeException If there's an issue with the input, task description, or deadline.
     */
    public String handleDeadlineTask(String input) throws DukeException {
        String[] parts = input.split("/by ");
        String[] taskArray = parts[0].split(" ");
        if (parts.length != 2) {
            throw new DukeException("SUI, Specify description, by date and time!");
        }
        String[] deadlineInfo = parts[1].split(" ");

        if (deadlineInfo.length != 2) {
            throw new DukeException("SUI, Specify both date and time in the following manner : yyyy-mm-dd hh:mm");
        }
        String byDate = deadlineInfo[0];
        String endTime = deadlineInfo[1];
        String task = "";
        for (int i = 1; i < taskArray.length; i++) {
            task += taskArray[i] + " ";
        }
        validateDeadlineTaskInput(task, byDate);
        DateAndTimeHandler.checkIfDateIsValid(byDate);
        DateAndTimeHandler.checkIfDeadlineTimelineIsValid(byDate, endTime);

        tasks.addTask(new Deadline(task, byDate, endTime + ":00", TaskType.DEADLINE));

        String str = tasks.getTaskAtIndex(tasks.getSize() - 1).toString();
        String res = "SUI, Got it. I've added this task :\n" + str + "\n";
        res += tasks.getTaskLeft();

        return res;
    }
    /**
     * Validates the task description and '/by' date.
     *
     * @param task   The task description.
     * @param byDate The '/by' date.
     * @throws DukeException If either the task description or '/by' date is empty.
     */
    private void validateDeadlineTaskInput(String task, String byDate) throws DukeException {
        if (task.isEmpty() || byDate.isEmpty()) {
            throw new DukeException("Deadline task must have description, /by date and time");
        }
    }
    /**
     * Adds an event task to the task tasks based on the provided input.
     *
     * @param input The user input containing the task description and event timings.
     * @return A message indicating the success of adding the duke.tasks.Event task.
     * @throws DukeException If there's an issue with the input, task description, or event timings.
     */
    public String handleEventTask(String input) throws DukeException {
        String[] parts = input.split("/from ");
        if (parts.length != 2) {
            throw new DukeException("SUI, Specify description, from and to date and time!");
        }
        String[] taskArray = parts[0].split(" ");
        String[] taskInfo = parts[1].split("/to ");

        validateEventFormat(taskInfo);
        String[] fromInfo = taskInfo[0].split(" ");
        String[] toInfo = taskInfo[1].split(" ");
        String startDate = fromInfo[0];
        String startTime = fromInfo[1];
        String endDate = toInfo[0];
        String endTime = toInfo[1];
        String task = "";
        for (int i = 1; i < taskArray.length; i++) {
            task += taskArray[i] + " ";
        }
        validateEventTaskDescription(task, startDate, endDate);
        DateAndTimeHandler.checkIfDateIsValid(startDate);
        DateAndTimeHandler.checkIfDateIsValid(endDate);

        tasks.addTask(new Event(task, startDate, endDate, startTime + ":00", endTime + ":00", TaskType.EVENT));

        String str = tasks.getTaskAtIndex(tasks.getSize() - 1).toString();
        assert !str.isEmpty() : "Failed to add deadline task.";
        String res = "SUI, Got it. I've added this task :\n" + str + "\n";
        res += tasks.getTaskLeft();

        return res;
    }
    /**
     * Validates the format of the event input.
     *
     * @param taskInfo An array containing the event information.
     * @throws DukeException If the format is invalid.
     */
    private void validateEventFormat(String[] taskInfo) throws DukeException {
        if (taskInfo.length != 2 || taskInfo[0].isEmpty() || taskInfo[1].isEmpty()) {
            throw new DukeException("SUI, Specify both date and time for /from and /to in the following manner: "
                    + "yyyy-mm-dd hh:mm");
        }
    }
    /**
     * Validates the event task description, start date, and end date.
     *
     * @param task      The event task description.
     * @param startDate The start date.
     * @param endDate   The end date.
     * @throws DukeException If any of the fields are empty.
     */
    private void validateEventTaskDescription(String task, String startDate, String endDate) throws DukeException {
        if (task.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
            throw new DukeException("SUI, Event task must have description, /from and /to date and time");
        }
    }
}
