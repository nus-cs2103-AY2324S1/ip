import java.util.Scanner;
public class Duke {
    final static String LINE = "____________________________________________________________\n";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(LINE +
                " Hello! I'm Anya Forger\n" +
                " What can I do for you?\n" +
                LINE);

        String command = sc.nextLine();

        while (!command.equals("bye")) {
            echo(command);
            command = sc.nextLine();
        }

        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
    public static void echo(String s) {
        System.out.println(LINE + " " + s + "\n" + LINE);
    }
}
