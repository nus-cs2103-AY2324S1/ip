import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static String name = "Dook";
    public static ArrayList<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        greetUser();
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            input = sc.nextLine();
            String[] tmp = input.split(" ", 2);
            String command = tmp[0];
            String body = tmp.length == 1 ? " " : tmp[1];

            switch (command) {
                case ("bye"):
                    bidFarewell();
                    return;
                case ("list"):
                    displayList();
                    break;
                case ("mark"):
                    markTask(body, true);
                    break;
                case ("unmark"):
                    markTask(body, false);
                    break;
                default:
                    printMessage("added: " + input);
                    addToTaskList(input);
                    break;
            }

        }
    }

    private static void addToTaskList(String str) {
        taskList.add(new Task(str));
    }

    private static void displayList() {
        String result = "";
        for (int i = 0; i < taskList.size(); i++) {
            result += String.format("%d. %s\n", i + 1, taskList.get(i));
        }
        printMessage(result);
    }

    private static void markTask(String body, boolean value) {
        int index;
        try {
            index = Integer.parseInt(body.split(" ", 2)[0]);
        } catch (NumberFormatException e) {
            printMessage(String.format("Usage: %s [task number]", value ? "mark" : "unmark"));
            return;
        }

        if (index <= 0 || index > taskList.size()) {
            printMessage("That task does not exist on the list.");
            return;
        }

        Task curr = taskList.get(index - 1);
        if (value) {
            curr.markAsDone();
        } else {
            curr.unmarkAsDone();
        }
        String message = String.format("I have marked this task as %s:\n   %s",
                value ? "done" : "not done yet", curr);
        printMessage(message);
    }


    private static void greetUser() {
        printMessage(String.format("%s here.\nWhat can I do for you?", name));
    }

    public static void printMessage(String msg) {
        printDivider();
        System.out.println(msg);
        printDivider();
    }
    public static void printDivider() {
        System.out.println("_______________________________________");
    }
    private static void bidFarewell() {
        printMessage("Goodbye.");
    }
}
