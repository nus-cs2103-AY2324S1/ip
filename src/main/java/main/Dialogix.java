package main;

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

        List<String> list = new ArrayList<>();

        boolean continueDialog = true;
        while (continueDialog) {
            System.out.print("User: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Bot: Here is the list.");
                for (String str: list) {
                    System.out.println(str);
                }
            } else if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bot: Bye. Hope to see you again soon!");
                continueDialog = false;
            } else {
                list.add(userInput);
                System.out.println("Bot: I have added " + userInput + " to the database.");
            }
        }

        System.out.println("____________________________________________________________");
        scanner.close();
    }
}
