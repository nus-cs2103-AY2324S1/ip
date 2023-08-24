package main;
import java.util.Scanner;
import mypackage.CustomList;

public class Duke {
    private static final String chatBotName = "CHAD CCP";
    private static final CustomList list = new CustomList();
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        Duke dukeInstance = new Duke();
        Scanner scanner = new Scanner(System.in);
        dukeInstance.greetUser();

        while(true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                dukeInstance.goodBye();
                break;
            } else if (command.equals("list")) {
                dukeInstance.printList(command);
            } else if (command.startsWith("mark")) {
                int index = Integer.valueOf(command.substring(5));
                list.markAsDone(index);
            } else if (command.startsWith("unmark")) {
                int index = Integer.valueOf(command.substring(7));
                list.markAsUndone(index);
            }
            else {
                dukeInstance.echoUserAndAddToList(command);
            }
        }

        scanner.close();
    }

    public void greetUser() {
        System.out.println("________________________________");
        System.out.println("Hello! I'm " + Duke.chatBotName);
        System.out.println("What can I do for you?");
        System.out.println("________________________________");
    }

    public void echoUserAndAddToList(String command) {
        System.out.println("________________________________");
        this.addToList(command);
        System.out.println("added: " + command);
        System.out.println("________________________________");
    }

    public void printList(String command) {
        list.printList();
    }

    public void addToList(String command) {
        list.addToList(command);
    }

    public void goodBye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
    }

}
