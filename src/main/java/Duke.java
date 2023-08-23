import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String intro = "____________________________________________________________\n" +
                " Hello! I'm Dookie\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(intro);

        ArrayList<String> list = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        // Get user command
        String cmd = sc.nextLine();

        // Exit if command is "bye"
        while (!cmd.equals("bye")) {
            // If cmd is "list", list items and wait for next command
            if (cmd.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i));
                }
            } else {    // else, echo command and add to the list
                String echo = "____________________________________________________________\n" +
                        " added: " + cmd + "\n" +
                        "____________________________________________________________";
                System.out.println(echo);
                list.add(cmd);
            }
            cmd = sc.nextLine();
        }

        String exitMessage = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        System.out.println(exitMessage);
    }
}
