import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";

        // Greeting
        System.out.println(line);
        System.out.println("Hello! I'm Eepy\nWhat can I do for you?");
        System.out.println(line);

        // Get input and store it
        Scanner in = new Scanner(System.in);
        String[] items = new String[100];
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
                    System.out.println("    " + index + ". " + items[i]);
                }
                System.out.println("    " + line);
            } else {
                // Add item
                items[itemsCount] = s;
                itemsCount++;

                System.out.println("    added: " + s);
                System.out.println("    " + line);
            }
        }
    }
}
