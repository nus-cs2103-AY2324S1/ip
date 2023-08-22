import exception.EmptyDescriptionException;
import exception.UnknownCommandException;

import java.util.Scanner;
public class Duke {
    private static Task[] taskList = new Task[100];
    private static int counter = 0;
    public static void main(String[] args) {
        String name = "Botty";
        String tmp = "";
        int counter = 0;
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
                    System.out.println(e);
                } catch (EmptyDescriptionException e) {
                    System.out.println(e);
                }
            }
        }
        bye();
    }

    public static void parseInput(String input) throws UnknownCommandException, EmptyDescriptionException {
        String[] stringArray = input.split(" ", 2);
        switch (stringArray[0]) {
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    Task currTask = taskList[i];
                    String listCounter = (i + 1) + ".";
                    System.out.println(listCounter + currTask.toString());
                }
                System.out.println("");
                break;
            case "unmark":
                System.out.println("OK, I've marked this task as not done yet:");
                Task unmarkTask = taskList[Integer.parseInt(stringArray[1]) - 1];
                unmarkTask.markUndone();
                System.out.println(unmarkTask + "\n");
                break;
            case "mark":
                System.out.println("Nice! I've marked this task as done:");
                Task markTask = taskList[Integer.parseInt(stringArray[1]) - 1];
                markTask.markDone();
                System.out.println(markTask + "\n");
                break;
            case "todo":
                if (stringArray.length == 1) {
                    throw new EmptyDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.\n");
                }
                Todo toAddTodo = new Todo(stringArray[1]);
                taskList[counter] = toAddTodo;
                counter++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + toAddTodo);
                System.out.println("Now you have " + counter + " tasks in the list.\n");
                break;
            case "deadline":
                String[] splitDeadline = stringArray[1].split(" /by ", 2);
                Deadline toAddDeadline = new Deadline(splitDeadline[0], splitDeadline[1]);
                taskList[counter] = toAddDeadline;
                counter++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + toAddDeadline);
                System.out.println("Now you have " + counter + " tasks in the list.\n");
                break;
            case "event":
                String[] splitFrom = stringArray[1].split(" /from ", 2);
                String description = splitFrom[0];
                String[] splitTo = splitFrom[1].split(" /to ", 2);
                String from = splitTo[0];
                String to = splitTo[1];
                Event toAddEvent = new Event(description, from, to);
                taskList[counter] = toAddEvent;
                counter++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + toAddEvent);
                System.out.println("Now you have " + counter + " tasks in the list.\n");
                break;
            default:
                throw new UnknownCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }
    public static void greet(String name) {
        System.out.println("Hello! I'm " + name + "\n" +
                "What can I do for you?\n");
    }
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
