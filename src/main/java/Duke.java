import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int i = 0;
    public static void printHorizontalLine() {
        System.out.println("\t------------------------------------------------------------------------------------");
    }
    public static void printBotMessage(String msg) {
        printHorizontalLine();
        System.out.println("\t" + msg);
        printHorizontalLine();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = "\n" +
                "\t_________ .__            __    __                \n" +
                "\t\\_   ___ \\|  |__ _____ _/  |__/  |_  ___________ \n" +
                "\t/    \\  \\/|  |  \\\\__  \\\\   __\\   __\\/ __ \\_  __ \\\n" +
                "\t\\     \\___|   Y  \\/ __ \\|  |  |  | \\  ___/|  | \\/\n" +
                "\t \\______  /___|  (____  /__|  |__|  \\___  >__|   \n" +
                "\t        \\/     \\/     \\/                \\/       \n";
        printBotMessage("Hello! I'm" + logo + "\n\t \uD83E\uDD9C What can I do for you?");

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                printBotMessage("Bye. Hope to see you again soon! \uD83D\uDD19 \uD83D\uDD1B \uD83D\uDD1D");
                break;
            } else if (input.equals("list")) {
                printHorizontalLine();
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < 100; i++) {
                    if (tasks[i] != null) {
                        System.out.println("\t" + (i + 1) + ". " + tasks[i]);
                    } else {
                        break;
                    }
                }
                printHorizontalLine();
            } else if (input.contains("unmark")) {
                String[] split = input.split(" ");
                int index = Integer.parseInt(split[1]);
                tasks[index - 1].markAsUndone();
                printBotMessage("OK, I've marked this task as not done yet:\n\t\t" + tasks[index - 1]);
            } else if (input.contains("mark")){
                String[] split = input.split(" ");
                int index = Integer.parseInt(split[1]);
                tasks[index - 1].markAsDone();
                printBotMessage("Nice! I've marked this task as done:\n\t\t" + tasks[index - 1]);
            } else {
                tasks[i] = new Task(input);
                i++;
                printBotMessage("added: " + input);
            }
        }

    }
}
