import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final ArrayList<Task> items = new ArrayList<>();
    private static final String bar = "――――――――――――――――――――――――――――――――――――――――――";

    public static void greetUser() {
        String greeting = "Hello! I'm CringeBot\n"
                + "What can I do for you?";
        printWrapWord(greeting);
    }

    public static void bidFarewell() {
        printWrapWord("Bye. Hope to see you again soon!");
    }

    public static void printWrapWord(String word) {
        System.out.println(String.format("%s\n%s\n%s", Duke.bar, word, Duke.bar));
    }

    public static void addItem(String item) {
        String taskType = getFirstWord(item);
        String taskName = getRestOfSentence(item.split(" /")[0]);
        Task newTask = null;

        switch(taskType) {
            case "deadline":
                String date = item
                        .split(" /")[1]
                        .replaceAll("by", "by:");
                taskName = String.format("%s (%s)", taskName, date);
                newTask = new Deadline(taskName);
                break;
            case "event":
                String fromDatetime = item
                        .split(" /")[1]
                        .replaceAll("from", "from:");
                String toDatetime = item
                        .split(" /")[2]
                        .replaceAll("to", "to:");
                taskName = String.format("%s (%s %s)", taskName, fromDatetime, toDatetime);
                newTask = new Event(taskName);
                break;
            case "todo":
                newTask = new Todo(taskName);
                break;
            default:
                printWrapWord("Invalid task type detected, please give another command");
                break;
        }

        if (newTask != null) {
            Duke.items.add(newTask);
        }

        String sayWord = "Got it. I've added this task:\n"
                + newTask.toString()
                + "\nNow you have "
                + Duke.items.size()
                + " in the list.";
        printWrapWord(sayWord);
    }

    public static String getFirstWord(String sentence) {
        String[] parts = sentence.split(" ");
        return parts[0];
    }

    public static String getRestOfSentence(String sentence) {
        String[] parts = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        if (parts.length > 1) {
            for (int i = 1; i < parts.length - 1; i++) {
                result.append(parts[i]).append(" ");
            }
            result.append(parts[parts.length - 1]);
        }
        return result.toString();
    }

    public static void printItems() {
        System.out.println(Duke.bar);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Duke.items.size(); i++) {
            System.out.printf("%d.%s%n", i + 1, Duke.items.get(i));
        }
        System.out.println(Duke.bar);
    }

    public static int getIndex(String command) {
        String[] parts = command.split(" ");

        if (parts.length >= 2) {
            String secondPart = parts[1];
            return Integer.parseInt(secondPart);
        }
        return -1;
    }

    public static void printMarkedOrUnmarked(int index, String sentence) {
        if (index < Duke.items.size()) {
            String markedTask = String.format("%s\n%s", sentence, Duke.items.get(index));
            printWrapWord(markedTask);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanObj = new Scanner(System.in);
        greetUser();
        while(true) {
            String nextLine = scanObj.nextLine();
            String firstWord = getFirstWord(nextLine);
            boolean breakLoop = false;
            switch(firstWord) {
                case "bye":
                    breakLoop = true;
                    break;
                case "list":
                    printItems();
                    break;
                case "unmark":
                    int index_unmarked = getIndex(nextLine) - 1;
                    if (index_unmarked <= Duke.items.size()) {
                        Duke.items.get(index_unmarked).unMarkTask();
                        printMarkedOrUnmarked(index_unmarked, "OK, I've marked this task as not done yet:");
                    }
                    break;
                case "mark":
                    int index_marked = getIndex(nextLine) - 1;
                    if (index_marked <= Duke.items.size()) {
                        Duke.items.get(index_marked).markTask();
                        printMarkedOrUnmarked(index_marked, "Nice! I've marked this task as done:");
                    }
                    break;
                default:
                    addItem(nextLine);
            }
            if (breakLoop) {
                break;
            }
        }
        bidFarewell();
    }
}
