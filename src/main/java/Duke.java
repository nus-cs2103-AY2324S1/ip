import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
public class Duke {
    public static Integer count = 0;
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
                } else if (input.startsWith("todo")){
                    Task curr = new Todo(input.split(" ")[1]);
                    count++;
                    inputs.add(curr);
                    System.out.println("Got it. I've added this task:\n"  +
                            curr + '\n' +
                            "Now you have " + count + " tasks in the list\n");
                } else if (input.startsWith("event")){
                    String description = input.split("/")[0].split(" ", 2) [1].stripLeading();
                    String date = input.split("/")[1].substring(5);
                    String time = input.split("/to")[1];
                    Task curr = new Event(description, date, time);
                    count ++;
                    inputs.add(curr);
                    System.out.println("Got it. I've added this task:\n"  +
                            curr + '\n' +
                            "Now you have " + count + " tasks in the list\n");
                }
                else if (input.startsWith("deadline")){
                    String description = input.split("/by")[0].split(" ", 2) [1].stripLeading();
                    String date = input.split("/by")[1];
                    Task curr = new Deadline(description, date);
                    count ++;
                    inputs.add(curr);
                    System.out.println("Got it. I've added this task:\n"  +
                            curr + '\n' +
                            "Now you have " + count + " tasks in the list\n");
                }
                else {
                    inputs.add(new Task(input));
                    System.out.println("added: " + input);
                }

                input = scanner.nextLine();
            }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
