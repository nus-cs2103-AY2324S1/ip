import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greetings = "____________________________________________________________\n" +
                " Hello! I'm Tackie\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        String farewell = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";

        System.out.println(greetings);

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println("____________________________________________________________\n" +
                    userInput +
                    "\n____________________________________________________________");
            userInput = scanner.nextLine();
        }
        System.out.println(farewell);
    }
}
