import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        List<String> inputList = new ArrayList<>();

        String initMsg = "____________________________________________________________\n"
                + " Hello! I'm IRIS\n"
                + " What can I do for you?\n"
                + "____________________________________________________________";
        System.out.println(initMsg);

        input = scanner.nextLine();
        int count = 1;

        while (!input.equals("bye")) {
            System.out.println("____________________________________________________________");
            if (input.equals("list")) {
                for (String userInput : inputList) {
                    System.out.println(userInput);
                }
            } else {
                inputList.add(count++ + " " + input);
                System.out.println("added: " + input);
            }
            System.out.println("____________________________________________________________");
            input = scanner.nextLine();
        }

        scanner.close();
        String endMsg = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(endMsg);
    }
}