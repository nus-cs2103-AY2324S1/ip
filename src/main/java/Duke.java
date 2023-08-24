import java.util.Scanner;
public class Duke {
    final static String LINE = "____________________________________________________________\n";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;

        // Greetings
        System.out.println(LINE +
                " Hello! I'm Anya Forger\n" +
                " What can I do for you?\n" +
                LINE);

        String[] tasks = new String[100];
        int index = 0;

        while (sc.hasNextLine()) {
            command = sc.nextLine();


            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                System.out.println(LINE);
                for (int i = 0; i < index; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println(LINE);
            } else {
                tasks[index++] = command;
                System.out.println(LINE);
                System.out.println(" Added: " + command);
                System.out.println(LINE);
            }
        }

        // Exit
        System.out.println(LINE +
                " Bye. Hope to see you again soon!\n" +
                LINE);
    }
    public static void echo(String s) {
        System.out.println(LINE + " " + s + "\n" + LINE);
    }
}
