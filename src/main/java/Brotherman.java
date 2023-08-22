import java.util.Scanner;
import java.util.ArrayList;


public class Brotherman {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        ArrayList<String> userList = new ArrayList<String>();


        System.out.println(
                "___________________________________________________________\n"
                + "Hello! I'm Brotherman\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n");
        String userCommand = input.nextLine();

        while (!userCommand.equals("bye")) {
            if (userCommand.equals("list")) {
                int start = 1;
                for (String listItems : userList) {
                    System.out.println(start + ". " + listItems);
                    start++;
                }
            } else {
                userList.add(userCommand);
            }
            userCommand = input.nextLine();
        }

        System.out.println(
                "___________________________________________________________\n"
                + "Bye, see you again Brotherman!\n"
                + "___________________________________________________________\n"

        );

    }
}
