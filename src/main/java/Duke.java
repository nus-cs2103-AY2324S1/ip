import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<String> textList = new ArrayList<>();
        String greet = "____________________________________________________________\n"
                + "Hello! I'm Kevin\n"
                + "What can I do  for you?\n"
                + "____________________________________________________________\n";
        String bye = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";

        System.out.println(greet);

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (true) {
            if (userInput.equals("bye")) {
                System.out.println(bye);
                break;
            } else if (userInput.equals("list")) {
                int count = 1;
                System.out.println("____________________________________________________________");
                for (String str: textList) {
                    String string = String.format("%d. %s", count, str);
                    System.out.println(string);
                    count++;
                }
                System.out.println("____________________________________________________________");
            } else {
                textList.add(userInput);
                String message = "____________________________________________________________\n"
                        + "added: " + userInput + "\n"
                        + "____________________________________________________________\n";
                System.out.println(message);
            }
            userInput = scanner.nextLine();
        }
        scanner.close();
    }
}
