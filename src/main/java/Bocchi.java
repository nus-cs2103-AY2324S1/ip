import exceptions.BocchiException;
import exceptions.EmptyTaskException;
import exceptions.InvalidInputException;
import exceptions.InvalidSyntaxException;
import storage.BocchiLoader;
import storage.BocchiSaver;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bocchi {
    private static final int EXPECTED_TOKENS_IN_EVENT = 3;
    private static final String LINE_BREAK = "___________________________________________________";

    /**
     * Outputs greeting message.
     */
    private static void greet() {
        System.out.println(LINE_BREAK);
        System.out.println("Hello! I'm Bocchi");
        System.out.println("What can I do for you?");
        System.out.println(LINE_BREAK);
    }

    /**
     * Outputs exit message.
     */
    private static void exit() {
        System.out.println(LINE_BREAK);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_BREAK);
    }

    /**
     * Adds the task to the current task list.
     *
     * @param input Remaining input string excluding action
     * @param taskList Current list of tasks
     * @return Updated task list
     */
    private static TaskList addTask(String input, String action, TaskList taskList)
            throws InvalidSyntaxException {
        Task task;
        switch (action) {
        case "deadline":
            if (!input.contains("/by")) {
                throw new InvalidSyntaxException("deadline");
            }
            // Further tokenize into action and deadline
            String[] deadlineTokens = input.split("\\s*/by\\s*");
            task = new Deadline(deadlineTokens[0], deadlineTokens[1]);
            break;
        case "event":
            if (!input.contains("/from") || !input.contains("/to")) {
                throw new InvalidSyntaxException("event");
            }
            // Further tokenize into action, start time and end time
            // Regex identified by /to OR /from
            String[] eventTokens = input.split(
                    "\\s*/to\\s*|\\s*/from\\s*",
                    EXPECTED_TOKENS_IN_EVENT
            );
            task = new Event(eventTokens[0], eventTokens[1], eventTokens[2]);
            break;
        // Task.Todo is the default case
        default:
            task = new Todo(input);
        }
        System.out.println(LINE_BREAK);
        TaskList newTaskList = taskList.addTask(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.%n", taskList.size());
        System.out.println(LINE_BREAK);
        return newTaskList;
    }

    private static TaskList deleteTask(int taskNumber, TaskList taskList) {
        System.out.println(LINE_BREAK);
        System.out.println("Noted. I've removed this task:");
        Task toDelete = taskList.getTask(taskNumber);
        TaskList newTaskList = taskList.deleteTask(toDelete);
        System.out.println(toDelete);
        System.out.printf("Now you have %d tasks in the list.%n", newTaskList.size());
        return newTaskList;
    }

    /**
     * Marks the indicated task.
     *
     * @param taskNumber Task.Task number
     * @param taskList Current list of tasks
     * @return Updated task list
     */
    private static TaskList markTask(int taskNumber, TaskList taskList) {
        System.out.println(LINE_BREAK);
        TaskList newTaskList = taskList.markTask(taskNumber);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.getTask(taskNumber));
        System.out.println(LINE_BREAK);
        return newTaskList;
    }

    /**
     * Unmarks the indicated task.
     *
     * @param taskNumber Task.Task number
     * @param taskList Current list of tasks
     * @return Updated task list
     */
    private static TaskList unmarkTask(int taskNumber, TaskList taskList) {
        System.out.println(LINE_BREAK);
        TaskList newTaskList = taskList.unmarkTask(taskNumber);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.getTask(taskNumber));
        System.out.println(LINE_BREAK);
        return newTaskList;
    }

    /**
     * Outputs the current list of tasks to be done.
     *
     * @param taskList Current list of tasks
     */
    private static void displayTasks(TaskList taskList) {
        System.out.println(LINE_BREAK);
        System.out.println(taskList);
        System.out.println(LINE_BREAK);
    }

    /**
     * Main method.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList taskList;
        try {
            taskList = new BocchiLoader().loadTaskList();
            System.out.println("Previous data has been loaded");
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found");
            taskList = new TaskList();
        }
        greet();
        String message = sc.nextLine();
        // This splits the input string into two to isolate the first token as an action
        String[] tokens = message.split(" ", 2);
        String action = tokens[0];
        while (!action.equals("bye")) {
            try {
                switch (action) {
                case "list":
                    displayTasks(taskList);
                    break;
                case "mark":
                    taskList = markTask(Integer.parseInt(tokens[1]), taskList);
                    break;
                case "unmark":
                    taskList = unmarkTask(Integer.parseInt(tokens[1]), taskList);
                    break;
                case "delete":
                    taskList = deleteTask(Integer.parseInt(tokens[1]), taskList);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    // tokens[1] which is the remaining input is parsed based on the action
                    if (tokens.length == 1) {
                        throw new EmptyTaskException(action);
                    }
                    taskList = addTask(tokens[1], action, taskList);
                    break;
                default:
                    throw new InvalidInputException();
                }
            } catch (BocchiException e) {
                System.out.println(LINE_BREAK);
                System.out.println(e.getMessage());
                System.out.println(LINE_BREAK);
            }

            message = sc.nextLine();
            tokens = message.split(" ", 2);
            action = tokens[0];
        }
        sc.close();
        BocchiSaver saver = new BocchiSaver(taskList);
        saver.saveTaskList();
        exit();
    }
}
