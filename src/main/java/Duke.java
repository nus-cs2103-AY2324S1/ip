import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________\n" +
                "Hello! I'm CarrotCake\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n");

        String input = scanner.nextLine();

        while (!input.toLowerCase().equals("bye")) {
            System.out.println("____________________________________________________________\n" +
                    input +
                    "\n____________________________________________________________\n");

            input = scanner.nextLine();
        }

        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}
