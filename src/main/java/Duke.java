import java.util.Scanner;

public class Duke {
    public static void greetUser() {
        String greeting = "――――――――――――――――――――――――――――――――――――――――――\n"
                + "Hello! I'm CringeBot\n"
                + "What can I do for you?\n"
                + "――――――――――――――――――――――――――――――――――――――――――";
        System.out.println(greeting);
    }

    public static void bidFarewel() {
        String farewell = "――――――――――――――――――――――――――――――――――――――――――\n"
                + "Bye. Hope to see you again soon!\n"
                + "――――――――――――――――――――――――――――――――――――――――――";
        System.out.println(farewell);
    }

    public static void sayWord(String word) {
        String wrappedWord = "――――――――――――――――――――――――――――――――――――――――――\n"
                + word
                + "\n"
                + "――――――――――――――――――――――――――――――――――――――――――";
        System.out.println(wrappedWord);
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
            }
            sayWord(nextLine);
        }
        bidFarewel();
    }
}
