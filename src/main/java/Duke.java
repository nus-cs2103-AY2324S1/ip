import java.util.Scanner;

public class Duke {
    private static String[] tasks = new String[100];
    private static int taskAmount = 0;

    private static void greet() {
        String greetings = "____________________________________________________________\n" +
                " Hello! I'm Tackie\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(greetings);
    }

    private static void farewell() {
        String farewell = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        System.out.println(farewell);
    }

    private static void list(){
        for (int i = 0; i < Duke.taskAmount; i++) {
            System.out.println(Duke.tasks[i]);
        }
    }

    public static void main(String[] args) {
        Duke.greet();

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                Duke.list();
            } else {
                System.out.println("____________________________________________________________\n" +
                        "Added: " + userInput +
                        "\n____________________________________________________________");
                tasks[Duke.taskAmount] = userInput;
                Duke.taskAmount += 1;
            }
            userInput = scanner.nextLine();
        }
        Duke.farewell();
    }
}
