import java.util.Scanner;
public class Duke {
    private static final String chatBotName = "CHAD CCP";
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
            } else {
                dukeInstance.echoUser(command);
            }
        }
    }

    public void greetUser() {
        System.out.println("________________________________");
        System.out.println("Hello! I'm " + Duke.chatBotName);
        System.out.println("What can I do for you?");
        System.out.println("________________________________");
    }

    public void echoUser(String command) {
        System.out.println("________________________________");
        System.out.println(command);
        System.out.println("________________________________");
    }

    public void goodBye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
    }
}
