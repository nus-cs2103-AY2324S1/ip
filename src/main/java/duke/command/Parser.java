package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.time.format.DateTimeFormatter;

/**
 * Class that parses the user commands
 */
public class Parser {

    /**
     * Parses the given command of the user
     * @param command Command given by the user
     * @param taskList TaskList object containing the existing tasks
     * @return The reply to the given command
     * @throws DukeException Error message to indicate invalid command
     */
    public static String parseCommand(String command, TaskList taskList) throws DukeException {
        String[] tokens = command.split(" ", 2);
        String action = tokens[0].trim().toLowerCase();

        switch (action) {
            case "list":
                return generateListResponse(taskList);
            case "delete":
                return generateDeleteResponse(tokens, taskList);
            case "mark":
                return generateMarkResponse(tokens, taskList);
            case "unmark":
                return generateUnmarkResponse(tokens, taskList);
            case "todo":
                return generateTodoResponse(tokens, taskList);
            case "deadline":
                return generateDeadlineResponse(tokens, taskList);
            case "event":
                return generateEventResponse(tokens, taskList);
            case "find":
                return generateFindResponse(tokens, taskList);
            default:
                throw new DukeException("Sorry, I don't understand what that means.");
        }
    }

    /**
     * Runs through the existing TaskList and prints out the string representation of it
     * @param taskList Given TaskList of the user
     * @return String representation of the list
     */
    private static String generateListResponse(TaskList taskList) {
        List<Task> tasks = taskList.getTasks();
        if (tasks.isEmpty()) {
            return "You currently have no tasks in your list.";
        }

        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return response.toString();
    }

    /**
     * Generates the response for a delete command
     * @param tokens String array containing the command split into parts
     * @param taskList Given TaskList of the user
     * @return String containing the deleted task response
     * @throws DukeException Error message to indicate invalid delete command
     */
    private static String generateDeleteResponse(String[] tokens, TaskList taskList) throws DukeException {
        if (tokens.length < 2) {
            throw new DukeException("Please specify which task you wish to delete.");
        }

        int taskIndex;
        try {
            taskIndex = Integer.parseInt(tokens[1].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please insert a valid integer.");
        }

        if (taskIndex < 0 || taskIndex >= taskList.getTasks().size()) {
            throw new DukeException("The given task does not exist.");
        }

        Task deletedTask = taskList.getTasks().get(taskIndex);
        taskList.removeTask(taskIndex);

        return generateTaskDeletedResponse(deletedTask, taskList);
    }

    /**
     * Generates the response for a valid delete task command
     * @param task Task the user wishes to delete
     * @param taskList Given TaskList of the user
     * @return String containing the deleted task response
     */
    private static String generateTaskDeletedResponse(Task task, TaskList taskList) {
        StringBuilder response = new StringBuilder("Noted. I've removed this task:\n\t");
        response.append(task.toString()).append("\n");
        response.append("Now you have ").append(taskList.getTasks().size()).append(" tasks in the list.");
        return response.toString();
    }

    /**
     * Generates the response for a mark task command
     * @param tokens The string array containing the command split into parts
     * @param taskList Given TaskList of the user
     * @return String containing the task marked response
     * @throws DukeException Error message to indicate invalid mark command
     */
    private static String generateMarkResponse(String[] tokens, TaskList taskList) throws DukeException {
        if (tokens.length < 2) {
            throw new DukeException("Please specify which task you wish to mark.");
        }

        int taskIndex;
        try {
            taskIndex = Integer.parseInt(tokens[1].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please insert a valid integer.");
        }

        if (taskIndex < 0 || taskIndex >= taskList.getTasks().size()) {
            throw new DukeException("The given task does not exist.");
        }

        Task task = taskList.getTasks().get(taskIndex);
        taskList.markTaskAsDone(taskIndex);

        return generateTaskMarkedResponse(task, true);
    }

    /**
     * Generates the response for an unmark task command
     * @param tokens The string array containing the command split into parts
     * @param taskList Given TaskList of the user
     * @return String containing the task unmarked response
     * @throws DukeException Error message to indicate invalid unmark command
     */
    private static String generateUnmarkResponse(String[] tokens, TaskList taskList) throws DukeException {
        if (tokens.length < 2) {
            throw new DukeException("Please specify which task you wish to unmark.");
        }

        int taskIndex;
        try {
            taskIndex = Integer.parseInt(tokens[1].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please insert a valid integer.");
        }

        if (taskIndex < 0 || taskIndex >= taskList.getTasks().size()) {
            throw new DukeException("The given task does not exist.");
        }

        Task task = taskList.getTasks().get(taskIndex);
        taskList.unmarkTaskAsDone(taskIndex);

        return generateTaskMarkedResponse(task, false);
    }

    /**
     * Generates the response for a valid mark or unmark task command
     * @param task Task the user wishes to mark
     * @param isDone Status of the task to be set
     * @return String containing the task marked response
     */
    private static String generateTaskMarkedResponse(Task task, boolean isDone) {
        String status = isDone ? "done" : "not done yet";
        return "OK, I've marked this task as " + status + ":\n\t" + task.toString();
    }

    /**
     * Generates the response for a todo command
     * @param tokens The string array containing the command split into parts
     * @param taskList Given TaskList of the user
     * @return String containing the todo added response
     * @throws DukeException Error message to indicate invalid todo command
     */
    private static String generateTodoResponse(String[] tokens, TaskList taskList) throws DukeException {
        if (tokens.length < 2) {
            throw new DukeException("The description of todo cannot be empty.");
        }

        String taskDescription = tokens[1].trim();
        Task newTask = new ToDo(taskDescription, "0");
        taskList.addTask(newTask);

        return generateTaskAddedResponse(newTask, taskList);
    }

    /**
     * Generates the response for a deadline command
     * @param tokens The string array containing the command split into parts
     * @param taskList Given TaskList of the user
     * @return String containing the deadline added response
     * @throws DukeException Error message to indicate invalid deadline command
     */
    private static String generateDeadlineResponse(String[] tokens, TaskList taskList) throws DukeException {
        if (tokens.length < 2) {
            throw new DukeException("The description of deadline cannot be empty.");
        }

        String[] parts = tokens[1].split("/by", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new DukeException("Please provide a task description and deadline.");
        }

        String taskDescription = parts[0].trim();
        String dueByString = parts[1].trim();

        try {
            LocalDate dueBy = LocalDate.parse(dueByString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            Task newTask = new Deadline(taskDescription, dueBy, "0");
            taskList.addTask(newTask);

            return generateTaskAddedResponse(newTask, taskList);
        } catch (DateTimeException e) {
            throw new DukeException("Please enter valid date in dd/MM/yyyy format.");
        }

    }

    /**
     * Generates the response for an event command
     * @param tokens The string array containing the command split into parts
     * @param taskList Given TaskList of the user
     * @return String containing the event added response
     * @throws DukeException Error message to indicate invalid event command
     */
    private static String generateEventResponse(String[] tokens, TaskList taskList) throws DukeException {
        if (tokens.length < 2) {
            throw new DukeException("The description of event cannot be empty.");
        }

        String[] parts = tokens[1].split("/from", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new DukeException("Please provide a task description and event timing.");
        }

        String[] timingParts = parts[1].split("/to", 2);
        if (timingParts.length < 2 || timingParts[0].trim().isEmpty() || timingParts[1].trim().isEmpty()) {
            throw new DukeException("Please provide both the start and end timings for the event.");
        }

        String taskDescription = parts[0].trim();
        String startTimingString = timingParts[0].trim();
        String endTimingString = timingParts[1].trim();

        try {
            LocalDate startTiming = LocalDate.parse(startTimingString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate endTiming = LocalDate.parse(endTimingString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            Task newTask = new Event(taskDescription, startTiming, endTiming, "0");
            taskList.addTask(newTask);

            return generateTaskAddedResponse(newTask, taskList);

        } catch (DateTimeException e) {
            throw new DukeException("Please enter valid date in dd/MM/yyyy format.");
        }

    }

    /**
     * Generates the response for a valid task command
     * @param task Task that has been added to the list
     * @param taskList Given TaskList of the user
     * @return String containing the task added response
     */
    private static String generateTaskAddedResponse(Task task, TaskList taskList) {
        StringBuilder response = new StringBuilder("Got it. I've added this task:\n\t");
        response.append(task.toString()).append("\n");
        response.append("Now you have ").append(taskList.getTasks().size()).append(" tasks in the list.");
        return response.toString();
    }

    /**
     * Generates the response for a find command
     * @param tokens The string array containing the command that has been split into parts
     * @param taskList Given TaskList of the user
     * @return String containing the find response
     */
    private static String generateFindResponse(String[] tokens, TaskList taskList) {
        if (tokens.length < 2) {
            return "Please specify a keyword to search for.";
        }

        String keyword = tokens[1].trim();
        List<Task> matchingTasks = taskList.findTasks(keyword);

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        }

        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            response.append((i + 1)).append(". ").append(matchingTasks.get(i).toString()).append("\n");
        }
        return response.toString();
    }


}

