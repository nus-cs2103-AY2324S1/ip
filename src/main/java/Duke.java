import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<String> messages = new ArrayList<>();

    public static void addToList(String message) {
        String addMessage = "\t____________________________________________________________\n"
                + "\t Added: " + message + "\n"
                + "\t____________________________________________________________\n";
        messages.add(message);
        System.out.println(addMessage);
    }

    public static void printList() {
        System.out.println("\t____________________________________________________________");
        for (int i = 0; i < messages.size(); i++) {
            System.out.println("\t " + (i + 1) + ". " + messages.get(i));
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) {
        /**
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */

        String intro_message = "\t____________________________________________________________\n"
                + "\t Hello! I'm Bob the Chatbot!\n"
                + "\t What can I do for you?\n"
                + "\t____________________________________________________________\n";

        String bye_message = "\t____________________________________________________________\n"
                + "\t Bye. Hope to see you again soon!\n"
                + "\t____________________________________________________________";

        System.out.println(intro_message);
        Scanner sc = new Scanner(System.in);
        String message = "";

        while (true) {
            message = sc.nextLine();
            if (message.equals("bye")) break;

            if (message.equals("list")) {
                printList();
            } else {
                addToList(message);
            }
        }

        System.out.println(bye_message);
    }
}
