import javax.sound.sampled.Line;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */

        String name = "CathyTheChattyCat";
        String lineBreak = "\n_________________________________________\n";
        System.out.println(lineBreak + "Hello! I'm " + name);
        System.out.printf("What can I do for you?" + lineBreak);

        String message;
        ArrayList<Task> userList = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);

        do {
                message = userInput.nextLine();
                Task task = null;
            try {
                if (message.equalsIgnoreCase("list")) {
                    System.out.println(lineBreak);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < userList.size(); i++) {
                        int index = i + 1;
                        System.out.println(index + ". " + userList.get(i));
                    }
                    System.out.println(lineBreak);
                }
                if (message.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(message.substring(5)) - 1;
                    if (taskIndex >= 0 && taskIndex < userList.size()) {
                        userList.get(taskIndex).markDone();
                        System.out.println(lineBreak + "Nice! I've marked this task as done:");
                        System.out.println("  " + userList.get(taskIndex) + lineBreak);
                    } else {
                        System.out.println(lineBreak + "Invalid Task Number" + lineBreak);
                    }
                    userList.remove(task);
                }
                if (message.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(message.substring(7)) - 1;
                    if (taskIndex >= 0 && taskIndex < userList.size()) {
                        userList.get(taskIndex).unmarkDone();
                        System.out.println(lineBreak + "OK, I've marked this task as not done yet:");
                        System.out.println("  " + userList.get(taskIndex) + lineBreak);
                    } else {
                        System.out.println(lineBreak + "Invalid Task Number" + lineBreak);
                    }
                    userList.remove(task);
                }
                if (!message.equalsIgnoreCase("bye")) {
                    if (message.equalsIgnoreCase("list")) continue;
                    if (message.startsWith("mark")) continue;
                    if (message.startsWith("unmark")) continue;
                    if (message.startsWith("todo")) {
                        String info = message.substring(5);
                        if (info.isEmpty()) {
                            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                            continue;
                        }
                        task = new Todo(info);
                    }
                    if (message.startsWith("deadline")) {
                        String info = message.substring(9);
                        String[] split = info.split("/by");
                        if (split.length != 1) {
                            System.out.println("☹ OOPS!!! The description of a deadline is invalid.");
                            continue;
                        }
                        task = new Deadline(split[0], split[1]);
                    }
                    if (message.startsWith("event")) {
                        String info = message.substring(6);
                        String[] split = info.split("/from | /to");
                        if (split.length != 2) {
                            System.out.println("☹ OOPS!!! The description of a event is invalid.");
                            continue;
                        }
                        task = new Event(split[0], split[1], split[2]);
                    }
                    if (task != null) {
                        System.out.println(lineBreak + "Got it. I've added this task:");
                        userList.add(task);
                        int size = userList.size();
                        System.out.println(task);
                        System.out.println("Now you have " + size + " tasks in the list." + lineBreak);
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        } while (!message.equalsIgnoreCase("bye"));
        System.out.print(lineBreak + "Hope to see you again soon!" + lineBreak);
        userInput.close();
    }
}

