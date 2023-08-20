import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Muddy\n" + "What can I do for you?");

        while (true) {
            String input = in.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (input.equalsIgnoreCase("hello")) {
                System.out.println("Hello, what can I help you with?");
                continue;
            }

            System.out.println(input);
        }
        in.close();
    }
}
