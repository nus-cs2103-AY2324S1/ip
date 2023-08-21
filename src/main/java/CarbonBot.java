import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarbonBot {
    private static String DIVIDER = "____________________________________________________________";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> commandsList = new ArrayList<>();

        System.out.println(DIVIDER);
        System.out.println("Hello! I'm CarbonBot");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);

        while(true) {
            String cmd = sc.nextLine();

            switch(cmd) {
                case "bye":
                    // Stops listening to user input if the command is "bye"
                    System.out.println(DIVIDER);
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(DIVIDER);
                    sc.close();
                    return;
                case "list":
                    // Lists all the commands saved in the arraylist
                    System.out.println(DIVIDER);
                    int idx = 1;
                    for(String c : commandsList) {
                        System.out.println(String.format("%d. %s", idx, c));
                        idx++;
                    }
                    System.out.println(DIVIDER);
                    break;
                default:
                    // Echos and saves the command given
                    System.out.println(DIVIDER);
                    commandsList.add(cmd);
                    System.out.println("added: " + cmd);
                    System.out.println(DIVIDER);
            }
        }
    }
}
