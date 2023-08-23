import Task.Task;
import Task.Deadline;
import Task.Event;
import Task.Todo;
import Exception.UnknownCommandException;
import Exception.EmptyDescriptionException;

import java.util.Scanner;

public class Duke {
    public static void filterInput(String[] words) throws UnknownCommandException, EmptyDescriptionException {
        // Only allows commands that are listed below
        String command = words[0];
        boolean validCommand = command.equals("bye") || command.equals("todo") || command.equals("deadline") || command.equals("event")
                || command.equals("list") || command.equals("mark") || command.equals("unmark");

        if (!validCommand) {
            throw new UnknownCommandException();
        } else if (words.length < 2) {
            throw new EmptyDescriptionException();
        }
    }

    public static void main(String[] args) {
        String logo = ">. <\n";
        String name = "your father";
        String line = "_________________________\n";
        System.out.println(logo +
                line +
                "Hello! I'm " + name + "\n" +
                "What can I do for you?\n" +
                line);

        Scanner scanner = new Scanner(System.in);
        String input;

        Task[] tasks = new Task[100];
        int taskIndex = 0;

        while (true) {
            input = scanner.nextLine().toLowerCase();
            String[] words = input.split(" ", 2); // splits into the command and the rest

            try {
                filterInput(words);
            } catch (UnknownCommandException | EmptyDescriptionException e) {
                System.out.println(line + e.getMessage() + "\n" + line);
                continue;
            }

            if (input.equals("bye")) {
                break;
            } else if (words[0].equals("todo")) {
                tasks[taskIndex] = new Todo(words[1]);
                taskIndex++;
                System.out.println(line + "Got it. I've added this task:\n  " +
                        tasks[taskIndex - 1] +
                        "\nNow you have " +
                        taskIndex +
                        " tasks in the list.\n" +
                        line);
            } else if (words[0].equals("deadline")) {
                String[] parts = words[1].split(" /by ");
                tasks[taskIndex] = new Deadline(parts[0], parts[1]);
                taskIndex++;
                System.out.println(line +
                        "Got it. I've added this task:\n  " +
                        tasks[taskIndex - 1] +
                        "\nNow you have " +
                        taskIndex +
                        " tasks in the list.\n" + line);
            } else if (words[0].equals("event")) {
                String[] parts = words[1].split(" /from "); // second part will consist the timings
                String[] times = parts[1].split(" /to ");
                tasks[taskIndex] = new Event(parts[0], times[0], times[1]);
                taskIndex++;
                System.out.println(line +
                        "Got it. I've added this task:\n  " +
                        tasks[taskIndex - 1] +
                        "\nNow you have " +
                        taskIndex +
                        " tasks in the list.\n" +
                        line);
            } else if (input.equals("list")) {
                System.out.println(line + "\nHere are the tasks in your list:");
                for (int i = 0; i < taskIndex; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
                System.out.println(line);
            } else if (words[0].equals("mark") && words.length > 1) {
                int index = Integer.parseInt(words[1]) - 1; // string -> int
                tasks[index].markDone();
                System.out.println(line + "\nNice! I've marked this task as done:\n  " +
                        tasks[index] +
                        "\n" +
                        line);
            } else if (words[0].equals("unmark") && words.length > 1) {
                int index = Integer.parseInt(words[1]) - 1; // string -> int
                tasks[index].unmarkDone();
                System.out.println(line +
                        "\nOK, I've marked this task as not done yet:\n  " +
                        tasks[index] +
                        "\n" +
                        line);
            } else {
                tasks[taskIndex] = new Task(input);
                taskIndex++;
                System.out.println(line + "added: " + input + "\n" + line);
            }
        }

        System.out.println(line +
                "Bye. Hope to see you again soon!\n" +
                line);
        scanner.close();
    }
}
