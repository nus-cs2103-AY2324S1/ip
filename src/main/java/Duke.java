import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // makes it such that the scanner takes in inputs from the console

        String openingStr =
                "____________________________________________________________\n" +
                " Hello! I'm JEOE\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(openingStr);

        String closingStr =
                "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";

        boolean isRunning = true;
        while (isRunning) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(closingStr);
                isRunning = false;
            } else {
                String reply =
                        "____________________________________________________________\n" +
                        input + "\n" +
                        "____________________________________________________________\n";
                System.out.println(reply);
            }
        }
        scanner.close();
    }
}