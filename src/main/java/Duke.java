import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINEBREAK = "    ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿";
    private static ArrayList<String> tasks = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greet();

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                displayList();
            } else {
                addToList(input);
            }
            input = sc.nextLine();
        }

        bye();
    }

    private static void addToList(String input) {
        tasks.add(input);
        String msg = "added: " + input;
        displayMessage(msg);
    }

    private static void displayList() {
        String list = "";
        for (int i = 1; i <= tasks.size(); i++) {
            list += i + ". " + tasks.get(i - 1) + "\n";
        }

        displayMessage(list);
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
