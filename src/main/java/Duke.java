import java.util.Scanner;

public class Duke {
    private static class Task {
        public String name;
        public boolean done;

        public Task(String name) {
            this.name = name;
            this.done = false;
        }

        public void markAsDone() {
            this.done = true;
        }

        public void markAsUndone() {
            this.done = false;
        }

        public String toString() {
            return String.format("[%s] %s", this.done ? "X" : " ", this.name);
        }
    }

    public static void main(String[] args) {
        String line = "____________________________________________________________";

        // Greeting
        System.out.println(line);
        System.out.println("Hello! I'm Eepy\nWhat can I do for you?");
        System.out.println(line);

        // Get input and store it
        Scanner in = new Scanner(System.in);
        Task[] items = new Task[100];
        int itemsCount = 0;

        while (true) {
            String s = in.nextLine();
            System.out.println("    " + line);

            if (s.equals("bye")) {
                // Exit
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    " + line);
                break;
            } else if (s.equals("list")) {
                // List out all items
                for (int i = 0; i < itemsCount; i++) {
                    int index = i + 1;
                    System.out.println("    " + index + "." + items[i]);
                }
                System.out.println("    " + line);
            } else if (s.startsWith("mark ")) {
                // Mark item as done
                int index = Integer.parseInt(s.substring(5));
                items[index - 1].markAsDone();
                System.out.println("    Nice! I've marked this task as done:\n\t" + items[index - 1]);
                System.out.println("    " + line);
            } else if (s.startsWith("unmark ")) {
                // Mark item as done
                int index = Integer.parseInt(s.substring(7));
                items[index - 1].markAsUndone();
                System.out.println("    OK, I've marked this task as not done yet:\n\t  " + items[index - 1]);
                System.out.println("    " + line);
            } else {
                // Add item
                items[itemsCount] = new Task(s);
                itemsCount++;

                System.out.println("    added: " + s);
                System.out.println("    " + line);
            }
        }
    }
}
