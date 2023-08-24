import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // makes it such that the scanner takes in inputs from the console
        String[] storage = new String[100];
        int filledIndex = 0;

        String openingStr =
                "____________________________________________________________\n" +
                        " Hello! I'm JEOE\n" +
                        " What can I do for you?\n" +
                        " type :\n" +
                        " list => to list out items in storage\n" +
                        " _Anything else_ => store in storage\n" +
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
            } else if (input.equals("list")) {
                String list = "____________________________________________________________\n";
                for (int i = 1; i <= filledIndex; i++) {
                    String num = String.valueOf(i);
                    list += num + ". " + storage[i] + "\n";
                }
                list += "____________________________________________________________\n";
                System.out.println(list);
            } else {
                storage[++filledIndex] = input; // storage array starts filling from index 1
                String reply =
                        "____________________________________________________________\n" +
                                "added: " + input + "\n" +
                                "____________________________________________________________\n";
                System.out.println(reply);
            }
        }
        scanner.close();
    }
}
