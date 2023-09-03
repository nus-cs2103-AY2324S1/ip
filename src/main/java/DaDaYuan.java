import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DaDaYuan {

    private static class Task {
        String description;
        boolean isDone;

        Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        void markAsDone() {
            this.isDone = true;
        }

        void unmark() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return "[" + (isDone ? "X" : " ") + "] " + description;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println("Hello! I'm DaDaYuan");
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (true) {
            String input = scanner.nextLine();

            System.out.println(line);

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < tasks.size()) {
                    tasks.get(index).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("   " + tasks.get(index));
                }
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < tasks.size()) {
                    tasks.get(index).unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks.get(index));
                }
            } else {
                tasks.add(new Task(input));
                System.out.println("added: " + input);
            }

            System.out.println(line);
        }

        scanner.close();
    }
}


