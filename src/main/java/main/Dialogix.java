package main;

import task.Task;

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
            System.out.print("User: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Bot: Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i).getStatusIcon() + " " + list.get(i).getDescription());
                }
            } else if (userInput.startsWith("mark")) {
                int index = Integer.parseInt(userInput.replaceFirst("mark\\s+", "")) - 1;
                if (index >= 0 && index < list.size()) {
                    list.get(index).markAsDone();
                    System.out.println("Bot: Nice! I've marked this task as done:");
                    System.out.println("  " + list.get(index).getStatusIcon() + " " + list.get(index).getDescription());
                } else {
                    System.out.println("Bot: Invalid task index.");
                }
            } else if (userInput.startsWith("unmark")) {
                int index = Integer.parseInt(userInput.replaceFirst("unmark\\s+", "")) - 1;
                if (index >= 0 && index < list.size()) {
                    list.get(index).markAsNotDone();
                    System.out.println("Bot: OK, I've marked this task as not done yet:");
                    System.out.println("  " + list.get(index).getStatusIcon() + " " + list.get(index).getDescription());
                } else {
                    System.out.println("Bot: Invalid task index.");
                }
            } else if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bot: Bye. Hope to see you again soon!");
                continueDialog = false;
            } else {
                list.add(new Task(userInput));
                System.out.println("Bot: I have added task '" + userInput + "' to the database.");
            }
        }

        System.out.println("____________________________________________________________");
        scanner.close();
    }
}
