import java.util.Scanner;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
public class MattBot {
    private static final String NAME = "MattBot";
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        List<String> taskList = new ArrayList<String>();
        String userInput;
        while (true) {
            userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye, Hope to see you soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(String.valueOf(i+1) + ". " + taskList.get(i));
                }
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                taskList.add(userInput);
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }
    }
}
