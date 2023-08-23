import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private static final String line = "____________________________________________________________\n";
    private static final String greetingMessage = "Hello! I'm EnPassant\n"
                                                + "What can I do for you?\n";
    private static final String exitMessage = "Bye! Hope to see you again soon!\n";
    private static final String invalidIndexMessage = "Great heavens! You have entered an invalid index!\n";
    private static final String invalidInputMessage = "Great heavens! You have entered an invalid input! Valid commands:\n"
            + "bye\n"
            + "list\n"
            + "mark|unmark <index>\n"
            + "todo <name>\n"
            + "deadline <name> /by <date>\n"
            + "event <name> /from <start> /to <end>\n";
    private static final String markDoneMessage = "Great success! I have marked this task as done:\n";
    private static final String markUndoneMessage = "Very nice! I have marked this task as not done yet:\n";
    private static final String listMessage = "Here are the tasks in your list:\n";
    private static final String regexPattern = "(bye|list|unmark|mark|todo|deadline|event)\\s*" // match command
            + "([^/]*[^/\\s])?\\s*"                     // match chars that are not / after command, trimming whitespace
            + "(?:(/by|/from)\\s+([^/]*[^/\\s]))?\\s*"  // match /by or /from command and argument, trimming whitespace
            + "(?:(/to)\\s+([^/]*[^/\\s]))?\\s*";       // match /to command and argument, trimming whitespace
    private static final String newTaskAddedMessage = "New task just dropped!\n";
    private static final String totalTaskCountMessage = "You now have %d tasks in the list!\n";

    private static void printWithLines(String message) {
        System.out.print(line + message + line);
    }

    private static void printList(ArrayList<Task> list) {
        System.out.print(line + listMessage);
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.print(line);
    }

    private static void printTaskAdded(Task task, int totalTasks) {
        System.out.print(line + newTaskAddedMessage);
        System.out.print("  " + task + "\n");
        System.out.printf(totalTaskCountMessage, totalTasks);
        System.out.print(line);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pattern pattern = Pattern.compile(regexPattern);
        ArrayList<Task> tasks = new ArrayList<>();
        printWithLines(greetingMessage);

        while (true) {
            String str = sc.nextLine();
            Matcher matcher = pattern.matcher(str);
            int index;
            Task newTask;

            if (!matcher.matches()) {
                // no match means input is not valid
                printWithLines(invalidInputMessage);
                continue;
            }

            if (matcher.group(1).equals("bye")) {
                printWithLines(exitMessage);
                break;
            }

            switch (matcher.group(1)) {
                case "list":
                    printList(tasks);
                    break;
                case "mark":
                    if (matcher.group(2) == null) {
                        printWithLines(invalidInputMessage);
                        break;
                    }

                    index = Integer.parseInt(matcher.group(2));

                    if (index < 1 || index > tasks.size()) {
                        printWithLines(invalidIndexMessage);
                        break;
                    }

                    tasks.get(index - 1).markAsDone();
                    printWithLines(markDoneMessage + "  " + tasks.get(index - 1) + '\n');
                    break;
                case "unmark":
                    if (matcher.group(2) == null) {
                        printWithLines(invalidInputMessage);
                        break;
                    }

                    index = Integer.parseInt(matcher.group(2));

                    if (index < 1 || index > tasks.size()) {
                        printWithLines(invalidIndexMessage);
                        break;
                    }

                    tasks.get(index - 1).markAsUndone();
                    printWithLines(markUndoneMessage + "  " + tasks.get(index - 1) + '\n');
                    break;
                case "todo":
                    if (matcher.group(2) == null) {
                        printWithLines(invalidInputMessage);
                        break;
                    }

                    newTask = new Todo(matcher.group(2));
                    tasks.add(newTask);
                    printTaskAdded(newTask, tasks.size());
                    break;
                case "deadline":
                    if (matcher.group(2) == null
                            || matcher.group(3) == null
                            || !matcher.group(3).equals("/by")
                            || matcher.group(4) == null) {
                        printWithLines(invalidInputMessage);
                        break;
                    }

                    newTask = new Deadline(matcher.group(2), matcher.group(4));
                    tasks.add(newTask);
                    printTaskAdded(newTask, tasks.size());
                    break;
                case "event":
                    if (matcher.group(2) == null
                            || matcher.group(3) == null
                            || !matcher.group(3).equals("/from")
                            || matcher.group(4) == null
                            || matcher.group(5) == null
                            || !matcher.group(5).equals("/to")
                            || matcher.group(6) == null) {
                        printWithLines(invalidInputMessage);
                        break;
                    }

                    newTask = new Event(matcher.group(2), matcher.group(4), matcher.group(6));
                    tasks.add(newTask);
                    printTaskAdded(newTask, tasks.size());
                    break;
                default:
                    printWithLines(invalidInputMessage);
                    break;
            }
        }
    }
}
