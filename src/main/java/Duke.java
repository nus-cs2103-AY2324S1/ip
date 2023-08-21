import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final ArrayList<Task> items = new ArrayList<>();
    private static final String bar = "――――――――――――――――――――――――――――――――――――――――――";

    public static void greetUser() {
        String greeting = "Hello! I'm CringeBot\n"
                + "What can I do for you?";
        String wrappedGreeting = wrapWord(greeting);
        System.out.println(wrappedGreeting);
    }

    public static void bidFarewell() {
        String farewell = wrapWord("Bye. Hope to see you again soon!");
        System.out.println(farewell);
    }

    public static void sayWord(String word) {
        String wrappedWord = wrapWord(word);
        System.out.println(wrappedWord);
    }

    public static String wrapWord(String word) {
        return String.format("%s\n%s\n%s", Duke.bar, word, Duke.bar);
    }

    public static void addItem(String item) {
        Task newTask = new Task(item);
        Duke.items.add(newTask);
        sayWord("added: " + item);
    }

    public static void printItems() {
        System.out.println(Duke.bar);
        for (int i = 0; i < Duke.items.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, Duke.items.get(i));
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
            System.out.println(wrapWord(String.format("%s\n%s", sentence, Duke.items.get(index))));
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
            if (nextLine.equals("bye")) {
                break;
            } else if (nextLine.equals("list")) {
                printItems();
            } else if (nextLine.contains("unmark")) {
                int index = getIndex(nextLine) - 1;
                if (index <= Duke.items.size()) {
                    Duke.items.get(index).unMarkTask();
                    printMarkedOrUnmarked(index, "OK, I've marked this task as not done yet:");
                }
            } else if (nextLine.contains("mark")) {
                int index = getIndex(nextLine) - 1;
                if (index <= Duke.items.size()) {
                    Duke.items.get(index).markTask();
                    printMarkedOrUnmarked(index, "Nice! I've marked this task as done:");
                }
            } else {
                addItem(nextLine);
            }
        }
        bidFarewell();
    }
}
