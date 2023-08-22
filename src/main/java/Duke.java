import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String INDENTATION = "    ";
    private static ArrayList store = new ArrayList<String>();

    private static String formatOutput(String output) {
        String horizontalLine = "____________________________________________________________";
        return INDENTATION + horizontalLine + "\n " +
                INDENTATION + output + '\n' + INDENTATION + horizontalLine + '\n';
    }

    private static String formatList(ArrayList storeList) {
        String str = "";
        int len = storeList.size();
        for (int i = 0; i < len; i++) {
            str = str + Integer.toString(i + 1) + ". " + storeList.get(i);
            if (i != (len - 1)) {
                str += "\n " + INDENTATION;
            }
        }
        return str;
    }

    private static void handleCommand() {
        Scanner sc = new Scanner(System.in);
        String command;

        while (true) {
            command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println(formatOutput("Bye. Hope to see you again soon!"));
                break;
            } else if (command.equals("list")) {
                System.out.println(formatOutput(formatList(store)));
            }
            else {
                store.add(command);
                System.out.println(formatOutput("added: " + command));
            }
        }

    }

    public static void main(String[] args) {

        System.out.println(formatOutput("Hello! I'm Nano\n" + INDENTATION + " What can I do for you?"));
        handleCommand();

    }
}
