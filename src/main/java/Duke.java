import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String intro = "____________________________________________________________\n" +
                " Hello! I'm Dookie\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(intro);

        int counter = 1;
        String list = "";

        Scanner sc = new Scanner(System.in);
        // Get user command
        String cmd = sc.nextLine();

        // Exit if command is "bye"
        while (!cmd.equals("bye")) {
            // If cmd is "list", list items and wait for next command
            if (cmd.equals("list")) {
                System.out.println(list);
            } else {    // else, echo command and add to the list
                String echo = "____________________________________________________________\n" +
                        " added: " + cmd + "\n" +
                        "____________________________________________________________";
                System.out.println(echo);
                list += counter + ". " + cmd + "\n";
                counter++;
            }
            cmd = sc.nextLine();
        }

        String exitMessage = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        System.out.println(exitMessage);
    }
}
