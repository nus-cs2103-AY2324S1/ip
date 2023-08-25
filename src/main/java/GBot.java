import java.util.ArrayList;
import java.util.Scanner;

public class GBot extends Task {
    public GBot(String description, boolean isDone) {
        super(description, isDone);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello I'm GBot!");
        System.out.println("What can I do for you?\n");

        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            String message = scanner.nextLine();
            if (message.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (message.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int len = list.size();
                for (int i = 0; i < len; i++) {
                    Task curr = list.get(i);
                    System.out.println((i + 1) + ".[" + curr.getStatusIcon() + "] " + curr.getDescription());
                }
            } else if (message.startsWith("mark")) {
                System.out.println("Nice, I've marked this task as done:");
                int taskNum = Integer.parseInt(message.split(" ")[1]);
                Task curr = list.get(taskNum - 1);
                curr.markAsDone();
                System.out.println("[" + curr.getStatusIcon() + "] " + curr.getDescription());
            } else if (message.startsWith("unmark")) {
                System.out.println("Nice, I've marked this task as not done yet:");
                int taskNum = Integer.parseInt(message.split(" ")[1]);
                Task curr = list.get(taskNum - 1);
                curr.markAsUndone();
                System.out.println("[" + curr.getStatusIcon() + "] " + curr.getDescription());
            } else {
                list.add(new GBot(message, false));
                System.out.println("added: " + message);
            }
        }
    }
}

