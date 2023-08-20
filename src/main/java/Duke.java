import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;
        String initMsg = "____________________________________________________________\n"
                + " Hello! I'm IRIS\n"
                + " What can I do for you?\n";
        System.out.println(initMsg);
        command = scanner.nextLine();

        while (!command.equals("bye")) {
            String echo = "____________________________________________________________\n"
                    + command + "\n"
                    + "____________________________________________________________";
            System.out.println(echo);
            command = scanner.nextLine();
        }

        scanner.close();
        String endMsg = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(endMsg);
    }
}