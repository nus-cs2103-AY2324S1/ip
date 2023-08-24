import java.util.Scanner;
import java.util.ArrayList;
public class DevyBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> stringList = new ArrayList<>();

        String welcome = "Hello! I'm DevyBot\n"
                + "What can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!\n";
        System.out.println(welcome);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(exit);
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0 ; i < stringList.size(); i++) {
                    System.out.println( (i+1) + ". " + stringList.get(i) );
                }
            } else {
                    System.out.println("____________________________________________________________");
                    stringList.add(userInput);
                    System.out.println(" added " + userInput);
                    System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
}
