import java.util.ArrayList;
import java.util.Scanner;

import exceptions.InvalidParametersException;
import exceptions.MissingDescriptionException;
import exceptions.UnknownCommandException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Duke {

    public static void printWithTab(String s) {
        String tab = "     ";
        System.out.println(tab + s);
    }

    public static void main(String[] args) {
        String tab = "     ";
        String line = "____________________________________________________________";
        String logo = """
                _______         _
                |  ____|       (_)
                | |__ ___  _ __ _ _ __   ___
                |  __/ _ \\| '__| | '_ \\ / _ \\
                | | | (_) | |  | | | | |  __/
                |_|  \\___/|_|  |_|_| |_|\\___|
                              """;

        ArrayList<Task> taskList = new ArrayList<Task>();

        System.out.println(logo);
        printWithTab(line);
        printWithTab("Hello! I'm Forine");
        printWithTab("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            printWithTab(line);
            try {
                Commands command = Commands.parseCommand(input);
                switch (command) {
                case LIST:
                    for (int i = 0; i < taskList.size(); i++) {
                        printWithTab(tab + (i + 1) + ". " + taskList.get(i));
                    }
                    break;
                case MARK:
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= taskList.size() || index < 0) {
                        printWithTab("Invalid index!");
                        break;
                    }
                    taskList.get(index).markAsDone();
                    printWithTab("Nice! I've marked this task as done:");
                    printWithTab(tab + taskList.get(index).toString());
                    break;
                case UNMARK:
                    index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= taskList.size() || index < 0) {
                        printWithTab("Invalid index!");
                        break;
                    }
                    taskList.get(index).markAsUndone();
                    printWithTab("Ok! I've marked this task as not done yet:");
                    printWithTab(tab + taskList.get(index).toString());
                    break;
                case TODO:
                    taskList.add(new Todo(Commands.extractTaskDescription(input)));
                    printWithTab("Got it. I've added this task:");
                    printWithTab(tab + taskList.get(taskList.size() - 1));
                    printWithTab("Now you have " + taskList.size() + " tasks in the list.");
                    break;
                case DEADLINE:
                    Deadline deadline = new Deadline(Commands.extractTaskDescription(input),
                            Commands.extractDeadline(input));
                    taskList.add(deadline);
                    printWithTab("Got it. I've added this task:");
                    printWithTab(tab + deadline);
                    printWithTab("Now you have " + taskList.size() + " tasks in the list.");
                    break;
                case EVENT:
                    Event event = new Event(Commands.extractTaskDescription(input),
                            Commands.extractEventFrom(input), Commands.extractEventTo(input));
                    taskList.add(event);
                    printWithTab("Got it. I've added this task:");
                    printWithTab(tab + event);
                    printWithTab("Now you have " + taskList.size() + " tasks in the list.");
                    break;
                case DELETE:
                    index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= taskList.size() || index < 0) {
                        printWithTab("Invalid index!");
                        break;
                    }
                    Task task = taskList.get(index);
                    taskList.remove(index);
                    printWithTab("Noted. I've removed this task:");
                    printWithTab(tab + task.toString());
                    printWithTab("Now you have " + taskList.size() + " tasks in the list.");
                    break;
                default:
                    printWithTab("Unknown command!");
                    break;
                }
            } catch (UnknownCommandException | MissingDescriptionException | InvalidParametersException e) {
                printWithTab(e.getMessage());
            }
            printWithTab(line);
            input = sc.nextLine();
        }
        sc.close();
        printWithTab("Bye. Hope to see you again soon!");
        printWithTab(line);
    }
}
