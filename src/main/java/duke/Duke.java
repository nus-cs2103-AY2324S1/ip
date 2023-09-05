package duke;

import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Event;
import duke.io.Loader;
import duke.io.Saver;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.UnknownCommandException;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private List<Task> tasks;
    private static final String LINE = "____________________________________________________________";

    public Duke() {
        tasks = new Loader().load();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(LINE);
        System.out.println("Hello! I'm DaDaYuan");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        while (true) {
            String input = scanner.nextLine();
            System.out.println(LINE);

            try {
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(LINE);
                    new Saver().save(tasks);
                    break;
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }
                } else if (input.startsWith("todo")) {
                    String description = input.length() > 5 ? input.substring(5) : "";
                    if (description.isEmpty()) {
                        throw new Exception("OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        tasks.add(new Todo(description));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
                    }
                } else if (input.startsWith("deadline")) {
                    String[] parts = input.split(" /by ", 2);
                    if (parts.length < 2) {
                        throw new Exception("OOPS!!! The deadline of a task cannot be empty.");
                    } else {
                        String description = parts[0].substring(9);
                        tasks.add(new Deadline(description, parts[1]));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
                    }
                } else if (input.startsWith("event")) {
                    String[] parts = input.split(" /from | /to ", 3);
                    if (parts.length < 3) {
                        throw new Exception("OOPS!!! The event timing details are incomplete.");
                    } else {
                        tasks.add(new Event(parts[0].substring(6), parts[1], parts[2]));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
                    }
                } else if (input.startsWith("delete")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + tasks.get(index));
                        tasks.remove(index);
                        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
                    }
                } else {
                    throw new Exception("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                new Saver().save(tasks); // saving to file after each operation

            } catch (Exception e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("OOPS!!! The index provided is not valid.");
            }

            System.out.println(LINE);
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}

