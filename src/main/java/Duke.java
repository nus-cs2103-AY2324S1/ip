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
    private static final String invalidCmdMsg = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String emptyFieldMsg = "☹ OOPS!!! The %s of a %s cannot be empty.";
    private static final String invalidIndexMsg = "☹ OOPS!!! The index should be a valid integer.";
    private static final String negativeIndexMsg = "☹ OOPS!!! The index should be a positive integer.";
    private static final String outOfBoundsMsg = "☹ OOPS!!! %d is out of range. The list only has %d item%s.";
    private static final String invalidByeMsg = "☹ OOPS!!! Did you mean \"bye\" without additional arguments?";
    private static final String invalidListMsg = "☹ OOPS!!! Did you mean \"list\" without additional arguments?";
    private static final String invalidMarkMsg = "☹ OOPS!!! mark should have exactly one argument.";
    private static final String invalidUnmarkMsg = "☹ OOPS!!! unmark should have exactly one argument.";
    private static final String goodbyeMsg = "Bye. Hope to see you again soon!";
    private static final Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<Task>();
        printMsg(helloMsg + "\n" + requestMsg);
        while (true) {
            String[] cmd = userInput.nextLine().split(" ");
            if (cmd.length == 0) {
                continue;
            }
            int idx;
            Task newTask;
            switch (cmd[0]) {
                case "bye":
                    if (cmd.length > 1) {
                        printMsg(invalidByeMsg);
                    } else {
                        break;
                    }
                    continue;
                case "list":
                    if (cmd.length > 1) {
                        printMsg(invalidListMsg);
                    } else {
                        printMsg(String.format(listMsg, stringifyList(tasks)));
                    }
                    continue;
                case "mark":
                    if (cmd.length != 2) {
                        printMsg(invalidMarkMsg);
                    } else {
                        idx = getIndex(cmd[1], tasks.size());
                        if (idx >= 0) {
                            tasks.get(idx).mark();
                            printMsg(String.format(markMsg, tasks.get(idx).toString()));
                        }
                    }
                    continue;
                case "unmark":
                    if (cmd.length != 2) {
                        printMsg(invalidUnmarkMsg);
                    } else {
                        idx = getIndex(cmd[1], tasks.size());
                        if (idx >= 0) {
                            tasks.get(idx).unmark();
                            printMsg(String.format(unmarkMsg, tasks.get(idx).toString()));
                        }
                    }
                    continue;
                case "todo":
                    newTask = makeToDo(cmd);
                    if (newTask != null) {
                        tasks.add(newTask);
                        printLastAdd(tasks);
                    }
                    continue;
                case "deadline":
                    newTask = makeDeadline(cmd);
                    if (newTask != null) {
                        tasks.add(makeToDo(cmd));
                        printLastAdd(tasks);
                    }
                    continue;
                case "event":
                    newTask = makeEvent(cmd);
                    if (newTask != null) {
                        tasks.add(makeToDo(cmd));
                        printLastAdd(tasks);
                    }
                    continue;
                default:
                    printMsg(invalidCmdMsg);
                    continue;
            }
            break;
        }
        printMsg(goodbyeMsg);
    }

    private static void printMsg(String msg) {
        printResponse(horizontalLine);
        for (String line : msg.split("\n")) {
            printResponse(msgIndent + line);
        }
        printResponse(horizontalLine);
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
        printMsg(String.format(addedMsg, arr.get(arr.size() - 1).toString(), arr.size(), arr.size() == 1 ? "" : "s"));
    }

    private static ToDo makeToDo(String[] arr) {
        List<String> item = new ArrayList<String>();
        for (int i = 1; i < arr.length; i++) {
            item.add(arr[i]);
        }
        if (item.size() == 0) {
            printMsg(String.format(emptyFieldMsg, "description", "todo"));
            return null;
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
        if (item.size() == 0) {
            printMsg(String.format(emptyFieldMsg, "description", "deadline"));
            return null;
        }
        if (by.size() == 0) {
            printMsg(String.format(emptyFieldMsg, "by", "deadline"));
            return null;
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
        if (item.size() == 0) {
            printMsg(String.format(emptyFieldMsg, "description", "event"));
            return null;
        }
        if (from.size() == 0) {
            printMsg(String.format(emptyFieldMsg, "from", "event"));
            return null;
        }
        if (to.size() == 0) {
            printMsg(String.format(emptyFieldMsg, "to", "event"));
            return null;
        }
        return new Event(String.join(" ", item), String.join(" ", from), String.join(" ", to));
    }

    private static int getIndex(String num, int size) {
        int idx;
        try {
            idx = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            printMsg(invalidIndexMsg);
            return -1;
        }
        if (idx <= 0) {
            printMsg(negativeIndexMsg);
            return -1;
        }
        if (idx > size) {
            printMsg(String.format(outOfBoundsMsg, idx, size, size == 1 ? "" : "s"));
            return -1;
        }
        return --idx;
    }
}
