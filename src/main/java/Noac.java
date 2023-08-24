
import java.util.Scanner;

public class Noac {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String logo =  " _   _  ___    _    ____\n" +
                "| \\ | |/ _ \\  / \\  / ___|\n" +
                "|  \\| | | | |/ _ \\| |\n" +
                "| |\\  | |_| / ___ \\ |___\n" +
                "|_| \\_|\\___/_/   \\_\\____|\n";
        System.out.println("Hello from\n" + logo);


        String welcomeMessage = "    ____________________________________________________________\n" +
                "     Hello! I'm NOAC\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";

        System.out.println(welcomeMessage);


        String userInput = scanner.nextLine().replaceAll("(.{60})", "$1\n");

        while (!userInput.equals("bye")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("    " + userInput);
            System.out.println("    ____________________________________________________________");

            userInput = scanner.nextLine().replaceAll("(.{60})", "$1\n    ");

        }

        String byeMessage = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________";

        System.out.println(byeMessage);
    }
}
