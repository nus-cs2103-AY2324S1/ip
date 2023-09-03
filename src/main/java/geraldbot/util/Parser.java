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
    public void parse(String input) throws DukeException {
        if (input.equals("list")) {
            this.printList();
        } else if (input.startsWith("find")) {

            if (input.replaceAll("\\s", "").equals(input)) {
                throw new DukeInvalidCommandException("find");
            }

            String keyword = input.substring(input.indexOf("find") + 5).trim();
            findTasks(keyword);
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
                this.markCompletion(selectedTask, num);
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
                this.unmarkCompletion(selectedTask, num);
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
                this.deleteTask(num);
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
                this.addTodo(description, false);
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
                    this.addDeadline(description, false, deadlineDate);
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
                    this.addEvent(description, false, start, by);
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
    public void addTodo(String input, boolean isDone) {
        Todo newTask = new Todo(input, isDone);
        String newTaskString = newTask.fileFormat();

        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + newTask);

        lst.add(newTask);
        storage.addTask(newTaskString);

        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    /**
     * Adds a new deadline task to the task list and storage.
     *
     * @param input   The description of the deadline task.
     * @param isDone  The completion status of the task.
     * @param by The deadline in LocalDateTime format of that task.
     */
    public void addDeadline(String input, boolean isDone, LocalDateTime by) {
        Deadline newTask = new Deadline(input, isDone, by);
        String newTaskString = newTask.fileFormat();

        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + newTask);

        lst.add(newTask);
        storage.addTask(newTaskString);

        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    /**
     * Adds a new event task to the task list and storage.
     *
     * @param input   The description of the event task.
     * @param isDone  The completion status of the task.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public void addEvent(String input, boolean isDone, String start, String end) {
        Event newTask = new Event(input, isDone, start, end);
        String newTaskString = newTask.fileFormat();

        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + newTask);

        lst.add(newTask);
        storage.addTask(newTaskString);

        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    /**
     * Prints the list of tasks.
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println((i + 1) + ". " + lst.get(i).toString());
        }
    }

    /**
     * Marks a task as done and updates the storage.
     *
     * @param task The task to be marked as done.
     * @param num  The index of the task in the list.
     */
    public void markCompletion(Task task, int num) {
        if (task.getStatusIcon().equals("X")) {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("\t" + task);
        } else {
            System.out.println("Nice! I've marked this task as done:");

            task.toggleCompletion();
            String updatedTaskString = task.fileFormat();
            this.storage.updateTask(num - 1, updatedTaskString);

            System.out.println("\t" + task);
        }
    }

    /**
     * Removes the completion status of a task and updates the storage.
     *
     * @param task The task to be marked as not done.
     * @param num  The index of the task in the list.
     */
    public void unmarkCompletion(Task task, int num) {
        if (task.getStatusIcon().equals(" ")) {
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("\t" + task);
        } else {
            System.out.println("OK, I've marked this task as not done yet:");

            task.toggleCompletion();
            String updatedTaskString = task.fileFormat();
            this.storage.updateTask(num - 1, updatedTaskString);

            System.out.println("\t" + task);
        }
    }

    /**
     * Deletes a task from the task list and updates the storage.
     *
     * @param num The index of the task in the task list to be deleted.
     */
    public void deleteTask(Integer num) {
        System.out.println("Noted. I've removed this task:");

        Task selectedTask = lst.remove(num - 1);
        this.storage.updateTask(num - 1, null);

        System.out.println("\t" + selectedTask);
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    /**
     * Finds tasks that match a specified keyword and displays them.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    private void findTasks(String keyword) {
        List<Task> matchingTasks = lst.findTasksByKeyword(keyword);

        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + ". " + matchingTasks.get(i).toString());
        }
    }
}
