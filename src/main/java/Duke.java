import java.util.Scanner;

public class Duke {
    public static String[] textList = new String[100];
    public static int textListIndex = 0;

    public static void listText() {
        for (int i = 1; i <= textListIndex; i++) {
            System.out.println("    " + i + ". " + textList[i - 1]);
        }
    }
    public static void addTextToList(String input) {
        textList[textListIndex] = input;
        System.out.println("    added: " + input);
        textListIndex += 1;
        System.out.println("--------------------------------");
    }
    public static boolean continueOrNot(String input) {
        if (input.equals("bye")) {
            return false;
        }
        return true;
    }
    public static String input() {
        Scanner myInput = new Scanner(System.in);
        String reply = myInput.nextLine();
        System.out.println("--------------------------------");
        return reply;
    }
    public static void greeting() {
        System.out.println("Hello.. I'm ekuD..");
        System.out.println("I probably won't be much of a help.. But ask me something..");
        System.out.println("--------------------------------");
        String input = input();
        while (continueOrNot(input)) {
            if (input.equals("list")) {
                listText();
            } else {
                addTextToList(input);
            }
            input = input();
        }
        System.out.println("    bye...");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
    }
}
