import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<String> items = new ArrayList<>();
    private static String bar = "――――――――――――――――――――――――――――――――――――――――――";

    public static void greetUser() {
        String greeting = "Hello! I'm CringeBot\n"
                + "What can I do for you?";
        String wrappedGreeting = wrapWord(greeting);
        System.out.println(wrappedGreeting);
    }

    public static void bidFarewel() {
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
        Duke.items.add(item);
        sayWord("added:" + item);
    }

    public static void printItems() {
        System.out.println(Duke.bar);
        for (int i = 0; i < Duke.items.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, Duke.items.get(i)));
        }
        System.out.println(Duke.bar);
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
            } else {
                addItem(nextLine);
            }
        }
        bidFarewel();
    }
}
