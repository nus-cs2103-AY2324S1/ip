package main;

import exception.DialogixException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dialogix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Dialogix");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        List<Task> list = new ArrayList<>();

        boolean continueDialog = true;





        while (continueDialog) {
            try {
                System.out.print("User: ");
                String userInput = scanner.nextLine();

                if (userInput.equalsIgnoreCase("list")) {
                    System.out.println("Bot: Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + ". " + list.get(i).toString());
                    }
                } else if (userInput.startsWith("todo")) {
                    String description = userInput.replaceFirst("todo\\s+", "");
                    list.add(new Todo(description));
                    System.out.println("Bot: Got it. I've added this task:\n  " + list.get(list.size() - 1).toString());
                } else if (userInput.startsWith("deadline")) {
                    String[] parts = userInput.replaceFirst("deadline\\s+", "").split(" /by ");
                    if (parts.length == 2) {
                        list.add(new Deadline(parts[0], parts[1]));
                        System.out.println("Bot: Got it. I've added this task:\n  " + list.get(list.size() - 1).toString());
                    } else {
                        System.out.println("Bot: Invalid input format for 'deadline'.");
                    }
                } else if (userInput.startsWith("event")) {
                    String[] parts = userInput.replaceFirst("event\\s+", "").split(" /from | /to ");
                    if (parts.length == 3) {
                        list.add(new Event(parts[0], parts[1], parts[2]));
                        System.out.println("Bot: Got it. I've added this task:\n  " + list.get(list.size() - 1).toString());
                    } else {
                        System.out.println("Bot: Invalid input format for 'event'.");
                    }
                } else if (userInput.equalsIgnoreCase("bye")) {
                    System.out.println("Bot: Bye. Hope to see you again soon!");
                    continueDialog = false;
                } else {
                    throw new DialogixException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DialogixException e) {
                System.out.println("Bot: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Bot: An error occurred: " + e.getMessage());
            }
        }

        System.out.println("____________________________________________________________");
        scanner.close();
    }
}
