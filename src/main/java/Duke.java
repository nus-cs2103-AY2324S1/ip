import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Duke {
    private static final String horizontalLine = "_".repeat(60);
    private static final String msgIndent = " ";
    private static final String responseIndent = " ".repeat(4);
    private static final String name = "chatBot";
    private static final String helloMsg = String.format("Hello! I'm %s", name);
    private static final String requestMsg = "What can I do for you?";
    private static final String taskCountMsg = "Now you have %d task%s in the list.";
    private static final String addedMsg = "Got it. I've added this task:\n  %s\n%s";
    private static final String removedMsg = "Noted. I've removed this task:\n  %s\n%s";
    private static final String listMsg = "Here are the tasks in your list:\n%s";
    private static final String markMsg = "Nice! I've marked this task as done:\n  %s";
    private static final String unmarkMsg = "OK, I've marked this task as not done yet:\n  %s";
    private static final String invalidCmdMsg = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String emptyFieldMsg = "☹ OOPS!!! The %s of a %s cannot be empty.";
    private static final String additionalFieldMsg = "☹ OOPS!!! %s command should not have a %s field.";
    private static final String invalidIndexMsg = "☹ OOPS!!! Please enter a valid positive integer for the index.";
    private static final String outOfBoundsMsg = "☹ OOPS!!! %d is out of range. %s";
    private static final String repeatedFieldMsg = "☹ OOPS!!! %s field is repeated.";
    private static final String goodbyeMsg = "Bye. Hope to see you again soon!";
    private static final Scanner userInput = new Scanner(System.in);
    private static final Map<String, CommandType> commands = Map.ofEntries(
            new SimpleEntry<String, CommandType>("list", CommandType.LIST),
            new SimpleEntry<String, CommandType>("mark", CommandType.MARK),
            new SimpleEntry<String, CommandType>("unmark", CommandType.UNMARK),
            new SimpleEntry<String, CommandType>("todo", CommandType.TODO),
            new SimpleEntry<String, CommandType>("deadline", CommandType.DEADLINE),
            new SimpleEntry<String, CommandType>("event", CommandType.EVENT),
            new SimpleEntry<String, CommandType>("remove", CommandType.REMOVE),
            new SimpleEntry<String, CommandType>("bye", CommandType.BYE)
        );
    private static final Map<CommandType, List<String>> arguments = Map.ofEntries(
            new SimpleEntry<CommandType, List<String>>(CommandType.LIST, List.of()),
            new SimpleEntry<CommandType, List<String>>(CommandType.MARK, List.of("description")),
            new SimpleEntry<CommandType, List<String>>(CommandType.UNMARK, List.of("description")),
            new SimpleEntry<CommandType, List<String>>(CommandType.TODO, List.of("description")),
            new SimpleEntry<CommandType, List<String>>(CommandType.DEADLINE, List.of("description", "by")),
            new SimpleEntry<CommandType, List<String>>(CommandType.EVENT, List.of("description", "from", "to")),
            new SimpleEntry<CommandType, List<String>>(CommandType.REMOVE, List.of("description")),
            new SimpleEntry<CommandType, List<String>>(CommandType.BYE, List.of())
        );
    private static final Map<CommandType, Function<Map<String, String>, Task>> taskConstructors = Map.ofEntries(
            new SimpleEntry<CommandType, Function<Map<String, String>, Task>>(CommandType.TODO, cmd -> new ToDo(cmd.get("description"))),
            new SimpleEntry<CommandType, Function<Map<String, String>, Task>>(CommandType.DEADLINE, cmd -> new Deadline(cmd.get("description"), cmd.get("by"))),
            new SimpleEntry<CommandType, Function<Map<String, String>, Task>>(CommandType.EVENT, cmd -> new Event(cmd.get("description"), cmd.get("from"), cmd.get("to")))
        );

    private enum CommandType {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, REMOVE, BYE
    }

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<Task>();
        printMsg(helloMsg + "\n" + requestMsg);
        while (true) {
            Map<String, String> cmd = parseInput(userInput.nextLine().replaceAll("\n", "").trim().split(" "));
            if (cmd == null) {
                continue;
            }
            CommandType type = commands.get(cmd.get("command"));
            int idx;
            switch (type) {
                case BYE:
                    break;
                case LIST:
                    printMsg(String.format(listMsg, stringifyList(tasks)));
                    continue;
                case MARK:
                    idx = getIndex(cmd.get("description"), tasks.size());
                    if (idx >= 0) {
                        tasks.get(idx).mark();
                        printMsg(String.format(markMsg, tasks.get(idx).toString()));
                    }
                    continue;
                case UNMARK:
                    idx = getIndex(cmd.get("description"), tasks.size());
                    if (idx >= 0) {
                        tasks.get(idx).unmark();
                        printMsg(String.format(unmarkMsg, tasks.get(idx).toString()));
                    }
                    continue;
                case REMOVE:
                    idx = getIndex(cmd.get("description"), tasks.size());
                    if (idx >= 0) {
                        printMsg(String.format(removedMsg, tasks.remove(idx).toString(), getTaskCount(tasks.size())));
                    }
                    continue;
                case TODO: case DEADLINE: case EVENT:
                    Task newTask = taskConstructors.get(type).apply(cmd);
                    if (newTask != null) {
                        tasks.add(newTask);
                        printLastAdd(tasks);
                    }
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
        printMsg(String.format(addedMsg, arr.get(arr.size() - 1).toString(), getTaskCount(arr.size())));
    }

    private static String getTaskCount(int size) {
        return String.format(taskCountMsg, size, size == 1 ? "" : "s");
    }
    
    private static Map<String, String> parseInput(String[] arr) {
        if (arr.length == 0) {
            return null;
        }
        if (!commands.containsKey(arr[0])) {
            printMsg(invalidCmdMsg);
            return null;
        }
        Map<String, List<String>> disjointMap = new HashMap<String, List<String>>();
        List<String> curr = new ArrayList<String>();
        disjointMap.put("description", curr);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].length() == 0) {
                continue;
            }
            if (arr[i].charAt(0) == '/') {
                String field = arr[i].substring(1);
                if (disjointMap.containsKey(field)) {
                    printMsg(String.format(repeatedFieldMsg, field));
                    return null;
                }
                curr = new ArrayList<String>();
                disjointMap.put(field, curr);
            } else {
                curr.add(arr[i]);
            }
        }
        if (disjointMap.get("description").size() == 0) {
            disjointMap.remove("description");
        }
        Map<String, String> jointMap = new HashMap<String, String>();
        List<String> emptyFields = new ArrayList<String>();
        for (String key : arguments.get(commands.get(arr[0]))) {
            if (!disjointMap.containsKey(key) || disjointMap.get(key).size() == 0) {
                emptyFields.add(String.format(emptyFieldMsg, key, arr[0]));
            } else {
                jointMap.put(key, String.join(" ", disjointMap.get(key)));
            }
            disjointMap.remove(key);
        }
        if (emptyFields.size() > 0) {
            printMsg(String.join("\n", emptyFields));
            return null;
        }
        if (disjointMap.size() > 0) {
            List<String> additionalFields = new ArrayList<String>(disjointMap.keySet());
            Collections.sort(additionalFields);
            for (int i = 0; i < additionalFields.size(); i++) {
                additionalFields.set(i, String.format(additionalFieldMsg, arr[0], additionalFields.get(i)));
            }
            printMsg(String.join("\n", additionalFields));
            return null;
        }
        jointMap.put("command", arr[0]);
        return jointMap;
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
            printMsg(invalidIndexMsg);
            return -1;
        }
        if (idx > size) {
            printMsg(String.format(outOfBoundsMsg, idx, getTaskCount(size)));
            return -1;
        }
        return --idx;
    }
}
