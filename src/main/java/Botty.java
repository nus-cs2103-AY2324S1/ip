/**
 * The Duke class is responsible for responding to user's input.
 * It provides functionalities to add, mark, unmark, and delete tasks.
 */

import exception.EmptyTodoException;
import exception.UnknownCommandException;
import exception.EmptyChoiceException;

import java.util.ArrayList;
import java.util.Scanner;

public class Botty {

    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        String name = "Botty";
        String tmp = "";
        Scanner scanner = new Scanner(System.in);
        greet(name);
        while (true) {
            tmp = scanner.nextLine();
            if (tmp.equals("bye")) {
                break;
            } else {
                try {
                    parseInput(tmp);
                } catch (UnknownCommandException e) {
                    System.out.println(e.getMessage());
                } catch (EmptyTodoException e) {
                    System.out.println(e.getMessage());
                } catch (EmptyChoiceException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        bye();
    }

    /**
     * Parses the user's input and performs the requested action on the task list.
     *
     * @param input The user's input.
     * @throws UnknownCommandException If the input botty.botty.command is not recognized.
     * @throws EmptyTodoException      If the description of a todo is empty.
     * @throws EmptyChoiceException    If the task number to be marked or unmarked is not provided.
     */
    public static void parseInput(String input) throws UnknownCommandException, EmptyTodoException, EmptyChoiceException {
        String[] stringArray = input.split(" ", 2);
        switch (stringArray[0]) {
        case "list":
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task currTask = taskList.get(i);
                String listCounter = (i + 1) + ".";
                System.out.println(listCounter + currTask.toString());
            }
            System.out.println("");
            break;
        case "unmark":
            if (stringArray.length == 1) {
                throw new EmptyChoiceException("☹ OOPS!!! Select the task number to be unmarked.\n");
            }
            System.out.println("OK, I've marked this task as not done yet:");
            Task unmarkTask = taskList.get(Integer.parseInt(stringArray[1]) - 1);
            unmarkTask.markUndone();
            System.out.println(unmarkTask + "\n");
            break;
        case "mark":
            if (stringArray.length == 1) {
                throw new EmptyChoiceException("☹ OOPS!!! Select the task number to be marked.\n");
            }
            System.out.println("Nice! I've marked this task as done:");
            Task markTask = taskList.get(Integer.parseInt(stringArray[1]) - 1);
            markTask.markDone();
            System.out.println(markTask + "\n");
            break;
        case "todo":
            if (stringArray.length == 1) {
                throw new EmptyTodoException("☹ OOPS!!! The description of a todo cannot be empty.\n");
            }
            Todo toAddTodo = new Todo(stringArray[1]);
            taskList.add(toAddTodo);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + toAddTodo);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
            break;
        case "deadline":
            String[] splitDeadline = stringArray[1].split(" /by ", 2);
            Deadline toAddDeadline = new Deadline(splitDeadline[0], splitDeadline[1]);
            taskList.add(toAddDeadline);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + toAddDeadline);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
            break;
        case "event":
            String[] splitFrom = stringArray[1].split(" /from ", 2);
            String description = splitFrom[0];
            String[] splitTo = splitFrom[1].split(" /to ", 2);
            String from = splitTo[0];
            String to = splitTo[1];
            Event toAddEvent = new Event(description, from, to);
            taskList.add(toAddEvent);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + toAddEvent);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
            break;
        case "delete":
            System.out.println("Noted. I've removed this task:");
            Task deletedTask = taskList.get(Integer.parseInt(stringArray[1]) - 1);
            System.out.println("  " + deletedTask);
            taskList.remove(deletedTask);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
            break;
        default:
            throw new UnknownCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }

    /**
     * Greets the user with a welcome message.
     *
     * @param name The bot name to use in the greeting.
     */
    public static void greet(String name) {
        System.out.println("Hello! I'm " + name + "\n" +
                "What can I do for you?\n");
    }

    /**
     * Prints a goodbye message.
     */
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
