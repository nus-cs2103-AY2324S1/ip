package geraldbot.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import geraldbot.exception.DukeEmptyParametersException;
import geraldbot.exception.DukeException;
import geraldbot.exception.DukeInvalidCommandException;
import geraldbot.exception.DukeInvalidDateException;
import geraldbot.exception.DukeInvalidIndexException;
import geraldbot.task.Deadline;
import geraldbot.task.Event;
import geraldbot.task.Task;
import geraldbot.task.Todo;

/**
 * The Parser class handles the parsing of user input and the execution of corresponding actions
 * based on the parsed commands. It interacts with the Storage and TaskList classes to manage tasks.
 */
public class Parser {
    private final Storage storage;

    private final TaskList lst;

    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Initializes a new Parser instance with the provided storage and task list.
     *
     * @param storage   The storage instance used for reading and writing tasks.
     * @param taskList  The task list containing the tasks to be processed.
     */
    public Parser(Storage storage, ArrayList<Task> taskList) {
        this.storage = storage;
        this.lst = new TaskList(taskList);
    }

    /**
     * Parses the user input and executes the corresponding actions.
     *
     * @param input The user input to be parsed and processed.
     * @throws DukeException If an error occurs during parsing or execution.
     */
    public String parse(String input) throws DukeException {
        if (input.equals("list")) {
            return this.printList();
        } else if (input.startsWith("find")) {

            if (input.replaceAll("\\s", "").equals(input)) {
                throw new DukeInvalidCommandException("find");
            }

            String keyword = input.substring(input.indexOf("find") + 5).trim();
            return findTasks(keyword);
        } else if (input.startsWith("mark")) {

            if (input.replaceAll("\\s", "").equals(input)) {
                throw new DukeInvalidCommandException("mark");
            }

            String[] parsedString = input.split(" ");
            try {
                int num = Integer.parseInt(parsedString[1]);
                if (num > lst.size() || num <= 0) {
                    throw new DukeInvalidIndexException(lst.size());
                }
                Task selectedTask = lst.get(num - 1);
                String message = this.markCompletion(selectedTask, num);
                return message;
            } catch (NumberFormatException e) {
                throw new DukeInvalidIndexException(lst.size());
            }
        } else if (input.startsWith("unmark")) {

            if (input.replaceAll("\\s", "").equals(input)) {
                throw new DukeInvalidCommandException("unmark");
            }

            String[] parsedString = input.split(" ");
            try {
                int num = Integer.parseInt(parsedString[1]);
                if (num > lst.size() || num <= 0) {
                    throw new DukeInvalidIndexException(lst.size());
                }
                Task selectedTask = lst.get(num - 1);
                String message = this.unmarkCompletion(selectedTask, num);
                return message;
            } catch (NumberFormatException e) {
                throw new DukeInvalidIndexException(lst.size());
            }
        } else if (input.startsWith("delete")) {

            if (input.replaceAll("\\s", "").equals(input)) {
                throw new DukeInvalidCommandException("delete");
            }

            String[] parsedString = input.split(" ");
            try {
                int num = Integer.parseInt(parsedString[1]);
                if (num > lst.size() || num <= 0) {
                    throw new DukeInvalidIndexException(lst.size());
                }
                String message = this.deleteTask(num);
                return message;
            } catch (NumberFormatException e) {
                throw new DukeInvalidIndexException(lst.size());
            }

        } else {
            Parser.TaskType taskType;
            if (input.startsWith("todo")) {
                taskType = Parser.TaskType.TODO;
            } else if (input.startsWith("deadline")) {
                taskType = Parser.TaskType.DEADLINE;
            } else if (input.startsWith("event")) {
                taskType = Parser.TaskType.EVENT;
            } else {
                taskType = null;
            }

            if (taskType == Parser.TaskType.TODO) {

                if (input.replaceAll("\\s", "").equals(input)) {
                    throw new DukeInvalidCommandException("todo");
                }

                String command = input.substring(0, input.indexOf(' '));
                String description = input.substring(input.indexOf(' ') + 1).trim();
                if (description.equals("")) {
                    throw new DukeInvalidCommandException(command);
                }
                String message = this.addTodo(description, false);
                return message;
            } else if (taskType == Parser.TaskType.DEADLINE) {

                if (input.replaceAll("\\s", "").equals(input)) {
                    throw new DukeInvalidCommandException("deadline");
                }

                String command = input.substring(0, input.indexOf(' '));
                String task = input.substring(input.indexOf(' ') + 1);
                String[] parsedTask = task.split("/", 2);
                String description = parsedTask[0].trim();

                if (parsedTask.length < 2) {
                    throw new DukeEmptyParametersException();
                }

                String by = parsedTask[1].trim();
                LocalDateTime deadlineDate = parseDate(by);

                if (description.equals("")) {
                    throw new DukeInvalidCommandException(command);
                } else if (deadlineDate == null) {
                    throw new DukeInvalidDateException();
                } else {
                    String message = this.addDeadline(description, false, deadlineDate);
                    return message;
                }
            } else if (taskType == Parser.TaskType.EVENT) {

                if (input.replaceAll("\\s", "").equals(input)) {
                    throw new DukeInvalidCommandException("event");
                }

                String command = input.substring(0, input.indexOf(' '));
                String task = input.substring(input.indexOf(' ') + 1);
                String[] parsedTask = task.split("/");

                if (parsedTask.length < 3) {
                    throw new DukeEmptyParametersException();
                }

                String description = parsedTask[0].trim();
                String start = parsedTask[1].substring(parsedTask[1].indexOf(' ') + 1).trim();
                String by = parsedTask[2].substring(parsedTask[2].indexOf(' ') + 1).trim();
                if (description.equals("")) {
                    throw new DukeInvalidCommandException(command);
                } else if (start.equals("") || by.equals("")) {
                    throw new DukeEmptyParametersException();
                } else {
                    String message = this.addEvent(description, false, start, by);
                    return message;
                }
            } else {
                throw new DukeInvalidCommandException();
            }
        }
    }

    /**
     * Parses a date string and returns a LocalDateTime object.
     *
     * @param dateStr The date string to be parsed.
     * @return A LocalDateTime object representing the parsed date.
     */
    private LocalDateTime parseDate(String dateStr) {
        try {
            String[] parts = dateStr.split("\\s+", 2);
            String dateString = parts.length > 1 ? parts[1] : parts[0]; // Use the second part if available

            return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Adds a new todo task to the task list and storage.
     *
     * @param input   The description of the todo task.
     * @param isDone  The completion status of the task.
     */
    public String addTodo(String input, boolean isDone) {
        Todo newTask = new Todo(input, isDone);
        String newTaskString = newTask.fileFormat();

        String message = "Got it. I've added this task:\n";
        message += "\t" + newTask;

        lst.add(newTask);
        storage.addTask(newTaskString);

        message += "\nNow you have " + lst.size() + " tasks in the list.";
        return message;
    }

    /**
     * Adds a new deadline task to the task list and storage.
     *
     * @param input   The description of the deadline task.
     * @param isDone  The completion status of the task.
     * @param by The deadline in LocalDateTime format of that task.
     */
    public String addDeadline(String input, boolean isDone, LocalDateTime by) {
        Deadline newTask = new Deadline(input, isDone, by);
        String newTaskString = newTask.fileFormat();

        String message = "Got it. I've added this task:\n";
        message += "\t" + newTask;

        lst.add(newTask);
        storage.addTask(newTaskString);

        message += "\nNow you have " + lst.size() + " tasks in the list.";
        return message;
    }

    /**
     * Adds a new event task to the task list and storage.
     *
     * @param input   The description of the event task.
     * @param isDone  The completion status of the task.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public String addEvent(String input, boolean isDone, String start, String end) {
        Event newTask = new Event(input, isDone, start, end);
        String newTaskString = newTask.fileFormat();

        String message = "Got it. I've added this task:\n";
        message += "\t" + newTask;

        lst.add(newTask);
        storage.addTask(newTaskString);

        message += "\nNow you have " + lst.size() + " tasks in the list.";
        return message;
    }

    /**
     * Prints the list of tasks.
     */
    public String printList() {
        String taskList = "Here are the tasks in your list:\n";
        for (int i = 0; i < lst.size(); i++) {
            taskList += (i + 1) + ". " + lst.get(i).toString() + "\n";
        }
        return taskList;
    }

    /**
     * Marks a task as done and updates the storage.
     *
     * @param task The task to be marked as done.
     * @param num  The index of the task in the list.
     */
    public String markCompletion(Task task, int num) {
        if (task.getStatusIcon().equals("X")) {
            String message = "Nice! I've marked this task as done:\n";
            message += "\t" + task;
            return message;
        } else {
            String message = "Nice! I've marked this task as done:\n";

            task.toggleCompletion();
            String updatedTaskString = task.fileFormat();
            this.storage.updateTask(num - 1, updatedTaskString);

            message += "\t" + task;
            return message;
        }
    }

    /**
     * Removes the completion status of a task and updates the storage.
     *
     * @param task The task to be marked as not done.
     * @param num  The index of the task in the list.
     */
    public String unmarkCompletion(Task task, int num) {
        if (task.getStatusIcon().equals(" ")) {
            String message = "OK, I've marked this task as not done yet:\n";
            message += "\t" + task;
            return message;
        } else {
            String message = "OK, I've marked this task as not done yet:\n";
            task.toggleCompletion();
            String updatedTaskString = task.fileFormat();
            this.storage.updateTask(num - 1, updatedTaskString);

            message += "\t" + task;
            return message;
        }
    }

    /**
     * Deletes a task from the task list and updates the storage.
     *
     * @param num The index of the task in the task list to be deleted.
     */
    public String deleteTask(Integer num) {
        String message = "Noted. I've removed this task:\n";
        Task selectedTask = lst.remove(num - 1);
        this.storage.updateTask(num - 1, null);

        message += "\t" + selectedTask;
        message += "\nNow you have " + lst.size() + " tasks in the list.";
        return message;
    }

    /**
     * Finds tasks that match a specified keyword and displays them.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    private String findTasks(String keyword) {
        List<Task> matchingTasks = lst.findTasksByKeyword(keyword);

        String taskList = "";
        for (int i = 0; i < matchingTasks.size(); i++) {
            taskList += (i + 1) + ". " + matchingTasks.get(i).toString() + "\n";
        }
        return taskList;
    }
}
