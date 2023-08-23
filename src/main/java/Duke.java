import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String intro = "____________________________________________________________\n" +
                " Hello! I'm Dookie\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(intro);

        Scanner sc = new Scanner(System.in);
        // Get user command
        String cmd = sc.nextLine();

        // Exit if command is "bye"
        while (!cmd.equals("bye")) {
            String echo = "____________________________________________________________\n" +
                    " " + cmd + "\n" +
                    "____________________________________________________________";
            System.out.println(echo);
            cmd = sc.nextLine();
        }

        String exitMessage = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        System.out.println(exitMessage);
    }
}
