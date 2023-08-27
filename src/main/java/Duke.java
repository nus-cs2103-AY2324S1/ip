import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greet = "____________________________________________________________\n"
                + "Hello! I'm Kevin\n"
                + "What can I do  for you?\n"
                + "____________________________________________________________\n";
        String bye = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";

        System.out.println(greet);

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        while (true) {
            if (!userInput.equals("bye")) {
                String message = "____________________________________________________________\n"
                        + userInput + "\n"
                        + "____________________________________________________________\n";
                System.out.println(message);
            } else {
                System.out.println(bye);
                break;
            }
            userInput = scanner.next();
        }
        scanner.close();
    }
}
