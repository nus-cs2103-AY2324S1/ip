import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ___  __    ________  ________  ________   ________  ________      \r\n" + //
                "|\\  \\|\\  \\ |\\   __  \\|\\   __  \\|\\   ___  \\|\\   __  \\|\\   ____\\     \r\n" + //
                "\\ \\  \\/  /|\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\\\ \\  \\ \\  \\|\\  \\ \\  \\___|_    \r\n" + //
                " \\ \\   ___  \\ \\   _  _\\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\\\\\  \\ \\_____  \\   \r\n" + //
                "  \\ \\  \\\\ \\  \\ \\  \\\\  \\\\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\\\\\  \\|____|\\  \\  \r\n" + //
                "   \\ \\__\\\\ \\__\\ \\__\\\\ _\\\\ \\_______\\ \\__\\\\ \\__\\ \\_______\\____\\_\\  \\ \r\n" + //
                "    \\|__| \\|__|\\|__|\\|__|\\|_______|\\|__| \\|__|\\|_______|\\_________\\\r\n" + //
                "                                                       \\|_________|";

        String divider = "\n____________________________________________________________\n";

        Scanner sc = new Scanner(System.in);

        String command = "";
        ArrayList<String> list = new ArrayList<String>();

        System.out.println("Greetings, puny mortal. This is \n" + logo
                + "\nThe Lord of Time. \nWhat inconsequential errand do you seek to accomplish with my immense powers?");
        System.out.println(divider);

        while (true) {
            command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                System.out.println(divider);
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
                System.out.println(divider);
            } else {
                System.out.println(divider);
                System.out.println("added: " + command);
                list.add(command);
                System.out.println(divider);
            }
        }

        System.out.println(divider);
        System.out.println("Is that all? I have better things to do than to listen to lesser beings. Farewell.");
        System.out.println(divider);
        sc.close();
    }
}
