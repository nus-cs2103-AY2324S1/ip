import java.util.Scanner;
import java.util.ArrayList;
public class Remy {

    public static void main(String[] args) {
        String welcomeMessage = "____________________________________________________________\n" +
                " Hello! I'm Remy.\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String exitMessage = " Bye. Hope to see you again soon!\n" +
        "____________________________________________________________\n";

        ArrayList<String> taskList = new ArrayList(100);
        System.out.println(welcomeMessage);
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(exitMessage);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("__________");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + taskList.get(i));
                }
                System.out.println("__________");
            } else {
                taskList.add(input);
                System.out.println("__________");
                System.out.println(" added: " + input);
                System.out.println("__________");
            }


        }
    }
}
