import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Duke {
    private static final int lineLength = 60;
    private static final String horizontalLine = "_".repeat(lineLength);
    private static final String msgIndent = " ";
    private static final String responseIndent = " ".repeat(4);
    private static final String name = "chatBot";
    private static final String helloMsg = String.format("Hello! I'm %s", name);
    private static final String requestMsg = "What can I do for you?";
    private static final String addedMsg = "added: %s";
    private static final String outOfRangeMsg = "Task %d is out of range. You only have %d tasks.";
    private static final String markMsg = "Nice! I've marked this task as done:\n  %s";
    private static final String unmarkMsg = "OK, I've marked this task as not done yet:\n  %s";
    private static final String goodbyeMsg = "Bye. Hope to see you again soon!";
    private static final Scanner userInput = new Scanner(System.in);
    private static final Pattern markCommand = Pattern.compile("^((un)?)mark [1-9]([0-9]*)$");

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<Task>();
        Duke.printMsg(helloMsg + "\n" + requestMsg);
        while (true) {
            String msg = userInput.nextLine();
            if (msg.equals("bye")) {
                break;
            } else if (msg.equals("list")) {
                Duke.printMsg(Duke.stringifyList(tasks));
            } else if (markCommand.matcher(msg).matches()) {
                String[] cmd = msg.split(" ");
                int idx = Integer.parseInt(cmd[1]);
                if (idx > tasks.size()) {
                    Duke.printMsg(String.format(outOfRangeMsg, idx, tasks.size()));
                    continue;
                }
                idx--;
                if (cmd[0].equals("mark")) {
                    tasks.get(idx).mark();
                    Duke.printMsg(String.format(markMsg, tasks.get(idx).toString()));
                } else {
                    tasks.get(idx).unmark();
                    Duke.printMsg(String.format(unmarkMsg, tasks.get(idx).toString()));
                }
            } else {
                tasks.add(new Task(msg));
                Duke.printMsg(String.format(addedMsg, msg));
            }
        }
        Duke.printMsg(goodbyeMsg);
    }

    private static void printMsg(String msg) {
        Duke.printResponse(horizontalLine);
        for (String line : msg.split("\n")) {
            Duke.printResponse(msgIndent + line);
        }
        Duke.printResponse(horizontalLine);
        System.out.println();
    }

    private static void printResponse(String response) {
        for (String line : response.split("\n")) {
            System.out.println(responseIndent + line);
        }
    }

    private static <T> String stringifyList(List<T> arr) {
        List<String> enumArr = new ArrayList<String>();
        for (int i = 0; i < arr.size(); i++) {
            enumArr.add(String.format("%d. %s", i + 1, arr.get(i).toString()));
        }
        return String.join("\n", enumArr);
    }
}
