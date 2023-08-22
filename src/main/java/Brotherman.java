import java.util.Scanner;

public class Brotherman {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println(
                "___________________________________________________________\n"
                + "Hello! I'm Brotherman\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n");
        String userCommand = input.nextLine();

        while (!userCommand.equals("bye")) {
            System.out.println(userCommand);
            userCommand = input.nextLine();
        }

        System.out.println(
                "___________________________________________________________\n"
                        + "Bye, see you again Brotherman!\n"
                        + "___________________________________________________________\n"

        );

    }
}
