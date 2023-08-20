import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    protected static ArrayList<Task> taskList = new ArrayList<Task>();
    private static final Pattern markPattern = Pattern.compile("mark\\s(\\d+)\\b$");
    private static final Pattern unmarkPattern = Pattern.compile("unmark\\s(\\d+)$");
    private static final Pattern indexPattern = Pattern.compile("(\\d+)$");
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        greet();
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                exit();
                return;
            } else if (input.equals("list")) {
                System.out.println(listString());
            } else if (markPattern.matcher(input).find()) {
                int idx = parseMark(input);
                if (idx != 0) {
                    taskList.get(idx - 1).markAsDone();
                }
            } else if (unmarkPattern.matcher(input).find()) {
                int idx = parseMark(input);
                if (idx != 0) {
                    taskList.get(idx - 1).markAsNotDone();
                }
            } else if (input.isEmpty()) {
                System.out.println("No Input");
            } else {
                taskList.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
    }

    private static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can i do for you?\n");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static int parseMark(String input) {
        Matcher m = indexPattern.matcher(input);
        int res = 0;
        if (m.find()) {
            String idx = m.group(1);
            res = Integer.parseInt(idx);
        }
        if (res > taskList.size()) {
            System.out.println("Task " + res + " does not exist");
            res = 0;
        }
        return res;
    }

    private static String listString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(i + 1);
            sb.append(".[");
            sb.append(taskList.get(i).getStatusIcon());
            sb.append("] ");
            sb.append(taskList.get(i).getDescription());
            sb.append("\n");
        }
        return sb.toString();
    }
}
