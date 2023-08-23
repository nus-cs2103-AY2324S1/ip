import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private static final String LINEBREAK = "    ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿";
    private static final Pattern markPattern = Pattern.compile("^mark (?<taskNumber>[0-9]+)$", Pattern.CASE_INSENSITIVE);
    public static final Pattern unmarkPattern = Pattern.compile("^unmark (?<taskNumber>[0-9]+)$", Pattern.CASE_INSENSITIVE);
    private static ArrayList<Task> allTasks = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greet();

        String input = sc.nextLine();
        Matcher matcher;
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                displayAllTasks();
            } else if ((matcher = markPattern.matcher(input)).find()) {
                markTask(matcher.group("taskNumber"));
            } else if ((matcher = unmarkPattern.matcher(input)).find()) {
                unmarkTask(matcher.group("taskNumber"));
            } else {
                addToList(input);
            }
            input = sc.nextLine();
        }

        bye();
    }

    private static void markTask(String taskNumberStr) {
        System.out.println(taskNumberStr);
        int taskNumber = Integer.parseInt(taskNumberStr);
        if (taskNumber > allTasks.size() || taskNumber <= 0) {
            return;
        }

        Task task = allTasks.get(taskNumber - 1);
        task.mark();
        String msg = "Nice! I've marked this task as done: \n" + task.toString();
        displayMessage(msg);
    }

    private static void unmarkTask(String taskNumberStr) {
        int taskNumber = Integer.parseInt(taskNumberStr);
        if (taskNumber > allTasks.size() || taskNumber <= 0) {
            return;
        }

        Task task = allTasks.get(taskNumber - 1);
        task.unmark();
        String msg = "OK, I've marked this task as not done yet: \n" + task.toString();
        displayMessage(msg);
    }

    private static void addToList(String input) {
        allTasks.add(new Task(input));
        String msg = "added: " + input;
        displayMessage(msg);
    }

    private static void displayAllTasks() {
        String list = "";
        for (int i = 1; i <= allTasks.size(); i++) {
            list += i + ". " + allTasks.get(i - 1).toString() + "\n";
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
