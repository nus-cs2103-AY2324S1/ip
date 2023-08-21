import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Pong");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (running) {
            System.out.print("You:  ");
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                running = false;
            } else {
                System.out.print("Pong: ");
                System.out.println(userInput);
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
