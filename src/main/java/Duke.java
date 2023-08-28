import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final ArrayList<Task> items = new ArrayList<>();
    private static final String bar = "____________________________________________________________";

    enum modifyStatus {
        MARK,
        UNMARK
    }

    enum taskType {
        EVENT,
        DEADLINE,
        TODO
    }

    public static void greetUser() {
        String greeting = "Hello! I'm CringeBot\n"
                + "What can I do for you?";
        printWrapped(greeting);
    }

    public static void bidFarewell() {
        printWrapped("Bye. Hope to see you again soon!");
    }

    public static void printWrapped(String input) {
        System.out.printf("%s\n%s\n%s%n", Duke.bar, input, Duke.bar);
    }

    public static String getFirstWord(String input) {
        String[] parts = input.split(" ");
        return parts[0];
    }

    public static String getRestOfSentence(String input) {
        String[] parts = input.split(" ");
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
        String sayWord = "Here are the tasks in your list:";
        for (int i = 0; i < Duke.items.size(); i++) {
            sayWord += String.format("\n%d.%s", i + 1, Duke.items.get(i));
        }
        printWrapped(sayWord);
    }

    public static int getIndex(String input) {
        String[] parts = input.split(" ");

        if (parts.length >= 2) {
            String secondPart = parts[1];
            return Integer.parseInt(secondPart);
        }
        return -1;
    }

    public static void printMarkedOrUnmarked(int index, String input) {
        if (index < Duke.items.size()) {
            String markedTask = String.format("%s\n%s", input, Duke.items.get(index));
            printWrapped(markedTask);
        }
    }

    public static void checkOutOfBounds(int index) throws DukeException {
        if (index > Duke.items.size() - 1) {
            throw new DukeException("\u2639 OOPS!!! I'm sorry, but the index you have inputted is out of bounds :-(");
        }
    }

    public static void checkEmpty(String input, String taskName) throws DukeException {
        if (input.equals("")) {
            throw new DukeException(String.format("\u2639 OOPS!!! The description of a %s cannot be empty.", taskName));
        }
    }

    public static void addItem(taskType task, String input) throws DukeException {
        String[] splitSentence = input.split(" /");
        String taskName = getRestOfSentence(splitSentence[0]).strip();
        Task newTask;

        switch(task) {
            case DEADLINE:
                checkEmpty(taskName, "deadline");
                if (splitSentence.length < 2 || !splitSentence[1].contains("by")) {
                    throw new DukeException("\u2639 OOPS!!! Please indicate a deadline with the /by keyword");
                }
                String date = splitSentence[1].replaceAll("by", "by:");
                taskName = String.format("%s (%s)", taskName, date);
                newTask = new Deadline(taskName);
                break;
            case EVENT:
                checkEmpty(taskName, "event");
                if (splitSentence.length < 3 || (!splitSentence[1].contains("from") && !splitSentence[2].contains("to"))) {
                    throw new DukeException("\u2639 OOPS!!! Please indicate a duration for the event with the /from and /to keywords");
                }
                String fromDatetime = splitSentence[1].replaceAll("from", "from:");
                String toDatetime = splitSentence[2].replaceAll("to", "to:");
                taskName = String.format("%s (%s %s)", taskName, fromDatetime, toDatetime);
                newTask = new Event(taskName);
                break;
            case TODO:
                if (taskName.equals("")) {
                    throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
                }
                newTask = new Todo(taskName);
                break;
            default:
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        Duke.items.add(newTask);

        String sayWord = "Got it. I've added this task:\n"
                + newTask
                + "\nNow you have "
                + Duke.items.size()
                + " tasks in the list.";
        printWrapped(sayWord);
    }

    public static void modifyStatus(modifyStatus status, String input) throws DukeException {
        int index = getIndex(input) - 1;
        checkOutOfBounds(index);
        switch(status) {
            case MARK:
                Duke.items.get(index).markTask();
                printMarkedOrUnmarked(index, "Nice! I've marked this task as done:");
                break;
            case UNMARK:
                Duke.items.get(index).unMarkTask();
                printMarkedOrUnmarked(index, "OK, I've marked this task as not done yet:");
                break;
            default:
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but an error occured when modifying your task :-(");
        }
    }

    public static void deleteItem(String input) throws DukeException {
        int index = getIndex(input) - 1;
        checkOutOfBounds(index);
        if (index <= Duke.items.size()) {
            Task deletedTask = Duke.items.remove(index);
            String sayWord = "Noted. I've removed this task:\n"
                    + deletedTask
                    + "\nNow you have "
                    + Duke.items.size()
                    + " tasks in the list.";
            printWrapped((sayWord));
        }
    }

    public static void main(String[] args) {
        Scanner scanObj = new Scanner(System.in);
        greetUser();
        while(true) {
            String nextLine = scanObj.nextLine();
            String firstWord = getFirstWord(nextLine);
            try {
                switch(firstWord) {
                    case "bye":
                        bidFarewell();
                        return;
                    case "list":
                        printItems();
                        break;
                    case "unmark":
                        modifyStatus(modifyStatus.UNMARK, nextLine);
                        break;
                    case "mark":
                        modifyStatus(modifyStatus.MARK, nextLine);
                        break;
                    case "delete":
                        deleteItem(nextLine);
                        break;
                    case "event":
                        addItem(taskType.EVENT, nextLine);
                        break;
                    case "deadline":
                        addItem(taskType.DEADLINE, nextLine);
                        break;
                    case "todo":
                        addItem(taskType.TODO, nextLine);
                        break;
                    default:
                        throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                printWrapped(e.getMessage());
            }
        }
    }
}
