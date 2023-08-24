import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.intro();
        duke.run();
        duke.exit();
    }

    public void lines() {
        System.out.println("_".repeat(50));
    }
    public void intro() {
        lines();
        System.out.println("Hello! I'm sillyBot\nWhat can I do for you?");
        lines();
    }

    public void exit() {
        lines();
        System.out.println("Bye. Hope to see you again soon!");
        lines();
    }

    public void run() {
        ArrayList<Task> tasks = new ArrayList<>();
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                lines();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                lines();
            } else if (input.contains("unmark")) {
                lines();
                String[] split = input.split(" ");
                int index = Integer.parseInt(split[1]) - 1;
                tasks.get(index).unmark();
                System.out.println(tasks.get(index));
                lines();
            } else if (input.contains("mark")) {
                lines();
                String[] split = input.split(" ");
                int index = Integer.parseInt(split[1]) - 1;
                tasks.get(index).mark();
                System.out.println(tasks.get(index));
                lines();
            } else {
                lines();
                System.out.println("added: " + input);
                tasks.add(new Task(input));
                lines();
            }
            input = sc.nextLine();
        }
    }

    public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); //return tick or X symbols
        }

        public void mark() {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:");
        }

        public void unmark() {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
        }

        public String getDescription() {
            return this.description;
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.getDescription();
        }
    }
}
