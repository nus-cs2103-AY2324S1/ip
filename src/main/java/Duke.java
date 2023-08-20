import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final int lineLength = 60;
    private static final String horizontalLine = "_".repeat(lineLength);
    private static final String msgIndent = " ";
    private static final String responseIndent = " ".repeat(4);
    private static final String name = "chatBot";
    private static final String helloMsg = String.format("Hello! I'm %s", name);
    private static final String requestMsg = "What can I do for you?";
    private static final String addedMsg = "Got it. I've added this task:\n  %s\nNow you have %d task%s in the list.";
    private static final String listMsg = "Here are the tasks in your list:\n%s";
    private static final String markMsg = "Nice! I've marked this task as done:\n  %s";
    private static final String unmarkMsg = "OK, I've marked this task as not done yet:\n  %s";
    private static final String invalidCmdMsg = "Invalid command.";
    private static final String goodbyeMsg = "Bye. Hope to see you again soon!";
    private static final Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<Task>();
        Duke.printMsg(helloMsg + "\n" + requestMsg);
        while (true) {
            String[] cmd = userInput.nextLine().split(" ");
            if (cmd.length == 0) {
                continue;
            }
            int idx;
            switch (cmd[0]) {
                case "bye":
                    break;
                case "list":
                    Duke.printMsg(String.format(listMsg, Duke.stringifyList(tasks)));
                    continue;
                case "mark":
                    idx = Integer.parseInt(cmd[1]) - 1;
                    tasks.get(idx).mark();
                    Duke.printMsg(String.format(markMsg, tasks.get(idx).toString()));
                    continue;
                case "unmark":
                    idx = Integer.parseInt(cmd[1]) - 1;
                    tasks.get(idx).unmark();
                    Duke.printMsg(String.format(unmarkMsg, tasks.get(idx).toString()));
                    continue;
                case "todo":
                    tasks.add(makeToDo(cmd));
                    Duke.printLastAdd(tasks);
                    continue;
                case "deadline":
                    tasks.add(makeDeadline(cmd));
                    Duke.printLastAdd(tasks);
                    continue;
                case "event":
                    tasks.add(makeEvent(cmd));
                    Duke.printLastAdd(tasks);
                    continue;
                default:
                    Duke.printMsg(invalidCmdMsg);
                    continue;
            }
            break;
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

    private static <T> void printLastAdd(List<T> arr) {
        Duke.printMsg(String.format(addedMsg, arr.get(arr.size() - 1).toString(), arr.size(), arr.size() == 1 ? "" : "s"));
    }

    private static ToDo makeToDo(String[] arr) {
        List<String> item = new ArrayList<String>();
        for (int i = 1; i < arr.length; i++) {
            item.add(arr[i]);
        }
        return new ToDo(String.join(" ", item));
    }

    private static Deadline makeDeadline(String[] arr) {
        List<String> item = new ArrayList<String>();
        List<String> by = new ArrayList<String>();
        List<String> curr = item;
        for (int i = 1; i < arr.length; i++) {
            switch (arr[i]) {
                case "/by":
                    curr = by;
                    break;
                default:
                    curr.add(arr[i]);
            }
        }
        return new Deadline(String.join(" ", item), String.join(" ", by));
    }

    private static Event makeEvent(String[] arr) {
        List<String> item = new ArrayList<String>();
        List<String> from = new ArrayList<String>();
        List<String> to = new ArrayList<String>();
        List<String> curr = item;
        for (int i = 1; i < arr.length; i++) {
            switch (arr[i]) {
                case "/from":
                    curr = from;
                    break;
                case "/to":
                    curr = to;
                    break;
                default:
                    curr.add(arr[i]);
            }
        }
        return new Event(String.join(" ", item), String.join(" ", from), String.join(" ", to));
    }
}
