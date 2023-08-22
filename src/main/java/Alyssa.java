import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Exceptions.AlyssaArgumentException;

/**
 * This class represents the main program.
 */
public class Alyssa {
    private static final String line = "____________________________________________________________";
    private static final String logo = " ___  __    __   __  ____   ____    ___\n"
            + "|   | | |   \\ \\ / / |  __| |  __|  |   |\n"
            + "|   | | |    \\   /   \\ \\    \\ \\    |   |\n"
            + "|___| | |     | |     \\ \\    \\ \\   |___|\n"
            + "|   | | |___  | |     _\\ \\   _\\ \\  |   |\n"
            + "|   | |_____| |_|    |____| |____| |   |\n";
    private static List<Task> taskList;
    private static void greet() {
        System.out.println(logo);
        System.out.println(line);
        System.out.println("Hello! I'm Alyssa!");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }
    private static void goodbye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
    private static void listTasks() {
        int counter = 1;
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskList) {
            System.out.println(counter + "." + task.toString());
            counter++;
        }
        System.out.println(line);
    }
    private static void markTask(String rest) throws AlyssaArgumentException, NumberFormatException {
        int index = Integer.valueOf(rest); //this could throw a NumberFormatException if rest is not a number
        if (index < 1 || index > taskList.size()) {
            throw new AlyssaArgumentException("Invalid task number");
        }
        Task task = taskList.get(index - 1);
        task.markAsDone();
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        System.out.println(line);
    }
    private static void unmarkTask(String rest) throws AlyssaArgumentException, NumberFormatException {
        int index = Integer.valueOf(rest); //this could throw a NumberFormatException if rest is not a number
        if (index < 1 || index > taskList.size()) {
            throw new AlyssaArgumentException("Invalid task number");
        }
        Task task = taskList.get(index - 1);
        task.markAsUndone();
        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        System.out.println(line);
    }
    private static void addTodo(String desc) throws AlyssaArgumentException {
        if (desc.isEmpty()) {
            throw new AlyssaArgumentException("Please specify a description for the todo.");
        }
        Task newTodo = new Todo(desc);
        taskList.add(newTodo);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTodo.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(line);
    }
    private static void addDeadline(String rest) throws AlyssaArgumentException {
        String[] parsed = rest.split(" /by ");
        if (parsed.length < 2) {
            throw new AlyssaArgumentException("Incorrect deadline syntax. Syntax: deadline desc /by date");
        }
        String desc = parsed[0];
        String by = parsed[1];
        Task newDeadline = new Deadline(desc, by);
        taskList.add(newDeadline);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(newDeadline.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(line);
    }
    private static void addEvent(String rest) throws AlyssaArgumentException {
        String[] parsed = rest.split(" /from | /to ");
        if (parsed.length < 3) {
            throw new AlyssaArgumentException("Incorrect event syntax. Syntax: event desc /from date /to date");
        }
        String desc = parsed[0];
        String from = parsed[1];
        String to = parsed[2];
        Task newEvent = new Event(desc, from, to);
        taskList.add(newEvent);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(newEvent.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(line);
    }
    private static void deleteTask(String rest) throws AlyssaArgumentException, NumberFormatException {
        int index = Integer.valueOf(rest); //this could throw a NumberFormatException if rest is not a number
        if (index < 1 || index > taskList.size()) {
            throw new AlyssaArgumentException("Invalid task number");
        }
        Task toDelete = taskList.get(index - 1);
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println(toDelete.toString());
        taskList.remove(index - 1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(line);
    }
    private static void invalidTaskResponse() {
        System.out.println(line);
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(line);
    }
    //CHECKSTYLE.OFF: Indentation
    private static void run(Command command, String rest) {
        switch (command) {
            case BYE:
                goodbye();
                break;
            case LIST:
                listTasks();
                break;
            case MARK:
                try {
                    markTask(rest);
                } catch (NumberFormatException e) {
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                } catch (AlyssaArgumentException e) {
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                }
                break;
            case UNMARK:
                try {
                    unmarkTask(rest);
                } catch (NumberFormatException e) {
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                } catch (AlyssaArgumentException e) {
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                }
                break;
            case TODO:
                try {
                    addTodo(rest);
                } catch (AlyssaArgumentException e) {
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                }
                break;
            case DEADLINE:
                try {
                    addDeadline(rest);
                } catch (AlyssaArgumentException e) {
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                }
                break;
            case EVENT:
                try {
                    addEvent(rest);
                } catch (AlyssaArgumentException e) {
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                }
                break;
            case DELETE:
                try {
                    deleteTask(rest);
                } catch (AlyssaArgumentException e) {
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                } catch (NumberFormatException e) {
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                }
                break;
            default:
                invalidTaskResponse();
        }
    }
    //CHECKSTYLE.ON: Indentation

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        taskList = new ArrayList<>();
        boolean isRunning = true;
        Command command;
        greet();
        while (isRunning) {
            String nextInput = sc.nextLine();
            String[] parsedInput = nextInput.split(" ", 2);
            String commandString = parsedInput[0];
            command = Command.assignCommand(commandString);
            String rest = parsedInput.length > 1 ? parsedInput[1] : "";
            run(command, rest);
            if (command == Command.BYE) {
                isRunning = false;
            }
        }
    }
}
