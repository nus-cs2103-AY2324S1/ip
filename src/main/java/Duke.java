import java.util.Scanner;

public class Duke {
    private static final String LINEBREAK = "    ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greet();

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            displayMessage(input);
            input = sc.nextLine();
        }

        bye();
    }

    private static void bye() {
        String byeMsg = "Bye. Hope to see you again soon!";

        displayMessage(byeMsg);
    }

    private static void greet() {
        String logo = " _           _        \n"
                    + "| |    _   _| | _____ \n"
                    + "| |   | | | | |/ / _ \\\n"
                    + "| |__ | |_| |   <  __/\n"
                    + "|____| \\__,_|_|\\_\\___|\n";
        String greetingMsg = "Hello! I'm Luke \n"
                        + "What can I do for you?\n";

        System.out.println("Hello from\n" + logo);

        displayMessage(greetingMsg);
    }

    /**
     * Displays the given message between horizontal lines
     *
     * @param msg String to output
     */
    private static void displayMessage(String msg) {
        System.out.println(LINEBREAK);
        System.out.println(indent(msg));
        System.out.println(LINEBREAK);
    }

    private static String indent(String msg) {
        return msg.replaceAll("(?m)^", "\t");
    }

}
