import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final Task[] tasks = new Task[100];
    private static int i = 0;
    public static void printHorizontalLine() {
        System.out.println("\t------------------------------------------------------------------------------------");
    }
    public static void printBotMessage(String msg) {
        printHorizontalLine();
        System.out.println("\t" + msg);
        printHorizontalLine();
    }
    public static void printGreeting() {
        String logo = "\n" +
                "\t_________ .__            __    __                \n" +
                "\t\\_   ___ \\|  |__ _____ _/  |__/  |_  ___________ \n" +
                "\t/    \\  \\/|  |  \\\\__  \\\\   __\\   __\\/ __ \\_  __ \\\n" +
                "\t\\     \\___|   Y  \\/ __ \\|  |  |  | \\  ___/|  | \\/\n" +
                "\t \\______  /___|  (____  /__|  |__|  \\___  >__|   \n" +
                "\t        \\/     \\/     \\/                \\/       \n";
        printBotMessage("Hello! I'm" + logo + "\n\t \uD83E\uDD9C What can I do for you?");
    }
    public static void printFarewell() {
        printBotMessage("Bye. Hope to see you again soon! \uD83D\uDD19 \uD83D\uDD1B \uD83D\uDD1D");
    }
    public static void printTasks() {
        printHorizontalLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < 100; i++) {
            if (tasks[i] != null) {
                System.out.println("\t" + (i + 1) + ". " + tasks[i]);
            } else {
                break;
            }
        }
        printHorizontalLine();
    }
    public static void unmarkTask(String taskNumber) {
        int index = Integer.parseInt(taskNumber);
        tasks[index - 1].markAsUndone();
        printBotMessage("OK, I've marked this task as not done yet:\n\t\t" + tasks[index - 1]);
    }
    public static void markTask(String taskNumber) {
        int index = Integer.parseInt(taskNumber);
        tasks[index - 1].markAsDone();
        printBotMessage("Nice! I've marked this task as done:\n\t\t" + tasks[index - 1]);
    }
    public static void addTodo(List<String> inputList) {
        String description = String.join(" ", inputList.subList(1, inputList.size()));
        Todo todo = new Todo(description);
        tasks[i] = todo;
        i++;
        printBotMessage("Got it. I've added this task:\n\t\t" + todo +
                "\n\tNow you have " + i + " tasks in the list.");
    }
    public static void addDeadline(List<String> inputList) {
        String input = String.join(" ", inputList.subList(1, inputList.size()));
        String[] split = input.split(" /by ");
        Deadline deadline = new Deadline(split[0], split[1]);
        tasks[i] = deadline;
        i++;
        printBotMessage("Got it. I've added this task:\n\t\t" + deadline +
                "\n\tNow you have " + i + " tasks in the list.");
    }
    public static void addEvent(List<String> inputList) {
        String input = String.join(" ", inputList.subList(1, inputList.size()));
        String[] split = input.split(" /from ");
        String[] split2 = split[1].split(" /to ");
        Event event = new Event(split[0], split2[0], split2[1]);
        tasks[i] = event;
        i++;
        printBotMessage("Got it. I've added this task:\n\t\t" + event +
                "\n\tNow you have " + i + " tasks in the list.");
    }
    public static void main(String[] args) {
        printGreeting();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            List<String> inputList = Arrays.asList(input.split(" "));
            String keyword = inputList.get(0);

            switch (keyword) {
                case "bye":
                    printFarewell();
                    return;
                case "list":
                    printTasks();
                    break;
                case "unmark":
                    unmarkTask(String.join(" ", inputList.subList(1, inputList.size())));
                    break;
                case "mark":
                    markTask(String.join(" ", inputList.subList(1, inputList.size())));
                    break;
                case "todo":
                    addTodo(inputList);
                    break;
                case "deadline":
                    addDeadline(inputList);
                    break;
                case "event":
                    addEvent(inputList);
                    break;
                default:
                    printBotMessage("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
            }
        }

    }
}
