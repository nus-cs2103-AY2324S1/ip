import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ren {
    public static void main(String[] args) {
        String LS_COMMAND = "list";
        String EXIT_COMMAND = "bye";
        List<String> tasks = new ArrayList<String>();

        Scanner input = new Scanner(System.in);
        String welcomeMsg = "____________________________________________________________\n" +
                " Hello! I'm Ren\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String goodbyeMsg = "____________________________________________________________\n" +
                " Bye! Pleasure speaking with you :) \n" +
                "____________________________________________________________\n";
        System.out.println(welcomeMsg);
        String inputStr = input.nextLine();
        while (!inputStr.equals(EXIT_COMMAND)) {
            if (inputStr.equals(LS_COMMAND)) {
                System.out.println("____________________________________________________________\n");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d %s\n%n", i + 1, tasks.get(i));
                }
                System.out.println("____________________________________________________________\n");
            } else {
                tasks.add(inputStr);

                System.out.println("____________________________________________________________\n" +
                        String.format("Added %s\n", inputStr) +
                        "____________________________________________________________\n");
            }

            inputStr = input.nextLine();
        }

        System.out.println(goodbyeMsg);
    }
}
