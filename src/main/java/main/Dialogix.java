package main;

import java.util.Scanner;

public class Dialogix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Dialogix");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        boolean continueDialog = true;
        while (continueDialog) {
            System.out.print("User: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Bot: You entered 'list'. Printing out list.");
                // Print the list or perform related action
            } else if (userInput.equalsIgnoreCase("blah")) {
                System.out.println("Bot: You entered 'blah'. Printing out blah.");
                // Print blah or perform related action
            } else if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bot: Bye. Hope to see you again soon!");
                continueDialog = false;
            } else {
                System.out.println("Bot: I'm not sure how to respond to that.");
            }
        }

        System.out.println("____________________________________________________________");
        scanner.close();
    }
}
