import java.util.Scanner;

public class Duke {
    private static String line = "____________________________________________________________";
    private static Task[] items = new Task[100];
    private static int itemsCount = 0;

    public static void addTask(Task t) {
        items[itemsCount] = t;
        itemsCount++;

        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + t);
        System.out.println("    Now you have " + itemsCount + " task(s) in the list.");
        System.out.println("    " + line);
    }

    public static void main(String[] args) {
        // Greeting
        System.out.println(line);
        System.out.println("Hello! I'm Eepy\nWhat can I do for you?");
        System.out.println(line);

        // Get input and store it
        Scanner in = new Scanner(System.in);

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
            } else if (s.startsWith("todo ")) {
                // New ToDo item
                String name = s.substring(5);
                addTask(new ToDo(name));
            } else if (s.startsWith("deadline ")) {
                // New Deadline item
                // Extract name and by
                int byIndex = s.indexOf("/by");
                String name = s.substring(9, byIndex).trim();
                String by = s.substring(byIndex + 4).trim();

                addTask(new Deadline(name, by));
            } else if (s.startsWith("event ")) {
                // New Event item
                // Extract name, from and to
                int fromIndex = s.indexOf("/from");
                int toIndex = s.indexOf("/to");
                String name = s.substring(6, fromIndex).trim();
                String from = s.substring(fromIndex + 6, toIndex).trim();
                String to = s.substring(toIndex + 4).trim();

                addTask(new Event(name, from, to));
            } else {
                System.out.println("    I don't understand :(");
                System.out.println("    " + line);
            }
        }
    }
}
