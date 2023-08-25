import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String chatbotName = "Bleep";
        String header = "____________________________________________________________\n" +
                "Hello! I'm " + chatbotName +
                " \nWhat can I do for you?";
        ArrayList<String> userEntries = new ArrayList<>();
        System.out.println(header);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("____________________________________________________________\n");
            String userInput = scanner.nextLine();
            System.out.println("____________________________________________________________\n");
            if (userInput.equals("bye")){
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("Here are your entries:");
                for (int i = 0; i < userEntries.size(); i++) {
                    System.out.println("\t" + (i + 1) + ". " + userEntries.get(i));
                }
            } else {
                userEntries.add(userInput);
                System.out.println("\t added: " + userInput);
                //System.out.println();
            }
        }
    }
}
