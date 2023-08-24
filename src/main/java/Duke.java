import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm ChatBot.\n" +
         "What can I do for you?\n" );

        Scanner scanner = new Scanner(System.in);
        List<Task> inputs = new ArrayList<>();
        String input = scanner.nextLine();

            while (!input.equals("bye")) {
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < inputs.size(); i++) {
                        System.out.println((i + 1) + ". " + inputs.get(i));
                    }
                } else if (input.startsWith("mark")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < inputs.size()) {
                        inputs.get(index).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + inputs.get(index));
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } else if (input.startsWith("unmark")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < inputs.size()) {
                        inputs.get(index).markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + inputs.get(index));
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } else {
                    inputs.add(new Task(input));
                    System.out.println("added: " + input);
                }

                input = scanner.nextLine();
            }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
