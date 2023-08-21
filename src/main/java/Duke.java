import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Pong");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (running) {
            System.out.print("You:  ");
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                running = false;
            } else if (userInput.equals("list")) {
                System.out.println("Pong: ");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("    %d. %s\n", i+1, tasks.get(i));
                }
            } else if (userInput.startsWith("mark")) {
                String userIndex = userInput.substring(5);
                int index = Integer.parseInt(userIndex) - 1;
                tasks.get(index).markDone();
                System.out.println("Pong: I've marked this task as done.");
                System.out.printf("    %s\n", tasks.get(index));
            } else if (userInput.startsWith("unmark")) {
                String userIndex = userInput.substring(7);
                int index = Integer.parseInt(userIndex) - 1;
                tasks.get(index).unmarkDone();
                System.out.println("Pong: I've marked this task as not done.");
                System.out.printf("    %s\n", tasks.get(index));
            } else {
                tasks.add(new Task(userInput));
                System.out.printf("Pong: [Added] %s\n", userInput);
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
