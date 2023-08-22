import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<String> userList = new ArrayList<>();

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello! I'm Meep.\nWhat can I do for you?");

        while (true) {
            String userCommand = scanner.nextLine();
            if (userCommand.equalsIgnoreCase("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            } if (userCommand.equalsIgnoreCase("list")) {
                for (int i = 0; i < userList.size(); i++) {
                    System.out.println((i + 1) + ". " + userList.get(i));
                }
            } else {
                userList.add(userCommand);
                System.out.println("added: " + userCommand);
            }
        }

    }
}
